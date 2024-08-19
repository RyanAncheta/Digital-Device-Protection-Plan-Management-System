<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
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
    <title>Manage Products</title>
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
    <h1>Manage Products</h1>
    <a href="product_form.jsp">Add New Product</a>
    <table>
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Model</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.productName}</td>
                    <td>${product.model}</td>
                    <td>${product.description}</td>
                    <td><a href="admin_product?action=edit&productId=${product.productId}">Edit</a></td>
                    <td><a href="admin_product?action=delete&productId=${product.productId}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="admin_dashboard.jsp">Back to Dashboard</a>
</body>
</html>

