<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/footer.jspf"%>
<%@ include file="../../common/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <title>История тарифов </title>

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
                                    <td style="min-width:100px; width:500px;">
                                    ${tariff.title}</td>


                                        <td>${tariff.dateStart}</td>
                                        <td>${tariff.dateFinish}</td>
                                        <td>${tariff.status}</td>



                    </tr>
        </c:forEach>
		</tbody>


</table>

		<td><a class="btn btn-warning" href="/user/list-user-tariff.do">Назад</a></td>
</div>

