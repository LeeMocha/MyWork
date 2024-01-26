<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
</head>
<body>
<h2>** Spring_02 Jo Information **</h2>

<c:set value="${requestScope.apple}" var="j" />
<br>
<table border=1>
	<c:if test="${ !empty requestScope.apple }">
		<tr height="40">
			<th bgcolor="yellow"> JNO </th><td>${ j.jno }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> JNAME </th><td>${ j.jname }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> CAPTAIN </th><td>${ j.captain }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> PROJECT </th><td>${ j.project }</td>
		</tr>
		<tr height="40">
			<th bgcolor="yellow"> SLOGAN </th><td>${ j.slogan}</td>
		</tr>
	</c:if>
	<c:if test="${ empty requestScope.apple }">
		<tr>
			<td colspan="6"><h3>** 출력할 자료가 없습니다.</h3></td>
		</tr>
	</c:if>
</table>
<c:if test="${!empty requestScope.message}">
		<hr><br> ${ requestScope.message }<br><br><hr>
</c:if>
<br>
&nbsp;<a href="detail?jCode=${ -j.jno }">정보 수정</a>&nbsp;
&nbsp;<a href="delete?jCode=${ j.jno }" onclick="return deljochk();">조 삭제</a>&nbsp;
<hr>
<br>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
	
</body>
<script type="text/javascript">
function deljochk(){
	return confirm("정말로 조를 삭제하시겠습니까?");	
}
</script>
</html>