<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JoinForm **</title>
</head>
<body>
<h2>**  Web MVC2 JoinForm **</h2>
<form action="/web02/mjoin" method="post">
<table>
		<tr height="40">
			<td bgcolor="skyblue"><label for="id">I D </label></td>
			<td><input type="text" id="id" name="id" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="password">Password </label></td>
			<td><input type="password" id="password" name="password" placeholder="특수문자 필" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="name">Name </label></td>
			<td><input type="text" id="name" name="name" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="age">Age </label></td>
			<td><input type="text" id="age" name="age" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="jno">Jno </label></td>
			<td>
				<select name="jno" id="jno">
					<option value="1">1조: Business</option>
					<option value="2">2조: Static</option>
					<option value="3">3조: 칭찬해조</option>
					<option value="4">4조: 카톡으로얘기하조</option>
					<option value="7">7조: 칠면조(관리)</option>
				</select>
			</td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="info">Info </label></td>
			<td><input type="text" id="info" name="info" placeholder="자기소개 & 희망사항" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="point">Point</label></td>
			<td><input type="text" id="point" name="point" placeholder="실수 입력" size ="30"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="birthday">Birthday </label></td>
			<td><input type="date" id="birthday" name="birthday"></td>
		</tr>
		<tr height="40">
			<td bgcolor="skyblue"><label for="rid">추천인 </label></td>
			<td><input type="text" id="rid" name="rid" size ="30"></td>
		</tr>
		<tr height="40">
			<td></td>
			<td><input type="submit" value="회원가입">&nbsp;&nbsp;
				<input type="reset" value="취소">
			</td>
		</tr>
</table>
</form>

<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr><br>
</c:if>

&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>