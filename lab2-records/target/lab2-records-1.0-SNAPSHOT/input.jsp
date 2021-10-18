<%--
  Created by IntelliJ IDEA.
  User: asimion
  Date: 10/17/2021
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Records</title>
</head>
<body>
<h1>First page</h1>
<form action="InputServlet" method="POST">
    Key: <input type="text" name="key">
    <br/>
    Value: <input type="text" name="value"/>
    <br/>
    Category:
    <select id="category" name="category">
        <option value="key1">Cars</option>
        <option value="key1">Food</option>
    </select>
    <br/>
    <input type="submit" value="Submit"/>
</form>
<a href="ResultServlet">Go see results</a>
</body>
</html>
