<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<title>Обновление тарифа</title>
<div class="container">
<H1>Обновление ${title}</H1>
<form  method="POST" action="/update-tariff.do">
<fieldset class="form-group">
<label>Имя
<input name="newTitle" type="text" class="form-control" value="${oldTariff.title}" /> <BR />
</label>
</fieldset>
<fieldset class="form-group">
<label>Цена
<input name="newPriceByDay" type="text" class="form-control" value="${oldTariff.priceByDay}" /> <BR />
</label>
</fieldset>
<fieldset class="form-group">
</select>
</fieldset>
<input name="add" type="submit" class="btn btn-success" value="Обновить"/>
</form>
</div>
</body>
