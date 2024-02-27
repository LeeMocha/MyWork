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
	
	  // ** for 간편출력 : of, in
      // => in: undifined 는 통과하고, 배열(index Return), 객체(속성명 Return)
      // => of: undifined 까지 모두출력 (순차출력과 동일), value 를 return, 
      //        ES6 에 for ~ in 의 단점을 보완 개선하여 추가됨.
      //        일반 객체에는 적용안되지만, (오류발생, 개발자모드로 확인가능)
      //         Array, String, Map, Set, function의 매개변수 객체 와
      //        이터러블 규약을 따르는 이터러블 객체 (Iterable Object) 는 적용됨
      // => 이터러블 규약
      //      내부에 Symbol.iterator (줄여서 @@iterator로 표현하기도함) 메서드가 구현되어 있어야 한다는 규약 
	
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

//  => event 객체 적용하기
//  => document.getElmentById(id) 대신 
//	   e.target 로 대상 객체(태그) 인식가능
function axidelete(e, id){
	let url = "rest/axidelete/"+id;
	alert(`idbList=${url}`);

	axios.delete(url
	).then(response => {
		alert(response.data);
		//document.getElementById(id).innerHTML = 'Deleted';
		//document.getElementById(id).style.color = 'gray';
		//document.getElementById(id).style.fontWeight = 'bold';
		//document.getElementById(id).classList.remove('textlink');
		//document.getElementById(id).removeAttribute('onclick');
		
		e.target.removeAttribute('onclick');
		e.target.innerHTML = 'Deleted';
		e.target.style.color = 'gray';
		e.target.style.fontWeight = 'bold';
		e.target.classList.remove('textlink');
		
	}).catch(err => {
		if(err.response.status == '502'){
			alert(err.response.data);
		} else {
			alert("** axiDelete 삭제 실패, 시스템 오류 => " + err.message);
		}
	});
}

// JoDetail
// 1) mouseover : showDetail
// => jno mouseover : JoDetail content Div에 출력 (마우스 포인터 위치에)
// => request : axios, get , RESTController 에 "/jodetail"
// => response : 성공시엔 body 에 JoDTO 객체로 
function showjodetail(e, jno){
	// ** 마우스 포인터 위치 확인 필요
    // => 이벤트객체 활용
    //     - event객체 (이벤트핸들러 첫번째 매개변수) 가 제공
    //     - event객체 프로퍼티: type, target, preventDefault() 등 (JS 9장_Event.pptx 28p)   
    //    - e.pageX, e.pageY : 전체 Page 기준
    //     - e.clientX, e.clientY : 보여지는 화면 기준-> page Scroll 시에 불편함
	console.log(`** e.pageX=${e.pageX}, e.pageY=${e.pageY}`);
	console.log(`** e.clientX=${e.clientX}, e.clientY=${e.clientY}`);
	
	let url = "rest/jodetail/"+jno;
	let mleft = e.pageX+20+'px';
	let mtop = e.pageY;
	
	axios.get(url
	).then(response => {
		console.log(`response 성공 => ${response.data}`)
		// ** JSON.stringify 적용 비교
      let jj =JSON.stringify(response.data);   
      // => Object -> JSON : Data를 나열해줌 
      // => JS 객체포맷을 JSON 포맷화 하면 key:value 형태로 나열해줌
      //    (즉, 하나의 긴문자열, 문자Type 이됨)
      //    console.log 로 response.data 의 내용을 확인할때 사용하면 편리함.  
      console.log(`** response 성공: JSON포맷 => ${jj}`);

		let content = document.getElementById('content');
		
		content.innerHTML = `
		<table>
			<tr>
				<th> JNO </th><td>${response.data.jno}</td>
			</tr>
			<tr>
				<th> JNAME </th><td>${ response.data.jname }</td>
			</tr>
			<tr>
				<th> CAPTAIN </th><td>${ response.data.captain }</td>
			</tr>
			<tr>
				<th> PROJECT </th><td>${ response.data.project }</td>
			</tr>
			<tr>
				<th> SLOGAN </th><td>${ response.data.slogan}</td>
			</tr>
		</table>
		`
		content.style.display ='block';
		content.style.left = mleft + 'px';
		content.style.top = mtop + 'px';
		
	}).catch(err => {
		if(err.response.status=='502') alert(err.response.data)
		alert("** showJoDetail 시스템 오류 => " + err.message);
	})
	
} // showJoDetail

// 2) mouseout
// => 화면에 표시되어있던 content Div가 사라지면 됨 
function hidejodetail(){
	document.getElementById('content').style.display = 'none';
} // hideJoDetail