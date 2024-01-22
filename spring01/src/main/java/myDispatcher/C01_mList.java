package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class C01_mList implements MyController {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// Member List
		MemberService service = new MemberService();
		
		request.setAttribute("banana", service.selectList());
		
		return "member/memberList";
		// prefix & suffix 가 있기때문에 전체 경로, 확장자 필요없음
		
	}
}
