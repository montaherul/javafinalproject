<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 1/15/2025
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.course.coursewebapplication.model.CourseMaterial" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Playlist Videos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fa;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            font-size: 36px;
            color: #2c3e50;
            margin-top: 50px;
        }

        .video-list {
            max-width: 900px;
            margin: 0 auto;
            padding: 20px;
        }

        .video-item {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.3s ease;
        }

        .video-item:hover {
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
        }

        .video-item h3 {
            font-size: 24px;
            color: #3498db;
            margin: 0 0 10px 0;
        }

        .video-item iframe {
            width: 100%;
            height: 400px;
            border-radius: 8px;
        }

        .video-item a {
            font-size: 16px;
            color: #3498db;
            text-decoration: none;
            border: 2px solid #3498db;
            padding: 8px 16px;
            border-radius: 5px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .video-item a:hover {
            background-color: #3498db;
            color: #fff;
        }
    </style>
</head>
<body>
<h1>Playlist Videos</h1>

<%
    List<String> videoTitles = (List<String>) request.getAttribute("videoTitles");
    List<String> videoUrls = (List<String>) request.getAttribute("videoUrls");

    if (videoTitles != null && !videoTitles.isEmpty()) {
        for (int i = 0; i < videoTitles.size(); i++) {
%>
<div class="video-item">
    <h3><%= videoTitles.get(i) %></h3>
    <a href="<%= videoUrls.get(i) %>" target="_blank">Watch Video</a>
</div>
<%
    }
} else {
%>
<p>No videos available in this playlist.</p>
<% } %>
</body>
</html>
