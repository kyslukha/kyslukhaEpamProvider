<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<body>
<title>Добавить новую услугу</title>
<div class="container">
<form method="POST" action="/add-service.do">
<fieldset class="form-group">
<meta charset="UTF-8" />
<label>Название услуги</label> <input name="title" type="text"
class="form-control" /> <BR />
</fieldset>
<input name="add" type="submit" class="btn btn-success" value="Добавить"/>
</form>

</div>




</body>

</html>