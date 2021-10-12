<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form method="GET" action="hello">
    <label>KEY</label>
    <input type="text" name="key" /> <br>
    <label>VALUE</label>
    <input type="text" name="value" /> <br>
    <label>MOCK</label>
    <input type="text" name="mock" /><br>
    <label>SYNC</label>
    <input type="text" name="sync" /><br>
    <input type="submit" value="Invoke the servlet" />
</form>
</body>
</html>