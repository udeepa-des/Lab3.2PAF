<%@page import="java.sql.Connection"%>
<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if(request.getParameter("itemCode") != null)
	{
		Item item = new Item();
		
		String stsmsg  = item.insertItem(request.getParameter("itemCode"),
										request.getParameter("itemName"),
										request.getParameter("itemPrice"),
										request.getParameter("itemDesc"));
		
		session.setAttribute("statusMsg", stsmsg);
		
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
</head>
<body>
	<h1>Items Management</h1>
	<form method="post" action="items.jsp">
		Item code : <input name="itemCode" type="text"> <br> Item
		name : <input name="itemName" type="text"> <br> Item
		price: <input name="itemPrice" type="text"><br> Item
		description: <input name="itemDesc" type="text"><br> <input
			name="btnSubmit" type="submit" value="save">
	</form>
	<%
			out.print(session.getAttribute("statusMsg"));
		
		%>
	<br>
	<%
		Item item = new Item();
		out.print(item.readItems());
		%>


</body>
</html>