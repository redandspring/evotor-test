<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="/login" var="loginUrl"/>

<h2>Авторизация</h2>

<jsp:include page="./messages.jsp" />

<c:if test="${param.error_wrong_password != null}">
    <div class="alert alert-danger">Пароль не верен</div>
</c:if>
<c:if test="${param.error_not_found != null}">
    <div class="alert alert-danger">Пользователь не существует</div>
</c:if>
<c:if test="${param.error != null}">
    <div class="alert alert-danger">Техническая ошибка</div>
</c:if>
<c:if test="${param.logout != null}">
    <div class="alert alert-danger">You have been logged out.</div>
</c:if>

<div class="row">
    <div class="col-xs-6 col-xs-offset-3">
        <form action="${loginUrl}" class="form-horizontal" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <div class="form-group">
                <label class="control-label col-xs-3" for="username">Логин:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="username" id="username" placeholder="Введите логин">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="password">Пароль:</label>
                <div class="col-xs-9">
                    <input type="password" class="form-control" name="password" id="password" placeholder="Введите пароль">
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <input type="submit" class="btn btn-primary" value="Войти">
                    <input type="reset" class="btn btn-default" value="Очистить форму">
                </div>
            </div>
        </form>
    </div>
</div>