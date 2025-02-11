<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 2/6/2025
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<h2>Scan the QR Code to Pay</h2>
<img src="uploads/bkashqrcode.png" alt="bKash payment QR Code" height="300" width="300">

<form action="verifyPayment" method="POST">
    <input type="hidden" name="email" value="<%= request.getAttribute("email") %>">
    <input type="hidden" name="course_id" value="<%= request.getAttribute("courseId") %>">

    <label>Enter bKash Transaction ID:</label>
    <input type="text" name="txnId" required>

    <button type="submit">Submit Payment</button>
</form>
</body>
</html>
