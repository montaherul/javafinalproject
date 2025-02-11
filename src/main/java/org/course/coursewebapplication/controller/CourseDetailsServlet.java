package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.model.Course;

import java.io.IOException;

@WebServlet("/course-details")
public class CourseDetailsServlet extends HttpServlet {
    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String courseIdParam = request.getParameter("id");
            if (courseIdParam == null || courseIdParam.isEmpty()) {
                request.setAttribute("errorMessage", "Invalid course ID.");
                request.getRequestDispatcher("course-details.jsp").forward(request, response);
                return;
            }

            int courseId = Integer.parseInt(courseIdParam);
            Course course = courseDAO.getCourseById(courseId);

            if (course != null) {
                request.setAttribute("course", course);
            } else {
                request.setAttribute("errorMessage", "Course not found.");
            }

            request.getRequestDispatcher("course-details.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving course details.");
            request.getRequestDispatcher("course-details.jsp").forward(request, response);
        }
    }
}
