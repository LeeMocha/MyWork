package myDispatcher;

import java.util.HashMap;
import java.util.Map;

public class MyHandlerMapping {
	// 1) Map 정의
	private Map<String, MyController> mappings;
	
	
	// 2) 생성자 정의 (싱글톤 패턴)
	// => 생성자 내부에서 Map 초기화
	private static MyHandlerMapping hm = new MyHandlerMapping();
	private MyHandlerMapping() {
		mappings = new HashMap<String, MyController>();
		mappings.put("/mlist", new C01_mList());
		mappings.put("/mdetail", new C02_mDetail());
	}
	
	// 3) 내부에서 인스턴스 생성 & 제공 (싱글턴 패턴이라서)
	private static MyHandlerMapping instance = new MyHandlerMapping();
	
	public static MyHandlerMapping getInstance() {
		
		return instance;
	}
	
	// 4) Dispatcher_Servlet (FrontController) 의 요청에 대한
	//    컨트롤러 클래스의 인스턴스를 제공
	public MyController getController(String mappingName) {
		return mappings.get(mappingName);
	}
	
} // class