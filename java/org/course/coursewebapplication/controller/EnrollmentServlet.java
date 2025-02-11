package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.EnrollmentDAO;
import org.course.coursewebapplication.dao.UserDAO;
import org.course.coursewebapplication.model.User;

import java.io.IOException;

@WebServlet("/enroll")
public class EnrollmentServlet extends HttpServlet {
    private EnrollmentDAO enrollmentDAO;

    @Override
    public void init() {
        enrollmentDAO = new EnrollmentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String courseIdParam = request.getParameter("course_id");
        Double SpecialPrice = Double.parseDouble(request.getParameter("special-price"));

        if (isInvalidInput(email, courseIdParam)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input for email or course_id.");
            return;
        }

        try {
            int courseId = Integer.parseInt(courseIdParam);

            User user = UserDAO.findByEmail(email);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found for email: " + email);
                return;
            }

            if (enrollmentDAO.isAlreadyEnrolled(email, courseId)) {
                response.sendRedirect("alreadyenrolled.jsp?courseId=" + courseId);
                return;
            }

            if (enrollmentDAO.enrollUserInCourse(email, courseId)) {
                // Generate QR Code for payment
                String merchantNumber = "01782719433"; // Replace with actual merchant number
                String amount = String.valueOf(SpecialPrice); // Example amount
                String reference = email + "_C" + courseId;
                String qrCodePath = QRCodeGenerator.generateQRCode(merchantNumber, amount, reference);

                // Redirect to payment page
                request.setAttribute("qrCodePath", qrCodePath);
                request.setAttribute("email", email);
                request.setAttribute("courseId", courseId);
                request.getRequestDispatcher("payment.jsp").forward(request, response);
            } else {
                response.sendRedirect("enrollfail.jsp");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid course_id format: " + courseIdParam);
        } catch (Exception e) {
            throw new ServletException("Error processing enrollment.", e);
        }
    }

    private boolean isInvalidInput(String email, String courseIdParam) {
        return email == null || courseIdParam == null || email.trim().isEmpty() || courseIdParam.trim().isEmpty();
    }
}
