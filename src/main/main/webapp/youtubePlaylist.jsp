<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 1/15/2025
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YouTube Playlist Viewer</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<h1>YouTube Playlist Videos</h1>

<div class="video-container">
    <%
        List<Map<String, String>> videoList = (List<Map<String, String>>) request.getAttribute("videoList");

        if (videoList != null && !videoList.isEmpty()) {
            for (Map<String, String> video : videoList) {
                String title = video.get("title");
                String videoId = video.get("videoId");
                String description = video.get("description");
                String thumbnailUrl = video.get("thumbnailUrl");
    %>
    <div class="video-item">
        <img class="video-thumbnail" src="<%= thumbnailUrl %>" alt="Thumbnail" data-video-id="<%= videoId %>">
        <div class="video-content">
            <div class="video-title"><%= title %></div>
            <div class="video-description"><%= description %></div>
            <iframe class="video-frame" src="https://www.youtube.com/embed/<%= videoId %>?autoplay=0"></iframe>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <p class="no-videos">No videos found in this playlist.</p>
    <%
        }
    %>
</div>

<script>
    document.querySelectorAll('.video-thumbnail').forEach(thumbnail => {
        thumbnail.addEventListener('click', function() {
            const videoId = thumbnail.getAttribute('data-video-id');
            const iframe = thumbnail.parentElement.querySelector('.video-frame');

            iframe.style.display = 'block';
            iframe.src = 'https://www.youtube.com/embed/' + videoId + '?autoplay=1';

            document.querySelectorAll('.video-frame').forEach(function(otherIframe) {
                if (otherIframe !== iframe) {
                    otherIframe.style.display = 'none';
                    otherIframe.src = '';
                }
            });
        });
    });
</script>

</body>
</html>
