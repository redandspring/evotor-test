<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${messages != null}">
    <div class="alert alert-danger" role="alert">
        <c:forEach items="${messages}" var="message">
            ${message}
            <br />
        </c:forEach>
    </div>
</c:if>
