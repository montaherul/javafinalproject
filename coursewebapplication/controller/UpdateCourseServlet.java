package org.course.coursewebapplication.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.model.Course;

import java.io.IOException;

@WebServlet("/update-course")
    @MultipartConfig
    public class UpdateCourseServlet extends HttpServlet {

        private final CourseDAO courseDao = new CourseDAO();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String courseId = request.getParameter("id");

            if (courseId != null && !courseId.isEmpty()) {
                Course course = courseDao.getCourseById(Integer.parseInt(courseId));
                if (course != null) {
                    request.setAttribute("course", course);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("edit-course.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.getWriter().println("Course not found.");
                }
            } else {
                response.getWriter().println("Invalid course ID.");
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String instructor = request.getParameter("instructor");
            double price = Double.parseDouble(request.getParameter("price"));
            String duration = request.getParameter("duration");

            // Handle image upload
            Part imagePart = request.getPart("image");
            String imageFileName = saveUploadedFile(imagePart);

            // Handle instructor image upload
            Part instructorImagePart = request.getPart("instructorimage");
            String instructorImageFileName = saveUploadedFile(instructorImagePart);

            Course course = new Course(id, title, description, instructor, price, duration, imageFileName, instructorImageFileName);

            boolean isUpdated = courseDao.updateCourse(course);

            if (isUpdated) {
                response.sendRedirect("courses?action=list&success=1");
            } else {
                request.setAttribute("error", "Failed to update course.");
                request.getRequestDispatcher("edit-course.jsp").forward(request, response);
            }
        }

        private String saveUploadedFile(Part part) throws IOException {
            String fileName = extractFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                String uploadDir = "uploads"; // Ensure this directory exists on your server
                String filePath = getServletContext().getRealPath("") + uploadDir + "/" + fileName;
                part.write(filePath);
                return uploadDir + "/" + fileName; // Save relative path for database
            }
            return null;
        }

        private String extractFileName(Part part) {
            String contentDisposition = part.getHeader("Content-Disposition");
            for (String cd : contentDisposition.split(";")) {
                if (cd.trim().startsWith("filename")) {
                    return cd.substring(cd.indexOf('=') + 2, cd.length() - 1);
                }
            }
            return null;
        }
    }

