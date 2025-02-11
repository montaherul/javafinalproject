package org.course.coursewebapplication.dao;

import org.course.coursewebapplication.DatabaseConnection;
import org.course.coursewebapplication.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {
    public boolean savePayment(Payment payment) {
        String sql = "INSERT INTO payments (email, course_id, txn_id, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, payment.getEmail());
            stmt.setInt(2, payment.getCourseId());
            stmt.setString(3, payment.getTxnId());
            stmt.setString(4, payment.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPaymentExists(String txnId) {
        String sql = "SELECT id FROM payments WHERE txn_id = ?";
        try (Connection conn =DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, txnId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getPaymentStatus(String txnId) {
        String query = "SELECT status FROM payments WHERE txn_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, txnId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");  // Returns the payment status
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if txnId not found or an error occurs
    }
    public boolean confirmPayment(String txnId) {
        System.out.println("Checking Transaction ID: " + txnId); // Debugging

        String checkQuery = "SELECT status FROM payments WHERE txn_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(checkQuery)) {
            ps.setString(1, txnId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Transaction ID not found in the database.");
                    return false;
                } else {
                    System.out.println("Current status: " + rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String updateQuery = "UPDATE payments SET status = 'Confirmed' WHERE txn_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, txnId);
            int rowsUpdated = ps.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated); // Debugging
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
