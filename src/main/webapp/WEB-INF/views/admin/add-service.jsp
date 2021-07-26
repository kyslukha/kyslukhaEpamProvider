 <%@ page pageEncoding="UTF-8"%>
 <%@ include file="../../common/page.jspf"%>

<!DOCTYPE html>
<html>
<body>
<title>Добавить новую услугу</title>
<div class="container">
<form method="POST" action="/admin/add-service.do">
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