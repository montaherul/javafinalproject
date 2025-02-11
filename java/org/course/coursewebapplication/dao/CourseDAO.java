package org.course.coursewebapplication.dao;

import org.course.coursewebapplication.model.Course;
import org.course.coursewebapplication.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CourseDAO {
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM courses")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("instructor"),
                        resultSet.getDouble("price"),
                        resultSet.getString("duration"),
                        resultSet.getString("image"),
                        resultSet.getString("instructorimage")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }


    public boolean addCourse(Course course) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO courses (title, description, instructor, price, duration, image,instructorimage) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setString(3, course.getInstructor());
            preparedStatement.setDouble(4, course.getPrice());
            preparedStatement.setString(5, course.getDuration());
            preparedStatement.setString(6, course.getImage());
            preparedStatement.setString(7, course.getInstructorimage());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Your existing methods...

    // Method to get the latest course ID
    public int getLatestCourseId() {
        int latestCourseId = -1;  // Default value if no course is found
        String query = "SELECT id FROM courses ORDER BY id DESC LIMIT 1";  // Query to get the latest course ID

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                latestCourseId = resultSet.getInt("id");  // Corrected column name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return latestCourseId;
    }



    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                courses.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("instructor"),
                        resultSet.getDouble("price"),
                        resultSet.getString("duration"),
                        resultSet.getString("image"),
                        resultSet.getString("instructorimage")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    // Find course by ID

    // Method to get a course by ID
    // Method to get a course by ID
    public Course getCourseById(int id) {
        Course course = null;

        // SQL query to fetch a course by ID
        String query = "SELECT * FROM courses WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the ID parameter in the query
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    course = new Course();
                    course.setId(resultSet.getInt("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setDescription(resultSet.getString("description"));
                    course.setInstructor(resultSet.getString("instructor"));
                    course.setPrice(resultSet.getDouble("price"));
                    course.setDuration(resultSet.getString("duration"));
                    course.setImage(resultSet.getString("image"));
                    course.setInstructorimage(resultSet.getString("instructorimage"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log error details
        }

        return course;
    }




    // Update an existing course
    public boolean updateCourse(Course course) {
        String query = "UPDATE courses SET title = ?, description = ?, instructor = ?, price = ?, duration = ?, image = ?, instructorimage = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters for the SQL query
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setString(3, course.getInstructor());
            preparedStatement.setDouble(4, course.getPrice());
            preparedStatement.setString(5, course.getDuration());
            preparedStatement.setString(6, course.getImage());
            preparedStatement.setString(7, course.getInstructorimage());
            preparedStatement.setInt(8, course.getId());

            // Execute the update and return true if rows were affected
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            } else {
                // Optionally log that no rows were updated, in case the course ID does not exist
                System.out.println("No course found with the given ID: " + course.getId());
                return false;
            }
        } catch (SQLException e) {
            // Improved error logging with additional context
            System.err.println("Error updating course with ID: " + course.getId());
            e.printStackTrace();
            return false;
        }
    }


    // Delete a course by ID
    public boolean deleteCourse(int id) {
        String query = "DELETE FROM courses WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}

