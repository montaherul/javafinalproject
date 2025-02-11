package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Perform authentication logic
        if (email.equals("admin@example.com") && password.equals("password")) {
            request.getSession().setAttribute("userRole", "admin");
            response.sendRedirect("admin-dashboard.jsp");
        } else if (email.equals("student@example.com") && password.equals("password")) {
            request.getSession().setAttribute("userRole", "student");
            response.sendRedirect("student-dashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
