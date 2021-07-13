<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
     <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
     <link rel="stylesheet" href="/resources/demos/style.css">
     <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
     <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

 </head>
<title>Изменение тарифа </title>
<div class="container">
<H1>Установить новую дату окончания тарифа  ${title} ${id}</H1>
<form method="POST" action="/update-user-tariff.do">
 </fieldset>
                  <fieldset class="form-group">
                    <label for="start">Дата окончания</label>
                     <input type="date" id="start" name="dateFinish"
                                                       value="2021-08-01"
                                                       min="2021-08-01" max="2025-12-31">
                    </fieldset>

               <input name="add" type="submit" class="btn btn-info" value="Установить"/>
            </form>
 </div>
 <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
 <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>