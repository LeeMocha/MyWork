package controllerF;

import java.util.HashMap;
import java.util.Map;

//** ServiceFactory
//=> FrontController 가 요청한 서비스 컨트롤러 클래스를 제공

//** Map 적용
//=> HashMap 객체 정의 : 전역변수 (맴버)
//=> 자료 put : 생성자에 정의

//** 싱글톤 패턴 : 인스턴스를 단 1개만 허용
//=> 일반적인 경우 : 복수의 인스턴스 가능 
// Student s1 = new Student();
// Student s2 = new Student();
//=> 클래스 외부에서 인스턴스를 생성할 수 없도록 제한  

//** 방법
//=> 생성자를 private 으로 내부에서만 사용가능하도록하고
//=> 내부에서 getInstance() 메서드로 생성 제공해줌 
//=> 외부에서는 getInstance() 메서드를 통해서만 사용

public class Ex03_ServiceFactory {
	// 1) Map 정의
	private Map<String, Ex04_Controller> mappings;
	
	//2) 생성자 정의 (싱글톤 패턴)
	//   -> 생성자를 private 으로 정의
	//   -> 내부에서 인스턴스 생성
	//   -> 인스턴스를 제공하는 getter

	private static Ex03_ServiceFactory instance = new Ex03_ServiceFactory();
	// 싱글톤 패턴에서는 필드에 셀프이너클래스 인스턴스를 하나 Static 으로 지정해줘야함.
	// 생성자가 private 이지만 비정적 생성자이기 때문에 클래스 내부에서 new 생성자 호출 가능
	// 그치만 생성된 클래스 내부 객체인 instance 또한 private이기 때문에 외부에서 접근 불가
	// 그러므로 getInstance() 메서드로 접근해야함.
	
	private Ex03_ServiceFactory() {
		mappings = new HashMap<String, Ex04_Controller>();
		
		mappings.put("/mlist.fo", new Ex05_mList());
		mappings.put("/mdetail.fo", new Ex05_mDetail());
		
	}// 생성자 메서드
	
	public static Ex03_ServiceFactory getInstance(){
		return instance;
	}
	// 필드의 instance 하나만 갖고 사용하려는 목적으로 싱글톤을 하려하는데 필드 자체도 private 이기 때문에
	// get메서드 필요, public 지정 필요
	
	//public static Ex03_ServiceFactory getInstance() { return new Ex03_ServiceFactory(); }
	//  => 싱글톤 적용되지않음 : 메서드 호출시마다 생성됨
	
	//
	public Ex04_Controller getController(String mappingName) {
	// mappingName을 통해서 해당하는 class의 instance들을 리턴하기 위해서
	// Interface 타입으로 리턴 타입 지정. (다형성)
	// 1단계 방법으로 if-else로 하게 되면 새로운 페이지가 생성될 떄마다 계속 만들어줘야함
	// 그래서 Map으로 key 값을 mappingName 들로, value값들을 mappingName에 해당하는 클래스 Instacne를 리턴 
		
		/* 1 단계
		if("mList.fo".equals(mappingName)) {
			return new Ex05_mList();
			
		} else if("mDetail.fo".equals(mappingName)) {
			return new Ex05_mDetail();
			
		} else {
			return null;
			
		} */
		
		// 2단계 Map 적용
		return mappings.get(mappingName);
		
	} // getController
	
} // class
