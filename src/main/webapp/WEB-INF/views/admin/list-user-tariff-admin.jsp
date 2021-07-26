<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<div class="container">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
crossorigin="anonymous">
</head>
    <title>Список тарифов пользователя </title>

    <table class="table table-striped">
    <thead>
    <th>Имя</th>
    <th>Почта</th>
    </thead>
     <br>
    <thead>
    <th><c:out value="${user.name}" default="No Name"/></th>
    <th>${user.email}</th>
    </thead>
    </table>

	<table class="table table-striped">

		<thead>
		        <th>Название</th>
                <th>Дата начала</th>
                <th>Дата окончания</th>
                <th>Статус</th>


                </thead>
		<tbody>
			<c:forEach items="${listTariffs}" var="tariff">
				  <tr>
                                <td style="min-width:100px; width:300px;">${tariff.title}</td>

                                <td>${tariff.dateStart}</td>
                                <td>${tariff.dateFinish}</td>
                                <td>${tariff.status}</td>


			       </tr>
			</c:forEach>
		</tbody>

<H1>---------------------------------------------------------------------</H1>
<table class="table table-striped">

		<td><a class="btn btn-warning" href="/admin/list-user.do">Назад</a></td>
	</table>
