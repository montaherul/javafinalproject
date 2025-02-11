package org.course.coursewebapplication.dao;

import org.course.coursewebapplication.DatabaseConnection;
import org.course.coursewebapplication.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    // Find user by email
    public static User findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";

        // Create a connection to the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Prepare the query to fetch user details
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            // If a user is found, map the result to a User object
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setHsc(rs.getString("hsc"));
                user.setPhone(rs.getString("phone"));
                user.setParentMobile(rs.getString("parentMobile"));
                user.setInstitution(rs.getString("institution"));
                user.setFacebook(rs.getString("facebook"));
                user.setAddress(rs.getString("address"));

                // Handle profile picture
                String profilePic = rs.getString("profile_pic");
                user.setProfile_pic((profilePic != null && !profilePic.isEmpty())
                        ? profilePic
                        : "default-profile.png"); // Fallback to default picture

                return user; // Return the populated User object
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return null; // Return null if no user is found or an error occurs
    }
    public static User getUserById(int userId) {
        String query = "SELECT * FROM user WHERE id = ?";

        // Create a connection to the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Prepare the query to fetch user details by ID
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            // If a user is found, map the result to a User object
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setHsc(rs.getString("hsc"));
                user.setPhone(rs.getString("phone"));
                user.setParentMobile(rs.getString("parentMobile"));
                user.setInstitution(rs.getString("institution"));
                user.setFacebook(rs.getString("facebook"));
                user.setAddress(rs.getString("address"));

                // Handle profile picture
                String profilePic = rs.getString("profile_pic");
                user.setProfile_pic((profilePic != null && !profilePic.isEmpty())
                        ? profilePic
                        : "default-profile.png"); // Fallback to default picture

                return user; // Return the populated User object
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return null; // Return null if no user is found or an error occurs
    }



    public static User finduserByEmail(String email) {
        // Create a connection to the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Query to fetch user details
            String sql = "SELECT * FROM user WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            // If a user is found, map the result to a User object
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setHsc(rs.getString("hsc"));
                user.setPhone(rs.getString("phone"));
                user.setParentMobile(rs.getString("parentMobile"));
                user.setInstitution(rs.getString("institution"));
                user.setFacebook(rs.getString("facebook"));
                user.setAddress(rs.getString("address"));
                return user;  // Return the User object with populated details
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no user was found
    }

    // Save a new user to the database
    private static final String INSERT_USER_SQL =
            "INSERT INTO user (name, email, password, hsc, phone, parentMobile, institution, facebook, address, profile_pic) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean save(User user) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword()); // Hash the password before storing
            stmt.setString(4, user.getHsc());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getParentMobile());
            stmt.setString(7, user.getInstitution());
            stmt.setString(8, user.getFacebook());
            stmt.setString(9, user.getAddress());
            stmt.setString(10, user.getProfile_pic());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    // Save reset token for password recovery
    public boolean saveResetToken(String email, String token) {
        String query = "UPDATE user SET reset_token = ? WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, token);
            stmt.setString(2, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update user details
    public static boolean update(User user) {
        String query = "UPDATE user SET name = ?, email = ?, password = ?, hsc = ?, phone = ?, parentMobile = ?, institution = ?, facebook = ?, address = ?,profile_pic =? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getHsc());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getParentMobile());
            stmt.setString(7, user.getInstitution());
            stmt.setString(8, user.getFacebook());
            stmt.setString(9, user.getAddress());
            stmt.setString(10, user.getProfile_pic());
            stmt.setInt(11, user.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
        return false;
    }

    // Remove reset token after password reset
    public void removeResetToken(String email) {
        String query = "UPDATE user SET reset_token = NULL WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing reset token: " + e.getMessage());
        }
    }

    // Find user by reset token
    public User findByResetToken(String token) {
        String query = "SELECT * FROM user WHERE reset_token = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add a new user (wrapper for save)
    public boolean addUser(User user) {
        return save(user);
    }

    // Get all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    // Helper method to map ResultSet to User object
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setHsc(rs.getString("hsc"));
        user.setPhone(rs.getString("phone"));
        user.setParentMobile(rs.getString("parent_mobile"));
        user.setInstitution(rs.getString("institution"));
        user.setFacebook(rs.getString("facebook"));
        user.setAddress(rs.getString("address"));
        user.setProfile_pic(rs.getString("profile_pic"));
        return user;
    }

    // Get last student ID
    public int getLastStudentId() {
        String query = "SELECT MAX(id) FROM user ";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE user SET password = ? WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; // Return true if at least one row was updated.
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Integer getUserIdByCredentials(String username, String password) {
        String query = "SELECT id FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id"); // return user ID if login is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // return null if no matching user is found
    }
}

