<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Account Creation</title>
    <link rel="stylesheet" href="css/admin_styles.css">
</head>
<body>
<header>
        <nav>
            <div class="logo">AE Industry</div>
            <div class="login">
                <a href="<c:url value='/login.jsp'/>" class="login-button">Log In</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
        </header>
    <h2>Create Admin Account</h2>
    <form action="adminCreation" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        
        <label for="token">Secret Token:</label>
        <input type="password" id="token" name="token" required><br>
        
        <input type="submit" value="Create Account">
    </form>
    
    <p>${message}</p>
</body>
</html>
