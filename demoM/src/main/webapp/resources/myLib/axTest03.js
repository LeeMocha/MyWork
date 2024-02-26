// ** Ajax_REST API, Axios Test **
// => Axios 메서드형식 적용
// => 1. List 출력
//   - axiMList : MemberController, Page response
//   - idbList(id별 boardList) : RESTestController, List_Data response 
// => 2. 반복문에 이벤트 적용하기
//   - Delete, JoDetail
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
"use strict"

// 1. List 출력 
// 1.1) Page response
// => response 를 resultArea1 에 출력하기
// => 요청명 :  /member/aximlist
// => response : axMemberList.jsp 
function axiMList() {
	let url = "member/axMemberList";
	
	axios.get(url)
		.then( response => {
			console.log("** response joinForm 성공");
			document.getElementById('resultArea1').innerHTML = response.data;
		}).catch(err => {
			alert(`** response joinForm 실패 => ${err.message}`);
		})
		
		document.getElementById('resultArea2').innerHTML = "";
}

function axiPList(){
		let url = "member/axCheckList";
	
	axios.get(url)
		.then( response => {
			console.log("** response axPageList 성공");
			document.getElementById('resultArea1').innerHTML = response.data;
		}).catch(err => {
			alert(`** response axPageList 실패 => ${err.message}`);
		})
		
		document.getElementById('resultArea2').innerHTML = "";
}


// ** Ajax Member_PageList *********************
// => axiMList 에 Paging + 검색기능 추가
// => 검색조건 & Paging , Ajax 구현
//    -> 입력된 값들을 서버로 전송요청: axios
//   -> url 완성후 axios 호출

// => 1) 검색조건 입력 후 버튼클릭
//   -> jsp  문서내무의 script 구문을 외부문서로 작성 : EL Tag 적용안됨
//    ${pageMaker.makeQuery(1)} -> ?currPage=1&rowsPerPage=5 
function searchDB(){
	let url = 'axmcri'
				+ '?currPage=1&rowsPerPage=5'
				+ '&searchType='+ document.getElementById('searchType').value
				+ '&keyword='+ document.getElementById('keyword').value;
	
	axiMListCri(url);
	/*.then( response => {
				console.log("** response axSearchList 성공");
				document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		alert(`** response axSearchList => ${err.message}`);
	})
	
	document.getElementById('resultArea2').innerHTML = "";*/
	
} // searchDB

// => 2) searchType 을 '전체' 로 변경하면 Keyword는 clear
function keywordClear(){
	if(document.getElementById('searchType').value == 'all'){
		document.getElementById('keyword').value = '';
	}	
} // keywordClear

function checkClear(){
	// document.querySelectorAll('.clear').checked = false;
	// => nodeList 를 반환하기 때문에 적용안됨
	let ck = document.querySelectorAll('.clear');
	for(let i = 0 ; i < ck.length ; i++){
		ck[i].checked = false;
	}
	
	return false; // reset 의 기본이벤트 제거
} // checkClear

// => 3) axios Code
function axiMListCri(url){
	url ="member/"+url;
	alert(`axiMListCri=${url}`);
	axios.get(url
	).then(response => {
		console.log("** response 성공 **");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		document.getElementById('resultArea1').innerHTML = "** axiMListCri 오류 **" + err.message;
	});
	document.getElementById('resultArea2').innerHTML = "";
} // axiMList


function checkDB(){
	
	let check = document.getElementsByClassName('check');
	let checkQ = '';
	
	check.forEach( check => {
		if( check.chekced )
		checkQ = "&check="+check.value;
	});
	
/*	for(let i = 0 ; i < check.length ; i++){
		if(check[i].checked)
			checkQ += '&check=' + check[i].value;
	}*/
	
	let url = 'axmcheck'
				+ '?currPage=1'
				+ '&rowsPerPage=5'
				+ '&searchType='+ document.getElementById('searchType').value
				+ '&keyword='+ document.getElementById('keyword').value
				+ checkQ;
	
	axiMListCri(url);
}

// idblist(id별 boardList)
// => RESTController, PathVariable 처리, List_Data response
// => Server : service, Sql 구문 작성
// => request : id 를 path 로 전송 "/rest/idblist/banana"
// => Response
//  -> 성공 : 반복문, Table로 List 출력문 완성, resultArea2에 출력
//	-> 출력자료의 유/무 : Server 에서 502로 처리
//  -> 실패 : resultArea2 clear , alert 으로 에러메세지 출력
function idblist(id){
	let url = '/rest/idblist/'+id;
	
	alert(`idbList=${url}`);
	
	axios.get(url
	).then(response => {
		console.log("** response 성공 **");
		console.log("** result List Data => "+response.data);
		
		let listData = response.data;
		let resultHtml = `
<table style="width:85%;">
	<tr bgcolor="Orange">
		<th>글번호</th>
		<th>제 목</th>
		<th>작성자</th>
		<th>작성시간</th>
		<th>조회수</th>
	</tr>`;
	for (let b of listData){
		resultHtml += `
			<tr>
				<td>${ b.seq }</td>
				<td>${ b.title }</td>
				<td>${ b.id }</td>
				<td>${ b.regdate }</td>
				<td>${ b.cnt }</td>
			</tr>`;
	} // for of
	resultHtml+= '</table>';
	
	document.getElementById('resultArea2').innerHTML = resultHtml;
		
	}).catch(err => {
		// => response 의 status 값이 502 면 출력자료 없음
		if(err.response.status == '502'){
			alert("** 출력할 자료가 없습니다. **");
			document.getElementById('resultArea2').innerHTML = err.response.data;
		} 
		else{
			alert("** 시스템 오류, 잠시후 다시 하세요 => " + err.message);
			document.getElementById('resultArea2').innerHTML = "";
		} 
	});
}

// ** axiDelete('${m.id}')
// 	=> 요청명 : "rest/axidelete/"+ PathVariable 적용
//  => 결과는 성공 / 실패 여부만 전달받음, 그러므로 RESTController 사용
//	=> 성공 : deleted 로 변경, onclick 해제
function axidelete(id){
	let url = "rest/axidelete/"+id;
	alert(`idbList=${url}`);

	axios.delete(url
	).then(response => {
		alert(response.data);
		document.getElementById(id).innerHTML = 'Deleted';
		document.getElementById(id).style.color = 'gray';
		document.getElementById(id).style.fontWeight = 'bold';
		document.getElementById(id).classList.remove('textlink');
		document.getElementById(id).removeAttribute('onclick');
		
	}).catch(err => {
		if(err.response.status == '502'){
			alert(err.response.data);
		} else {
			alert("** axiDelete 삭제 실패, 시스템 오류 => " + err.message);
		}
	});
}

// jno 위에 호버 했을때 mause over 로 pagex,y 사용해서 호버한 위치에 
// 마우스 위치값 알기 위해서는

let jno = document.querySelectorAll(".jno");

for(let i = 0 ; i < jno.length ; i++){
	jno[i].addEventListener('mouseover', (event) => {

	  	

	});
}
