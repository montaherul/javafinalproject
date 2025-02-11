package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.course.coursewebapplication.model.User;
import org.course.coursewebapplication.dao.UserDAO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/register-user")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class RegisterUserServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve and validate form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hsc = request.getParameter("hsc");
        String phone = request.getParameter("phone");
        String parentMobile = request.getParameter("parentMobile");
        String institution = request.getParameter("institution");
        String facebook = request.getParameter("facebook");
        String address = request.getParameter("address");
        Part userImagePart = request.getPart("profile_pic");

        if (isNullOrEmpty(name) || isNullOrEmpty(email) || isNullOrEmpty(password)) {
            request.setAttribute("error", "Name, Email, and Password are required fields.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        String userImageFileName = null;
        if (userImagePart != null && userImagePart.getSize() > 0) {
            userImageFileName = Paths.get(userImagePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Create directory if not exists
            }

            String filePath = uploadPath + File.separator + userImageFileName;
            userImagePart.write(filePath); // Save file to the server
        } else {
            request.setAttribute("error", "Profile picture is required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Create a User object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);  // Encrypt the password using a secure method
        user.setHsc(hsc);
        user.setPhone(phone);
        user.setParentMobile(parentMobile);
        user.setInstitution(institution);
        user.setFacebook(facebook);
        user.setAddress(address);
        user.setProfile_pic(UPLOAD_DIR + "/" + userImageFileName); // Store relative path in DB

        // Save user to the database
        UserDAO userDAO = new UserDAO();
        boolean isRegistered = userDAO.save(user);

        if (isRegistered) {
            // Redirect to success page or login
            response.sendRedirect("login.jsp");
        } else {
            // Handle registration failure
            request.setAttribute("error", "Registration failed. Email might already be registered.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    // Utility method to check if a string is null or empty
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
