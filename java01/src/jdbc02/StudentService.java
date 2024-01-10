package jdbc02;

import java.util.List;

//** Service
// => Controller 요청에 해당하는 DAO 의 메소드를 실행
// => Controller 와 DAO의 중간에 위치하면서
// 	  이 둘의 의존성(필요성, Controller는 List, StudentDTO 같은 것들이 의존관계임)을 낮추어줌.
// 의존성 => 다른 클래스를 수정하는 동안 받는 영향도
public class StudentService {
	
	// ** 전역변수 정의
	StudentDAO dao = new StudentDAO();
	
	// ** selectList
	public List<StudentDTO> selectList(){
		return dao.selectList();
	}
	
	// ** selectOne
	public StudentDTO selectOne(int sno) {
		return dao.selectOne(sno);
	}
	// ** selectOne2
	public void selectOne2(StudentDTO dto) {
		dao.selectOne2(dto);
	}
	
	// ** insert
	public int insert(StudentDTO dto) {
		return dao.insert(dto);
	}
	
	// ** update
	public int update(StudentDTO dto) {
		return dao.update(dto);
	}

	// ** delete
	public int delete(int sno) {
		return dao.delete(sno);
	}
	
	// ** selectJoinList
	public List<StudentDTO> selectJoinList(){
		return dao.selectJoinList();
	}
	
	public void  transactionTest() {
		dao.transactionTest();
	}

	
}
