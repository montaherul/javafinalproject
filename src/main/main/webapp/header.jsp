<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.course.coursewebapplication.model.User" %>
<%@ page import="org.course.coursewebapplication.dao.UserDAO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Enrollment System</title>
    <style>
        :root {
            --primary-color: #4A90E2;
            --secondary-color: #5C6BC0;
            --accent-color: #FF5722;
            --text-color: #333;
            --bg-color: #F5F7FA;
            --card-bg: #FFFFFF;
            --error-color: #FF3B30;
            --success-color: #4CD964;
            --gradient-1: linear-gradient(135deg, #6e8efb, #a777e3);
            --gradient-2: linear-gradient(135deg, #84fab0, #8fd3f4);
            --gradient-3: linear-gradient(135deg, #f6d365, #fda085);
            --box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        /* Scoped Header Styling */

        /* Prevent general overlapping by scoping to header-specific classes */
        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 2rem;
            background: var(--gradient-1);
            box-shadow: var(--box-shadow);
        }

        .header-container .logo a {
            font-size: 1.8rem;
            font-weight: 700;
            color: white;
            text-decoration: none;
        }

        .header-container .logo a:hover {
            color: var(--accent-color);
        }

        .header-container nav ul {
            display: flex;
            gap: 1.5rem;
            list-style: none;
            padding: 0;
        }

        .header-container nav li {
            display: inline;
        }

        .header-container nav a {
            font-size: 1rem;
            font-weight: 500;
            color: white;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .header-container nav a:hover {
            color: var(--secondary-color);
        }


    </style>


</head>
<body>
<header>
    <div class="header-container">
        <div class="logo">
            <a href="home.jsp">Course Enrollment System</a>
        </div>
        <nav>
            <ul>
                <li><a href="home.jsp">Home</a></li>

                <!-- Conditional display of Login/Logout -->
                <%
                    String email = (String) session.getAttribute("email");
                    if (email != null) { // User is logged in
                        UserDAO userDao = new UserDAO();
                        User user =userDao.findByEmail(email);  // Retrieve the user from database based on email
                %>
                <li><a href="logout.jsp">Logout</a></li>
                <li><a href="student-dashboard?email=<%= user.getEmail() %>">My Profile</a></li>
                <%
                } else { // User is not logged in
                %>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
                <%
                    }
                %>
            </ul>
        </nav>
    </div>
</header>
</body>
</html>
