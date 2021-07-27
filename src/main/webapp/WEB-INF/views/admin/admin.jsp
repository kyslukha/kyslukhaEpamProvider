<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<%@ include file="../../common/navigation.jspf"%>
<%@ include file="../../common/footer.jspf"%>

<ul class="nav navbar-nav navbar-right">
<li><a class="btn btn-info" href="/sign-up.do">Новый пользователь</a></li>
</ul>
<body>
<div class="container">

<H1>Страница пользователя </H1>

<table class="table table-striped">
<thead>
<th>Имя</th>
<th>Почта</th>
<th>Телефон</th>
<th></th>


</thead>
 <br>
<thead>
<th><c:out value="${name}" default="No Name" /></th>
<th>${email}</th>
<th><c:out value="${phone}" default="No phone" /></th>
<th><a class="btn btn-info"
href="/admin/update-user.do?email=${email}&name=${name}&phone=${phone}">Обновить данные</a></th>
</thead>
</table>

</table>


<H1>---------------------------------------------------------------------</H1>
<table class="table table-striped">
<td><a class="btn btn-info" href="/admin/tariff-admin.do">Список тарифов и услуг</a></td>
<td><a class="btn btn-danger" href="/admin/service-admin.do">Список услуг</a></td>
<td><a class="btn btn-warning" href="/admin/list-user.do">Список пользователей</a></td>
<td><a class="btn btn-warning" href="/admin/list-inactive-user.do">Список заблокированых пользователей</a></td>
<td><a class="btn btn-primary" href="/admin/list-admin.do">Список адмиристраторов</a></td>
<td><a class="btn btn-info" href="/admin/user-list-info.do">Список заказов</a></td>
</div>
</body>

