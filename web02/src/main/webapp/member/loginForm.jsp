<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** LoginForm **</title>
</head>
<body>
<h2>** Web MVC2 LoginForm **</h2>
<form action="/web02/login" method="post">
<table>
	<tr height="40">
		<td style="color:#656565;"><label for="id">I D :</label></td>
		<td><input type="text" id="id" name="id" size ="30"></td>
	</tr>
	<tr height="40">
		<td style="color:#656565;" ><label for="password">Password: </label></td>
		<td><input type="password" id="password" name="password" size="30"></td>
	</tr>
	<tr height="40">
		<td></td>
		<td><input type="submit" value="로그인">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
<hr>


<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr><br>
</c:if>

&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>