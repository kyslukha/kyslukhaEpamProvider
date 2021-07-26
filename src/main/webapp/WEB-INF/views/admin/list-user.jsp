<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<%@ include file="../../common/navigation.jspf"%>
<%@ include file="../../common/footer.jspf"%>

<body>
<div class="container">

<H1>Список пользователей</H1>
<table  class="table table-striped">
 <tr>
<th>Имя</th>
<th>Почта</th>
<th>Телефон</th>
<th>Состояние счёта</th>
<th>Обновить</th>
<th>Тарифы</th>
<th>Блокировка</th>
<th>История</th>
 </tr>

    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}" default="No Name"/></td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.account}</td>
            <td><a class="btn btn-primary"
            href="/admin/update-user.do?email=${user.email}&name=${user.name}&phone=${user.phone}">Обновить</a></td>
            <td><a class="btn btn-info"
            href="/admin/list-user-tariff-admin.do?email=${user.email}&name=${user.name}">Тарифы</a></td>
            <td><a class="btn btn-danger"
            href="/admin/change-status-user.do?email=${user.email}">Заблокировать</a></td>
            <td><a class="btn btn-primary"
            href="/admin/admin-tariff-history.do?email=${user.email}&name=${user.name}">История</a></td>
        </tr>
    </c:forEach>

</table>

            <c:if test="${currentPage != 1}">
                <td><a href="/admin/list-user.do?page=${currentPage - 1}">Предыдущая</a></td>
            </c:if>


<table class="table table-striped">
                <tr>
                <c:forEach begin="1" end="${numberPages}" var="i">
                <c:choose>
                <c:when test="${currentPage eq i}">
                <td>${i}</td>
                </c:when>
                <c:otherwise>
                <td><a href="list-user.do?page=${i}">${i}</a></td>
                </c:otherwise>
                </c:choose>
                </c:forEach>
                </tr>
</table>


            <c:if test="${currentPage != numberPages}">
                <td><a href="/admin/list-user.do?page=${currentPage + 1}">Следующая</a></td>
            </c:if>
</p>
<a class="btn btn-info" href="/admin/admin.do">Назад</a>
</div>
</body>