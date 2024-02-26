<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Axios MemberPageList **</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css" >
<script type="text/javascript">
"use strict"
//** 검색 & 페이징 포함한 요청의 Ajax 처리
// => Ajax 요청 function 작성, url 을 매개변수로 전달 : axiMListCri(url) 
// => Page 요청 : aTag -> span 으로 변경하고 function 으로 처리 
// => Check 검색은 submit 을 사용하기 때문에 적용하지 않음(주석처리)

// => Ajax 처리시에는 문서내부의 function이 인식 안되므로
//    searchDB(), keywordClear() 모두 axTest03.js 에 작성  


// 1. 검색조건 입력 후 버튼 클릭시 실행
// => 입력된 값을 서버로 전송요청처리: location

// ** self.location
// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
//=> self.location="bcrilist?currPage=?????" : 해당 요청을 서버로 전달
// 2) location 객체의 메서드
// => href, replace('...'), reload() 
function searchDB(){
	self.location = 'mPageList'
/* 				+ '${pageMaker.makeQuery(1)}'
					=> 하나의 jsp 문서로 다양한 요청을 처리하기위해 쿼리스트링에 url 을 포함했기 때문에
					첫 요청에서는 makeQuery 메서드 사용할 수 없음 */
				+ '?currPage=1&rowsPerPage=5'
				+ '&searchType='+ document.getElementById('searchType').value
				+ '&keyword='+ document.getElementById('keyword').value;
				//** JS 코드 내부에서 el Tag 사용시 주의사항
				//=> JS 코드의 스트링 내에서 사용한 el Tag 는 JSP 가 처리해주므로   
				// 사용가능 하지만, 이 스크립트가 외부 문서인 경우에는 처리해주지 않으므로 주의
				// 이 코드를 외부문서로 작성하면 "${pageMaker.makeQuery(1)}" 이 글자 그대로 적용되어 404 발생 
} // searchDB
//=> searchBtn 을 클릭하는 경우 : 검색조건 입력 후 첫 Page 요청
//이때는 서버에 searchType, keyword 가 전달되기 이전이므로 
//searchType, keyword 가 없는 makeQuery 메서드사용
    
		
// 2. searchType 을 '전체' 로 변경하면 keyword는 clear
function keywordClear(){
	if(document.getElementById('searchType').value == 'all'){
		document.getElementById('keyword').value = '';
	}	
} // keywordClear

// 3. Board Check_List
function checkClear(){
	// document.querySelectorAll('.clear').checked = false;
	// => nodeList 를 반환하기 때문에 적용안됨
	let ck = document.querySelectorAll('.clear');
	for(let i = 0 ; i < ck.length ; i++){
		ck[i].checked = false;
	}
	
	return false; // reset 의 기본이벤트 제거
} // checkClear

// ** querySelector
// => css 선택자를 이용하여 첫번째 만난 요소 1개만 선택

// ** querySelectorAll 
// => css 선택자를 이용하여 해당하는 nodeList 를 반환
// =>  ","를 사용하면 여러 요소를 한번에 가져올 수 있음
//     querySelectorAll("#id,.class");
// => 그러므로 반복문과 이용됨.

</script>
</head>
<body>
<h2>** Spring_Boot Axios MemberPageList **</h2>
<hr>
<c:if test="${ !empty request.message }">
=> ${ request.message }<br><hr>
</c:if>
<br>
<div id="searchBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${ pageMaker.cri.searchType eq 'all' ? 'selected' : '' } >전체</option>
		<option value="id" ${ pageMaker.cri.searchType eq 'id' ? 'selected' : '' } >id</option>
		<option value="name" ${ pageMaker.cri.searchType eq 'name' ? 'selected' : '' } >이름</option>
		<option value="age" ${ pageMaker.cri.searchType eq 'age' ? 'selected' : '' } >나이</option>
		<option value="birthday" ${ pageMaker.cri.searchType eq 'birthday' ? 'selected' : '' } >생일</option>
		<option value="info" ${ pageMaker.cri.searchType eq 'info' ? 'selected' : '' } >info</option>
		<option value="rid" ${ pageMaker.cri.searchType eq 'rid' ? 'selected' : '' } >추천인</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${ pageMaker.cri.keyword }">	
	<button id="searchBtn" onclick="searchDB()" >Search</button>
	<br>
	<br>
	<!-- checkBox Test -->
 	<form action="axmcheck" method="get">
		<b>조 이름 : </b>
		<!-- check 의 선택한 값 유지를 위한 코드 -->
      	<c:set var="ck1" value="false" />
      	<c:set var="ck2" value="false" />
      	<c:set var="ck3" value="false" />
      	<c:set var="ck4" value="false" />
      	<c:set var="ck5" value="false" />
      	<c:forEach  var="jno" items="${pageMaker.cri.check}" >
        	<c:if test="${ jno =='1'}"> <c:set var="ck1" value="true" /> </c:if>
        	<c:if test="${ jno =='2'}"> <c:set var="ck2" value="true" /> </c:if>
        	<c:if test="${ jno =='3'}"> <c:set var="ck3" value="true" /> </c:if>
        	<c:if test="${ jno =='4'}"> <c:set var="ck4" value="true" /> </c:if>
        	<c:if test="${ jno =='7'}"> <c:set var="ck5" value="true" /> </c:if>
      	</c:forEach>
	    <!-- --------------------------------- -->
		<input type="checkbox" name="check" class="check clear" value="1" ${ ck1 ? 'checked' : ''}>Business&nbsp;
		<input type="checkbox" name="check" class="check clear" value="2" ${ ck2 ? 'checked' : '' }>static&nbsp;
		<input type="checkbox" name="check" class="check clear" value="3" ${ ck3 ? 'checked' : ''}>칭찬해조&nbsp;
		<input type="checkbox" name="check" class="check clear" value="4" ${ ck4 ? 'checked' : ''}>카톡으로얘기하조&nbsp;
		<input type="checkbox" name="check" class="check clear" value="7" ${ ck5 ? 'checked' : ''}>칠면조&nbsp;
		<!-- <input type="submit" value="Search">&nbsp; -->
		<button type="button" onclick="checkDB()">Check</button>&nbsp;
		<!-- 폼태그 내부에서 button의 type 을 지정하지 않으면 default 가 submit이 되어버림 -->
		<input type="reset" value="Clear" onclick="return checkClear()"><br><br>
	</form>
