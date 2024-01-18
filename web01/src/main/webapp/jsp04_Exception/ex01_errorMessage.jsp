<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!-- 이 페이지는 에러페이지 임을 인지 시켜주는 페이지 디렉티브 
	 기본 값은  isErrorPage = "false"
-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Error Message Page **</title>
</head>
<body style="width:100%;height:100%;line-height:100%;">
<h3>** Error Message Page **
<pre>
=>

=> 서비스 처리과정에서 <%= exception.getClass().getSimpleName() %>
   오류가 발생 했습니다.
   잠시후 다시 해주세요 ~~
   
=> Exception Type : <%= exception.getClass().getName() %>
=> Exception toString : <%= exception.toString() %>
=> Exception getMessage : <%= exception.getMessage() %>
</h3></pre>
<br>
<div style="height:100px; width:100%; display:flex; justify-content:center; background-color:black;" >
<h1 style="display:inline-block; font-size:60px; color:red;">ERROR!!!!!!!</h1>
</div>
</body>
</html>