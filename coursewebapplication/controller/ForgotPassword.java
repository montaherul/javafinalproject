package org.course.coursewebapplication.controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        RequestDispatcher dispatcher;

        if (email != null && !email.trim().isEmpty()) {
            // Generate OTP
            int otpValue = new Random().nextInt(900000) + 100000;

            // SMTP Configuration
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");

            // Session Setup
            Session mailSession = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("islammontaherul@gmail.com", "adyu yldv irbn oqtv");
                }
            });

            try {
                // Compose Email
                MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress("islammontaherul@gmail.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                message.setSubject("Password Reset OTP");
                message.setText("Dear User,\n\nYour OTP for password reset is: " + otpValue);

                // Send Email
                Transport.send(message);

                // Store OTP in session
                HttpSession session = request.getSession();
                session.setAttribute("otp", otpValue);
                session.setAttribute("email", email);

                // Forward to EnterOtp.jsp
                dispatcher = request.getRequestDispatcher("/EnterOtp.jsp");
                request.setAttribute("message", "OTP sent to your email.(check inbox,spam also)");
                dispatcher.forward(request, response);
            } catch (MessagingException e) {
                e.printStackTrace();
                request.setAttribute("error", "Failed to send OTP. Please try again.");
                dispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("error", "Email address is required!");
            dispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
}
