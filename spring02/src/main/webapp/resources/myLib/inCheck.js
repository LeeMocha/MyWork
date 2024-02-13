/**
** 입력값의 무결성 확인
** member 무결성 확인사항
// ID : 길이(4~10), 영문자,숫자 로만 구성
// Password : 길이(4~10), 영문,숫자,특수문자로 구성, 특수문자는 반드시 1개 이상 포함할것
// Password2: 재입력후 Password 와 일치성 확인
// Name : 길이(2이상), 영문 또는 한글로 만 입력
// Age: 정수의 범위  ( 숫자이면서, '.'이 없어야함 )  
// BirthDay : 입력 여부 확인  ( length == 10 )
// Point : 실수 ( 구간설정 100 ~ 10000 까지만 가능 )
// Jno : select 를 이용 (X)
// Info : (X)

** 작성 규칙
   => JavaScript function 으로 정의 하고 
      결과를 true or false 로 return
   => 정규식을 활용한다.
   
** match Test
   => 아래 조건에 true -> not (!)  match 적용해보면
   => 정확하지 않으므로 (부적절, replace 를 사용)
        ...       
        } else if (!id.match(/[a-z.0-9]/gi)) {
            alert(' ID는 영문자와 숫자로만 입력하세요. !!!')
            return false;
        }    
 */
"use strict"

// 1) ID
// => 길이, 영문과 숫자만 가능
let special = /[a-z.0-9]/gi;
function idCheck () {
	let id = document.getElementById('id').value;
	
	if (id.length<4 || id.length>10){
		document.getElementById('iMessage').innerHTML = 'id는 4~10 글자 입니다.';
		return false;
	// => test(검사 대상문자) 활용
	//    정규식에 정의된 문자가 아닌 문자가 있으면 false
	} else if (id.replace(/[a-z.0-9]/gi,'').length>0){
		document.getElementById('iMessage').innerHTML = 'id는 영문과 숫자만 가능합니다.';
		return false;
	} else {
		document.getElementById('iMessage').innerHTML = '';
		return true;
	}
}

// 2) Password
special = /[a-z.0-9.!-*.@:;]/gi;
function pwCheck () {
	let pw = document.getElementById('password').value;
	
	if(pw.length < 4 || pw.length > 10){ 
		document.getElementById('pMessage').innerHTML = '비밀번호는 4~10 자리여야 합니다.';
		return false;

	// => 영문, 숫자, 특수문자로만 구성
	/*} else if(!special.test(pw)){*/
	} else if(pw.replace(special, '').length > 0){
		document.getElementById('pMessage').innerHTML = '영문, 숫자, 특수문자로만 구성되어야 합니다.';
		return false;

	// => 특수문자는 반드시 포함
	} else if(pw.replace(/[!-*.@:;]/gi,'').length >= pw.length){
		document.getElementById('pMessage').innerHTML = '특수문자는 반드시 포함되어야 합니다.';
		return false;
	} else {
		document.getElementById('pMessage').innerHTML = '';
		return true;
	}
}

// 3) Password2
function pw2Check () {
	let pw = document.getElementById('password').value;
	let pw2 = document.getElementById('password2').value;
	
	if(pw != pw2){
		document.getElementById('p2Message').innerHTML = '재입력 비밀번호가 일치 하지 않습니다';
		return false;
	} else {
		document.getElementById('p2Message').innerHTML = '';
		return true;
	}
}

// 4) Name
function nmCheck () {
	let name = document.getElementById('name').value;
	
	if(name.replace(/[가-힣.a-z]/gi,"").length>0){
		document.getElementById('nMessage').innerHTML = '이름이 올바르지 않습니다1.';
		return false;
/*	} else if(name.repalce(/[가-힣]/,"")>0 || name.replace(/[a-z]/gi,"")>0){
		document.getElementById('nMessage').innerHTML = '이름이 올바르지 않습니다2.';
		return false;*/
	} else {
		document.getElementById('nMessage').innerHTML = '';
		return true;
	}
}

