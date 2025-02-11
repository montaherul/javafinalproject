package org.course.coursewebapplication.dao;
import org.course.coursewebapplication.model.CourseMaterial;
import org.course.coursewebapplication.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseMaterialDAO {

    // Method to get all materials for a specific course
    public List<CourseMaterial> getCourseMaterials(int courseId) {
        List<CourseMaterial> materials = new ArrayList<>();
        String sql = "SELECT * FROM course_material WHERE course_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                CourseMaterial material = new CourseMaterial();
                material.setId(resultSet.getInt("id"));
                material.setCourseId(resultSet.getInt("course_id"));
                material.setMaterialType(resultSet.getString("material_type"));
                material.setContent(resultSet.getString("content"));
                material.setFilePath(resultSet.getString("file_path"));
                material.setDate(resultSet.getString("date"));
                materials.add(material);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    // Add a new course material
    public boolean addCourseMaterial(CourseMaterial material) {
        String query = "INSERT INTO course_material (course_id, material_type, content, date) VALUES (?,?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, material.getId());
            statement.setInt(1, material.getCourseId());
            statement.setString(2, material.getMaterialType());
            statement.setString(3, material.getContent());
            statement.setString(5, material.getDate());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
