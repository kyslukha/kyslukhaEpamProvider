<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
     <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
     <link rel="stylesheet" href="/resources/demos/style.css">
     <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
     <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

 </head>
<title>Подключить тариф</title>
<div class="container">
<H1>Подключение к тарифу ${title}</H1>
<form method="POST" action="/user/add-user-tariff.do">

                    <fieldset class="form-group">
                    <label for="start">Дата начала</label>
                     <input type="date" id="start" name="dateStart"
                                                       value="2021-07-07"
                                                       min="2021-07-07" max="2025-12-31">

                  </fieldset>
                  <fieldset class="form-group">
                    <label for="start1">Дата окончания</label>
                     <input type="date" id="start1" name="dateFinish"
                                                       value="2021-08-01"
                                                       min="2021-08-01" max="2025-12-31">
                    </fieldset>

               <input name="add" type="submit" class="btn btn-info" value="Добавить"/>
            </form>
        </div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>