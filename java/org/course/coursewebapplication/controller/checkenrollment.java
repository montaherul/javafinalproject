package org.course.coursewebapplication.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.EnrollmentDAO;

import java.io.IOException;

@WebServlet("/check-enrollment")
public class checkenrollment extends HttpServlet {

    private EnrollmentDAO enrollmentDAO;

    @Override
    public void init() {
        enrollmentDAO = new EnrollmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String courseId =String.valueOf(request.getParameter("courseId"));

        boolean isEnrolled = enrollmentDAO.isUserEnrolled(userId, courseId);

        request.setAttribute("isEnrolled", isEnrolled);
        request.setAttribute("course_id", courseId);

        request.getRequestDispatcher("course-details.jsp").forward(request, response);
    }
}
