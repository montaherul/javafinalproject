package org.course.coursewebapplication.controller;


import org.course.coursewebapplication.utility.EmailUtility;
import org.course.coursewebapplication.dao.UserDAO;
import org.course.coursewebapplication.model.User;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

public class ForgotPasswordController {

    private final UserDAO userDAO = new UserDAO(); // DAO for accessing user data

    // Endpoint: Request to send reset link
    public String forgotPassword(String email, HttpServletRequest request) {
        User user = userDAO.findByEmail(email); // Check if user exists

        if (user != null) {
            // Generate reset token
            String token = UUID.randomUUID().toString();
            boolean tokenSaved = userDAO.saveResetToken(email, token); // Store token in the database

            if (tokenSaved) {
                // Create reset link
                String resetLink = request.getRequestURL().toString()
                        .replace("forgot-password", "reset-password")
                        + "?token=" + token;

                // Send email
                String subject = "Password Reset Request";
                String message = String.format(
                        "Dear %s,\n\nWe received a request to reset your password. Please click the link below to reset it:\n%s\n\nIf you didn't make this request, you can ignore this email.\n\nBest regards,\nCourse Management System Team",
                        user.getName(),
                        resetLink
                );

                EmailUtility.sendEmail(email, subject, message);
                return "Reset link has been sent to your email.";
            } else {
                return "Error while generating reset link. Please try again.";
            }
        } else {
            return "Email not registered.";
        }
    }

    // Endpoint: Reset Password
    public String resetPassword(String token, String newPassword) {
        User user = userDAO.findByResetToken(token); // Validate token

        if (user != null) {
            boolean passwordUpdated = userDAO.updatePassword(user.getEmail(), newPassword); // Update password
            if (passwordUpdated) {
                userDAO.removeResetToken(user.getEmail()); // Remove token after successful reset
                return "Password has been successfully reset.";
            } else {
                return "Failed to reset password. Please try again.";
            }
        } else {
            return "Invalid or expired reset token.";
        }
    }
}
