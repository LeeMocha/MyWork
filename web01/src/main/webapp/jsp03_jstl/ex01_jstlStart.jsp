<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
taglib 디렉티브 : 라이브러리를 이 jsp 에서 쓸 수 있게(현재문서_Page 가 인식할 수 있도록) 정의, 선언하는 디렉티브. 2가지 요소가 필요함  
 1) uri : 라이브러리 경로(외부에서 불러와야함)
 2) prefix : 접두어 (Core : c , XML : x , Formatting : fmt , Function(EL) : fn) 
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL Start **</title>
</head>
<body>
<h2>** JSTL Start **</h2>
<pre><b>
=> Jstl Library 를 정의 (현재문서_Page 가 인식할 수 있도록)
   디렉티브 taglib 에 uri=".." prefix=".."
1. 출력 : out Tag
=> Java의 out 객체, 표현식, EL역할

<c:out value="~~ Hello JSTL !!! 안녕 ~~" /> 
<!--닫는 태그 앞에 아무것도 없을 경우 태그 생략 가능 -->


2. 변수 정의
=> set
<c:set value="홍길동" var="name"/>
<c:set value="22" var="age"/>

3. 변수 출력 (out_tag, EL)
=> JSTL 의 out_Tag
* name = <c:out value="${ name }" />
* name = <c:out value="${ age }" />
=> EL
* name = ${ name }
* name = ${ age }
* age*100 = ${ age*100 }
=> Java 는 Jstl 변수와 호환 되는가? : 자바의 변수가 아니기 때문에 안된다
<%-- * name = <%= name %> --%>










</b></pre>
</body>
</html>