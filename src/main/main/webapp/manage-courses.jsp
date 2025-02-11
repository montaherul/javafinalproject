<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.course.coursewebapplication.model.Course" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Courses</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        :root {
            --primary-color: #4CAF50;
            --secondary-color: #388E3C;
            --accent-color: #FFC107;
            --error-color: #f44336;
            --success-color: #4CAF50;
            --card-bg: #ffffff;
            --box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        header.navbar {
            background-color: var(--primary-color);
            color: white;
            padding: 1rem;
            box-shadow: var(--box-shadow);
        }

        header .navbar-brand {
            font-size: 1.5rem;
            font-weight: bold;
            color: white;
            text-decoration: none;
        }

        header .navbar-brand:hover {
            color: var(--accent-color);
        }

        .container {
            margin: 2rem auto;
            max-width: 1200px;
        }

        h2 {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 1.5rem;
        }

        .alert {
            border: none;
            border-radius: 5px;
            padding: 1rem;
        }

        .alert-success {
            background-color: var(--success-color);
            color: white;
        }

        .alert-danger {
            background-color: var(--error-color);
            color: white;
        }

        .btn-primary {
            background-color: var(--primary-color);
            border: none;
            transition: all 0.3s ease;
        }

        .btn-primary:hover {
            background-color: var(--secondary-color);
        }

        .btn-danger {
            background-color: var(--error-color);
        }

        .btn-danger:hover {
            background-color: #d32f2f;
        }

        .table-container {
            background-color: var(--card-bg);
            padding: 1.5rem;
            border-radius: 8px;
            box-shadow: var(--box-shadow);
        }

        .table {
            width: 100%;
            border-collapse: collapse;
        }

        .table th, .table td {
            padding: 1rem;
            border: 1px solid #ddd;
            text-align: center;
        }

        .table th {
            background-color: var(--primary-color);
            color: white;
        }

        .table tbody tr:hover {
            background-color: #f1f1f1;
        }

        .img-thumbnail {
            border-radius: 5px;
        }

        @media (max-width: 768px) {
            .table th, .table td {
                font-size: 0.85rem;
                padding: 0.5rem;
            }

            .btn {
                font-size: 0.85rem;
                padding: 0.4rem 0.8rem;
            }
        }
    </style>
</head>
<body>
<header class="navbar navbar-expand-lg navbar-dark">
    <div class="container-fluid">
        <a href="admin-dashboard.jsp" class="navbar-brand">Admin Dashboard</a>
    </div>
</header>

<div class="container">
    <h2>Manage Courses</h2>

    <% String success = (String) request.getAttribute("success");
        if (success != null && !success.isEmpty()) { %>
    <div class="alert alert-success">
        <i class="fas fa-check-circle"></i> <%= success %>
    </div>
    <% } %>

    <% String error = (String) request.getAttribute("error");
        if (error != null && !error.isEmpty()) { %>
    <div class="alert alert-danger">
        <i class="fas fa-times-circle"></i> <%= error %>
    </div>
    <% } %>

    <div class="d-flex justify-content-end mb-3">
        <a href="add-course.jsp" class="btn btn-primary">
            <i class="fas fa-plus"></i> Add New Course
        </a>
    </div>

    <div class="table-container">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Instructor</th>
                <th>Duration</th>
                <th>Price</th>
                <th>Image</th>
                <th>Instructor Image</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% List<Course> courses = (List<Course>) request.getAttribute("courses");
                if (courses != null && !courses.isEmpty()) {
                    for (Course course : courses) { %>
            <tr>
                <td><%= course.getId() %></td>
                <td><%= course.getTitle() %></td>
                <td><%= course.getDescription() %></td>
                <td><%= course.getInstructor() %></td>
                <td><%= course.getDuration() %></td>
                <td>$<%= String.format("%.2f", course.getPrice()) %></td>
                <td><img src="<%= request.getContextPath() + "/" + course.getImage() %>" alt="<%= course.getTitle() %>" width="100" class="img-thumbnail"></td>
                <td><img src="<%= request.getContextPath() + "/" + course.getInstructorimage() %>" alt="<%= course.getInstructor() %>" width="40" class="img-thumbnail"></td>
                <td>
                    <a href="update-course?id=<%= course.getId() %>" class="btn btn-primary btn-sm">
                        <i class="fas fa-edit"></i> Edit
                    </a>
                    <a href="delete-course?id=<%= course.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this course?')">
                        <i class="fas fa-trash-alt"></i> Delete
                    </a>
                </td>
            </tr>
            <% } } else { %>
            <tr>
                <td colspan="9">No courses available.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>
