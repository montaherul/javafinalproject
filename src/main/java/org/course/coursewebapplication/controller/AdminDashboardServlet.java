package org.course.coursewebapplication.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.dao.EnrollmentDAO;
import org.course.coursewebapplication.dao.UserDAO;

import java.io.IOException;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private final CourseDAO courseDAO = new CourseDAO();
    private final UserDAO userDAO = new UserDAO();
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lastCourseId = courseDAO.getLatestCourseId();
        int lastStudentId =userDAO.getLastStudentId();// Call the method to get the last course ID
        int lastEnrollmentId = enrollmentDAO.getLastEnrollmentId();

        // Set the last course ID as a request attribute
        request.setAttribute("lastCourseId", lastCourseId);
        request.setAttribute("lastStudentId", lastStudentId);
        request.setAttribute("lastEnrollmentId", lastEnrollmentId);



        // Forward to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
