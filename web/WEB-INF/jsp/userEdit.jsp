<jsp:useBean id="user" scope="request" type="model.User"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello!!!</title>
</head>
<body>
<H2>Редактировать</H2>
<form action="/admin" method="POST">
    <input type="hidden" name="id" value="${user.id}">
    <input type="hidden" name="action" value="update">
    <dl>
        <dt>Имя</dt>
        <dd><input type="text" name="name" value="${user.name}"></dd>
    </dl>
    <dl>
        <dt>Фамилия</dt>
        <dd><input type="text" name="surname" value="${user.surname}"></dd>
    </dl>
    <dl>
        <dt>Возраст</dt>
        <dd><input type="number" name="age" value="${user.age}"></dd>
    </dl>
    <button type="submit">Сохранить</button>
    <input type="button" onclick="history.back();" value="Назад"/>

</form>
</body>
</html>
