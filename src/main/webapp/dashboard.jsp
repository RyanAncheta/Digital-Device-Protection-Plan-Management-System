<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="success.jsp" %>
<%@ include file="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script>
    function confirmDeletion(regId) {
        if (confirm("Are you sure you want to delete this product? All associated claims will be removed.")) {
            window.location.href = "deleteProduct?regId=" + regId;
        }
    }
</script>
    <style>
    body {
        overflow: hidden;
    }
    
    .success-message {
        color: green;
        padding: 10px;
        background-color: #e0f2e9;
        border: 1px solid green;
        margin-bottom: 15px;
    }

    .error-message {
        color: red;
        padding: 10px;
        background-color: #fdecea;
        border: 1px solid red;
        margin-bottom: 15px;
    }
    </style>
    <title>Dashboard</title>
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
            <span class="notification-icon">
                <a href="notifications">Notifications (${unreadCount})&nbsp;&nbsp;&nbsp;</a>
            </span>
                <a href="<c:url value='/logout'/>" class="login-button">Log Out</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
 </header>
    <div class="dashboard-container">
        <div class="sidebar">
            <h2>User Profile</h2>
            <ul>
                <li><a href="#">User</a></li>
                <li><a href="#">Edit Profile</a></li>
                <li><a href="<c:url value='/RegisterProductServlet'/>" class="register-device-button">Register Device</a></li>
            </ul>
        </div>
        <div class="main-content">
            <h1>DASHBOARD</h1>

            <c:if test="${not empty error}">
                <div id="errorMessage" class="error-message">${error}</div>
            </c:if>
            <c:if test="${not empty success}">
                <div id="successMessage" class="success-message">${success}</div>
            </c:if>

            <h2>Devices:</h2>
            <c:choose>
                <c:when test="${not empty devices}">
                    <c:forEach var="device" items="${devices}">
                        <div class="device">
                            <h3>${device.productName}</h3>
                            <p>Serial No: ${device.serialNo}</p>
                            <p>Purchase Date: ${device.purchaseDate}</p>
                            <p>Description: ${device.description}</p>
                            <c:choose>
                                <c:when test="${fn:length(claimsMap[device.regId]) > 0}">
                                    <c:forEach var="claim" items="${claimsMap[device.regId]}">
                                        <c:if test="${claim.status == 'Pending'}">
                                            <a href="view_claim_status.jsp?regId=${device.regId}" class="view-claim-status-button">View Claim Status</a>
                                            <c:remove var="showApplyForClaim"/>
                                        </c:if>
                                        <c:if test="${claim.status == 'Rejected' || claim.status == 'Approved'}">
                                            <c:set var="showApplyForClaim" value="true"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${showApplyForClaim == 'true' && fn:length(claimsMap[device.regId]) < 3}">
                                        <a href="submit_claim.jsp?regId=${device.regId}" class="apply-claim-button">Apply for Claim</a>
                                    </c:if>
                                    <c:if test="${fn:length(claimsMap[device.regId]) >= 3}">
                                        <p class="not-eligible">This device has reached the maximum number of claims (3).</p>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <a href="submit_claim.jsp?regId=${device.regId}" class="apply-claim-button">Apply for Claim</a>
                                </c:otherwise>
                            </c:choose>
                            <form action="deleteProduct" method="post">
                            <input type="hidden" name="regId" value="${device.regId}">
                            <button type="button" class="delete-button" onclick="confirmDeletion(${device.regId})">Delete</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No devices registered yet.</p>
                   <a href="<c:url value='/RegisterProductServlet'/>" class="register-device-button">Register Device</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
<script>
    setTimeout(function() {
        var successMessage = document.getElementById("successMessage");
        var errorMessage = document.getElementById("errorMessage");
        if (successMessage) {
            successMessage.style.display = "none";
        }
        if (errorMessage) {
            errorMessage.style.display = "none";
        }
    }, 5000); 
</script>
</html>
