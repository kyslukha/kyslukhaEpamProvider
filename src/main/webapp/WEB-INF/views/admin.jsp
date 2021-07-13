<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<ul class="nav navbar-nav navbar-right">
<li><a href="/sign-up.do">Sign Up</a></li>
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
<th>${name}</th>
<th>${email}</th>
<th>${phone}</th>
<th><a class="btn btn-info"
href="/update-user.do?email=${email}&name=${name}&phone=${phone}">Обновить данные</a></th>
</thead>
</table>

</table>


<H1>-----------------------------------------------------------------------------------------------</H1>
<table class="table table-striped">
<td><a class="btn btn-info" href="/tariff-admin.do">Список тарифов и услуг</a></td>
<td><a class="btn btn-danger" href="/service-admin.do">Список услуг</a></td>
<td><a class="btn btn-warning" href="/list-user.do">Список пользователей</a></td>
<td><a class="btn btn-warning" href="/list-inactive-user.do">Список заблокированых пользователей</a></td>
<td><a class="btn btn-primary" href="/list-admin.do">Список адмиристраторов</a></td>
</div>
</body>

