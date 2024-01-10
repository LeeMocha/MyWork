package jdbc02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc01.DBConnection;

//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
//   Create(Insert), Read(selectList, selectOne), Update, Detete

//** 첫번째 예제 DBStart와 ~~~DAO 와 차이점
//=> 결과를 직접 출력하거나 처리하지않고 요청자에게 제공해야됨
//=> 즉, 메서드 역할멸로 처리 결과를 return 해야됨
//=> 그러므로 특히 select 의 결과를 잘 전달하기 위해서는 결과를 객체화해야됨 
public class StudentDAO {
	// ** 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// JAVA 컬렉션 : 1) List : 규칙적인 순서가 있는 나열된 값들
	// - ArrayList : 연속된 공간을 차례대로 할당, 동적할당(확장) 가능, 재할당 가능, 그치만 재할당할때 느림, 고정된 데이터를
	// 차례대로 가져와서 차레대로 처리할 경우 유리
	// - LinkedList : 데이터 하나씩 기차 엮듯 앞뒤로 주소를 가지고 있음. 수정, 삽입, 삭제에 유리
	// 2) Map : 키와 벨류로 구성되어 있고 순서가 없음, 키는 중복이 허용되지 않고(유일성) 벨류는 중복이 허용 가능함.
	// 3) Set : 순서가 없고 중복이 허용되지 않는

	// ** selectList
	public List<StudentDTO> selectList() {
		sql = "SELECT * FROM student";
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		// List 는 인터페이스 이므로 객체생성 불가. 따라서 ArrayList객체로 만듬.
		// 좌변엔 조상, 우변엔 후손 타입으로 보통 정의를 많이 하게 됨.
		// 위 처럼 다형성을 이용하는데 있어 장점은 우변을 LinkedList 로 편하게 바꿀 수 있음에 있다.

		try {

			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// => 결과의 존재 여부 확인
			// => 존재 : list 에 담기
			// => 없음 : return null
			if (rs.next()) {
				do {
//					StudentDTO dto = new StudentDTO();
//					dto.setSno(rs.getInt(1));
//					dto.setName(rs.getString(2));
//					dto.setAge(rs.getInt(3));
//					dto.setJno(rs.getInt(4));
//					dto.setInfo(rs.getString(5));
//					dto.setPoint(rs.getDouble(6));

					StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5), rs.getDouble(6));

					list.add(dto);
				} while (rs.next());

				return list;
			} else {
				return null;
			} // else
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		} // try
	} // selectList

	// ** selectOne
	// => 기본자료형 매개변수 (Call By value)
	public StudentDTO selectOne(int sno) {
		sql = "SELECT * FROM student WHERE sno = " + sno;

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getDouble(6));
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	}

	// ** selectOne2
	// => 참조자료형 매개변수 Test (Call By Reference)
	// => 비교 Test
	public void selectOne2(StudentDTO dto) {
		sql = "SELECT * FROM student WHERE sno = " + dto.getSno();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setJno(rs.getInt(4));
				dto.setInfo(rs.getString(5));
				dto.setPoint(rs.getDouble(6));
			} else {
				System.out.println("** Student 없음 **");
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());

		}
	}

	// ** insert
	public int insert(StudentDTO dto) {
		sql = "INSERT INTO student VALUES (?,?,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSno());
			pst.setString(2, dto.getName());
			pst.setInt(3, dto.getAge());
			pst.setInt(4, dto.getJno());
			pst.setString(5, dto.getInfo());
			pst.setDouble(6, dto.getPoint());

			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** Insert Exception => " + e.toString());
			return 0;
		}
	}

	// ** update
	public int update(StudentDTO dto) {
		sql = "UPDATE student SET name=?, age=?, jno=?, info=?, point=? WHERE sno=" + dto.getSno();
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			pst.setDouble(5, dto.getPoint());

			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** Update Exception => " + e.toString());
			return 0;
		}
	}

	// ** delete
	public int delete(int sno) {
		sql = "DELETE FROM student WHERE sno =" + sno;
		try {
			pst = cn.prepareStatement(sql);

			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** Delete Exception => " + e.toString());
			return 0;
		}
	}

	// ** Join Test
	// => sno, name, age, jno, jname, project, cNum, cName
	// => JoDTO 작성, joinList() 메서드 작성(StudentController,Service,DAO 에)

	public List<StudentDTO> selectJoinList() {
		sql = "SELECT M.sno, M.name, M.age, M.jno, M.jname, M.captain, s.name\r\n" + "FROM(\r\n"
				+ "SELECT sno, name, age, j.jno, j.jname, j.captain, info, point\r\n"
				+ "FROM student s LEFT JOIN jo j \r\n" + "ON s.jno = j.jno\r\n" + ") M Join student s\r\n"
				+ "ON M.captain = s.sno";
		List<StudentDTO> list = new ArrayList<StudentDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				do {
					StudentDTO dto = new StudentDTO();
					dto.setSno(rs.getInt(1));
					dto.setName(rs.getString(2));
					dto.setAge(rs.getInt(3));
					dto.setJno(rs.getInt(4));
					dto.setJname(rs.getString(5));
					dto.setCaptain(rs.getInt(6));
					dto.setcName(rs.getString(7));

					list.add(dto);

				} while (rs.next());

				return list;

			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		}
	} // selectJoinList

	// ** Transaction Test
	// => Connection 객체가 관리
	// => 기본값은 AutoCommit true 임.
	// => setAutoCommit(false) -> commit 또는 rollback
	// => Test 사항
	// - 동일자료를 2번 입력 -> 2번째 입력에서 p.key 중복 오류발생

	// 1) Transaction 적용전
	// => 동일자료를 2번 입력
	// - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
	// - Rollback 불가능
	// - MySql Command 로 1번째 입력 확인 가능

	// 2) Transaction 적용후
	// => 동일자료를 2번 입력
	// - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
	// - Rollback 가능 -> 둘다 취소됨

	public void transactionTest() {
		sql = "INSERT INTO student VALUES(20,'유리아',99,9,'Transaction 적용후',0)";

		// 1) Transaction 적용전
//		try {
//			pst = cn.prepareStatement(sql);
//			pst.executeUpdate();	// 1번째는 Table에 입력완료
//			pst.executeUpdate();	// 2번째에서 p.Key 중복 오류 발생 -> Catch블럭으로
//		} catch (Exception e) {
//			System.out.println("** Transaction 적용전 => " + e.toString());
//		}

		// 2)
		try {
			cn.setAutoCommit(false); // Start Transaction 명령어랑 똑같음 - 기본값이 true 
			pst = cn.prepareStatement(sql);
			pst.executeUpdate(); // 1번째는 입력완료 되었지만 Buffer(DBMS 시스템이 관리)에 임시 저장됨
			pst.executeUpdate(); // 2번째에서 p.Key 중복 오류 발생 -> Catch블럭으로 -> Rollback
			cn.commit();
		} catch (Exception e) {
			System.out.println("** Transaction 적용후 => " + e.toString());
			// => Rollback 적용
			try {
				cn.rollback();
				System.out.println("** Rollback 성공 **");
			} catch (SQLException e1) {
				System.out.println("** Rollback Excption => " + e.toString());
			}
		}

	} // transactionTest

} // class
