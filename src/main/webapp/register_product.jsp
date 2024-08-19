<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Product</title>
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
                <a href="<c:url value='/logout'/>" class="login-button">Log Out</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
    </header>
    <main>
        <div class="register-product-container">
            <h2>Register Your Product</h2>
            <form action="RegisterProductServlet" method="post">
                <label for="productName">Product Name:</label>
                <select id="productName" name="productName" required>
                    <option value="">Select a product</option>
                    <c:forEach var="product" items="${products}">
                        <option value="${product.productName}">${product.productName}</option>
                    </c:forEach>
                </select>

                <label for="serialNo">Serial Number:</label>
                <input type="text" id="serialNo" name="serialNo" required>

                <label for="purchaseDate">Purchase Date:</label>
                <input type="date" id="purchaseDate" name="purchaseDate" required>

                <button type="submit">Register Product</button>
            </form>
        </div>
        <br>
        <br>
        <a href="<c:url value='/dashboard'/>" class="back-to-dashboard">Back to Dashboard</a>
    </main>
    <footer>
    
    </footer>
</body>
</html>
