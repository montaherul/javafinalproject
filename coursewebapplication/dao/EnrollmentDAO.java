package org.course.coursewebapplication.dao;

import org.course.coursewebapplication.model.Course;
import org.course.coursewebapplication.model.Enrollment;
import org.course.coursewebapplication.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String query = "SELECT * FROM enrollment"; // Table name changed to plural for consistency

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(resultSet.getInt("id"));
                enrollment.setUser_id(resultSet.getInt("user_id")); // Follow Java naming conventions
                enrollment.setCourse_id(resultSet.getInt("course_id"));
                enrollment.setEnrollment_date(resultSet.getDate("enrollment_date"));
                enrollments.add(enrollment);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log exception; consider logging framework for better debugging
        }
        return enrollments;
    }


    // Method to enroll a student in a course
    public boolean enrollStudent(int userId, int courseId) {
        String query = "INSERT INTO enrollment (user_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get enrollments by user ID
    public List<Enrollment> getEnrollmentsByUserId(int userId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String query = "SELECT * FROM enrollment WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Enrollment enrollment = new Enrollment();
                    enrollment.setId(resultSet.getInt("id"));
                    enrollment.setUser_id(resultSet.getInt("user_id"));
                    enrollment.setCourse_id(resultSet.getInt("course_id"));
                    enrollment.setEnrollment_date(resultSet.getDate("enrollment_date"));
                    enrollments.add(enrollment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
    public Enrollment getEnrollmentById(int enrollmentId) {
        String query = "SELECT * FROM enrollment WHERE id = ?";
        Enrollment enrollment = null;

        // Create a connection to the database
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the enrollment ID parameter
            preparedStatement.setInt(1, enrollmentId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    enrollment = new Enrollment();
                    enrollment.setId(resultSet.getInt("id"));
                    enrollment.setUser_id(resultSet.getInt("user_id"));
                    enrollment.setCourse_id(resultSet.getInt("course_id"));
                    enrollment.setEnrollment_date(resultSet.getDate("enrollment_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        }

        return enrollment; // Return the enrollment object or null if not found
    }


    // Method to save an enrollment object
    // Method to save an enrollment object
    public boolean save(Enrollment enrollment) {
        String query = "INSERT INTO enrollment (user_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, enrollment.getUser_id());
            statement.setInt(2, enrollment.getCourse_id());
            statement.setDate(3, new java.sql.Date(enrollment.getEnrollment_date().getTime()));

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean enrollUserInCourse(String email, int courseId) {
        String query = """
                INSERT INTO enrollment (user_id, course_id, enrollment_date)
                SELECT u.id, ?, CURRENT_DATE()
                FROM user u
                WHERE u.email = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the course_id and email
            statement.setInt(1, courseId);
            statement.setString(2, email);

            // Execute the update
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isAlreadyEnrolled(String email, int courseId) {
        String query = """
            SELECT COUNT(*) 
            FROM enrollment e
            JOIN user u ON e.user_id = u.id
            WHERE e.course_id = ? AND u.email = ?
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters for the query
            statement.setInt(1, courseId);
            statement.setString(2, email);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Return true if count is greater than 0
            return resultSet.next() && resultSet.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean doesCourseExist(int courseId) {
        String query = "SELECT COUNT(*) FROM courses WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete an enrollment by ID
    public boolean delete(int enrollmentId) {
        String query = "DELETE FROM enrollment WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, enrollmentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get all courses a user is enrolled in
    public List<Course> getEnrolledCourses(int userId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.id, c.title, c.description, c.instructor " +
                "FROM enrollment e " +
                "JOIN courses c ON e.course_id = c.id " +
                "WHERE e.user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    course.setId(resultSet.getInt("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setDescription(resultSet.getString("description"));
                    course.setInstructor(resultSet.getString("instructor"));
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Method to get the last enrollment ID
    public int getLastEnrollmentId() {
        String query = "SELECT MAX(id) FROM enrollment";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Returns the maximum (last) ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if no records exist or query fails
    }

    // Method to get a list of enrollments by user ID (simplified version of getEnrollmentsByUserId)
    public List<Enrollment> findByUserId(int userId) {
        return getEnrollmentsByUserId(userId); // Reuses the same method as above
    }

    private static final String CHECK_ENROLLMENT_QUERY =
            "SELECT COUNT(*) FROM enrollment WHERE user_id = ? AND course_id = ?";

    public boolean isUserEnrolled(int userId, String courseId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_ENROLLMENT_QUERY)) {
            statement.setInt(1, userId);
            statement.setString(2, courseId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Return true if count > 0
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
