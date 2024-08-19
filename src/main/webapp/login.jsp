<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="success.jsp" %>
<%@ include file="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - AE Industry</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>"> 
</head>
<body>
    <header>
        <nav>
            <div class="logo">AE Industry</div>
            <ul class="nav-links">
                <li><a href="<c:url value='/'/>">Home</a></li>
                <li><a href="<c:url value='/claims'/>">Claims</a></li>
                <li><a href="<c:url value='/contact'/>">Contact Us</a></li>
            </ul>
            <div class="login">
                <a href="<c:url value='/admin_login.jsp'/>" class="login-button">Admin Log In</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
    </header>
    <div class="login-container">
        <div class="login-blob"></div>
        <div class="login-form">
            <h2>Login</h2>
            <p>Sign in to continue</p>
            <form action="<c:url value='/login'/>" method="post">
                <label for="username">&nbsp;&nbsp;&nbsp;Username:</label>
                <input type="text" id="username" name="username" required>
                
                <label for="password">&nbsp;&nbsp;&nbsp;Password:</label>
                <input type="password" id="password" name="password" required>
                
                <button type="submit">Log In</button>
                <c:if test="${not empty errorMessage}">
                <p style="color:red">${errorMessage}</p>
            </c:if>
            </form>
            <p>Don't have an account? <a href="<c:url value='/register.jsp'/>">Register Now</a></p>
        </div>
    </div>
</body>
</html>
