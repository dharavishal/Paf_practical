<%@ page import = "com.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



 <%
 	//Save-------------------------------
 	if(request.getParameter("code") != null)
 	{
 		Item  itemObj = new Item();
 		String stsMsg = "";
 		
 		//Insert-----------------
 		
 		if (request.getParameter("hidItemIDSave")  == "")
 		{
 			stsMsg = itemObj.insertItem(request.getParameter("code"),
 		 		request.getParameter("name"),
 		 		request.getParameter("price"),
 		 		request.getParameter("des"));
 		
     	}
 	else
 	 //Update---------------------------------
 	{
 		stsMsg = itemObj.updateItem(request.getParameter("hidItemIDSave"),
 				request.getParameter("code"),
 				request.getParameter("name"),
 				request.getParameter("price"),
 				request.getParameter("des"));
 		
 	}
 		
 		session.setAttribute("statusMsg", stsMsg);
}
 
 	//Delete----------------------------------
 	
	 if(request.getParameter("hidItemIDDelete") != null){
 		
 		Item i = new Item();
 		String stsMsg = i.deleteItem(request.getParameter("hidItemIDDelete"));
 		session.setAttribute("statusMsg", stsMsg);
 	}
 	
 	
 	
 
 
 %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Insert.js"></script>

</head>
<body>
<h1>Funding Project Management</h1>

<form id="formItem" name="formItem" action="items.jsp" method="post">
	
	Item Code: <input id="code" name="code" type="text" class="form-control form-control-sm"><br><br>
	Item Name: <input id="name" name="name" type="text" class="form-control form-control-sm"><br><br>
	Item Price: <input id="price" name="price" type="number" class="form-control form-control-sm"><br><br>
	Item Description: <input id="des" name="des" type="text" class="form-control form-control-sm"><br><br>
	<input id="btnSubmit" name="btnSubmit" type="submit" value="Save" class="btn btn-primary"><br><br>
	<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	
</form>

<%
	out.print(session.getAttribute("statusMsg"));
%>


<br>

<%
	Item i = new Item();
	out.print(i.readItems());
%>


</body>
</html>