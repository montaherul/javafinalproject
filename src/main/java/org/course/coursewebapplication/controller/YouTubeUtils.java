package org.course.coursewebapplication.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class YouTubeUtils extends HttpServlet {

    private static final String API_KEY = "";  // Replace with your secure API key source
    private static final String PLAYLIST_ID = "PL6FfPT4AFGKe_MKrlxD6Z9aIMz4CDBlum";  // Replace with actual playlist ID

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch playlist videos
            List<Map<String, String>> videoList = fetchPlaylistVideos(PLAYLIST_ID);

            // Set the video list as a request attribute
            request.setAttribute("videoList", videoList);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/displayPlaylist.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // Handle errors
            response.setContentType("text/html");
            response.getWriter().println("<h3>Error fetching playlist data: " + e.getMessage() + "</h3>");
        }
    }

    private List<Map<String, String>> fetchPlaylistVideos(String playlistId) throws IOException {
        List<Map<String, String>> videos = new ArrayList<>();

        // YouTube API endpoint for retrieving playlist items
        String apiUrl = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=10&playlistId=" + playlistId + "&key=" + API_KEY;

        // Establish HTTP connection
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Check HTTP response code
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + responseCode);
        }

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuffer = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            responseBuffer.append(inputLine);
        }
        in.close();

        // Parse the JSON response
        JSONObject jsonResponse = new JSONObject(responseBuffer.toString());
        JSONArray items = jsonResponse.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject snippet = items.getJSONObject(i).getJSONObject("snippet");
            String title = snippet.getString("title");
            String videoId = snippet.getJSONObject("resourceId").getString("videoId");
            String description = snippet.getString("description");
            String thumbnailUrl = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");

            // Store video info in a map
            Map<String, String> video = new HashMap<>();
            video.put("title", title);
            video.put("videoId", videoId);
            video.put("description", description);
            video.put("thumbnailUrl", thumbnailUrl);
            videos.add(video);
        }

        return videos;
    }
}
