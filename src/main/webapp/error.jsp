<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty sessionScope.errorNotification}">
    <div class="notification_error">
        ${sessionScope.errorNotification}
    </div>
    <c:remove var="errorNotification" scope="session"/>
</c:if>
