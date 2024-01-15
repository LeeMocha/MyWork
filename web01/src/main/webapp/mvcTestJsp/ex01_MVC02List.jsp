<%@page import="mvcTest.StudentDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MVC02_List_Java **</title>
</head>
<body>
	<h2>** Jsp StudentList_MVC02 **</h2>
	<h3>=> service -> 결과 -> 출력</h3>

	<%
		List<StudentDTO> list = (List<StudentDTO>)request.getAttribute("myList");	
	%>
	<table border = 1 style="width: 100%">
	<tr bgcolor="pink">
		<th>Sno</th>
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
	</tr>
	<%
	if(list != null){
	for(StudentDTO s : list) { %>
	<tr>
		<td><%= s.getSno() %></td>
		<td><%= s.getName() %></td>
		<td><%= s.getAge() %></td>
		<td><%= s.getJno() %></td>
		<td><%= s.getInfo() %></td>
		<td><%= s.getPoint() %></td>
	</tr>
	<% 	}
		
	} else { %>
		<h3>** 출력할 자료가 없습니다.</h3>
	<% } %>
	</table>
	
	
</body>
</html>