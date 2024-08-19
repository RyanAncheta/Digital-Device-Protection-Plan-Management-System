<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Product Form</title>
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
    <h1>${product == null ? "Add New Product" : "Edit Product"}</h1>
    <form action="admin_product" method="post">
        <input type="hidden" name="action" value="${product == null ? 'insert' : 'update'}">
        <c:if test="${product != null}">
            <input type="hidden" name="productId" value="${product.productId}">
        </c:if>
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" value="${product.productName}" required>
        <br>
        <label for="model">Model:</label>
        <input type="text" id="model" name="model" value="${product.model}" required>
        <br>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${product.description}</textarea>
        <br>
        <button type="submit">Save</button>
    </form>
    <a href="admin_dashboard.jsp">Back to Dashboard</a>
</body>
</html>
