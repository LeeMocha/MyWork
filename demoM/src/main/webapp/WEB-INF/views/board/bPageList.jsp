<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MVC02 BoardList **</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css" >
<script type="text/javascript">
"use strict"

// 1. 검색조건 입력 후 버튼 클릭시 실행
// => 입력된 값을 서버로 전송요청처리: location

// ** self.location
// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
//=> self.location="bcrilist?currPage=?????" : 해당 요청을 서버로 전달
// 2) location 객체의 메서드
// => href, replace('...'), reload() 
function searchDB(){
	self.location = 'bPageList'
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
<h2>** Spring_MVC02 bPageList **</h2>
<hr>
<c:if test="${ !empty request.message }">
=> ${ request.message }<br><hr>
</c:if>
<br>
<div id="searchBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${ pageMaker.cri.searchType eq 'all' ? 'selected' : '' } >전체</option>
		<option value="title" ${ pageMaker.cri.searchType eq 'title' ? 'selected' : '' } >제목</option>
		<option value="content" ${ pageMaker.cri.searchType eq 'content' ? 'selected' : '' } >내용</option>
		<option value="id" ${ pageMaker.cri.searchType eq 'id' ? 'selected' : '' } >글쓴이</option>
		<option value="regdate" ${ pageMaker.cri.searchType eq 'regdate' ? 'selected' : '' } >작성일자</option>
		<option value="tc" ${ pageMaker.cri.searchType eq 'tc' ? 'selected' : '' } >제목+내용</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${ pageMaker.cri.keyword }">	
	<button id="searchBtn" onclick="searchDB()" >Search</button>
	<br>
	<br>
	<!-- checkBox Test -->
	<form action="bCheckList" method="get">
		<b>ID : </b>
		<!-- check 의 선택한 값 유지를 위한 코드 -->
      	<c:set var="ck1" value="false" />
      	<c:set var="ck2" value="false" />
      	<c:set var="ck3" value="false" />
      	<c:set var="ck4" value="false" />
      	<c:set var="ck5" value="false" />
      	<c:forEach  var="id" items="${pageMaker.cri.check}" >
        	<c:if test="${ id=='admin'}"> <c:set var="ck1" value="true" /> </c:if>
        	<c:if test="${ id=='simsim916'}"> <c:set var="ck2" value="true" /> </c:if>
        	<c:if test="${ id=='agr4005'}"> <c:set var="ck3" value="true" /> </c:if>
        	<c:if test="${ id=='bamboo7'}"> <c:set var="ck4" value="true" /> </c:if>
        	<c:if test="${ id=='kso1'}"> <c:set var="ck5" value="true" /> </c:if>
      	</c:forEach>
	    <!-- --------------------------------- -->
		<input type="checkbox" name="check" class="clear" value="admin" ${ ck1 ? 'checked' : ''}>관리자&nbsp;
		<input type="checkbox" name="check" class="clear" value="simsim916" ${ ck2 ? 'checked' : '' }>최문석&nbsp;
		<input type="checkbox" name="check" class="clear" value="agr4005" ${ ck3 ? 'checked' : ''}>김수빈&nbsp;
		<input type="checkbox" name="check" class="clear" value="bamboo7" ${ ck4 ? 'checked' : ''}>최승삼&nbsp;
		<input type="checkbox" name="check" class="clear" value="kso1" ${ ck5 ? 'checked' : ''}>김수옥&nbsp;
		<input type="submit" value="Search">&nbsp;
		<input type="reset" value="Clear" onclick="return checkClear()"><br><br>
	</form>
</div>
<br>
<br>
<div style="width:100%; display:flex; justify-content:center; ">
<table style="width:85%;">
	<tr bgcolor="Orange">
		<th>글번호</th>
		<th>제 목</th>
		<th>작성자</th>
		<th>작성시간</th>
		<th>조회수</th>
	</tr>
	<c:if test="${ !empty requestScope.banana }">
		<c:forEach var="b" items="${ requestScope.banana }">
			<tr>
				<td>${ b.seq }</td>
				<td>
				<!-- 답글 등록 되면 Title 출력전에 들여쓰기 추가 -->
				<c:if test="${ b.indent > 0 }">
					<c:forEach begin="1" end="${ b.indent }">
						<span>&nbsp;&nbsp;</span>
					</c:forEach>
					<span style="color:Red;">re...</span>
				</c:if>
				<c:if test="${ !empty sessionScope.loginID }">
				<a href="detail?jCode=D&seq=${ b.seq }">${ b.title }</a>
				</c:if>
				<c:if test="${ empty sessionScope.loginID}">
					${ b.title }
				</c:if>
				</td>
				<td>${ b.id }</td>
				<td>${ b.regdate }</td>
				<td>${ b.cnt }</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${ empty requestScope.banana }">
		<tr><td colspan="5">~~~ 출력할 게시글이 없습니다 ~~~</td></tr>
	</c:if>

</table>
</div>
<br>
<hr>
<br>
<div align="center">
<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성 makeQuery 메서드 적용
   => ver02: makeQuery 메서드 -> searchQuery 메서드로 변경
   1) FirstPage, Prev 
   => Old
       	<a href="bPageList?currPage=1&rowsPerPage=5">&LT;&LT;</a>&nbsp;
    	<a href="bPageList?currPage=${pageMaker.spageNo - 1}&rowsPerPage=5">&LT;</a>&nbsp;&nbsp;
   => ver01: makeQuery 적용
   		<a href="bPageList${pageMaker.makeQuery(1)}">&LT;&LT;</a>&nbsp;
    	<a href="bPageList${pageMaker.makeQuery(pageMake.spageNo-1)}">&LT;</a>&nbsp;&nbsp;
   => ver02: searchQuery 적용
   		<a href="bPageList${ pageMaker.searchQuery(1) }">&LT;&LT;</a>&nbsp;
    	<a href="bPageList${ pageMaker.searchQuery(pageMake.spageNo-1) }">&LT;</a>&nbsp;&nbsp;
      -->
	<c:choose>
    	<c:when test="${ pageMaker.prev && pageMaker.spageNo > 1 }">
    		<a href="${ pageMaker.searchQuery(1) }">&LT;&LT;</a>&nbsp;
    		<a href="${ pageMaker.searchQuery(pageMake.spageNo-1) }">&LT;</a>&nbsp;&nbsp;
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
			<a href="${ pageMaker.searchQuery(i) }">${ i }</a>&nbsp;
		</c:if>
	</c:forEach>
<!-- 3) Next, LastPage 
      => ver01: makeQuery() 메서드 사용
      => ver02: searchQuery() 메서드 사용 -->
     <c:choose>
    	<c:when test="${ pageMaker.next && pageMaker.epageNo > 0 }">
    		&nbsp;<a href="${ pageMaker.searchQuery(pageMaker.epageNo + 1) }">&GT;</a>&nbsp;
    		<a href="${ pageMaker.searchQuery(pageMaker.lastPageNo) }">&GT;&GT;</a>
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
<div>
&nbsp;<a href="boardInsert" id="write_botton"> 글쓰기 </a> &nbsp;
</div>
<br>
</c:if>
<hr>
&nbsp;<a href="/home"> home </a> &nbsp;
&nbsp;<a href="javascript:history.go(-1)"> 이전으로 </a> &nbsp;
</body>
</html>