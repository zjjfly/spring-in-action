<%--
  Created by IntelliJ IDEA.
  User: zjjfly
  Date: 2017/1/4
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" href="<s:url value="/resources/style.css"/>">
</head>
<body>
<h1>Register</h1>
<sf:form commandName="spitter" method="post">
    <sf:errors path="*" element="div" cssClass="error"/>
    <sf:label path="firstName" cssErrorClass="error">First Name: </sf:label><sf:input path="firstName" cssErrorClass="error"/><br/>
    Last Name: <sf:input path="lastName"/><br/>
    Email: <sf:input path="email"/><br/>
    Username: <sf:input path="username"/><br/>
    Password: <sf:password path="password"/><br/>
    <input type="submit" value="Register">
</sf:form>
</body>
</html>
