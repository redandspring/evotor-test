<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<c:url value="${pageContext.request.contextPath}/" var="root" />
<c:url value="${root}resources/" var="resources" />

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${titlePage}</title>

    <link href="${resources}css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            padding-top: 50px;
        }
    </style>

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="">Evotor Test</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="<c:if test="${currentPage == 'index'}">active</c:if>"><a href="${root}">Главная</a></li>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <li class="<c:if test="${currentPage == 'balance'}">active</c:if>"><a href="${root}balance">Баланс</a></li>
                </sec:authorize>
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="isAuthenticated()">
                    <li><p class="navbar-text">Welcome <sec:authentication property="principal" /></p></li>
                    <li>
                        <c:url var="logoutUrl" value="/logout"/>
                        <form class="form-inline" action="${logoutUrl}" method="post">
                            <input type="submit" class="btn btn-default navbar-btn" value="Log out" />
                            <sec:csrfInput />
                        </form>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAnonymous()">
                    <li><a href="${root}login">Логин</a></li>
                    <li><a href="${root}registration">Регистрация</a></li>
                </sec:authorize>

            </ul>
        </div>
    </div>
</nav>


<div class="container">

    <c:if test="${includeView == null}" >
        <div class="starter-template">
            <h1>Evotor test task</h1>
            <p class="lead">${titlePage}. Регистрация, авторизация и отображение баланса клиента <br />Stack: Spring MVC, Spring Security, JdbcTemplate, MySQL</p>
        </div>
    </c:if>

    <c:if test="${includeView != null}" >
        <jsp:include page="includes/${includeView}.jsp" />
    </c:if>

</div>

<script src="${resources}js/jquery.min.js"></script>
<script src="${resources}js/bootstrap.min.js"></script>
</body>

</html>