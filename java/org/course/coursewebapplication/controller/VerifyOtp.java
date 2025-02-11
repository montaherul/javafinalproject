package org.course.coursewebapplication.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/verifyOtp")
public class VerifyOtp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enteredOtp = Integer.parseInt(request.getParameter("otp"));
        HttpSession session = request.getSession();
        int storedOtp = (int) session.getAttribute("otp");

        if (enteredOtp == storedOtp) {
            session.setAttribute("authenticated", true);
            response.sendRedirect("ResetPassword.jsp");
        } else {
            request.setAttribute("error", "Invalid OTP. Try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
        }
    }
}
