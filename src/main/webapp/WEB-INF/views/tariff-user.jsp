<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
     <%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
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
                    href="/add-user-tariff.do?title=${tariff.key.title}&email=${email}">Подключить</a></td>

			       </tr>
			</c:forEach>
		</tbody>


	</table>
	<a class="btn btn-info" href="/user.do">Назад</a>