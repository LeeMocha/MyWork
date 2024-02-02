<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MVC2 Password Update **</title>
<script src="/spring02/resources/myLib/inCheck.js"></script>
<link rel="stylesheet" href="/spring02/resources/myLib/myStyle.css" type="text/css"/>
<script>
	let pCheck = false;
	let p2Check = false;
	
	onload = function(){
		document.getElementById("password").focus();
		
		document.getElementById('password').addEventListener('keydown',
		       (e)=>{
		          if (e.which==13) {
		             e.preventDefault();
		             document.getElementById('password2').focus();
		          }//if
		       });
		// -> 무결성 점검 
		document.getElementById('password').addEventListener('focusout', ()=>{ pCheck = pwCheck(); });
		
		// password2
		document.getElementById('password2').addEventListener('keydown',
		       (e)=>{
		          if (e.which==13) {
		             e.preventDefault();
		             document.getElementById('submitTag').focus();
		          }//if
		       });
		// -> 무결성 점검 
		document.getElementById('password2').addEventListener('focusout', ()=>{ p2Check = pw2Check(); });
		
	}
	
	function inCheck() {

		if (!pCheck) {document.getElementById('pMessage').innerHTML = '필수입력, password 를 확인하세요';}
		if (!p2Check) {document.getElementById('p2Message').innerHTML = '필수입력, password2 를 확인하세요';	}

		if (pCheck && p2Check) {
			// => submit 확인
			if (confirm("정말 Password를 수정 하시겠습니까? ( Yes:확인 / No:취소 )")) {
				// => submit 진행
				return true;
			} else {
				alert("~~~ 수정이 취소 되었습니다. ~~~");
				return false;
			} // confirm
		} else {
			return false;
		} // Check_조건
	} // inCheck
</script>
</head>
<body>
<div align="center">
<h2>** Spring_MVC2 Password Update **</h2>
<br><br><br>
<br><b>** 새로운 Password 를 입력 하세요 ~~~ </b><br>
<form action="pwUpdate" method="post">
	<table>
		<tr height="40px">
			<td bgcolor="lightCyan"><label>Password</label></td>
			<td><input type="password" id="password" name="password">
				<br><span id="pMessage" class="eMessage"></span>			
			</td>
		</tr>
		<tr height="40px">
			<td bgcolor="lightCyan"><label>재 확 인</label></td>
			<td><input type="password" id="password2" placeholder="반드시 입력 하세요">
				<br><span id="p2Message" class="eMessage"></span>			
			</td>
		</tr>
		<tr height="40px">
			<td></td>
			<td><input type="submit" value="수정" onclick="return inCheck()" id="submitTag">&nbsp;&nbsp;
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>
<c:if test="${not empty requestScope.message}">
<hr>
=> ${requestScope.message}<br><hr><br>
</c:if>
<br>
<hr>
<br>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</div>
</body>
</html>