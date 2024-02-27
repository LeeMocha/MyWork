<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Axios MemberList **</title>
</head>
<body>
<h2>** Spring_Boot Axios MemberList **</h2>
<hr>
<c:if test="${ !empty request.message }">
=> ${ request.message }<br><hr>
</c:if>
<table border="1" style="width:100%">
	<tr bgcolor="hotpink">
		<th>ID</th>
		<!-- <th>Password</th> -->
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
		<th>Birthday</th>
		<th>추천인</th>
		<th>Image</th>
		<th>Delete</th>
	</tr>
	<c:if test="${ !empty requestScope.banana }">
		<c:forEach var="m" items="${ requestScope.banana }">
			<tr>
				<!-- ** idblist : id별 boardList
					=> 선택된 id를 function 에 전달 (매개변수를 활용)
					idblist('banana') 처럼 -->
				<td>
					<a href="#resultArea2" onclick="idblist('${m.id}')">${ m.id }</a>
					<%-- <span class="textlink" onclick="idblist('${m.id}')">${ m.id }</span> 
						=> 선택된 id를 function 에 전달 (매개변수를 활용)
							idblist('banana')
						=> a Tag 에 이벤트 적용시 책갈피 기능 활용 가능
							-> href : 적용하지 않음 (이동하지 않음)
							-> href="#id" : id 위치로 이동
							-> href="#" : 최상단으로 이동
							-> href="javascript:;" :  적용하지 않음 (이동하지 않음)
							-> href="javascript:history.go(-1)" : 뒤로 가기 
					--%>
				</td>
				<%-- <td>${ m.password }</td> --%>
				<td>${ m.name }</td>
				<td>${ m.age }</td>
				<!-- ** Jo 정보 Div에 출력하기 -->
				<td><span class="textlink" onmouseover="showjodetail(event, ${m.jno})" onmouseout="hidejodetail()">${ m.jno }</span></td>
				<td>${ m.info }</td>
				<td>${ m.point }</td>
				<td>${ m.birthday }</td>
				<td>${ m.rid }</td>
				<td style="text-align:center;"><img alt="myImage"  src="/resources/uploadImages/${ m.uploadfile }" width="40" height="50"></td>
   <!--  ** Delete 기능 추가 
            => 선택된 id를 function 에 전달 (매개변수를 활용)
            => 결과는 성공/실패 여부만 전달: RESTController 로 
            => 성공 : Deleted 로 변경, onclick 이벤트 해제 
                     이를 위해 Delete Tag 를 function 에서 인식할수있어야함. 
                     
            ** function 에 이벤트객체 전달
            => 이벤트핸들러의 첫번째 매개변수에 event 라는 이름으로 전달함.
             => a Tag 와 span 사용시 e.target 값 비교
                -> a Tag : "javascript:;" 
                -> span  : [object HTMLSpanElement]          
         -->
				<td><span class="textlink" onclick="axidelete(event,'${m.id}')" id="${ m.id }" >Delete</span></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${ empty requestScope.banana }">
		<tr><td colspan="10"> </td></tr>
	</c:if>

</table>
<div id="content" >
	
</div>
<c:if test="${!empty requestScope.message}">
		<hr> ${ requestScope.message }<br><hr>
</c:if>

</body>
</html>