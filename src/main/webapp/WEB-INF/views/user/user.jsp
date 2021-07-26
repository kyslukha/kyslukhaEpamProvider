<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<%@ include file="../../common/navigation.jspf"%>
<%@ include file="../../common/footer.jspf"%>




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
<th><c:out value="${name}" default="No Name" /></th>
<th>${email}</th>
<th><c:out value="${phone}" default="No phone" /></th>
</thead>
</table>
<td><a class="btn btn-info"
href="/user/change-password-user.do?email=${user.email}">Изменить пароль</a></td>


<H1>Статус: ${status}</H1>

<H1>Счёт: <c:out value="${account}" default="0.0" /></H1>
<td><a class="btn btn-danger"
href="/user/add-money-user.do?email=${user.email}">Пополнить счёт</a></td>
<H1>----------------------------------------------------</H1>
<td><a class="btn btn-primary"
href="/user/tariff-user.do">Список тарифов  и услуг компании</a></td>
<td><a class="btn btn-warning"
href="/user/list-tariff-user.do">Список тарифов компании</a></td>


<td><a class="btn btn-info"
href="/user/list-user-tariff.do?email=${user.email}">Список моих тарифов</a></td>


</div>
</body>


