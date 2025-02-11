package org.course.coursewebapplication.service;

import org.course.coursewebapplication.dao.UserDAO;
import org.course.coursewebapplication.model.User;
import org.course.coursewebapplication.utility.EmailUtility;

import java.util.UUID;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Checks if the given user has an admin role.
     *
     * @param user The user object.
     * @return true if the user is an admin, false otherwise.
     */


    /**
     * Authenticates a user using email and password.
     *
     * @param email    The user's email.
     * @param password The user's password.
     * @return true if the user is authenticated, false otherwise.
     */
    public boolean login(String email, String password) {
        User user = userDAO.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    /**
     * Handles the forgot password functionality.
     *
     * @param email The user's email.
     * @return true if the reset process is successful, false otherwise.
     */
    public boolean forgotPassword(String email) {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            // Generate a unique reset token
            String resetToken = UUID.randomUUID().toString();

            // Save the token in the database
            if (userDAO.saveResetToken(email, resetToken)) {
                // Construct the reset link
                String resetLink = "http://yourwebsite.com/reset-password?token=" + resetToken;

                // Send the reset email
                String subject = "Password Reset Request";
                String message = "Hello " + user.getName() + ",\n\n" +
                        "You requested to reset your password. Click the link below to reset it:\n" +
                        resetLink + "\n\n" +
                        "If you did not request this, please ignore this email.\n\n" +
                        "Best regards,\nCourse Management System Team";

                return EmailUtility.sendEmail(email, subject, message);
            }
        }
        return false; // User not found or email sending failed
    }

    /**
     * Registers a new user in the system.
     *
     * @param user The user object.
     * @return true if the registration is successful, false otherwise.
     */
    public boolean register(User user) {
        if (userDAO.findByEmail(user.getEmail()) == null) {
            return userDAO.save(user);
        }
        return false; // Email already exists
    }

    /**
     * Resets the user's password.
     *
     * @param email       The user's email.
     * @param newPassword The new password.
     * @return true if the password reset is successful, false otherwise.
     */
    public boolean resetPassword(String email, String newPassword) {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            user.setPassword(newPassword);
            return userDAO.update(user);
        }
        return false; // User not found
    }
}
