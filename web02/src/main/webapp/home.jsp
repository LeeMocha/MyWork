<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- contentType="text/html; charset=UTF-8  : 이 페이지가 최종적으로 reponse 에 담아져서 웹브러우저에 보내지기 때문에 reponse의 설정값
    	 pageEncoding="UTF-8 : 톰캣이 서블릿에다가 보내는 설정 값
    -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 웹브라우저가 확인해야하는 Charset -->
<title>Insert title here</title>
</head>
<body>
	<h2>** Web02_MVC02 **</h2>
	<%-- 	<% if (session.getAttribute("name") != null) { %>
		<h3><%= session.getAttribute("name") %> 님 안녕하세요 ~~~ </h3>
	<%
	} else{ %>
		<h3>로그인 후 이용 하세요 ~~</h3>
	<%}%> --%>
	<!-- => 아래 JSTL 과 비교 -->

	<%--	<c:set value="${ sessionScope.name }" var="name"></c:set>
 	<c:if test="${ !empty name}">
		<h3>${ sessionScope.name }님 안녕하세요</h3>
	</c:if>
	<c:if test="${ empty name }">
		<h3>로그인 후 이용하세요~~~</h3>
	</c:if>
	<c:set value="${ sessionScope.name }" var="name"></c:set> --%>

	<h3>
		<c:if test="${ !empty sessionScope.loginName}">
		${sessionScope.loginId}(${sessionScope.loginName})님 
	</c:if>
		안녕하세요
	</h3>
	<img alt="" src="/web02/images/letsgo.png" width="300" height="200">
	<hr>
	<br>
	&nbsp;
	<c:if test="${ empty sessionScope.loginName}">
		<a href="/web02/member/loginForm.jsp">LogIn</a>&nbsp;
		<a href="/web02/member/joinForm.jsp">Join</a>
	</c:if>
	<c:if test="${ not empty sessionScope.loginName }">
		<a href="/web02/logout">Logout</a>&nbsp;
		<a href="/web02/mdetail">MyInfo</a>&nbsp;
		<a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
		<a href="/web02/mdelete" onclick= "return delchk();">회원탈퇴</a>&nbsp;
	</c:if>
	<br>
	<br>
	&nbsp;<a href="/web02/mlist">MList</a>&nbsp;
</body>

<script type="text/javascript">
function delchk(){
       return confirm("정말로 탈퇴 하시겠습니까?");
}
</script>
</html>