<%@ page import="org.course.coursewebapplication.model.CourseMaterial" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 1/15/2025
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Course Materials</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>Course Materials</h1>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color: red;"><%= error %></p>
<%
    }
%>

<%
    List<CourseMaterial> materials =
            (List<org.course.coursewebapplication.model.CourseMaterial>) request.getAttribute("materials");
    if (materials != null && !materials.isEmpty()) {
        for (org.course.coursewebapplication.model.CourseMaterial material : materials) {
%>
<div>
    <h3><%= material.getMaterialType() %></h3>
    <p><%= material.getContent() %></p>
    <p>Uploaded on: <%= material.getDate() %></p>
    <a href="<%= material.getFilePath() %>" target="_blank">Download</a>
</div>
<%
    }
} else {
%>
<p>No course materials available.</p>
<%
    }
%>
</body>
</html>
