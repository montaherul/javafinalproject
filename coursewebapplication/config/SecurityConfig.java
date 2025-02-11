package org.course.coursewebapplication.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityConfig {

    // Hash password with a secure method (SHA-256 or bcrypt recommended)
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] salt = getSalt(); // Optional: add salt for extra security
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e);
        }
    }

    // Compare raw input password with hashed password
    public static boolean checkPassword(String rawPassword, String storedHashedPassword) {
        String hashedInput = hashPassword(rawPassword);
        return hashedInput.equals(storedHashedPassword);
    }

    // Optional: Generate a secure salt (improves security if used)
    private static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
