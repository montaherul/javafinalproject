<!DOCTYPE html>
<html>
<head>
    <title>Course Management System</title>
    <link href="<%= request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<h1>Welcome to the Course Management System</h1>

<!-- Navigation Links -->
<h2>Navigation</h2>
<ul>
    <!-- Courses Section -->
    <li><a href="<%= request.getContextPath() %>/student-dashboard.jsp">View Courses</a></li>

    <!-- Admin Section -->
    <li><a href="<%= request.getContextPath() %>/admin-dashboard.jsp">Admin Dashboard</a></li>
    <li><a href="<%= request.getContextPath() %>/courses?action=list">Manage Courses</a></li>
    <li><a href="<%= request.getContextPath() %>/manage-enrollments.jsp">Manage Enrollments</a></li>
    <li> <a href="<%=request.getContextPath() %>/courses.jsp"> show course </a></li>

    <!-- Student Section -->
    <li><a href="<%= request.getContextPath() %>/student-dashboard.jsp">Student Dashboard</a></li>
    <li><a href="<%= request.getContextPath() %>/my-courses.jsp">My Courses</a></li>
    <li><a href="<%= request.getContextPath() %>/course-details.jsp">Course Details</a></li>

    <!-- Authentication -->
    <li> <a href="<%= request.getContextPath()%>/addcoursematerial.jsp" >add course material </li>
    <li><a href="<%= request.getContextPath() %>/login.jsp">Login</a></li>
    <li><a href="<%= request.getContextPath() %>/register.jsp">Register</a></li>
    <li><a href="<%= request.getContextPath() %>/forgot-password.jsp">Forgot Password</a></li>
</ul>

<!-- Static Pages -->
<h2>Other Links</h2>
<ul>
    <li><a href="<%= request.getContextPath() %>/home.jsp">Home Page</a></li>
    <li><a href="<%= request.getContextPath() %>/error.jsp">Error Page</a></li>
</ul>

<script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
</body>
</html>
