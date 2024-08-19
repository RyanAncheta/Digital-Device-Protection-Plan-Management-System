<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Claims</title>
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
    <h2>Your Claims</h2>
    
    <c:choose>
        <c:when test="${not empty claims}">
            <table class="claims-table">
                <thead>
                    <tr>
                        <th>Claim ID</th>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="claim" items="${claims}">
                        <tr>
                            <td>${claim.claimId}</td>
                            <td>${claim.dateOfClaim}</td>
                            <td>${claim.description}</td>
                            <td>${claim.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>You have no claims yet.</p>
        </c:otherwise>
    </c:choose>
    
    <a href="<c:url value='/dashboard'/>" class="back-to-dashboard">Back to Dashboard</a>
</body>
</html>
