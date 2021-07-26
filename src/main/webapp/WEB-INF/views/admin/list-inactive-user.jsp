<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<%@ include file="../../common/navigation.jspf"%>
<%@ include file="../../common/footer.jspf"%>

<div class="container">

<H1>Чёрный список</H1>
<table class="table table-striped">
<thead>
<th>Имя</th>
<th>Почта</th>
<th>Телефон</th>
<th>Состояние счёта</th>
<th>Разблокировка</th>
<th>История</th>
</thead>
<tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}" default="No Name"/></td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.account}</td>
            <td><a class="btn btn-danger"
            href="/admin/change-status-user.do?email=${user.email}">Разблокировать</a></td>
            <td><a class="btn btn-primary"
            href="/admin/admin-tariff-history.do?email=${user.email}&name=${user.name}">История</a></td>
        </tr>
    </c:forEach>
</tbody>
</table>
</p>
<a class="btn btn-info" href="/admin/admin.do">Назад</a>
</div>