<%@ page import = "com.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-1.9.1.min.js"></script>
<script src="Components/items.js"></script>

</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Items Management</h1>

<form id="formItem" name="formItem">
	
	Item Code: <input id="code" name="code" type="text" class="form-control form-control-sm"><br><br>
	Item Name: <input id="name" name="name" type="text" class="form-control form-control-sm"><br><br>
	Item Price: <input id="price" name="price" type="number" class="form-control form-control-sm"><br><br>
	Item Description: <input id="des" name="des" type="text" class="form-control form-control-sm"><br><br>
	<input id="btnSubmit" name="btnSubmit" type="submit" value="Save" class="btn btn-primary"><br><br>
	<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
	out.print(session.getAttribute("statusMsg"));
%>


<br>

<%
	Item i = new Item();
	out.print(i.readItems());
%>

</div> </div> </div>
</body>
</html>