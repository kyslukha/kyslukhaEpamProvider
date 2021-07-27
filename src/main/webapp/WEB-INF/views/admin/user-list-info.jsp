<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<div class="container">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
crossorigin="anonymous">

<body>
<div class="container">

<H1>Список пользователей</H1>
<table  class="table table-striped">
 <tr>
<th>Id</th>
<th>Почта</th>
<th>Название тарифа</th>
<th>Статус</th>
<th>Список услуг</th>
<th>Цена</th>
<th>Дата начала</th>
 </tr>
  <c:forEach items="${list}" var="list">
         <tr>
             <td>${list.id}</td>
             <td>${list.email}</td>
             <td>${list.title}</td>
             <td>${list.status}</td>
              <td style="min-width:100px; width:300px;">
                                             <ui class="list-cell">
             <c:forEach items="${list.serviceTitles}" var="service">
                                             <li>
                                             ${service}
                                             </li>
             </c:forEach>
             <td>${list.price}</td>
             <td>${list.dateStart}</td>
         </tr>
  </c:forEach>

  <form method="POST" action="/admin/user-list-info.do">

                      <fieldset class="form-group">
                      <label for="start">Дата начала</label>
                       <input type="date" id="start" name="start"
                                                         value="2021-07-07"
                                                         min="2021-07-07" max="2025-12-31">

                    </fieldset>
                    <fieldset class="form-group">
                      <label for="start1">Дата окончания</label>
                       <input type="date" id="start1" name="finish"
                                                         value="2021-08-01"
                                                         min="2021-08-01" max="2025-12-31">
                      </fieldset>

                 <input name="add" type="submit" class="btn btn-info" value="Диапазон времени"/>
              </form>


</table>

</table>

		<td><a class="btn btn-warning" href="/admin/admin.do">На главную  страницу</a></td>
</div>
