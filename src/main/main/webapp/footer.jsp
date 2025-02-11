<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Enrollment System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        /* Social Icons Container */
        .social-icons {
            display: flex;
            justify-content: center;
            gap: 1.5rem;  /* Space between icons */
            margin-top: 2rem;
        }

        /* Social Icons Links */
        .social-icons a {
            display: inline-block;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            overflow: hidden;
            transition: transform 0.3s ease;
        }

        .social-icons a:hover {
            transform: scale(1.1);  /* Slight zoom effect on hover */
        }

        /* Social Icons Images */
        .social-icons img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        /* Container for Follow Us Text and Social Icons */
        .social-icons-container {
            text-align: center;
            margin-bottom: 2rem;
        }

        /* Follow Us On Container */
        .social-icons-container {
            text-align: center;
            margin-bottom: 2rem;
        }

        /* Follow Us On Text with Border */
        .follow-us-text {
            font-size: 1.5rem;
            font-weight: 600;
            color: gold;
            margin-bottom: 1rem;
            padding: 0.5rem 1rem;
            border: 2px solid #FFD700;  /* Gold-colored border */
            border-radius: 8px;
            display: inline-block;
            animation: fadeInSlideDown 1s ease-out forwards;
            opacity: 0;
        }

        /* Animation for Text */
        @keyframes fadeInSlideDown {
            0% {
                opacity: 0;
                transform: translateY(-20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }


    </style>
</head>
<body>
<!-- Footer -->
<footer>

    <div class="social-icons-container">
        <p class="follow-us-text">Follow Us On</p>
        <div class="social-icons">
            <a href="#" target="_blank">
                <img src="uploads/facebook_logo.png" alt="Facebook">
            </a>
            <a href="#" target="_blank">
                <img src="uploads/tweeter.png" alt="Twitter">
            </a>
            <a href="#" target="_blank">
                <img src="uploads/instragram.png" alt="Instagram">
            </a>
            <a href="#" target="_blank">
                <img src="uploads/linkedin-icon-2048x2048-ya5g47j2.png" alt="LinkedIn">
            </a>
        </div>
    </div>


    <p>&copy; 2025 Course Enrollment System. All Rights Reserved.</p>
</footer>

</body>
</html>
