<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Claim" %>
<%
    if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Claims</title>
    <link rel="stylesheet" href="css/admin_styles.css">
</head>
<body>
<header>
        <nav>
            <div class="logo">AE Industry</div>
            <div class="login">
                <a href="<c:url value='admin_logout'/>" class="login-button">Log out</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
</header>
    <h1>Admin Claims</h1>
    <table>
        <thead>
            <tr>
                <th>Claim ID</th>
                <th>Reg ID</th>
                <th>Date of Claim</th>
                <th>Description</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="claim" items="${claims}">
                <tr>
                    <td>${claim.claimId}</td>
                    <td>${claim.regId}</td>
                    <td>${claim.dateOfClaim}</td>
                    <td>${claim.description}</td>
                    <td>${claim.status}</td>
                    <td>
                        <form action="admin_claims" method="post">
                            <input type="hidden" name="claimId" value="${claim.claimId}">
                            <select name="status">
                                <option value="Pending" ${claim.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                <option value="Approved" ${claim.status == 'Approved' ? 'selected' : ''}>Approved</option>
                                <option value="Rejected" ${claim.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                            </select>
                            <button type="submit">Update</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="admin_dashboard.jsp">Back to Dashboard</a>
</body>
</html>
