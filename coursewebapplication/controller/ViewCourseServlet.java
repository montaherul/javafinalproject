package org.course.coursewebapplication.controller;

import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.model.Course;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/view-courses")
public class ViewCourseServlet extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Course> courseList = courseDAO.getAllCourses();

        // Set the course list as an attribute and forward to JSP
        request.setAttribute("courses", courseList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/course-details.jsp");
        dispatcher.forward(request, response);
    }
}
