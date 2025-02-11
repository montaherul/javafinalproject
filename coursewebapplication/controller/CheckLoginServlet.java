package org.course.coursewebapplication.controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class CheckLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        // If user is not logged in, redirect to login page
        if (userId == null) {
            response.sendRedirect("login.jsp");
        } else {
            // Get the course ID and redirect to the enrollment page
            String courseId = request.getParameter("course_id");
            response.sendRedirect("enroll.jsp?course_id=" + courseId);
        }
    }
}
