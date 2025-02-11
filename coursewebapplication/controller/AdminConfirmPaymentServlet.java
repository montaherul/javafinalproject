package org.course.coursewebapplication.controller;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.PaymentDAO;

import java.io.IOException;
@WebServlet("/confirmPayment")
public class AdminConfirmPaymentServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    @Override
    public void init() {
        paymentDAO = new PaymentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String txnId = request.getParameter("txnId");

        if (txnId == null || txnId.isEmpty()) {
            response.sendRedirect("admin-dashboard.jsp?error=Transaction ID is required");
            return;
        }

        System.out.println("Received Transaction ID: " + txnId); // Debugging log

        boolean isConfirmed = paymentDAO.confirmPayment(txnId);

        if (isConfirmed) {
            response.sendRedirect("admin-dashboard.jsp?success=Payment confirmed");
        } else {
            response.sendRedirect("admin-dashboard.jsp?error=Transaction not found or failed to confirm");
        }
    }
}

