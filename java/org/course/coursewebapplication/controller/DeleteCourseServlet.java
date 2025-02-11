package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.CourseDAO;

import java.io.IOException;

@WebServlet("/delete-course")
public class DeleteCourseServlet extends HttpServlet {

    private final CourseDAO courseDao = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("id");

        if (courseId != null && !courseId.isEmpty()) {
            try {
                int id = Integer.parseInt(courseId);
                boolean isDeleted = courseDao.deleteCourse(id);

                if (isDeleted) {
                    response.sendRedirect("courses?action=list");
                } else {
                    response.getWriter().println("Error: Course not found.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid course ID.");
            }
        } else {
            response.getWriter().println("Course ID is required.");
        }
    }
}
