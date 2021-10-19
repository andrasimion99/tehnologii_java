<%--
  Created by IntelliJ IDEA.
  User: asimion
  Date: 10/17/2021
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Records</title>
</head>
<body>
<h1 align="center">Add records</h1>
<div align="center">
    <form action="CreateRecordServlet" method="POST">
        Name: <input type="text" name="name">
        <br/>
        Value: <input type="text" name="value"/>
        <br/>
        Category:
        <select id="category" name="category">
            <c:choose>
                <c:when test="${cookieCategory == null}">
                    <option selected>${applicationScope.defaultCategory}</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category}">${category}</option>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option>${applicationScope.defaultCategory}</option>
                    <c:forEach var="category" items="${categories}">
                        <c:choose>
                            <c:when test="${category.equals(cookieCategory)}">
                                <option selected value="${category}">${category}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${category}">${category}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </select>
        <br/>
        <input type="submit" value="Add"/>
    </form>
</div>
<div align="center">
    <a href="ResultServlet">Go see results</a>
</div>
</body>
</html>
