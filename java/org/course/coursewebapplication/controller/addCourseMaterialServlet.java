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

@WebServlet("/add-course-material")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,     // 10MB
        maxRequestSize = 1024 * 1024 * 50  // 50MB
)
public class addCourseMaterialServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve course material details
        int courseId;
        try {
            courseId = Integer.parseInt(request.getParameter("course_id"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid course ID format.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

            return;
        }

        String materialType = request.getParameter("materialType");
        String content = request.getParameter("content");

        // Handle file upload
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            filePart.write(uploadPath + File.separator + fileName);
        } catch (IOException e) {
            request.setAttribute("error", "Error uploading file: " + e.getMessage());

            return;
        }

        // Insert course material data into the database
        String sql = "INSERT INTO course_material (course_id, material_type, content, file_path) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            stmt.setString(2, materialType);
            stmt.setString(3, content);
            stmt.setString(4, UPLOAD_DIR + "/" + fileName);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("error", "Failed to add course material.");



            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);

        }
    }
}
