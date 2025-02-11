package org.course.coursewebapplication.controller;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hardcoded admin credentials
        String adminUsername = "admin";
        String adminPassword = "admin";

        // Get input from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check credentials
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            // Redirect to admin dashboard
            response.sendRedirect("admin-dashboard.jsp");
        } else {
            // Redirect back with an error message
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }
}
