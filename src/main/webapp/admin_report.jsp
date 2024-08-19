<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Device" %>
<%@ page import="model.Claim" %>
<%
        if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Report</title>
    <link rel="stylesheet" href="css/admin_styles.css">
</head>
<body>
<header>
        <nav>
            <div class="logo">AE Industry</div>
            <div class="login">
                  <a href="<c:url value='admin_logout'/>" class="login-button">Log out</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
        </header>
    <h1>Admin Report</h1>

    <h2>Users, Devices, and Claims</h2>
    <c:forEach var="user" items="${users}">
        <h3>User: ${user.username} (${user.email})</h3>
        <h4>Devices:</h4>
        <ul>
            <c:forEach var="device" items="${userDevices[user.userId]}">
                <li>${device.productName} (Serial No: ${device.serialNo}, Purchase Date: ${device.purchaseDate})</li>
            </c:forEach>
        </ul>
        <h4>Claims:</h4>
        <ul>
            <c:forEach var="device" items="${userDevices[user.userId]}">
                <c:forEach var="claim" items="${userClaims[device.regId]}">
                    <li>${claim.dateOfClaim}: ${claim.description} (${claim.status})</li>
                </c:forEach>
            </c:forEach>
        </ul>
    </c:forEach>
    <a href="admin_dashboard.jsp">Back to Dashboard</a>
</body>
</html>
