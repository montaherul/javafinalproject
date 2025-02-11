package org.course.coursewebapplication.controller;
import org.course.coursewebapplication.model.*;
import org.course.coursewebapplication.dao.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming the user's email is stored in the session after successful login
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // If email is null, the user might not be logged in, handle this case
        if (email == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not authenticated
            return;
        }

        // Retrieve the user from the database using the email
        User user = UserDAO.findByEmail(email);

        // If the user is not found, handle it (e.g., redirect to an error page)
        if (user == null) {
            response.sendRedirect("error.jsp"); // Or show an error message
            return;
        }

        // Set the user object as a request attribute
        request.setAttribute("user", user);

        // Forward the request to the JSP page for profile editing
        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
    }
}
