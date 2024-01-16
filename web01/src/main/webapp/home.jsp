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
	<h2>** Dynamic Web Project **</h2>
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
		<c:if test="${ !empty name}">
		${ sessionScope.name }님 
	</c:if>
		안녕하세요
	</h3>

	<form action="getpost" method="get">
		<input type="text" name="id" value="banana"> &nbsp; <input
			type="text" name="name" value="바나나"> &nbsp; <input
			type="text" name="password"> &nbsp; <input type="submit"
			Value="Test">
	</form>
	<hr>
	<!-- ** 경로 표기
		=> 절대 경로 : '/' 로시작, 프로젝트명부터 전체경로 표기 /web01/images/letsgo.png width="300" height="200"
		=> 상대 경로 : 현재위치에서 시작, /로 시작하면 안됨
			-> ./ : 현재위치, ../1단계 상위
			-> "./images/letsgo.png", "images/letsgo.png" 동일
		=> 다만, 리소스들은 webapp 안에 있다고 인식하기 때문에 webapp 까지는 생략 가능
		=> 이미지 파일을 webapp 에 넣고 Project Explorer 에 폴더가 안생기면 인지하지 못함
	 -->
	<img alt="" src="/web01/images/letsgo.png" width="300" height="200">
	<hr>
	&nbsp;
	<c:if test="${ empty name}">
		<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">LogIn</a>&nbsp;
		<a href="#">Join</a>
	</c:if>
	<c:if test="${ not empty name }">
		<a href="/web01/logout">Logout</a>&nbsp;
		<a href="#">MyInfo</a>
	</c:if>
	<br>
	<br> &nbsp;
	<a href="/web01/hello">HelloS</a>&nbsp; &nbsp;
	<a href="/web01/list">MVC01List</a>&nbsp; &nbsp;
	<a href="/web01/life">LifeCycle</a>
	<br> &nbsp;
	<a href="/web01/servletTestForm/form01_Adder.html">Adder</a>&nbsp;
	&nbsp;
	<a href="/web01/servletTestForm/form02_Radio.jsp">Radio</a>&nbsp;
	&nbsp;
	<a href="/web01/servletTestForm/form03_Check.jsp">Check</a>&nbsp;
	&nbsp;
	<a href="/web01/servletTestForm/form04_Select.jsp">Select</a>
	<br> &nbsp;
	<a href="/web01/flow01">Flow01</a>&nbsp; &nbsp;
	<a href="/web01/sessioni">SessionI</a>&nbsp;
	<br> &nbsp;
	<a href="/web01/jsp01/ex01_HelloJsp.jsp">HelloJ</a>&nbsp; &nbsp;
	<a href="/web01/jsp01/ex02_mvc01List.jsp">M01ListJ</a>&nbsp; &nbsp;
	<a href="/web01/list2">M02ListJ</a>&nbsp;
</body>
</html>