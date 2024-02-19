package javaTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;


public class Ex02_DBConnection {
	
	// 1) static, return 값이 있는 경우 ( @Test 불허)
	// => Test 메서드를 따로 작성해서 호출하는 형식으로 진행
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
			
			Connection cn = DriverManager.getConnection(url, "root", "mysqlmysql");
			// 원래라면 new Connection()해서 컨넥션 객체를 전달해주지만 DriverManager가 그걸 대신해줌
			
			System.out.println("** Connection 성공 **");
			
			return cn;
			
		} catch (Exception e) {
			System.out.println("** DB Connection Excption => " + e.toString());
			return null;
		} // try
	} // getConnection
	
	@Test
	public void connectionTest() {
		System.out.println("** DB_Connection => " + getConnection());
		
		assertNotNull(getConnection());
		
	} // connectionTest
	
} // class
