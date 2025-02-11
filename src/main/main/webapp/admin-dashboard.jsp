<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        /* Admin Dashboard */
        .admin-dashboard {
            padding-top: 6rem;
        }

        .admin-header {
            background: linear-gradient(45deg, #007bff, #6610f2);
            color: white;
            padding: 2rem 0;
            text-align: center;
        }

        .admin-header h1 {
            font-size: 2rem;
        }

        .admin-content {
            padding: 2rem;
        }

        .admin-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 2rem;
        }

        .admin-card {
            background-color: #f8f9fa;
            border-radius: 10px;
            padding: 1.5rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .admin-card h3 {
            font-size: 1.2rem;
            color: #007bff;
            margin-bottom: 1rem;
        }

        .admin-card p {
            font-size: 1rem;
            margin: 0.5rem 0;
        }

        .admin-action-link {
            display: inline-block;
            padding: 0.5rem 1rem;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .admin-action-link:hover {
            background-color: #6610f2;
        }

        /* Responsive Design */
        @media (max-width: 1024px) {
            .courses-grid {
                grid-template-columns: repeat(2, 1fr);
            }

            .course-details-info {
                flex-direction: column;
            }

            .course-details-sidebar {
                width: 100%;
            }
        }

        @media (max-width: 768px) {
            .hero h1 {
                font-size: 2.5rem;
            }

            .hero p {
                font-size: 1rem;
            }

            .courses-grid {
                grid-template-columns: 1fr;
            }

            .about-content {
                flex-direction: column;
            }

            .footer-content {
                flex-direction: column;
            }

            .admin-grid {
                grid-template-columns: 1fr;
            }
        }

        @media (max-width: 480px) {
            .nav-links {
                display: none;
            }

            .auth-form-container {
                padding: 1.5rem;
            }

            .course-details-header h1 {
                font-size: 2rem;
            }
        }
    </style>
</head>
<body>
<!-- Header -->
<header class="admin-header">
    <h1>Welcome to Admin Dashboard</h1>
</header>

<!-- Main Dashboard -->
<div class="admin-dashboard">
    <div class="admin-content">
        <!-- Admin Cards -->
        <div class="admin-grid">
            <div class="admin-card">
                <h3>Manage Courses</h3>
                <p>View, edit, and delete courses.</p>
                <a href="${pageContext.request.contextPath}/courses?action=list" class="admin-action-link">Go to Courses</a>
            </div>
            <div class="admin-card">
            <h3>Courses</h3>
            <p>view only courses</p>
            <a href="${pageContext.request.contextPath}/courses.jsp" class="admin-action-link">Show All Courses</a>
           </div>
            <div class="admin-card">
                <h3>Manage Users</h3>
                <p>View and manage user accounts.</p>
                <a href="${pageContext.request.contextPath}/view-user" class="admin-action-link">Go to Users</a>
            </div>
            <div class="admin-card">
                <h3>Manage Enrollments</h3>
                <p>Track and manage enrollments.</p>
                <a href="${pageContext.request.contextPath}/viewallenrollment" class="admin-action-link">Go to Enrollments</a>
            </div>
        </div>

        <form action="confirmPayment" method="post">
            <label for="txnId">Enter Transaction ID:</label>
            <input type="text" id="txnId" name="txnId" required>
            <button type="submit">Confirm Payment</button>
        </form>

        <!-- Success/Error Messages -->
        <% if (request.getParameter("success") != null) { %>
        <p style="color: green;"><%= request.getParameter("success") %></p>
        <% } else if (request.getParameter("error") != null) { %>
        <p style="color: red;"><%= request.getParameter("error") %></p>
        <% } %>


    </div>
</div>
</body>
</html>
