<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Axios MemberList **</title>
</head>
<body>
<h2>** Spring_Boot Axios MemberList **</h2>
<hr>
<c:if test="${ !empty request.message }">
=> ${ request.message }<br><hr>
</c:if>
<table border="1" style="width:100%">
	<tr bgcolor="hotpink">
		<th>ID</th>
		<!-- <th>Password</th> -->
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
		<th>Birthday</th>
		<th>추천인</th>
		<th>Image</th>
		<th>Delete</th>
	</tr>
	<c:if test="${ !empty requestScope.banana }">
		<c:forEach var="m" items="${ requestScope.banana }">
			<tr>
				<!-- ** idblist : id별 boardList
					=> 선택된 id를 function 에 전달 (매개변수를 활용)
					idblist('banana') 처럼 -->
				<td><span class="textlink" onclick="idblist('${m.id}')">${ m.id }</span></td>
				<%-- <td>${ m.password }</td> --%>
				<td>${ m.name }</td>
				<td>${ m.age }</td>
				<td>${ m.jno }</td>
				<td>${ m.info }</td>
				<td>${ m.point }</td>
				<td>${ m.birthday }</td>
				<td>${ m.rid }</td>
				<td style="text-align:center;"><img alt="myImage"  src="/resources/uploadImages/${ m.uploadfile }" width="40" height="50"></td>
				<td><span class="textlink" onclick="axidelete('${m.id}')" id="${ m.id }" >Delete</span></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${ empty requestScope.banana }">
		<tr><td colspan="10"> </td></tr>
	</c:if>

</table>
<c:if test="${!empty requestScope.message}">
		<hr> ${ requestScope.message }<br><hr>
</c:if>

</body>
</html>