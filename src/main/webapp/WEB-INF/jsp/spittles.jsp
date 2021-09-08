<%--
  Created by IntelliJ IDEA.
  User: zjjfly
  Date: 2016/12/28
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spittles</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">
</head>
<body>
<div class="listTitle">
    <h1>Recent Spittles</h1>
    <ul class="spittleList">
    <c:forEach items="${spittleList}" var="spittle">
        <li id="spittle_<c:out value="${spittle.id}"/>">
            <div class="spittleMessage">
                <c:out value="${spittle.message}"/>
            </div>
            <div>
            <span class="spittleTime">
               <c:out value="${spittle.time}"/>
            </span>
            <span class="spittleLocation">
               (<c:out value="${spittle.latitude}"/>,
               <c:out value="${spittle.longitude}"/>)
            </span>
            </div>
        </li>
    </c:forEach>
</ul>
</div>
</body>
</html>
