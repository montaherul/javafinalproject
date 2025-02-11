package org.course.coursewebapplication.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");
        String courseId = request.getParameter("courseId");
        String paymentMethod = request.getParameter("paymentMethod");

        // Process payment (placeholder logic)
        System.out.println("Processing payment for user " + userId + " on course " + courseId + " using " + paymentMethod);

        response.sendRedirect("enrollment-confirmation.jsp");
    }
}
