<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring01 MemberList **</title>
</head>
<body>
<h2>** Spring01 MemberList **</h2>
<hr>
<c:if test="${ !empty request.message }">
=> ${ request.message }<br><hr>
</c:if>
<table border="1" style="width:100%">
	<tr bgcolor="cyan">
		<th>ID</th>
		<th>Password</th>
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
		<th>Birthday</th>
		<th>추천인</th>
	</tr>
	<c:if test="${ !empty requestScope.banana }">
		<c:forEach var="m" items="${ requestScope.banana }">
			<tr>
				<td>${ m.id }</td>
				<td>${ m.password }</td>
				<td>${ m.name }</td>
				<td>${ m.age }</td>
				<td>${ m.jno }</td>
				<td>${ m.info }</td>
				<td>${ m.point }</td>
				<td>${ m.birthday }</td>
				<td>${ m.rid }</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${ empty requestScope.banana }">
		<tr><td colspan="9"> </td></tr>
	</c:if>

</table>
<c:if test="${!empty requestScope.message}">
		<hr> ${ requestScope.message }<br><hr>
</c:if>
<hr>
&nbsp;<a href="/spring02/home"> home </a> &nbsp;
&nbsp;<a href="javascript:history.go(-1)"> 이전으로 </a> &nbsp;

</body>
</html>