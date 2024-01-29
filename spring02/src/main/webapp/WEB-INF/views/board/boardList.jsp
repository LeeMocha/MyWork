<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MVC02 BoardList **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
</head>
<body>
<h2>** Spring_MVC02 BoardList **</h2>
<hr>
<c:if test="${ !empty request.message }">
=> ${ request.message }<br><hr>
</c:if>
<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table border="1" style="width:85%;">
	<tr bgcolor="Orange">
		<th>글번호</th>
		<th>제 목</th>
		<th>작성자</th>
		<th>작성시간</th>
		<th>조회수</th>
	</tr>
	<c:if test="${ !empty requestScope.banana }">
		<c:forEach var="b" items="${ requestScope.banana }">
			<tr>
				<td>${ b.seq }</td>
				<td>
				<c:if test="${!empty sessionScope.loginID}">
				<a href="detail?jCode=D&seq=${ b.seq }">${ b.title }</a>
				</c:if>
				<c:if test="${empty sessionScope.loginID}">
					${ b.title }
				</c:if>
				</td>
				<td>${ b.id }</td>
				<td>${ b.regdate }</td>
				<td>${ b.cnt }</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${ empty requestScope.banana }">
		<tr><td colspan="5">~~~ 출력할 게시글이 없습니다 ~~~</td></tr>
	</c:if>

</table>
</div>
<c:if test="${!empty requestScope.message}">
		<hr> ${ requestScope.message }<br><hr>
</c:if>
<c:if test="${ !empty sessionScope.loginID }">
<hr><br>
&nbsp;<a href="boardInsert"> 글쓰기 </a> &nbsp;
<br>
</c:if>
<br><hr>
&nbsp;<a href="/spring02/home"> home </a> &nbsp;
&nbsp;<a href="javascript:history.go(-1)"> 이전으로 </a> &nbsp;

</body>


</body>
</html>