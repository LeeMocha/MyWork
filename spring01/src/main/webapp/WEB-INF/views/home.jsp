<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h2>** Hello Spring !!! **</h2>
	<P>The time on the server is ${serverTime}.</P>
	<hr>
	<c:if test="${ !empty sessionScope.loginName}">
		<h3>${ sessionScope.loginName }님안녕하세요</h3>
	</c:if>
	<c:if test="${ empty sessionScope.loginName }">
		<h3>로그인 후 이용하세요~~~</h3>
	</c:if>
	<c:if test="${!empty requestScope.message}">
		<hr> ${ requestScope.message } <hr>
	</c:if>
	<br>
	<img alt="" src="resources/images/white01.gif" width="300" height="200">
	<img alt="" src="resources/images/apple.png" width="300" height="200">
	<img alt="" src="resources/images/pink01.png" width="300" height="200">
	<br> &nbsp;
	<c:if test="${ empty sessionScope.loginName}">
		<a href="/web02/member/loginForm.jsp">LogIn</a>&nbsp;
		<a href="/web02/member/joinForm.jsp">Join</a>
	</c:if>
	<c:if test="${ not empty sessionScope.loginName }">
		<a href="/web02/logout">Logout</a>&nbsp;
		<a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
		<a href="/web02/mdelete" onclick="return delchk();">회원탈퇴</a>&nbsp;
	</c:if>
	<br>
	<br> &nbsp;
	<a href="mlist">MList</a>&nbsp;
	<a href="mdetail">MyInfo</a>&nbsp; &nbsp;
	<a href="mlistsp">MListSp</a>&nbsp;
	<a href="mdetailsp">MyInfoSp</a>&nbsp;
</body>

<script type="text/javascript">
	function delchk() {
		return confirm("정말로 탈퇴 하시겠습니까?");
	}
</script>
</html>