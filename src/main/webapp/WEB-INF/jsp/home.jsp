<%--
  Created by IntelliJ IDEA.
  User: zjjfly
  Date: 2016/12/27
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spitrr</title>
    <link rel="stylesheet" href="<s:url value="/resources/style.css"/>">
</head>
<body>
<s:url value="/spittles" var="spittlesUrl" htmlEscape="true">
    <s:param name="max" value="60"/>
    <s:param name="count" value="20"/>
</s:url>
<%--<s:url value="/spitter/{username}" var="spitterUrl" >--%>
    <%--<s:param name="username" value="jbauer"/>--%>
<%--</s:url>--%>
<s:url value="/spitter/register" var="spitterUrl"/>
<h1><s:message code="spittr.welcome"/></h1>
<a href="<s:url value="${spittlesUrl}"/>">Spittles</a>
<a href="<s:url value="${spitterUrl}"/>">Register</a>
<%--<a href="<s:url value="${spitterUrl}"/>">${registerUrl}</a>--%>
</body>
</html>
