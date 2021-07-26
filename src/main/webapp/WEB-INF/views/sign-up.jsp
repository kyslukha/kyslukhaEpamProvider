<%@ page pageEncoding="UTF-8"%>
<%@ include file="../common/page.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
crossorigin="anonymous">
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
   <h1 class="text"><fmt:message key="sign_jsp.button.login"/></h1>
       <hr>
    <form method="POST" action="/sign-up.do">
        <fieldset class="form-group">
            <label><fmt:message key="sign_jsp.label.name"/></label>
            <input name="name" type="text" class="form-control"/> <BR/>
        </fieldset>
        <fieldset class="form-group">
            <label><label><fmt:message key="sign_jsp.label.password"/></label>
            <input name="password" type="text" class="form-control"/> <BR/>
        </fieldset>
        <fieldset class="form-group">
            <label><label><fmt:message key="sign_jsp.label.email"/></label>
           <input name="email" type="text" class="form-control"/> <BR/>
        </fieldset>
        <fieldset class="form-group">
            <label for="phone"><label><fmt:message key="sign_jsp.label.phone"/></label>
  <input  name="phone" type="text" class="form-control"/> <BR/>

           </fieldset>
        <fieldset class="form-group">
             <label for="statusUser_checkbox">
             <label><fmt:message key="sign_jsp.label.administrator"/><input type="checkbox" name="statusUser"
              id="statusUser" onclick="validate()"/>
             </label>
        </fieldset>


        <input type="submit" value='<fmt:message key="sign_jsp.button.login"/>' class="btn btn-success"/>
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