package org.course.coursewebapplication.controller;

import org.course.coursewebapplication.model.CourseMaterial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/playlistVideos")
public class playlistVideosServlet extends HttpServlet {

    private final String API_KEY = "312340041228-reb51r24n5ovlvu7j7fk9u3jd2oh1c35.apps.googleusercontent.com"; // Replace with your YouTube Data API key

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract playlist ID from the content URL
        String playlistUrl = request.getParameter("playlistUrl");
        String playlistId = extractPlaylistId(playlistUrl);

        if (playlistId != null && !playlistId.isEmpty()) {
            List<String> videoTitles = new ArrayList<>();
            List<String> videoUrls = new ArrayList<>();
            List<String> videoIds = new ArrayList<>();

            // Fetch playlist videos using YouTube Data API
            try {
                final String[] items = getStrings(playlistId);

                if (items.length > 1) {
                    String videosData = items[1].split("\\],")[0]; // Extract the JSON array part containing video data
                    String[] videoEntries = videosData.split("\\},\\{");

                    for (String videoEntry : videoEntries) {
                        String videoTitle = extractValue(videoEntry, "\"title\":\"", "\"");
                        String videoId = extractValue(videoEntry, "\"videoId\":\"", "\"");
                        String videoUrl = "https://www.youtube.com/watch?v=" + videoId;

                        videoTitles.add(videoTitle);
                        videoUrls.add(videoUrl);
                        videoIds.add(videoId);
                    }
                }

                // Store video data in request attributes
                request.setAttribute("videoTitles", videoTitles);
                request.setAttribute("videoUrls", videoUrls);
                request.setAttribute("videoIds", videoIds);

                // Forward the request to the JSP page to display videos
                request.getRequestDispatcher("playlistVideos.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("Error fetching playlist videos: " + e.getMessage());
            }
        } else {
            response.getWriter().write("Invalid Playlist URL.");
        }
    }

    private String[] getStrings(String playlistId) throws IOException {
        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=" + playlistId + "&key=" + API_KEY;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder responseContent = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            responseContent.append(inputLine);
        }

        in.close();

        // Manually extract the video data from the response string
        String responseString = responseContent.toString();
        String[] items = responseString.split("\"items\":\\[");
        return items;
    }

    // Method to extract playlist ID from the YouTube playlist URL
    private String extractPlaylistId(String url) {
        String playlistId = null;
        if (url != null && url.contains("list=")) {
            int startIndex = url.indexOf("list=") + 5;
            playlistId = url.substring(startIndex);
        }
        return playlistId;
    }

    // Method to extract the value between two strings
    private String extractValue(String source, String start, String end) {
        int startIndex = source.indexOf(start);
        int endIndex = source.indexOf(end, startIndex + start.length());
        if (startIndex != -1 && endIndex != -1) {
            return source.substring(startIndex + start.length(), endIndex);
        }
        return "";
    }
}
