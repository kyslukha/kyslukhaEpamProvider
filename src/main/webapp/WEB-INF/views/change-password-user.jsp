<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
     <%@ include file="../common/header.jspf"%>

<!DOCTYPE html>
<html>
<head>
<body>
</head>
    <title>Изменение пароля</title>

<div class="container">

	<form method="POST" action="/change-password-user.do">
		<fieldset class="form-group">
			<label>Введите новый пароль</label> <input name="password" type="text"
				class="form-control" /> <BR />
		</fieldset>

                        <input name="add" type="submit" class="btn btn-success" value="Изменить"/>
                    </form>
</div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>