</div>
<br>
<div style="width:100%; display:flex; justify-content:center; ">
<table border="1" style="width:100%">
	<tr bgcolor="cyan">
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
	</tr>
	<c:if test="${ !empty requestScope.banana }">
		<c:forEach var="m" items="${ requestScope.banana }">
			<tr>
				<td>${ m.id }</td>
				<%-- <td>${ m.password }</td> --%>
				<td>${ m.name }</td>
				<td>${ m.age }</td>
				<td>${ m.jno }</td>
				<td>${ m.info }</td>
				<td>${ m.point }</td>
				<td>${ m.birthday }</td>
				<td>${ m.rid }</td>
				<td style="text-align:center;"><img alt="myImage"  src="/resources/uploadImages/${ m.uploadfile }" width="40" height="50"></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${ empty requestScope.banana }">
		<tr><td colspan="9">~~~ 출력할 멤버가 없습니다 ~~~</td></tr>
	</c:if>
</table>
</div>
<br>
<hr>
<br>
<div align="center">
<!-- 1) Prev, First  -->
	<c:choose>
    	<c:when test="${ pageMaker.prev && pageMaker.spageNo > 1 }">
    		<%-- <a href="${ pageMaker.searchQuery(1) }">&LT;&LT;</a>&nbsp;
    		<a href="${ pageMaker.searchQuery(pageMaker.spageNo-1) }">&LT;</a>&nbsp;&nbsp; --%>
    		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(1)}')">&LT;&LT;</span>&nbsp;
    		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.spageNo-1)}')">&LT;</span>&nbsp;&nbsp;
    	</c:when> 
     	<c:otherwise>
     		<font color="#b6b6b6">&LT;&LT;&nbsp;&nbsp;&LT;&nbsp;&nbsp;</font>
     	</c:otherwise>
    </c:choose>
<!-- 2) Display PageNo
	=> currPage 제외한 PageNo 만 a Tag 처리
 -->
	<c:forEach var="i" begin="${ pageMaker.spageNo }" end="${ pageMaker.epageNo }">
		<c:if test="${ i eq pageMaker.cri.currPage }">
			<font color="black" size="5"><b>${ i }</b></font>&nbsp;
		</c:if>
		<c:if test="${ i ne pageMaker.cri.currPage }">
			<%-- <a href="${ pageMaker.searchQuery(i) }">${ i }</a>&nbsp; --%>
			<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(i)}')">${ i }</span>&nbsp;
		</c:if>
	</c:forEach>
<!-- 3) Next, LastPage 
      => ver01: makeQuery() 메서드 사용
      => ver02: searchQuery() 메서드 사용 -->
     <c:choose>
    	<c:when test="${ pageMaker.next && pageMaker.epageNo > 0 }">
    		<%-- &nbsp;<a href="${ pageMaker.searchQuery(pageMaker.epageNo + 1) }">&GT;</a>&nbsp;
    		<a href="${ pageMaker.searchQuery(pageMaker.lastPageNo) }">&GT;&GT;</a> --%>
    		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.epageNo+1)}')">&GT;</span>
    		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.lastPageNo)}')">&GT;&GT;</span>
    	</c:when> 
     	<c:otherwise>
     		<font color="#b6b6b6">&nbsp;&GT;&nbsp;&GT;&GT;</font>
     	</c:otherwise>
     </c:choose>
</div>
<c:if test="${ !empty requestScope.message }">
		<hr> ${ requestScope.message }<br>
</c:if>
<c:if test="${ !empty sessionScope.loginID }">
<br>
<hr>
<br>
</c:if>
<hr>
&nbsp;<a href="/home"> home </a> &nbsp;
&nbsp;<a href="javascript:history.go(-1)"> 이전으로 </a> &nbsp;
</body>
</html>