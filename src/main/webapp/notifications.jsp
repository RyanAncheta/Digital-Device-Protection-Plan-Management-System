<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Notifications</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h2>Notifications (${unreadCount} unread)</h2>
    <c:forEach var="notification" items="${notifications}">
        <div class="notification">
            <c:choose>
                <c:when test="${notification.read}">
                    <div class="notification read">
                </c:when>
                <c:otherwise>
                    <div class="notification unread">
                </c:otherwise>
            </c:choose>
                <p>${notification.message}</p>
                <form action="notifications" method="post">
                    <input type="hidden" name="notificationId" value="${notification.id}">
                    <button type="submit">Mark as Read</button>
                </form>
            </div>
        </div>
    </c:forEach>
   <a href="<c:url value='/dashboard'/>" class="back-to-dashboard">Back to Dashboard</a>
</body>
</html>
