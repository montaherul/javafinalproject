package org.course.coursewebapplication.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.course.coursewebapplication.dao.UserDAO;
import org.course.coursewebapplication.model.User;

import java.io.File;
import java.io.IOException;

@WebServlet("/update-profile")
@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {

    private final UserDAO userDao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");

        if (userId != null && !userId.isEmpty()) {
            User user = userDao.getUserById(Integer.parseInt(userId));
            if (user != null) {
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editprofile.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter().println("User not found.");
            }
        } else {
            response.getWriter().println("Invalid user ID.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hsc = request.getParameter("hsc");
        String phone = request.getParameter("phone");
        String parentMobile = request.getParameter("parentMobile");
        String institution = request.getParameter("institution");
        String facebook = request.getParameter("facebook");
        String address = request.getParameter("address");

        // Retrieve the existing profile picture path
        User existingUser = userDao.getUserById(id);
        String oldProfilePicPath = existingUser.getProfile_pic();

        // Handle profile picture upload
        Part profilePicPart = request.getPart("profile_pic");
        String profilePicFileName = saveUploadedFile(profilePicPart, oldProfilePicPath);

        User user = new User(id, name, email,hsc, phone, password,parentMobile, institution, facebook, address, profilePicFileName);

        boolean isUpdated = UserDAO.update(user);

        if (isUpdated) {
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", "Failed to update profile.");
            request.getRequestDispatcher("editprofile.jsp").forward(request, response);
        }
    }

    private String saveUploadedFile(Part part, String oldFilePath) throws IOException {
        String fileName = extractFileName(part);
        if (fileName != null && !fileName.isEmpty()) {
            String uploadDir = "uploads"; // Ensure this directory exists on your server
            String filePath = getServletContext().getRealPath("") + uploadDir + "/" + fileName;

            // Delete old profile picture if it exists
            if (oldFilePath != null && !oldFilePath.isEmpty()) {
                String oldFileFullPath = getServletContext().getRealPath("") + oldFilePath;
                File oldFile = new File(oldFileFullPath);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            part.write(filePath);
            return uploadDir + "/" + fileName; // Save relative path for database
        }
        return oldFilePath; // If no new file is uploaded, keep the old file path
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
