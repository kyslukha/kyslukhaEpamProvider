<%@ page pageEncoding="UTF-8"%>
<%@ include file="../common/page.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
crossorigin="anonymous">



<!DOCTYPE html>
<ul class="nav navbar-nav navbar-right">
<li><a href="/sign-up.do"> <fmt:message key="login_jsp.label.sign" /></a></li>

</ul>
<html>
<body>
<nav class="navbar navbar-default">
</nav>
<div class="container">
		<form action="/login.do" method="post">
            <fmt:message key="login_jsp.label.login"/>
            <input type="text" name="email" />
            <fmt:message key="login_jsp.label.password"/>
            <input type="password" name="password" />
			<input type="submit" value = '<fmt:message key="login_jsp.button.login"/>' />
            <label for="statusUser_checkbox">
            <fmt:message key="login_jsp.label.asAdministrator"/>
            <input name="statusUser" type="checkbox"
            id="statusUser" onclick="validate()"/>
            </label>
		</form>

	</div>
<script>
            function validate() {
                var el = document.getElementById('statusUser');
                if (el.checked) {
                    el.setAttribute("value", "1")
                }
            }
</script>

</body>
</html>
