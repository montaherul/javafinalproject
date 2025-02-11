package org.course.coursewebapplication.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.model.Course;
import org.course.coursewebapplication.service.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Save the course to the database (placeholder logic)
        System.out.println("Saving course: " + title);

        response.sendRedirect("courses.jsp");
    }
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        courseDAO = new CourseDAO(); // Initialize the DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses(); // Fetch courses from the database
        request.setAttribute("courses", courses); // Set the courses in the request scope
        request.getRequestDispatcher("courses.jsp").forward(request, response); // Forward to JSP
    }
    private void allCourses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);

        // Get success or error message
        String successMessage = request.getParameter("success");
        String errorMessage = request.getParameter("error");

        if (successMessage != null && !successMessage.isEmpty()) {
            request.setAttribute("success", successMessage);
        }

        if (errorMessage != null && !errorMessage.isEmpty()) {
            request.setAttribute("error", errorMessage);
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("courses.jsp").forward(request, response);
    }

}

