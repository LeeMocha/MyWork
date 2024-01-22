package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

public class C02_mDetail implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Member List
		MemberService service = new MemberService();
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("apple", service.selectOne("LeeMocha"));
		mv.setViewName("member/memberDetail");
		
		return mv;
		// prefix & suffix 가 있기때문에 전체 경로, 확장자 필요없음
	}
}
