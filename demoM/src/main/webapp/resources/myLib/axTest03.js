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
		let url = "member/axPageList";
	
	axios.get(url)
		.then( response => {
			console.log("** response joinForm 성공");
			document.getElementById('resultArea1').innerHTML = response.data;
		}).catch(err => {
			alert(`** response joinForm 실패 => ${err.message}`);
		})
		
		document.getElementById('resultArea2').innerHTML = "";
}