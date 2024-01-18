package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.MemberDTO;



//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
//   Create(Insert), Read(selectList, selectOne), Update, Detete

//** 첫번째 예제 DBStart와 ~~~DAO 와 차이점
//=> 결과를 직접 출력하거나 처리하지않고 요청자에게 제공해야됨
//=> 즉, 메서드 역할멸로 처리 결과를 return 해야됨
//=> 그러므로 특히 select 의 결과를 잘 전달하기 위해서는 결과를 객체화해야됨 
public class MemberDAO {
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
	public List<MemberDTO> selectList() {
		sql = "SELECT * FROM member";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		// List 는 인터페이스 이므로 객체생성 불가. 따라서 ArrayList객체로 만듬.
		// 좌변엔 조상, 우변엔 후손 타입으로 보통 정의를 많이 하게 됨.
		// 위 처럼 다형성을 이용하는데 있어 장점은 우변을 LinkedList 로 편하게 바꿀 수 있음에 있다.

		try {

			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				do {
					System.out.println("1");
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));

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
	public MemberDTO selectOne(String id) {
		sql = "SELECT * FROM member WHERE id = ?";
		// => Login : ~~~~~ where sno =? and name =?
		// 	  password 암호화 때문에 직접 비교 불가능 하기 때문에 비권장함.
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1,id);
			rs = pst.executeQuery();
			if (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAge(rs.getInt(4));
				dto.setJno(rs.getInt(5));
				dto.setInfo(rs.getString(6));
				dto.setPoint(rs.getDouble(7));
				dto.setBirthday(rs.getString(8));
				dto.setRid(rs.getString(9));
				
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	}
	
	// ** insert
	// => 모든 컬럼 입력
	public int insert(MemberDTO dto) {
		sql = "INSERT INTO member VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());

			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** Insert Exception => " + e.toString());
			return 0;
		}
	}

	// ** update
	// => id(P.key)를 제외한 모든 컬럼 수정
	public int update(MemberDTO dto) {
		sql = "UPDATE membet SET password=?"
				+ ", name=?, age=?, jno=?, info=?"
				+ ", point=?, birthday=?, rid=? WHERE id= ?";
		try {
			pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getPassword());
			pst.setString(2, dto.getName());
			pst.setInt(3, dto.getAge());
			pst.setInt(4, dto.getJno());
			pst.setString(5, dto.getInfo());
			pst.setDouble(6, dto.getPoint());
			pst.setString(7, dto.getBirthday());
			pst.setString(8, dto.getRid());
			pst.setString(9, dto.getId());

			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** Update Exception => " + e.toString());
			return 0;
		}
	}

	// ** delete
	public int delete(String id) {
		sql = "DELETE FROM member WHERE id = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			
			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** Delete Exception => " + e.toString());
			return 0;
		}
	}
} // class
