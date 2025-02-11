<%@ page import="java.util.List" %>
<%@ page import="org.course.coursewebapplication.model.Enrollment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Enrollments</title>

    <style>
        :root {
            --primary-color: #7c4caf;
            --secondary-color: #388E3C;
            --accent-color: #FFC107;
            --error-color: #f44336;
            --success-color: #4CAF50;
            --card-bg: #ffffff;
            --box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: var(--primary-color);
            color: white;
            padding: 1rem;
            text-align: center;
            box-shadow: var(--box-shadow);
        }

        .header-title {
            font-size: 1.5rem;
            font-weight: bold;
            margin: 0;
        }

        .header-link {
            color: white;
            text-decoration: none;
            font-weight: bold;
            margin-top: 0.5rem;
            display: inline-block;
        }

        .header-link:hover {
            color: var(--accent-color);
        }

        .admin-content {
            margin: 2rem auto;
            max-width: 1200px;
            padding: 1rem;
        }

        .content-title {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 1.5rem;
            font-size: 1.8rem;
        }

        .content-table {
            width: 100%;
            border-collapse: collapse;
            background-color: var(--card-bg);
            border-radius: 8px;
            overflow: hidden;
            box-shadow: var(--box-shadow);
        }

        .content-table th, .content-table td {
            padding: 1rem;
            border: 1px solid #ddd;
            text-align: center;
        }

        .content-table th {
            background-color: var(--primary-color);
            color: white;
            font-weight: bold;
        }

        .content-table tbody tr:hover {
            background-color: #f1f1f1;
        }

        .no-enrollments {
            text-align: center;
            color: var(--error-color);
            font-weight: bold;
        }

        .action-link {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: bold;
        }

        .action-link:hover {
            color: var(--secondary-color);
        }

        @media (max-width: 768px) {
            .content-table th, .content-table td {
                font-size: 0.85rem;
                padding: 0.5rem;
            }

            .header-title {
                font-size: 1.2rem;
            }

            .action-link {
                font-size: 0.85rem;
            }
        }

    </style>
</head>
<body>
<header class="header">
    <h1 class="header-title">Manage Enrollments</h1>
    <a href="admin-dashboard.jsp" class="header-link">Back to Dashboard</a>
</header>
<div class="admin-content">
    <h2 class="content-title">Enrollment List</h2>
    <table class="content-table">
        <thead>
        <tr>
            <th>Enrollment ID</th>
            <th>Student ID</th>
            <th>Course ID</th>
            <th>Date of Enrollment</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Enrollment> enrollments = (List<Enrollment>) request.getAttribute("enrollment");

            if (enrollments != null && !enrollments.isEmpty()) {
                for (Enrollment enrollment : enrollments) {
        %>
        <tr>
            <td><%= enrollment.getId() %></td>
            <td><%= enrollment.getUser_id() %></td>
            <td><%= enrollment.getCourse_id() %></td>
            <td><%= enrollment.getEnrollment_date() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/manage-enrollments?action=view&id=<%=enrollment.getId()%>" class="action-link">View</a> |
                <a href="${pageContext.request.contextPath}/manage-enrollments?action=cancel&id=<%=enrollment.getId()%>" class="action-link">Cancel</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5" class="no-enrollments">No enrollments found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>


</html>
