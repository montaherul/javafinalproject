package org.course.coursewebapplication.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.UserDAO;
import org.course.coursewebapplication.model.User;

import java.io.IOException;
@WebServlet("/student-dashboard")
public class StudentProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // Check if the email is not null or empty
        if (email != null && !email.isEmpty()) {
            // Instantiate the UserDAO to retrieve user details based on email
            UserDAO userDAO = new UserDAO();

            // Fetch the user object using the email
            User user = UserDAO.findByEmail(email);

            if (user != null) {
                // Set the user object in the request attribute for use in JSP
                request.setAttribute("user", user);  // Set the user attribute

                // Forward the request to student-dashboard.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("student-dashboard.jsp");
                dispatcher.forward(request, response);
            } else {
                // If no user found, set an error message and forward to error page
                request.setAttribute("errorMessage", "User not found");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            // If the email parameter is missing, redirect to the error page
            response.sendRedirect("error.jsp");
        }
    }
}


