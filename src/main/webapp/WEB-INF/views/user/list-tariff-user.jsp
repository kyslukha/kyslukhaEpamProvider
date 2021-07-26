<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>

<div class="container">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
crossorigin="anonymous">


<H1>Список тарифов</H1>
<table class="table table-striped">
<thead>
<th>Название</th>
<th>Цена(грн/день)</th>
</thead>
<tbody>
<c:forEach items="${tariffs}" var="tariff">
                    <tr>
                                <td style="min-width:100px; width:500px;">
                                ${tariff.title}</td>
                                <td style="min-width:100px; width:300px;">
                                ${tariff.priceByDay}</td>



			       </tr>
</c:forEach>
</tbody>
</table>

<a class="btn btn-info" href="/user/list-tariff-user.do?sortTitleASC">Название (A-Z)</a>
<a class="btn btn-info" href="/user/list-tariff-user.do?sortTitleDESC">Название (A-Z)</a>
<a class= "btn btn-danger" href="/user/list-tariff-user.do?sortPriceByDayASC">Цена(<<)</a>
<a class= "btn btn-danger"href="/user/list-tariff-user.do?sortPriceByDayDESC">Цена(>>)</a>
<p>

<H1>-------------------------------</H1>
<a  class="btn btn-primary" href="/DownloadFileServlet"> Загрузить список тарифов</a>

</p>
<a class="btn btn-primary" href="/user/user.do">Назад</a>
</div>