<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Device" %>
<%@ page import="model.Product" %>
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
    <title>Admin Dashboard</title>
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
    <h1>Admin Dashboard</h1>

    <h2>User Accounts</h2>
    <form action="admin_dashboard" method="get">
        <input type="text" name="query" placeholder="Search user..." required>
        <input type="hidden" name="action" value="searchUser">
        <button type="submit">Search</button>
    </form>
    <table>
        <thead>
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td><a href="viewUser?userId=${user.userId}">View</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Registered Products</h2>
    <form action="admin_dashboard" method="get">
        <input type="text" name="query" placeholder="Search product..." required>
        <input type="hidden" name="action" value="searchProduct">
        <button type="submit">Search</button>
    </form>
    <table>
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Serial No</th>
                <th>Purchase Date</th>
                <th>User ID</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="device" items="${devices}">
                <tr>
                    <td>${device.productName}</td>
                    <td>${device.serialNo}</td>
                    <td>${device.purchaseDate}</td>
                    <td>${device.userId}</td>
                    <td>${device.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Manage Products</h2>
    <a href="admin_dashboard?action=manageProducts">Manage Products</a>

    <h2>Manage Claims</h2>
    <a href="admin_claims">View and Manage Claims</a>
    
    <br><br>
    <a href="admin_dashboard?action=generateReport">Generate Full Report</a>
    
</body>
</html>
