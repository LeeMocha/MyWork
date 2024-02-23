<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css" >
</head>
<body>
<h2>** Spring02 Jo List **</h2>
<hr>
<c:if test="${ !empty request.message }">
=> ${ request.message }<br><hr>
</c:if>
<table border="1" style="width:100%">
	<tr bgcolor="Lime">
		<th>Jno</th>
		<th>JName</th>
		<th>Captain</th>
		<th> 조장명 </th>
		<th>Project</th>
		<th>Slogan</th>
	</tr>
	<c:if test="${ !empty requestScope.banana }">
		<c:forEach var="j" items="${ requestScope.banana }">
			<tr>
					<td>${ j.jno }</td>
					<td><a href="detail?jCode=${ j.jno }">${ j.jname }</a></td>
					<td>${ j.captain }</td>
					<td>${ j.cname }</td>
					<td>${ j.project }</td>
					<td>${ j.slogan }</td>
				
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
<br>
&nbsp;<a href="insertForm">조 삽입</a>&nbsp;
<br>
<hr>
&nbsp;<a href="/home"> home </a> &nbsp;
&nbsp;<a href="javascript:history.go(-1)"> 이전으로 </a> &nbsp;
</body>
</html>