
<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<ul class="nav navbar-nav navbar-right">
<li><a href="/sign-up.do">Sign Up</a></li>
</ul>
<html>
<body>
<nav class="navbar navbar-default">
</nav>
<div class="container">
		<form action="/login.do" method="post">
			логин(email): <input type="text" name="email" />
			password:<input type="password" name="password" />
			<input type="submit" value="Login" />
            <label for="status_user_checkbox"> as admin
            <input name="status_user" type="checkbox"
            class="form-control" id="status_user" onclick="validate()"/>
            </label>
		</form>

	</div>
<script>
            function validate() {
                var el = document.getElementById('status_user');
                if (el.checked) {
                    el.setAttribute("value", "1")
                }
            }
</script>

</body>
</html>
