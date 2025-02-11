package org.course.coursewebapplication;

import org.course.coursewebapplication.dao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentDAOTest {
    private PaymentDAO paymentDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {
        paymentDAO = new PaymentDAO();
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Mock database connection
        Mockito.mockStatic(DatabaseConnection.class);
        when(DatabaseConnection.getConnection()).thenReturn(mockConnection);
    }

    @Test
    void testConfirmPayment_Success() throws Exception {
        String txnId = "123";

        // Simulate existing transaction
        when(mockConnection.prepareStatement("SELECT status FROM payments WHERE txn_id = ?")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("status")).thenReturn("Pending");

        // Simulate update query
        PreparedStatement mockUpdateStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement("UPDATE payments SET status = 'Confirmed' WHERE txn_id = ?")).thenReturn(mockUpdateStatement);
        when(mockUpdateStatement.executeUpdate()).thenReturn(1); // 1 row updated

        boolean result = paymentDAO.confirmPayment(txnId);

        assertTrue(result, "Payment should be confirmed successfully.");
    }

    @Test
    void testConfirmPayment_Failure_TxnNotFound() throws Exception {
        String txnId = "invalid_txn";

        // Simulate no matching transaction
        when(mockConnection.prepareStatement("SELECT status FROM payments WHERE txn_id = ?")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // No result found

        boolean result = paymentDAO.confirmPayment(txnId);

        assertFalse(result, "Payment confirmation should fail if transaction is not found.");
    }

    @Test
    void testConfirmPayment_Failure_UpdateFails() throws Exception {
        String txnId = "123";

        // Simulate existing transaction
        when(mockConnection.prepareStatement("SELECT status FROM payments WHERE txn_id = ?")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("status")).thenReturn("Pending");

        // Simulate update failure
        PreparedStatement mockUpdateStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement("UPDATE payments SET status = 'Confirmed' WHERE txn_id = ?")).thenReturn(mockUpdateStatement);
        when(mockUpdateStatement.executeUpdate()).thenReturn(0); // No rows updated

        boolean result = paymentDAO.confirmPayment(txnId);

        assertFalse(result, "Payment confirmation should fail if update does not change any rows.");
    }
}
