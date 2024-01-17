<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** My Information **</title>
</head>
<body>
<h2>** My Information **</h2>
<br>
<table border = 1 style="width: 100%">
	<tr bgcolor="orange">
		<th>Sno</th>
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
	</tr>
	<c:if test="${ !empty requestScope.apple }">
		<tr>
			<td>${ requestScope.apple.sno }</td>
			<td>${ requestScope.apple.name }</td>
			<td>${ requestScope.apple.age }</td>
			<td>${ requestScope.apple.jno }</td>
			<td>${ requestScope.apple.info }</td>
			<td>${ requestScope.apple.point }</td>
		</tr>
	</c:if>
	<c:if test="${ empty requestScope.apple }">
		<tr>
			<td colspan="6"><h3>** 출력할 자료가 없습니다.</h3></td>
		</tr>
	</c:if>
</table>
<br><br><h2><a href='javascript:history.go(-1)'> 이전으로</a></h2>
</body>
</html>