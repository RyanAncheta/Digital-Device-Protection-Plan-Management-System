<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Claim" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    int regId = Integer.parseInt(request.getParameter("regId"));
    System.out.println("Debug: regId in view_claim_status.jsp: " + regId);

    Map<Integer, List<Claim>> claimsMap = (Map<Integer, List<Claim>>) session.getAttribute("claimsMap");

    if (claimsMap == null) {
        System.out.println("claimsMap is null in view_claim_status.jsp");
    } else {
        List<Claim> claims = claimsMap.get(regId);
        System.out.println("Debug: claims for regId " + regId + ": " + claims);

        request.setAttribute("claims", claims);
    }
%>

<html>
<head>
    <title>Claim Status</title>
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
    <div class="claim-status-container">
        <h2 class="claim-status-header">Claim Status for Device</h2>
        <a href="<c:url value='/dashboard'/>" class="back-to-dashboard">Back to Dashboard</a>

        <c:choose>
            <c:when test="${not empty claims}">
                <table class="claim-status-table">
                    <thead>
                        <tr>
                            <th>Claim ID</th>
                            <th>Date of Claim</th>
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
                <p>No claims found for this device.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
