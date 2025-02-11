package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.EnrollmentDAO;
import org.course.coursewebapplication.model.Enrollment;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewallenrollment")
public class ManageEnrollmentsServlet extends HttpServlet {
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all enrollments from the database
            List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();

            // Check for null or empty list and handle accordingly
            if (enrollments == null || enrollments.isEmpty()) {
                request.setAttribute("message", "No enrollments found.");
            } else {
                request.setAttribute("enrollment", enrollments);
            }

            // Forward the request to the JSP page
            request.getRequestDispatcher("manage-enrollments.jsp").forward(request, response);

        } catch (Exception e) {
            // Log the exception and redirect to an error page if needed
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while managing enrollments.");
            response.sendRedirect("error.jsp"); // Replace with your error page if applicable
        }
    }
}
