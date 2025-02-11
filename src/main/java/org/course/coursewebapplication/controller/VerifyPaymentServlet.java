package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.*;
import org.course.coursewebapplication.model.*;

import java.io.IOException;

@WebServlet("/verifyPayment")
public class VerifyPaymentServlet extends HttpServlet {
    private PaymentDAO paymentDAO;
    private EnrollmentDAO enrollmentDAO;

    @Override
    public void init() {
        paymentDAO = new PaymentDAO();
        enrollmentDAO = new EnrollmentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String courseIdParam = request.getParameter("course_id");
        String txnId = request.getParameter("txnId");

        if (email == null || courseIdParam == null || txnId == null || email.isEmpty() || txnId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid payment details.");
            return;
        }

        try {
            int courseId = Integer.parseInt(courseIdParam);

            // Check if payment already exists
            if (paymentDAO.isPaymentExists(txnId)) {
                String paymentStatus = paymentDAO.getPaymentStatus(txnId);

                if ("Confirmed".equals(paymentStatus)) {
                    // If already confirmed, enroll the user
                    if (enrollmentDAO.enrollUserInCourse(email, courseId)) {
                        response.sendRedirect("enrollmentsuccess.jsp?courseId=" + courseId);
                    } else {
                        response.sendRedirect("enrollfail.jsp");
                    }
                } else {
                    // If still pending, redirect to payment page
                    response.sendRedirect("payment.jsp?txnId=" + txnId);
                }
                return;
            }

            // New payment, mark as pending
            Payment payment = new Payment(email, courseId, txnId, "Pending");
            if (paymentDAO.savePayment(payment)) {
                response.sendRedirect("payment.jsp?txnId=" + txnId);
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid course ID format.");
        } catch (Exception e) {
            throw new ServletException("Error processing payment verification.", e);
        }
    }
}
