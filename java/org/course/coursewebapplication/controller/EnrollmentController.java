package org.course.coursewebapplication.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.course.coursewebapplication.dao.EnrollmentDAO;
import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.dao.UserDAO;
import org.course.coursewebapplication.model.Enrollment;
import org.course.coursewebapplication.model.Course;
import org.course.coursewebapplication.model.User;

import java.io.IOException;

@WebServlet("/manage-enrollments")
public class EnrollmentController extends HttpServlet {

    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action != null) {
                switch (action) {
                    case "view":
                        viewEnrollment(request, response);
                        break;
                    case "cancel":
                        cancelEnrollment(request, response);
                        break;
                    default:
                        response.sendRedirect("manage-enrollments.jsp?error=InvalidAction");
                        break;
                }
            } else {
                response.sendRedirect("manage-enrollments.jsp?error=ActionNotProvided");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            response.sendRedirect("manage-enrollments.jsp?error=ServerError");
        }
    }

    private void viewEnrollment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int enrollmentId = Integer.parseInt(request.getParameter("id"));
            Enrollment enrollment = enrollmentDAO.getEnrollmentById(enrollmentId);

            if (enrollment != null) {
                Course course = courseDAO.getCourseById(enrollment.getCourse_id());
                User user = UserDAO.getUserById(enrollment.getUser_id());

                request.setAttribute("enrollment", enrollment);
                request.setAttribute("course", course);
                request.setAttribute("user", user);

                request.getRequestDispatcher("view-enrollment.jsp").forward(request, response);
            } else {
                response.sendRedirect("manage-enrollments.jsp?error=EnrollmentNotFound");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("manage-enrollments.jsp?error=InvalidEnrollmentId");
        }
    }

    private void cancelEnrollment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int enrollmentId = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = enrollmentDAO.delete(enrollmentId);

            if (isDeleted) {
                response.sendRedirect("deleteenroll.jsp?id=" + enrollmentId);
            } else {
                response.sendRedirect("manage-enrollments.jsp?error=UnableToCancel");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("manage-enrollments.jsp?error=InvalidEnrollmentId");
        }
    }
}
