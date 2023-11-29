<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary p-0">
    <div class="navbar-nav">
        <a class='nav-item nav-link ${requestURI.contains("/home")?"active":""}' href="home">All videos</a>
        <c:if test="${not empty sessionScope.username}">
            <a class='nav-item nav-link ${requestURI.contains("/favorite")?"active":""}' href="favorite">My favorite list</a>
        </c:if>
    </div>
    <ul class="navbar-nav ms-auto">
        <li class="nav-item dropdown no-arrow">
            <c:if test="${not empty sessionScope.username}">
                <a class="nav-link active" href="logout">
                    Log out
                </a>
            </c:if>
            <c:if test="${empty sessionScope.username}">
                <a class="nav-link active" href="login">
                    Log in
                </a>
            </c:if>
        </li>
    </ul>
</nav>