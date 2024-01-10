package jdbc02;

import java.util.List;

//** Controller
//=> 요청 : 요청분석 -> 담당 Service -> Service 는 DAO 
//=> 결과 : DAO -> Service -> Controller
//=> View : Controller -> View 담당 (Java:Console // Web:JSP, Html.., React) 


public class StudentController {
	
	//** 전역변수 선언
	StudentService service = new StudentService();
	
	
	//** View 메서드 정의 (지금은 콘솔 , 나중엔 프론트쪽으로)
	// 출력만 쫙 할 예정
	// => selectList
	public void printList(List<StudentDTO> list) {
		System.out.println("** Student List **");
		// => 출력 자료의 존재 여부 확인
		if(list!=null) {
			// => List 출력 (forEach 문)
			for(StudentDTO s : list) {
				System.out.println(s);
			}
		} else {
			System.out.println("** electList 결과가 1건도 없음 **");
		}
	}// printList
	
	public void printDetail(StudentDTO dto) {
		System.out.println("** Student List **");
		if(dto!=null) {
				System.out.println(dto);
		} else {
			System.out.println("** electList 결과가 1건도 없음 **");
		}
	}// printList
	
	public void insert(int cnt) {
		System.out.println("** Student Insert **");
		if(cnt>0) {
			System.out.println("성공적으로 입력 되었습니다.");
		} else {
			System.out.println("입력하지 못했습니다.");
		}
	}
	
	public void delete(int cnt) {
		System.out.println("** Student Delete **");
		if(cnt>0) {
			System.out.println("성공적으로 삭제 되었습니다.");
		} else {
			System.out.println("삭제하지 못했습니다.");
		}
	}
	
	public void update(int cnt) {
		System.out.println("** Student Update **");
		if(cnt>0) {
			System.out.println("성공적으로 변경 되었습니다.");
		} else {
			System.out.println("변경하지 못했습니다.");
		}
	}
	
	
	public static void main(String[] args) {
		
		// ** selectList
		// => '요청' 에 해당하는 Service 의 메서드를 호출
		// 	-> 처리 결과를 View 에 전달해서 출력하도록 함.
		StudentController sc = new StudentController();
		
		sc.printList(sc.service.selectList());
//		sc.insert(sc.service.insert(new StudentDTO(30,"이정혁",35, 9, "응", 100)));
//		sc.printDetail(sc.service.selectOne(30));
//		sc.update(sc.service.update(new StudentDTO(30,"이정진",48,9,"아니",99)));
//		sc.printDetail(sc.service.selectOne(30));
//		sc.delete(sc.service.delete(30));
		
//		sc.printList(sc.service.selectJoinList());
		
		sc.service.transactionTest();
		sc.printList(sc.service.selectList());
		
	}	// main
}	// class