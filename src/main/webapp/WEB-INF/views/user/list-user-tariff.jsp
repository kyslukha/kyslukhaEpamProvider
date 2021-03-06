<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/footer.jspf"%>
<%@ include file="../../common/header.jspf"%>
</head>
    <title>Список моих тарифов </title>

	<table class="table table-striped">

		<thead>
		        <th>Название</th>
                <th>Дата начала</th>
                <th>Дата окончания</th>
                <th>Статус</th>
                <th>Изменить</th>
                <th>Закрыть</th>

                </thead>
		<tbody>
			<c:forEach items="${listTariffs}" var="tariff">
				  <tr>
                                <td style="min-width:100px; width:300px;">${tariff.title}</td>

                                <td>${tariff.dateStart}</td>
                                <td>${tariff.dateFinish}</td>
                                <td>${tariff.status}</td>

                                <td><a class="btn btn-primary"
                                href="/user/update-user-tariff.do?id=${tariff.id}&title=${tariff.title}&email=${user.email}">Изменить</a></td>
                                <td><a class="btn btn-danger"
                                href="/user/close-user-tariff.do?id=${tariff.id}&email=${user.email}">Закрыть</a></td>

			       </tr>
			</c:forEach>
		</tbody>

<H1>----------------------------------------------------------------------------------</H1>
<table class="table table-striped">
		<td><a class="btn btn-info" href="/user/user.do">Назад</a></td>
		<td><a class="btn btn-warning" href="/user/user-tariff-history.do">История</a></td>
	</table>
