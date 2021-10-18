<%--
  Created by IntelliJ IDEA.
  User: asimion
  Date: 10/17/2021
  Time: 9:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h1 align="center">RECORDS</h1>
<div align="center">
    <table cellpadding="5px" border="1">
        <tr>
            <th>Id</th>
            <th>Category</th>
            <th>Name</th>
            <th>Value</th>
        </tr>
        <jsp:useBean id="listRecords" scope="request" type="java.util.List"/>
        <c:forEach var="record" items="${listRecords}">
            <tr>
                <td>${record.recordId}</td>
                <td>${record.category}</td>
                <td>${record.name}</td>
                <td>${record.value}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div align="center">
    <a href="InputServlet">Add another record</a>
</div>
</body>
</html>
