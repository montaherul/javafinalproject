<%@ page import="org.course.coursewebapplication.model.Course" %>
<%@ page import="org.course.coursewebapplication.model.User" %>
<%@ page import="org.course.coursewebapplication.model.Enrollment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Enrollment</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Link to your external CSS -->
    <style>
        /* General page styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        .container {
            width: 80%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        .details-box {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 30px;
        }

        .details-box h2 {
            font-size: 24px;
            color: #007BFF;
            margin-bottom: 20px;
        }

        .details-box p {
            font-size: 16px;
            line-height: 1.6;
            margin: 10px 0;
        }

        .details-box p strong {
            color: #555;
        }

        .back-link {
            display: inline-block;
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 20px;
            text-align: center;
        }

        .back-link:hover {
            background-color: #0056b3;
        }

        /* Responsive styling */
        @media (max-width: 768px) {
            .container {
                width: 95%;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Enrollment Details</h1>

    <%
        Enrollment enrollment = (Enrollment) request.getAttribute("enrollment");
        Course course = (Course) request.getAttribute("course");
        User user = (User) request.getAttribute("user");

        if (enrollment != null && course != null && user != null) {
    %>

    <div class="details-box">
        <h2>Enrollment Information</h2>
        <p><strong>Enrollment ID:</strong> <%= enrollment.getId() %></p>
        <p><strong>Student Name:</strong> <%= user.getName() %></p>
        <p><strong>Course Name:</strong> <%= course.getTitle() %></p>
        <p><strong>Date of Enrollment:</strong> <%= enrollment.getEnrollment_date() %></p>
        <a href="${pageContext.request.contextPath}/viewallenrollment" class="back-link">Go to Enrollments</a>
    </div>

    <% } else { %>

    <div class="details-box">
        <h2>Enrollment Not Found</h2>
        <p>Sorry, we couldn't find the enrollment details you're looking for.</p>
        <a href="${pageContext.request.contextPath}/viewallenrollment" class="back-link">Go to Enrollments</a>
    </div>

    <% } %>
</div>
</body>
</html>
