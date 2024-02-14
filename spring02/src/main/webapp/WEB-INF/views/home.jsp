<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
</head>
<body>
	<h2>** Hello Spring_MVC02 !!! **</h2>
	<P>Home_time : ${serverTime}.</P>
	<hr>
	<c:if test="${ !empty sessionScope.loginName}">
		<h3><br>${ sessionScope.loginName }님 안녕하세요</h3>
	</c:if>
	<c:if test="${ empty sessionScope.loginName }">
		<h3>로그인 후 이용하세요~~~</h3><hr>
	</c:if>
	<c:if test="${!empty requestScope.message}">
		${ requestScope.message }<br><hr>
	</c:if>
	<br>
	<img alt="mainImage1" src="/spring02/resources/images/karina1.gif" width="300" height="400" >
	<img alt="mainImage2" src="/spring02/resources/images/karina2.gif" width="300" height="400" >
	<br> &nbsp;
	<br>
	<!--  Login 전  -->
	<c:if test="${ empty sessionScope.loginName}">
		&nbsp;
		<a href="member/loginForm">LogIn</a>&nbsp;
		<a href="member/joinForm">Join</a>
	</c:if>
	<!--  Login 후  -->
	<c:if test="${ not empty sessionScope.loginName }">
		&nbsp;
		<a href="member/logout" onclick="return logoutchk();">Logout</a>&nbsp;
		<a href="member/detail?jCode=D">MyInfo</a>&nbsp; &nbsp;
		<a href="member/detail?jCode=U">내정보수정</a>&nbsp;
		<a href="member/delete" onclick="return delchk();">회원탈퇴</a>&nbsp;
	</c:if>
	<br><br>
	<hr>
	<br>
	 &nbsp;
	<a href="member/memberList">MList</a>&nbsp;
	<a href="jo/joList">JList</a>&nbsp;
	<a href="board/boardList">BList</a>&nbsp;
	<a href="bcrypt">BCrypt</a>&nbsp;
	<br>
	<br>
	&nbsp;
	<a href="board/bPageList">BPage</a>&nbsp;
</body>
<script type="text/javascript">
	function delchk() {
		return confirm("정말로 탈퇴 하시겠습니까?");
	}
	function logoutchk() {
		return confirm("정말로 로그아웃 하시겠습니까?");
	}
</script>
</html>
