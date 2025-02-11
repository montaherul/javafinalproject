package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.course.coursewebapplication.DatabaseConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/add-course")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddCourseServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve course details
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String instructor = request.getParameter("instructor");
        String duration = request.getParameter("duration");

        double price;
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid price format.");
            request.getRequestDispatcher("add-course.jsp").forward(request, response);
            return;
        }

        // Handle course image upload
        Part courseImagePart = request.getPart("image");
        String courseImageFileName = Paths.get(courseImagePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        // Handle instructor image upload
        Part instructorImagePart = request.getPart("instructor image");
        String instructorImageFileName = Paths.get(instructorImagePart.getSubmittedFileName()).getFileName().toString();

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            courseImagePart.write(uploadPath + File.separator + courseImageFileName);
            instructorImagePart.write(uploadPath + File.separator + instructorImageFileName);
        } catch (IOException e) {
            request.setAttribute("error", "Error uploading images: " + e.getMessage());
            request.getRequestDispatcher("add-course.jsp").forward(request, response);
            return;
        }

        // Insert course data into the database
        String sql = "INSERT INTO courses (title, description, instructor, duration, price, image, instructorimage) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, instructor);
            stmt.setString(4, duration);
            stmt.setDouble(5, price);
            stmt.setString(6, UPLOAD_DIR + "/" + courseImageFileName);
            stmt.setString(7, UPLOAD_DIR + "/" + instructorImageFileName);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("courses?action=list&success=Course added successfully");
            } else {
                request.setAttribute("error", "Failed to add course.");
                request.getRequestDispatcher("add-course.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("add-course.jsp").forward(request, response);
        }
    }
}