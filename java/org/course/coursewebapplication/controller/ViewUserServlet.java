package org.course.coursewebapplication.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.course.coursewebapplication.model.User;
import org.course.coursewebapplication.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/view-user")
public class ViewUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> userList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM user"; // Fetch all users
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setHsc(resultSet.getString("hsc"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setParentMobile(resultSet.getString("parentMobile"));
                    user.setInstitution(resultSet.getString("institution"));
                    user.setFacebook(resultSet.getString("facebook"));
                    user.setAddress(resultSet.getString("address"));
                    user.setProfile_pic(resultSet.getString("profile_pic"));

                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set the user list as an attribute and forward to JSP
        request.setAttribute("users", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view-users.jsp");
        dispatcher.forward(request, response);
    }
}
