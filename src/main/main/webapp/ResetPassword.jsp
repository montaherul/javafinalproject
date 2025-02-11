<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 1/5/2025
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        /* Reset Password Styling */
        .reset-password-container {
            padding: 4rem 2rem;
            background-color: var(--bg-color);
            border-radius: 10px;
            box-shadow: var(--box-shadow);
            max-width: 400px;
            margin: 8rem auto;
            text-align: center;
        }

        .reset-title {
            font-size: 2rem;
            margin-bottom: 1.5rem;
            color: var(--primary-color);
        }

        .reset-password-form .form-group {
            margin-bottom: 1.5rem;
        }

        .reset-password-form .form-label {
            font-weight: 600;
            color: var(--secondary-color);
            margin-bottom: 0.5rem;
        }

        .reset-password-form .form-control {
            width: 100%;
            padding: 0.8rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: var(--box-shadow);
        }

        .reset-password-form .btn-primary {
            background-color: var(--primary-color);
            color: white;
            padding: 0.8rem 1.5rem;
            border-radius: 5px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .reset-password-form .btn-primary:hover {
            background-color: var(--secondary-color);
        }

        .reset-message {
            margin-top: 1rem;
            color: var(--error-color); /* Adjust this if you have different messages */
        }

    </style>

</head>
<body>
<div class="container reset-password-container">
    <h2 class="reset-title">Reset Password</h2>
    <form action="resetPassword" method="post" class="reset-password-form">
        <div class="form-group">
            <label for="newPassword" class="form-label">Enter New Password</label>
            <input type="password" id="newPassword" name="newPassword" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Reset Password</button>
    </form>

    <p class="reset-message">${message}</p>
</div>

<%@include file="footer.jsp"%>
</body>

</html>
