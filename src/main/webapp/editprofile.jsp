<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.course.coursewebapplication.model.User" %>
<%@include file="header.jsp"%>
<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
        user = new User(); // Create an empty user object for initial load
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <!-- Link to your CSS file -->
    <link rel="stylesheet" href="css/styles.css">
    <style>

        /* Edit Profile Page Styling */
        .edit-profile-container {
            padding: 10rem 2rem;
            background-color: var(--bg-color);
            text-align: center;
        }

        .profile-title {
            font-size: 2rem;
            color: var(--primary-color);
            margin-bottom: 2rem;
        }

        .edit-profile-form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
            max-width: 500px;
            margin: 0 auto;
            padding: 2rem;
            background-color: var(--card-bg);
            border-radius: 10px;
            box-shadow: var(--box-shadow);
        }

        .form-group label {
            font-weight: 600;
            color: var(--secondary-color);
            margin-bottom: 0.5rem;
        }

        .form-group input[type="text"],
        .form-group input[type="email"],
        .form-group input[type="password"],
        .form-group textarea {
            width: 100%;
            padding: 0.8rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: var(--box-shadow);
            transition: box-shadow 0.3s ease, border-color 0.3s ease;
        }

        .form-group input:focus,
        .form-group textarea:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .btn-submit {
            background-color: var(--primary-color);
            color: white;
            padding: 0.8rem 1.5rem;
            border: none;
            border-radius: 5px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-submit:hover {
            background-color: var(--secondary-color);
        }

    </style>
</head>
<body>
<div class="edit-profile-container">
    <h1 class="profile-title">Edit Profile</h1>
    <form action="update-profile" method="post" enctype="multipart/form-data" class="edit-profile-form">
        <input type="hidden" name="id" value="<%=user.getId()%>">

        <div class="form-group">
            <label>Name</label>
            <label>
                <input type="text" name="name" value="<%=user.getName()%>" placeholder="Name">
            </label>
        </div>

        <div class="form-group">
            <label>Email</label>
            <label>
                <input type="email" name="email" value="<%=user.getEmail()%>" placeholder="Email">
            </label>
        </div>

        <div class="form-group">
            <label>Password</label>
            <label>
                <input type="password" name="password" value="<%=user.getPassword()%>" placeholder="Password">
            </label>
        </div>

        <div class="form-group">
            <label>SSC</label>
            <label>
                <input type="text" name="hsc" value="<%=user.getHsc()%>" placeholder="HSC">
            </label>
        </div>

        <div class="form-group">
            <label>Phone</label>
            <label>
                <input type="text" name="phone" value="<%=user.getPhone()%>" placeholder="Phone">
            </label>
        </div>

        <div class="form-group">
            <label>Parent Mobile</label>
            <label>
                <input type="text" name="parentMobile" value="<%=user.getParentMobile()%>" placeholder="Parent Mobile">
            </label>
        </div>

        <div class="form-group">
            <label>Institution</label>
            <label>
                <input type="text" name="institution" value="<%=user.getInstitution()%>" placeholder="Institution">
            </label>
        </div>

        <div class="form-group">
            <label>Facebook</label>
            <label>
                <input type="text" name="facebook" value="<%=user.getFacebook()%>" placeholder="Facebook">
            </label>
        </div>

        <div class="form-group">
            <label>Address</label>
            <label>
                <textarea name="address" placeholder="Address"><%=user.getAddress()%></textarea>
            </label>
        </div>

        <div class="form-group">
            <label for="profile_pic">Profile Picture</label>
            <input type="file" name="profile_pic" id="profile_pic">
        </div>

        <button type="submit" class="btn-submit">Update Profile</button>
    </form>
</div>
<%@include file="footer.jsp"%>>
</body>
</html>
