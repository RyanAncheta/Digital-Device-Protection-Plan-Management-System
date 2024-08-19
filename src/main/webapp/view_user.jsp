<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View User Details</title>
    <link rel="stylesheet" href="css/admin_styles.css">
</head>
<body>
    <h2>User Details</h2>
    <table>
        <tr>
            <th>User ID:</th>
            <td>${user.userId}</td>
        </tr>
        <tr>
            <th>Username:</th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th>Email:</th>
            <td>${user.email}</td>
        </tr>
    </table>
    <a href="admin_dashboard.jsp">Back to Dashboard</a>
</body>
</html>
