<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
     <%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <title>История тарифов ${email}</title>

<table class="table table-striped">

		<thead>
		        <th>Название</th>
                <th>Дата начала</th>
                <th>Дата окончания</th>

        </thead>
		<tbody>
		<c:forEach items="${listTariffs}" var="tariff">
                    <tr>
                                    <td style="min-width:100px; width:500px;">
                                    ${tariff.title}</td>


                                        <td>${tariff.dateStart}</td>
                                        <td>${tariff.dateFinish}</td>



                    </tr>
        </c:forEach>
		</tbody>


</table>

		<td><a class="btn btn-warning" href="/admin.do">На главную  страницу</a></td>
</div>

