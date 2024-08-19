<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="css/styles.css"> 
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
                <a href="<c:url value='/logout'/>" class="login-button">Log In</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
    </header>
    <main>
        <div class="register-container">
            <h2>Register</h2>
            <form action="<c:url value='/register'/>" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${param.username}" required>
                <c:if test="${not empty usernameError}">
                    <div class="error">${usernameError}</div>
                </c:if>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <c:if test="${not empty passwordError}">
                    <div class="error">${passwordError}</div>
                </c:if>

                <label for="first_name">First Name:</label>
                <input type="text" id="first_name" name="first_name" value="${param.first_name}" required>
                <c:if test="${not empty firstNameError}">
                    <div class="error">${firstNameError}</div>
                </c:if>

                <label for="last_name">Last Name:</label>
                <input type="text" id="last_name" name="last_name" value="${param.last_name}" required>
                <c:if test="${not empty lastNameError}">
                    <div class="error">${lastNameError}</div>
                </c:if>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${param.email}" required>
                <c:if test="${not empty emailError}">
                    <div class="error">${emailError}</div>
                </c:if>

                <label for="cellphone">Cellphone:</label>
                <input type="text" id="cellphone" name="cellphone" value="${param.cellphone}" required>
                <c:if test="${not empty cellphoneError}">
                    <div class="error">${cellphoneError}</div>
                </c:if>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${param.address}" required>
                <c:if test="${not empty addressError}">
                    <div class="error">${addressError}</div>
                </c:if>

                <button type="submit">Register</button>
                <p>Already have an account? <a href="<c:url value='/login.jsp'/>">Log in</a></p>
            </form>
        </div>
    </main>
    <style>
        .error {
            color: red;
            margin-top: 5px;
        }
        .register-container {
            max-width: 500px;
            margin: auto;
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .register-container form {
            display: flex;
            flex-direction: column;
        }
        .register-container form label {
            margin-bottom: 5px;
            font-weight: bold;
        }
        .register-container form input {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .register-container form button {
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</body>
</html>
