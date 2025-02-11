package org.course.coursewebapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

@WebServlet("/fetchYouTubePlaylist")
public class YouTubePlaylistFetcher extends HttpServlet {
    private static final String API_KEY = "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"; // Replace with your API key
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playlistUrl = request.getParameter("playlistUrl");
        String playlistId = extractPlaylistId(playlistUrl);

        List<Map<String, String>> videoList = fetchPlaylistVideos(playlistId);
        request.setAttribute("videoList", videoList);
        request.getRequestDispatcher("/youtubePlaylist.jsp").forward(request, response);
    }

    private String extractPlaylistId(String url) {
        // Simple extraction based on known URL structure
        String[] parts = url.split("list=");
        return parts.length > 1 ? parts[1] : "";
    }

    private List<Map<String, String>> fetchPlaylistVideos(String playlistId) {
        List<Map<String, String>> videoList = new ArrayList<>();
        try {
            String apiUrl = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="
                    + playlistId + "&key=" + API_KEY + "&maxResults=20";

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray items = jsonResponse.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject snippet = items.getJSONObject(i).getJSONObject("snippet");
                String title = snippet.getString("title");
                String videoId = snippet.getJSONObject("resourceId").getString("videoId");
                String description = snippet.getString("description");
                String thumbnailUrl = snippet.getJSONObject("thumbnails").getJSONObject("default").getString("url");

                Map<String, String> video = new HashMap<>();
                video.put("title", title);
                video.put("videoId", videoId);
                video.put("description", description);
                video.put("thumbnailUrl", thumbnailUrl);

                videoList.add(video);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoList;
    }
}
