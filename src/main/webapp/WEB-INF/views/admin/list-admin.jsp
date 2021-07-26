<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<%@ include file="../../common/navigation.jspf"%>
<%@ include file="../../common/footer.jspf"%>

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
<a class="btn btn-info" href="/admin/admin.do">Назад</a>
</div>