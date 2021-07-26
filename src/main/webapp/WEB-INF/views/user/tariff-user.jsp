<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<div class="container">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
crossorigin="anonymous">

</head>
    <title>Список тарифов компании</title>

	<table class="table table-striped">

		<thead>
		        <th>Название</th>
                <th>Цена (грн./день)</th>
                <th>Услуги</th>
                <th>Подключение</th>

                </thead>
		<tbody>
			<c:forEach items="${tariffs}" var="tariff">
				  <tr>
                                <td style="min-width:100px; width:500px;">
                                ${tariff.key.title}</td>
                                <td style="min-width:100px; width:300px;">
                                ${tariff.key.priceByDay}</td>
                                <td style="min-width:100px; width:300px;">
                                <ui class="list-cell">
                                <c:forEach items="${tariff.value}" var="service">
                                <li>
                                ${service.title}
                                </li>
                                </c:forEach>
                                </ui>
                                </td>
                    <td><a class="btn btn-primary"
                    href="/user/add-user-tariff.do?title=${tariff.key.title}&email=${email}">Подключить</a></td>

			       </tr>
			</c:forEach>
		</tbody>


	</table>
	<a class="btn btn-info" href="/user/user.do">Назад</a>
