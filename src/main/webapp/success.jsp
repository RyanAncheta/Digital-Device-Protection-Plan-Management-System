<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty sessionScope.successNotification}">
    <div class="notification_success">
        ${sessionScope.successNotification}
    </div>
    <c:remove var="successNotification" scope="session"/>
</c:if>


