<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
     <%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
</head>
    <title>Список тарифов пользователя ${email}</title>

	<table class="table table-striped">

		<thead>
		        <th>Название</th>
                <th>Дата начала</th>
                <th>Дата окончания</th>


                </thead>
		<tbody>
			<c:forEach items="${listTariffs}" var="tariff">
				  <tr>
                                <td style="min-width:100px; width:300px;">${tariff.title}</td>

                                <td>${tariff.dateStart}</td>
                                <td>${tariff.dateFinish}</td>


			       </tr>
			</c:forEach>
		</tbody>

<H1>---------------------------------------------------------------------------------------------------------</H1>
<table class="table table-striped">

		<td><a class="btn btn-warning" href="/list-user.do">Назад</a></td>
	</table>
