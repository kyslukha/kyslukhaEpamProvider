<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ include file="../common/header.jspf"%>

<div class="container">

<H1>Список услуг</H1>
<table class="table table-striped">
<thead>

 <th>Услуга</th>
 <th>Обновление</th>
 <th>Удаление</th>

</thead>
<tbody>
<c:forEach items="${services}" var="service">
<tr>
<td style="min-width:100px; width:500px;">
${service.title}</td>

<td><a class="btn btn-primary"
href="/update-service.do?title=${service.title}">Обновить</a></td>
<td><a class="btn btn-danger"
href="/delete-service.do?title=${service.title}">Удалить</a></td>
</tr>
 </c:forEach>
</tbody>
</table>


<a class="btn btn-success" href="/add-service.do">Добавить новую услугу</a>
<p>

</p>
<a class="btn btn-info" href="/admin.do">Назад</a>
</div>
