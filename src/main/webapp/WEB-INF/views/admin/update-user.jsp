<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ include file="../../common/header.jspf"%>
<%@ include file="../../common/navigation.jspf"%>
<%@ include file="../../common/footer.jspf"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<title>Обновление данных пользователя</title>
<div class="container">
<H1>Обновление ${email} </H1>
<form  method="POST" action="/admin/update-user.do">
<fieldset class="form-group">
<label>Имя
<input name="newName" type="text" class="form-control" value="${oldUser.name}" /> <BR />
</label>
</fieldset>
<fieldset class="form-group">
<label>Почта
<input name="newEmail" type="text" class="form-control" value="${oldUser.email}" /> <BR />
</label>
</fieldset>
<fieldset class="form-group">
<label>Телефон
<input name="newPhone" type="text" class="form-control" value="${oldUser.phone}" /> <BR />
</label>
</fieldset>
<fieldset class="form-group">
<label>Пароль
<input name="newPassword" type="text" class="form-control" value="" /> <BR />
</label>
</fieldset>
<input name="add" type="submit" class="btn btn-success" value="Обновить"/>
</form>
</div>
</body>