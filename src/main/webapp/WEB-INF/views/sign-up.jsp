<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ include file="../common/navigation.jspf"%>
<!DOCTYPE html>
<html>
<head>

<title>Page Title</title>
</head>
<style>
.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 60px;
	background-color: #f5f5f5;
}
</style>
<body>
<nav class="navbar navbar-default">
</nav>
<div class="container">
   <h1 class="text">Sign Up</h1>
       <hr>
    <form method="POST" action="/sign-up.do">
        <fieldset class="form-group">
            <label>Имя</label>
            <input name="name" type="text" class="form-control"/> <BR/>
        </fieldset>
        <fieldset class="form-group">
            <label>Пароль</label>
            <input name="password" type="text" class="form-control"/> <BR/>
        </fieldset>
        <fieldset class="form-group">
            <label>Your email (use as login)</label>
           <input name="email" type="text" class="form-control"/> <BR/>
        </fieldset>
        <fieldset class="form-group">
            <label for="phone">Your number:</label>
  <input  name="phone" type="text" class="form-control"/> <BR/>

           </fieldset>
        <fieldset class="form-group">
             <label for="status_user_checkbox">
             As admin <input type="checkbox" name="status_user"
             class="form-control" id="status_user" onclick="validate()"/>
             </label>
        </fieldset>


        <input type="submit" value="Sign Up" class="btn btn-success"/>
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