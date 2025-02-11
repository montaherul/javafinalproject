<%@ page import="java.util.List" %>
<%@ page import="org.course.coursewebapplication.model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View All Users</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        /* View All Users Page */

        /* Main Container */
        .view-users-container {
            padding: 10rem 2rem;
        }

        .view-users-container h2 {
            font-size: 2rem;
            color: var(--primary-color);
            margin-bottom: 2rem;
            text-align: center;
        }

        /* Table Styling */
        .view-users-container .table {
            width: 100%;
            border-collapse: collapse;
            box-shadow: var(--box-shadow);
        }

        .view-users-container .table th,
        .view-users-container .table td {
            padding: 1rem;
            text-align: center;
            border: 1px solid #ddd;
        }

        .view-users-container .table th {
            background-color: var(--primary-color);
            color: white;
            font-weight: 600;
        }

        .view-users-container .table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .view-users-container .table tr:hover {
            background-color: rgba(0, 0, 0, 0.05);
        }

        /* Profile Picture */
        .view-users-container img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            box-shadow: var(--box-shadow);
        }

        /* Action Buttons */
        .view-users-container .btn-sm {
            font-size: 0.9rem;
            padding: 0.5rem 1rem;
        }

        .view-users-container .btn-primary {
            background-color: var(--primary-color);
            border: none;
        }

        .view-users-container .btn-primary:hover {
            background-color: var(--secondary-color);
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

    </style>

</head>
<body>
<header class="header">
    <h1 class="header-title">Manage Users</h1>
    <a href="admin-dashboard.jsp" class="header-link">Back to Dashboard</a>
</header>
<div class="view-users-container">
    <h2 class="my-4">All Users</h2>

    <!-- Table displaying users -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Parent Mobile</th>
            <th>SSC</th>
            <th>Address</th>
            <th>Institution</th>
            <th>Facebook</th>
            <th>Profile Picture</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Fetch the user list from request attribute
            List<User> userList = (List<User>) request.getAttribute("users");

            // Iterate over the user list and display each user
            if (userList != null) {
                for (User user : userList) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getPhone() %></td>
            <td><%= user.getParentMobile() %></td>
            <td><%= user.getHsc() %></td>
            <td><%= user.getAddress() %></td>
            <td><%= user.getInstitution() %></td>
            <td><%= user.getFacebook() %></td>
            <td>
                <!-- Display profile picture -->
                <img src="<%= (user.getProfile_pic() != null && !user.getProfile_pic().isEmpty()) ? user.getProfile_pic() : "uploads/default-profile.png" %>"
                     alt="profile_pic" style="width: 50px; height: 50px; border-radius: 50%;">
            </td>

            <td>
                <!-- Link to edit user -->
                <a href="update-profile?id=<%= user.getId() %>" class="btn btn-primary btn-sm">Edit</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
