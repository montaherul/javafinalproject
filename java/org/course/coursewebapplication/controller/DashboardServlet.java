package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.dao.UserDAO;
import org.course.coursewebapplication.dao.EnrollmentDAO;

import java.io.IOException;

@WebServlet("/adminDashboard")
public class DashboardServlet extends HttpServlet {
    private final CourseDAO courseDAO = new CourseDAO();
    private final UserDAO userDAO = new UserDAO();
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch counts from the database
            int totalCourses = courseDAO.getLatestCourseId();
            int totalStudents = userDAO.getLastStudentId();
            int activeEnrollments = enrollmentDAO.getLastEnrollmentId();

            // Set attributes to pass to JSP
            request.setAttribute("totalCourses", totalCourses);
            request.setAttribute("totalStudents", totalStudents);
            request.setAttribute("activeEnrollments", activeEnrollments);

            // Forward to JSP
            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching dashboard data.");
        }
    }
}
