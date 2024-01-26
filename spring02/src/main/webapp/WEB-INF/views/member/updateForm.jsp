<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Update Form **</title>
</head>
<body>
<h2>**  Web MVC2 UpdateForm **</h2>
<c:set value="${ requestScope.apple }" var="m"/>
<form action="update" method="post">
<table>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="id">I D </label></td>
			<td><input type="text" id="id" name="id" size ="30" value="${ m.id }" readonly ></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="password">Password </label></td>
			<td><input type="password" id="password" name="password" value="${ m.password }" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="name">Name </label></td>
			<td><input type="text" id="name" name="name" value="${ m.name }" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="age">Age </label></td>
			<td><input type="text" id="age" name="age" value="${ m.age }" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="jno">Jno </label></td>
			<td>
				<select name="jno" id="jno" >
					<option value="1" ${ m.jno eq 1 ? "selected" : "" } >1조: Business</option>
					<option value="2" ${ m.jno eq 2 ? "selected" : "" } >2조: Static</option>
					<option value="3" ${ m.jno eq 3 ? "selected" : "" } >3조: 칭찬해조</option>
					<option value="4" ${ m.jno eq 4 ? "selected" : "" } >4조: 카톡으로얘기하조</option>
					<option value="7" ${ m.jno eq 7 ? "selected" : "" } >7조: 칠면조(관리)</option>
				</select>
			</td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="info">Info </label></td>
			<td><input type="text" id="info" name="info" value="${ m.info }" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="point">Point</label></td>
			<td><input type="text" id="point" name="point" value="${ m.point }" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="birthday">Birthday </label></td>
			<td><input type="date" id="birthday" name="birthday" value="${ m.birthday }"></td>
		</tr>
		<tr height="40">
			<td bgcolor="LightPueple" style="color:white;"><label for="rid">추천인 </label></td>
			<td><input type="text" id="rid" name="rid" value="${ m.rid }"  size ="30"></td>
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