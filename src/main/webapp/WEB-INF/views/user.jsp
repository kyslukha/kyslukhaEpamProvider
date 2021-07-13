<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>



<body>
<div class="container">

<H1>Страница пользователя </H1>

<table class="table table-striped">
<thead>
<th>Имя</th>
<th>Почта</th>
<th>Телефон</th>
</thead>
 <br>
<thead>
<th>${name}</th>
<th>${email}</th>
<th>${phone}</th>
</thead>
</table>
<td><a class="btn btn-info"
href="/change-password-user.do?email=${user.email}">Изменить пароль</a></td>


<H1>Статус: ${status}</H1>

<H1>Счёт: ${account}</H1>
<td><a class="btn btn-danger"
href="/add-money-user.do?email=${user.email}">Пополнить счёт</a></td>
<H1>----------------------------------------------------</H1>
<td><a class="btn btn-primary"
href="/tariff-user.do">Список тарифов  и услуг компании</a></td>
<td><a class="btn btn-warning"
href="/list-tariff-user.do">Список тарифов компании</a></td>


<td><a class="btn btn-info"
href="/list-user-tariff.do?email=${user.email}">Список моих тарифов</a></td>


</div>
</body>


