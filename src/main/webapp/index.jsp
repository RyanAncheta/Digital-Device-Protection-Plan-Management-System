<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AE Industry - Protection Plan</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>"> 
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
                <a href="<c:url value='/login.jsp'/>" class="login-button">Log In</a>
                <a href="<c:url value='/profile.jsp'/>" class="profile-icon"><img src="<c:url value='/images/profile-icon.png'/>" alt="Profile"></a>
            </div>
        </nav>
    </header>
    <main>
        <section class="hero">
            <div class="hero-image">
                <img src="<c:url value='/images/hero-image.webp'/>" alt="Protection Plan">
            </div>
            <div class="hero-content">
                <h1>Protection Plan?</h1>
                <p>Protect your digital devices with AE Insurance's comprehensive Protection Plan. Cover your TV, camera, cellphone, and more against damage, theft, and malfunctions. Enjoy easy claims, 24/7 support, and quick repairs or replacements. Join now for peace of mind.</p>
                <a href="<c:url value='/register.jsp'/>" class="join-now-button">Join Now!</a>
            </div>
        </section>
        <section class="product-categories">
            <h2>Product Categories</h2>
            <div class="categories">
                <div class="category">
                    <img src="<c:url value='/images/register-device.jpg'/>" alt="Register Device">
                    <p>Register Device</p>
                </div>
                <div class="category">
                    <img src="<c:url value='/images/submit-claim.jpg'/>" alt="Submit Claim">
                    <p>Submit Claim</p>
                </div>
                <div class="category">
                    <img src="<c:url value='/images/coverage-plan.jpg'/>" alt="Coverage Plan">
                    <p>Coverage Plan</p>
                </div>
                <div class="category">
                    <img src="<c:url value='/images/claim-tracking.jpg'/>" alt="Claim Tracking">
                    <p>Claim Tracking</p>
                </div>
            </div>
        </section>
    </main>
    <footer>
       
    </footer>
</body>
</html>
