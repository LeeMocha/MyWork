package model;

import java.sql.Connection;
import java.sql.DriverManager;

//** DB 연결
//=> Connection 객체가 DB 연결및 연결정보를 관리함
//즉, Connection 객체를 생성해야함

//** Connection 객체 생성
//=> 일반적인 생성문 
// Connection cn = new Connection_구현클래스() -> XXX
//=> DB 연결정보를이용해서 생성후 그 생성값을 전달받음   

//** Connection 생성과정
//=> Class.forName : JDBC 드라이버 로딩 (드라이버를 가져오게 됨)
//=> DriverManager
//  getConnection() 메서드로 해당 JDBC 드라이버를 찾아 필요한 기본값으로 컨넥션을 생성해서 제공

//** JDBC 드라이버
//=> DBMS와 통신을 담당하는 자바 클래스
//  DBMS 별로 알맞은 JDBC 드라이버 필요
//  보통 jar 파일로 제공

//** DriverManager
//=> JDK(Java SE Development Kit)의 정적 클래스이며,
// 사용할 애플리케이션에 대해 사용가능한 JDBC(Java Database Connectivity) 드라이버 세트를 관리함.


// 연결을 위한, 커넥션을 얻기위한 객체를 생성하는 클래스
public class DBConnection {
	
	// 컨넥션 객체를 받기위해 타입도 컨넥션
	// 기본적으로 연결을 위해 I/O가 생기게됨 그러므로 예외처리 무조건 강제
	// I/O와 관련된 명령어들은 그 실행문이 완료될 때까지 무한 대기 상태가 될 수도 있음
	// 	ex) 스캐너 입력 대기 상태같은 경우
	// 컴파일 측에서 무조건 Exception을 체크하기 때문에 강제
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Class 라는 이름의 class
			// .forName() : 인자에 해당하는 클래스를 찾아줌.
			// 자동으로 로드하거나, 라이브러리들을 로드 할때 사용함.
			// new 연산자를 사용하지 않고 메모리에 할당하는 방법.
			// 찾는다고 끝이 아니고 계정로그인에 필요한 ID,PW,드라이버 정보 알려줘야함.
			
			
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		    // => allowPublicKeyRetrieval=true : local DB open 하지 않아도 connection 허용
	        // => localhost란 -> 동일값(ip주소) @127.0.0.1 내 pc ip주소(밖으로 찾아갔다 다시 이 주소로 돌아오게 됨)
			// => 즉, localhost 부분에 다른 외부 주소를 쓰게되면 외부로 나가게 됨.
			// db를 찾아가는 ip주소
			// mydb 내가 연결할 DB
			// ? (쿼리 스트링) & 은 구분자 
			// characterEncoding=UTF-8 , serverTimezone=UTC , useSSL=false , allowPublicKeyRetrieval=true
			// allowPublicKeyRetrieval=true 이걸 안주게 되면 mysql 을 열어놔 줘야함, true는 로컬 DB 를 열지 않아도 연결하게 하는 값
			
			Connection cn = DriverManager.getConnection(url, "root", "mysql");
			// 원래라면 new Connection()해서 컨넥션 객체를 전달해주지만 DriverManager가 그걸 대신해줌
			
			System.out.println("** Connection 성공 **");
			
			return cn;
			
		} catch (Exception e) {
			System.out.println("** DB Connection Excption => " + e.toString());
			return null;
		} // try
	} // getConnection
}	// class
