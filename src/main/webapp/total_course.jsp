<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 12/23/2024
  Time: 2:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest Course ID</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<header class="navbar navbar-dark bg-dark p-3">
    <div class="container-fluid">
        <a href="admin-dashboard.jsp" class="navbar-brand">Admin Dashboard</a>
        <a href="logout.jsp" class="btn btn-outline-light">Logout</a>
    </div>
</header>

<div class="container mt-5">
    <h2>Latest Course ID</h2>

    <!-- Check if the course ID is available -->
    <%
        int latestCourseId = (Integer) request.getAttribute("latestCourseId");
        if (latestCourseId != -1) {
    %>
    <p>The latest course ID is: <strong><%= latestCourseId %></strong></p>
    <% } else { %>
    <p>No courses available.</p>
    <% } %>
</div>

<!-- Bootstrap JS (Optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
