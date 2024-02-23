<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>** Spring_MVC2 Insert Form **</h2>
<form action="insert" method="post">
<table>
		<tr height="40">
			<td bgcolor="skyblue"><label for="id"> JNO </label></td>
			<td><input type="text" id="jno" name="jno" placeholder="조 번호 중복 불가" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="jname"> JNAME </label></td>
			<td><input type="text" id="jname" name="jname" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="captain"> CAPTAIN </label></td>
			<td><input type="text" id="captain" name="captain" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="project"> PROJECT </label></td>
			<td><input type="text" id="project" name="project" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="slogan"> SLOGAN </label></td>
			<td><input type="text" id="slogan" name="slogan" size ="30"></td>
		</tr>
		<tr height="40">
			<td></td>
			<td><input type="submit" value="조 생성">&nbsp;&nbsp;
				<input type="reset" value="취소">
			</td>
		</tr>
</table>
</form>

<c:if test="${not empty requestScope.message}">
${requestScope.message}<br><hr><br>
</c:if>

&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>