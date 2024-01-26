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
<h2>** Spring02_MVC2 UpdateForm **</h2>
<c:set value="${ requestScope.apple }" var="j"/>
<form action="update" method="post">
<table>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="id"> JNO </label></td>
			<td><input type="text" id="jno" name="jno" size ="30" value="${ j.jno }" readonly ></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="password"> JNAME </label></td>
			<td><input type="text" id="jname" name="jname" value="${ j.jname }" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="name"> CAPTAIN </label></td>
			<td><input type="text" id="captain" name="captain" value="${ j.captain }" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="age"> PROJECT </label></td>
			<td><input type="text" id="project" name="project" value="${ j.project }" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="info"> SLOGAN </label></td>
			<td><input type="text" id="slogan" name="slogan" value="${ j.slogan }" size ="30"></td>
		</tr>
		<tr height="40">
			<td></td>
			<td><input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="취소">
			</td>
		</tr>
</table>
</form>

<c:if test="${not empty requestScope.message}">
<hr>
=> ${requestScope.message}<br><hr><br>
</c:if>

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>