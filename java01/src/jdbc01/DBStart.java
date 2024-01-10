package jdbc01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// ** 순서
// 1) JDBC API 에 정의된 필요한 객체들을 전역 변수로 선언, 정의
// 2) CRUD 기능을 처리하는 메서드 정의
// 3) main 에서 사용


public class DBStart {
	// SQL 쿼리문을 프로그래밍 하기위해 여러 객체가 필요로 함.
	
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	// 모든 Java에서의 sql구문은 문자열로 표현되기에 sql변수 만듬

	// ** StudentList
	// => MySql Command
	// 	-> Login -> DB선택 -> sql 구문 실행 -> 결과 반환
	// => JDBC
	//  -> Connection 객체 생성 -> sql 구문 실행 : Statement 또는 PreparedStatement ->
	//  -> 결과 : ResultSet 으로 전달 됨
	public static void selectList() {
		
		sql = "SELECT * FROM student";
		// sql 구문 문자열로 저장, ';' 는 자동으로 삽입 해줌
		
		// DB 관련된건 무조건 exception처리 강제
		try {
			st = cn.createStatement();
				// 원래 객체를 만들어야하지만, Connection 객체로 생성해야함.
				// 쿼리문을 실행해주는 객체
			rs = st.executeQuery(sql);
				// st 로 실행한 쿼리를 결과로 받는 객체
		    	// ** 결과출력
	        	// => 결과 존재 확인
	        	// => ResultSet 객체는 이를 위한 메서드 제공 
	        	// => next() : 다음에 Data가 존재하면 true, 현재Data를 제공
				// => 데이터 최선에 커서를 두고 Data가 존재하면 다음 데이터 앞으로 커서를 옮김
				// => 즉, if 로 최초에 데이터가 아예 없으면 else로 1개라도 있으면 ture문으로 가게됨
			System.out.println("     **  Student List  **");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("sno  | name  | age  | jno  | info      | point");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			if(rs.next()) {
				// => selectList 결과가 존재하는 경우
				
				do {
					System.out.print(rs.getInt(1)+" ");
					// 쿼리문에 의해 생성된 데이터에서 1번째 Column(index라 0이 첫번째 일 수 있는데 아님. oracle의 row_num이 0번이라 그런듯)
					System.out.print(rs.getString("name")+" ");
					// 데이터 타입에 의해서
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n ");
				}while(rs.next());
				
			} else {
				
				// => selectList 결과가 1건도 없음을 의미
				System.out.println("** electList 결과가 1건도 없음 **");
			} // if_else
			
		} catch (Exception e) {
			System.out.println("** selectList Exception => " +e.toString());
		} // try
	} // selectList
	
	
	// ** 조별 List1
	// => Statement 활용 : 매개변수를 활용한 조건문 추가
	public static void joList (int jno) {
		sql = "SELECT * FROM student WHERE jno = " + jno;
		
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			
			System.out.println(" **  Jo별 List  ** " + jno+"조");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("sno  | name  | age  | jno  | info      | point");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
				}while(rs.next());
			} else {
				System.out.println("** electList 결과가 1건도 없음 **");
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => " +e.toString());
		}
	} // joList
	
	
	// ** 조별 List2
	// => PreparedStatement 활용
	public static void joListPS (int jno) {
		sql = "SELECT * FROM student WHERE jno = ?";
		
		try {
//			st = cn.createStatement();
//			rs = st.executeQuery(sql);
			
			pst = cn.prepareStatement(sql);
			// 미리 인자로 sql을 전달해야 ? 를 처리할 수 있음
			pst.setInt(1, jno);
			// 첫번째 인자는 (?) 의 순서 지정. ? 가 여러개일 경우엔 번호로 지정. 첫번째는 1
			// 두번째 인자는 ? 자리에 해당하는 값 (타입은 setX에서 정해짐)
			// cn.prepareStatement(sql) 로 전달된 ? 가 포함된 sql 문에서 
			// .setXXX 메서드를 통해서 ? 값을 바꿔 줄 수 있음
			
			rs = pst.executeQuery();
			// 이미 객체 안에 .setXXX 를 통해 완성된 SQL문이 있음으로
			// sql 을 인자로 따로 전달 하지 않아도 됨.
			// ? 가 없는 문자열 또한 PrepareStatement 로 사용 가능
			
			
			System.out.println(" **  Jo별 List  ** " + jno+"조");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("sno  | name  | age  | jno  | info      | point");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
				}while(rs.next());
			} else {
				System.out.println("** electList 결과가 1건도 없음 **");
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => " +e.toString());
		}
	} // joList
	
	
	// **  insert ( 문제점 )
	// => 입력에 필요한 column들을 모두 매개변수로 전달 받아야함
	// => 매개변수가 많아지면 불편해진다. 객체화 시켜서 전달
	// 		-> 엔티티(Table): 업무분석 과정에서 도출 = Java Class로 객체화 시키는 작업
	//		   DTO(Data Transform Object), VO(Value Objcet), Entity(JPA에서 나옴) Ex) Student DTO, Student VO
	//		   DAO(Data Access Object) : DB에 전달하고 받고 하는 메서드들을 따로 모음
	//		   MVC(Model(database) View Controller) design patter : 역할별로 나눠져서 데이터베이스 교류, 보여주는 작업등을 나눠놓은 통제소 
	//			-> 어트리뷰트(column)
	// => SQL 구문을 완성하기 위해 문자열 (+)연산을 작성해야함
	//	Ex) INSERT INTO student(name, age, jno, info) VALUES ('홍길동', 22, 9, '관리자입니다.')
	//	Ex) "INSERT INTO student(name, age, jno, info) VALUES ('" + name + "'," + age "," + .....
	// => 이러한 불편한 점을 보완하기 위해 'PreparedStatement' 임
	// => 변수의 위치에 ?(바인딩 변수) 를 사용 할 수 있게 됨.
	//  Ex) INSERT INTO student(name, age, jno, info) VALUES (?, ?, ?, ?)
	// => ?(바인딩 변수)에 대응하는 값은 JAVACode로 처리 (PreparedStatement에서 제공하는 메서드로)
	public static void insert(String name, int age, int jno, String info) {
		sql = "INSERT INTO student(name, age, jno, info) VALUES (?, ?, ?, ?)";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setInt(3, jno);
			pst.setString(4, info);
			
//			int cnt = pst.executeUpdate(); // DML(insert, update, delete) 실행 => return int (수정된 갯수)
										   // DML 구문은 데이터의 내용이 달라지기 때문에
										   // executeUpdate() 업데이트가 된 , 수정이 일어난 갯수를 int타입으로 리턴함( n rows affected).
										   // 즉, 해당 조건에 안맞아서 수정된게 없을 경우 오류로 간주 하지 않고 0 을 리턴 하게됨
										   // 보편적으로 return pst.executeUpdate(); 로해서 메서드 종료가 0이하이면 실패한 것으로 해석				
			
			if( pst.executeUpdate() > 0 ) System.out.println("** insert 성공 => ");
			else System.out.println("** insert 실패 => ");
			
		
		} catch (Exception e) {
			System.out.println("** insert Exception => " +e.toString());
		}
	}

	
	public static void main(String[] args) {
		// 1) Connection 확인
//		System.out.println("** Connection 확인 => " +cn);
		// 여기서 cn 은 .toString() 메서드가 리턴이 되기 때문에 .toString()생략 가능.
		// 즉, 인스턴스명을 사용하면 .toString() 메서드를 호출 
		
		// 2) Student List
//		selectList();
		
		// 3) 조별 List 출력
		joList(3);
		
		// 4) insert
		insert("홍길동", 22, 9 , "관리자 입니다.");
		
	} // main
} // class