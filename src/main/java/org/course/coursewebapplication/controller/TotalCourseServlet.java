package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.CourseDAO;

import java.io.IOException;

@WebServlet("/totalCourse")  // Updated to a more conventional URL pattern
public class TotalCourseServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        courseDAO = new CourseDAO(); // Initialize the CourseDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch the latest course ID from the DAO
        int latestCourseId = courseDAO.getLatestCourseId();

        // Set the fetched ID as a request attribute to be accessed in JSP
        request.setAttribute("latestCourseId", latestCourseId);

        // Forward the request to admin-dashboard.jsp
        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }
}
