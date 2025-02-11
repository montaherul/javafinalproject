package org.course.coursewebapplication.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.course.coursewebapplication.DatabaseConnection;
@WebServlet("/AlreadyEnrolledServlet")
public class AlreadyEnrolledServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        String query = """
                SELECT c.id, c.name, c.description
                FROM course c
                JOIN enrollment e ON c.id = e.course_id
                JOIN user u ON e.user_id = u.id
                WHERE u.email = ? AND c.id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setInt(2, courseId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String courseName = resultSet.getString("name");
                    String courseDescription = resultSet.getString("description");

                    // Set attributes for the JSP
                    request.setAttribute("courseId", courseId);
                    request.setAttribute("courseName", courseName);
                    request.setAttribute("courseDescription", courseDescription);

                    // Forward to the JSP
                    request.getRequestDispatcher("alreadyenrolled.jsp").forward(request, response);
                } else {
                    response.sendRedirect("enrollfail.jsp");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
