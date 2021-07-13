<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">

<H1>Список пользователей</H1>
<table class="table table-striped">
<thead>
<th>Имя</th>
<th>Почта</th>
<th>Телефон</th>
<th>Состояние счёта</th>
<th>Обновить</th>
<th>Тарифы</th>
<th>Блокировка</th>
<th>История</th>
</thead>
<tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.account}</td>
            <td><a class="btn btn-primary"
            href="/update-user.do?email=${user.email}&name=${user.name}&phone=${user.phone}">Обновить</a></td>
            <td><a class="btn btn-info"
            href="/list-user-tariff-admin.do?email=${user.email}">Тарифы</a></td>
            <td><a class="btn btn-danger"
            href="/change-status-user.do?email=${user.email}">Заблокировать</a></td>
            <td><a class="btn btn-primary"
            href="/admin-tariff-history.do?email=${user.email}">История</a></td>
        </tr>
    </c:forEach>
</tbody>
</table>
</p>
<a class="btn btn-info" href="/admin.do">Назад</a>
</div>