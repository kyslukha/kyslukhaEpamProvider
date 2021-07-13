<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">

<H1>Список администраторов</H1>
<table class="table table-striped">
<thead>
<th>Имя</th>
<th>Почта</th>
<th>Телефон</th>
</thead>
<tbody>
    <c:forEach items="${admins}" var="admin">
        <tr>
            <td>${admin.name}</td>
            <td>${admin.email}</td>
            <td>${admin.phone}</td>
        </tr>
    </c:forEach>
</tbody>
</table>
</p>
<a class="btn btn-info" href="/admin.do">Назад</a>
</div>