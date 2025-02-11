<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Courses</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container mt-5">
    <h2>My Enrolled Courses</h2>

    <!-- Display success or error messages -->
    <%
        String success = (String) request.getAttribute("success");
        String error = (String) request.getAttribute("error");
        List<?> enrolledCourses = (List<?>) request.getAttribute("enrolledCourses");
    %>

    <% if (success != null) { %>
    <div class="alert alert-success"><%= success %></div>
    <% } %>

    <% if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <!-- Check if there are enrolled courses -->
    <% if (enrolledCourses == null || enrolledCourses.isEmpty()) { %>
    <div class="alert alert-info">You have not enrolled in any courses yet.</div>
    <% } else { %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Instructor</th>
            <th>Instructor Picture </th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Object obj : enrolledCourses) {
            org.course.coursewebapplication.model.Course course = (org.course.coursewebapplication.model.Course) obj;
        %>
        <tr>
            <td><%= course.getId() %></td>
            <td><%= course.getTitle() %></td>
            <td><%= course.getDescription() %></td>
            <td><%= course.getInstructor() %></td>
            <td><%= course.getInstructorimage() %> </td>
            <td>
                <a href="<%= request.getContextPath() %>/courses?action=view&id=<%= course.getId() %>" class="btn btn-primary btn-sm">View Details</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
