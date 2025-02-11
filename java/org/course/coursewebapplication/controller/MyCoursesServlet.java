package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.EnrollmentDAO;
import org.course.coursewebapplication.model.Course;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-courses")
public class MyCoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assume the user ID is stored in the session
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not authenticated
            return;
        }

        // Fetch enrolled courses for the user
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        List<Course> enrolledCourses = enrollmentDAO.getEnrolledCourses(userId);

        // Set the courses in the request scope
        request.setAttribute("enrolledCourses", enrolledCourses);

        // Forward to the JSP
        request.getRequestDispatcher("my-courses.jsp").forward(request, response);
    }
}
