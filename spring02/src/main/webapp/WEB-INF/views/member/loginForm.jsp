<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** LoginForm **</title>
<script src="/spring02/resources/myLib/inCheck.js"></script>
<link rel="stylesheet" href="/spring02/resources/myLib/myStyle.css" type="text/css"/>
<script>
'use strict'

let iCheck = false;
let pCheck = false;

onload = function(){
	   document.getElementById('id').focus();
	   // id
	   document.getElementById('id').addEventListener('keydown',
		         (e)=>{
		            if (e.which==13) {
		               e.preventDefault();
		               // => form 에서는
		               // => enter 누르면 자동 submit 발생되므로 이를 제거함
		               document.getElementById('password').focus();
		            }//if
		         });
		   // -> 무결성 점검 
	   document.getElementById('id').addEventListener('focusout', ()=>{ iCheck = idCheck(); });
		   
	   // Password
	   document.getElementById('password').addEventListener('keydown',
		         (e)=>{
		            if (e.which==13) {
		               //e.preventDefault();
		               //document.getElementById('submitTag').focus();
		               if(!pCheck) pCheck = pwCheck();
		               else if(!iCheck) iCheck = idCheck();
		               else document.getElementById('myForm').submit();
		               // => password 에서 입력후 Enter_Key 누르면 바로 submit 진행 되도록~~
		               //     type="submit" 을 사용하는경우 정확하게 적용하기 어려워 적용하지 않음    
		               //if (!iCheck) iCheck=idCheck();
		               //else if (!pCheck) pCheck=pwCheck();
		               //else document.getElementById('myForm').submit();
		            }//if
		         });
	   document.getElementById('password').addEventListener('focusout', ()=>{ pCheck = pwCheck(); });
}

function inCheck() {

	if (!iCheck) {document.getElementById('iMessage').innerHTML = '필수입력, id 를 확인하세요';}
//	if (!pCheck) {document.getElementById('pMessage').innerHTML = '필수입력, password 를 확인하세요';}

	if (iCheck && pCheck){
		return true;
	} else {
		return false;
	}
} // inCheck
</script>
</head>
<body>
	<h2>** Web MVC2 LoginForm **</h2>
	<form action="login" method="post" id="myForm">
		<table>
			<tr height="40">
				<td style="color: #656565;" bgcolor="Gold"><label for="id">I
						D :</label></td>
				<td><input type="text" id="id" name="id" size="30"> <br>
					<span id="iMessage" class="eMessage"></span></td>
			</tr>
			<tr height="40">
				<td style="color: #656565;" bgcolor="Gold"><label
					for="password">Password: </label></td>
				<td><input type="password" id="password" name="password"
					size="30"> <br> <span id="pMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<td></td>
				<td><input type="submit" id="submitTag" value="로그인" onclick="return inCheck()">&nbsp;&nbsp; <input
					type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
	<hr>


	<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br>
		<hr>
		<br>
	</c:if>

	&nbsp;
	<a href="/spring02/home">Home</a>&nbsp;

</body>
</html>