// 5) Age
// => 정수의 조건 : 숫자이면서 소수점이 없어야함
// => Number .isInteger(n) : n 이 정수일때만 true 반환
// 	-> 단, n 은 숫자 type 이어야만 함
//  -> value 속성의 값은 문자 Type 이므로 숫자화_parseInt() 가 필요함
//  -> 단, parseInt(age) 사용시 주의 
//     - 실수의 경우에는 정수만 사용 (123.56 -> 123)
//     - 숫자 뒤쪽에 문자가 포함되면 앞쪽의 숫자만 가져와 정수 return (123abc -> 123)
//     - 문자로 시작하면 문자로 취급, NaN(Not a Number) 를 return
// => 숫자가 아닌 값이 있는지 확인, Number.isInteger(...)확인
function agCheck () {
	let age = document.getElementById('age').value;
	
	if(age.replace(/[^0-9]/,'').length < age.length || !Number.isInteger(parseInt(age))){
		document.getElementById('aMessage').innerHTML = '숫자가 아닌 값이 들어갔습니다.';
		return false;
	} else {
		document.getElementById('aMessage').innerHTML = '';
		return true;
	}
}

// 6) Point
// => 정수 또는 실수 허용
// => 범위: 100 ~ 10000
// => parseFloat(point)
//      -> 오류 또는 입력값이 없는 경우 NaN return
//      -> 확인 : Number.isNaN(parseFloat(point)) 
//    -> 단, 숫자로 시작하면 뒤쪽에 문자가 섞여있어도 숫자값만 사용함 ( NaN 을 return 하지않음 )
function poCheck () {
	let point = document.getElementById('point').value;
	console.log(`** parseFloat(point) => ${parseFloat(point)}`);
	console.log(`** Number.isNaN(point) => ${Number.isNaN(point)}`);
	console.log(`** Number.isNaN(parseFloat(point)) => ${Number.isNaN(parseFloat(point))}`);
	
	// => 숫자 아닌값이 있는지 확인, Number.isNaN(...) 적용
	// => 단, 소숫점은 허용
	//    (비교값으로 소숫점을 사용하기위해 "/." 으로 표기함)
	if(point.replace(/[^0-9.\.]/g,'').length < point.length || Number.isNaN(parseFloat(point))){
		document.getElementById('oMessage').innerHTML = 'Point 는 정수, 실수만 가능합니다.';
		return false;
	} else if(parseFloat(point)<100 || parseFloat(point)> 10000){
		document.getElementById('oMessage').innerHTML = 'Point 값이 범위(100~10000) 를 벗어납니다.';
		return false;
	} else {
		document.getElementById('oMessage').innerHTML = '';
		return true;
	}
}

// 7) Birthday
function bdCheck () {
	let birthday = document.getElementById('birthday').value;
	
	if(birthday.length != 10){
		document.getElementById('bMessage').innerHTML = 'Birthday (yyyy-mm-dd) 입력 확인하세요.';
		return false;
	} else {
		document.getElementById('bMessage').innerHTML = '';
		return true;
	}
}

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 <!-- 
 ** Good 정리
 => https://inpa.tistory.com/entry/JS-📚-정규식-RegExp-누구나-이해하기-쉽게-정리
 
** 정규 표현식 (정규식:Regular Expression) 객체 : RegExp
=> 자바스크립트의 기본 내장 객체 중의 하나
=> 특정한 규칙을 가진 문자열 집합을 표현하는데 사용하는 형식
* 생성
   var regExp1= new RegExp('text') ;
   var regExp2= /text/ ; 
* 메서드   
   test() : 정규식과 일치하는 문자열이 있으면 true 아니면  false 를 return 
   exec() : 정규식과 일치하는 문자열을 return 
* 예제   
   let regExp= /script/ ; 
   let s='Javascript jQuery Ajax';
   
   var output = regExp.test(s) ;
   alert(output) ; 
* 그러나 주로 문자열의 메서드와 같이 사용됨
    
/.../ : 정규식 RegExp 의 리터럴

** 플래그 문자 
g : global, 전역비교
i : 대문자는 소문자 변환후 비교 (대/소문자 구분 없음)
m : 여러줄의 검사 수행
   ( 각줄을 개별문자로 인식하고 검사해줌
    예 : 'JavaScript\njQuery\nAjax' )

\. : . 문자 (. 는 한 문자를 의미하나 \. 는 . 문자를 의미함)
a-z : abcdefghijklmnopqrstuvwxyz 와 같음
0-9 : 0123456789 와 같음
: : : 문자
_ : _ 문자
- : - 문자
[~.~] : ~ 와 ~ , Or 의 묶음
[..] : Or 의 묶음. 안에 기록된 1가지외 중복 적용됨.
[^...] : 내부내용의 부정. 기록된 이외의 것을 찾음.
+ : 하나 이상의 반복적용. (단어(?) 찾음)

*/