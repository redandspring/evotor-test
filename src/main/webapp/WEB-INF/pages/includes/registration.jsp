<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="${pageContext.request.contextPath}/" var="root" />

<h2>Регистрация</h2>

<jsp:include page="./messages.jsp" />

<div class="row">
    <div class="col-xs-6 col-xs-offset-3">
        <form action="${root}registration" class="form-horizontal" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <div class="form-group">
                <label class="control-label col-xs-3" for="username">Логин:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="username" id="username" placeholder="Придумайте логин">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="password">Пароль:</label>
                <div class="col-xs-9">
                    <input type="password" class="form-control" name="password" id="password" placeholder="Придумайте пароль">
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <input type="submit" class="btn btn-primary" value="Регистрация">
                    <input type="reset" class="btn btn-default" value="Очистить форму">
                </div>
            </div>
        </form>
      </div>
</div>
