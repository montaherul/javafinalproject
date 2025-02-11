package org.course.coursewebapplication.controller;
import java.io.IOException;
import org.course.coursewebapplication.dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/resetPassword")
public class ResetPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        HttpSession session = request.getSession();

        if (session.getAttribute("authenticated") != null) {
            String email = (String) session.getAttribute("email");

            // Update the password in the database
            UserDAO userDAO = new UserDAO();
            boolean passwordUpdated = userDAO.updatePassword(email, newPassword);

            if (passwordUpdated) {
                request.setAttribute("message", "Password updated successfully.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "Failed to update password. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("ResetPassword.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("forgotPassword.jsp");
        }
    }
}
