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
            <td>
                <form action="/" method="GET">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Редактировать">
                </form>
            </td>
            <td>
                <form action="/" method="POST">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<h3>New User</h3>
<form action="/" method="POST">
    Регистрация:<br>
    Имя: <input type="text" name="name">
    Фамилия: <input type="text" name="surname">
    Возраст: <input type="number" name="age">
    <input type="hidden" name="action" value="add">
    <input type="submit" value="Добавить">
</form>
</body>
</html>
