<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="success.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <link rel="stylesheet" href="css/admin_styles.css">
</head>
<body>
<header>
    <nav>
        <div class="logo">AE Industry</div>
        <div class="login">
            <a href="<c:url value='/login.jsp'/>" class="login-button">User Log In</a>
            <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
        </div>
    </nav>
</header>
<h2>Admin Login</h2>

<!-- Display success message -->
<c:if test="${not empty sessionScope.success}">
    <div class="success-message" id="successMessage">
        ${sessionScope.success}
    </div>
</c:if>

<form action="adminLogin" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="submit" value="Login">
    <br><br>
    <a href="admin_creation.jsp">Create new Admin</a>
</form>

<script>
    // Remove the success message after 5 seconds
    setTimeout(function() {
        var successMessage = document.getElementById("successMessage");
        if (successMessage) {
            successMessage.style.display = "none";
            <% session.removeAttribute("success"); %>
        }
    }, 5000); // 5000 milliseconds = 5 seconds
</script>

<p>${message}</p>
</body>
</html>
