<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Member Detail ** </title>
</head>
<body>
<h2>** My Information **</h2>

<c:set value="${requestScope.apple}" var="m" />

<table border=1>
	<c:if test="${ !empty requestScope.apple }">
		<tr height="40">
			<th bgcolor="yellow"> I D </th><td>${ m.id }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> NAME </th><td>${ m.name }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> AGE </th><td>${ m.age }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> JNO </th><td>${ m.jno }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> INFO </th><td>${ m.info }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> POTIN </th><td>${ m.point }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> BIRTHDAY </th><td>${ m.birthday }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> 추천인 </th><td>${ m.rid }</td>
		</tr>
	</c:if>
	<c:if test="${ empty requestScope.apple }">
		<tr>
			<td colspan="6"><h3>** 출력할 자료가 없습니다.</h3></td>
		</tr>
	</c:if>
</table>

<hr>
<br>
&nbsp;<a href="">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>