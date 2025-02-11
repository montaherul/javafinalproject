package org.course.coursewebapplication.utility;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtility {
    private static final String SMTP_HOST = "smtp.example.com"; // Replace with actual SMTP host
    private static final String SMTP_PORT = "587";              // Replace with actual port
    private static final String EMAIL = "noreply@example.com";  // Sender email address
    private static final String PASSWORD = "password";          // Sender email password

    // Method to send an email
    public static boolean sendEmail(String to, String subject, String messageContent) {
        // Set mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Authenticate the session using jakarta.mail.Authenticator
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));         // Set sender
            message.setRecipients(Message.RecipientType.TO,      // Set recipient
                    InternetAddress.parse(to));
            message.setSubject(subject);                         // Set subject
            message.setText(messageContent);                     // Set message body

            // Send email
            Transport.send(message);
            return true; // Email sent successfully
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; // Failed to send email
        }
    }
}
