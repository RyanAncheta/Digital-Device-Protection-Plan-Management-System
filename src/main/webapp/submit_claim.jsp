<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submit Claim</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
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
                <a href="<c:url value='/logout'/>" class="login-button">Log Out</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
 </header>
    <h1>Submit Claim for Device</h1>
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
    <form action="submitClaim" method="post">
        <input type="hidden" name="regId" value="${param.regId}">
        <label for="dateOfClaim">Date of Claim:</label>
        <input type="date" id="dateOfClaim" name="dateOfClaim" required>
        <br>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea>
        <br>
        <button type="submit">Submit Claim</button>
    </form>
   <a href="<c:url value='/dashboard'/>" class="back-to-dashboard">Back to Dashboard</a>
</body>
</html>
