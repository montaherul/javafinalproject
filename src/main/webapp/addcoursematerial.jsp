<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 1/15/2025
  Time: 2:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Course Material</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<h2>Add Course Material</h2>

<form action="add-course-material" method="POST" enctype="multipart/form-data">
    <!-- Hidden course ID passed from the previous page -->
    <input type="hidden" name="course_id" value="<%= request.getAttribute("course_id") %>"/>

    <label for="materialType">Material Type:</label>
    <input type="text" id="materialType" name="materialType" required/><br><br>

    <label for="content">Material Content:</label>
    <textarea id="content" name="content" required></textarea><br><br>

    <label for="file">Choose Material File:</label>
    <input type="file" id="file" name="file" required/><br><br>

    <input type="submit" value="Add Material"/>
</form>

</body>
</html>
