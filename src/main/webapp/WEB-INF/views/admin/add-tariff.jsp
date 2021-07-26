<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common/page.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
     <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
     <link rel="stylesheet" href="/resources/demos/style.css">
     <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
     <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

 </head>
<title>Добавить новый тариф</title>
<label>Добавление  нового тарифа</label>
<div class="container">

<form method="POST" action="/admin/add-tariff.do">
		  <label>Название тарифа</label>
<fieldset class="form-control">
                <input name="title" type="text" class="form-control" required/>
                </fieldset>
                <label>Цена</label>
                <fieldset class="form-control">
                 <input name="priceByDay" type="text" class="form-control" required/>
                </fieldset>
                <fieldset class="form-group">
                <label>Услуги:</label>
                <select id="services" name="services" multiple required>
                                    <c:forEach items="${listServices}" var="titleServices">
                                        <option value="${titleServices}">${titleServices}</option>
                                    </c:forEach>
                                </select>
                </fieldset>




                <input name="add" type="submit" class="btn btn-info" value="Добавить"/>
            </form>
        </div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>