package org.course.coursewebapplication.controller;

import org.course.coursewebapplication.dao.CourseMaterialDAO;
import org.course.coursewebapplication.model.CourseMaterial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
@WebServlet("/courseMaterials")
public class CourseMaterialServlet extends HttpServlet {
    private final CourseMaterialDAO courseMaterialDAO = new CourseMaterialDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdParam = request.getParameter("course_id");

        // Check if courseId parameter is present and valid
        if (courseIdParam != null && !courseIdParam.isEmpty()) {
            try {
                int courseId = Integer.parseInt(courseIdParam);

                // Get all course materials for the specific course
                List<CourseMaterial> materials = courseMaterialDAO.getCourseMaterials(courseId);

                if (materials.isEmpty()) {
                    request.setAttribute("error", "No materials found for the selected course.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {
                    request.setAttribute("materials", materials);
                    request.getRequestDispatcher("coursemeterial.jsp").forward(request, response);
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid course ID format.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Course ID is missing.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdParam = request.getParameter("course_id");

        // Check if courseId parameter is present and valid
        if (courseIdParam != null && !courseIdParam.isEmpty()) {
            try {
                int courseId = Integer.parseInt(courseIdParam);

                List<CourseMaterial> materials = courseMaterialDAO.getCourseMaterials(courseId);

                if (materials.isEmpty()) {
                    request.setAttribute("error", "No materials found for the selected course.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {
                    request.setAttribute("materials", materials);
                    request.getRequestDispatcher("coursemeterial.jsp").forward(request, response);
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid course ID format.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Course ID is missing.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

