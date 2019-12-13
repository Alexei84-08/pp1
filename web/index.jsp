<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello!!!</title>
</head>
<body>
<h3>Users</h3>
<hr>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Возраст</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td><a href="/?action=update&id=${user.id}">Редактировать</a></td>
            <td><a href="/?action=delete&id=${user.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<h3>New User</h3>
<form action="/" method="POST">
    Регистрация:<br>
    Имя: <input type="text" name="name">
    Фамилия: <input type="text" name="surname">
    Возраст: <input type="number" name="age">
    <input type="submit" value="Ok">
</form>
</body>
</html>
