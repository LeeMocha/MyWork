package com.ncs.spring01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.MemberService;

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller
//  -> 사용자 요청을 제어하는 Controller 클래스
//  -> DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.
//  -> 본래 DispatcherServlet에 인식하게 해주려고 Controller 인터페이스를 implements 했었음
//  -> interface Controller 의 구현의무 없어짐
//  -> 이로 인해 메서드 overriding 의무도 없어짐 Ex) handleRequest()
//  -> 이로 인해 메서드 명, 매개변수, 리턴타입들이 자유로워 짐
//  -> 다만 리턴타입은 ModelAndView 또는 String , void 이렇게 세가지만 올 수 있음
//  -> 그 이유는 컨트롤러의 목적은 바뀌지 않았기 때문 ( 요청에 대해서 해당 Bean 인스턴스를 map 에다 넣는 목적)
//  -> 그리고 클래스와 메서드 단위로 mapping이 가능한 @RequestMapping 이 등장하게됨.
//  -> 그러므로 하나의 Controller 클래스에서 여러개의 매핑메서드 구현이 가능해짐
//  -> 그래서 주로 테이블(엔티티) 단위로 작성함 ( MemberController.java ) 
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//			        DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가 

@Controller
public class MemberController {

	@Autowired(required = false)
	MemberService service;
	
	// ** Member List
	@RequestMapping(value = {"/mlistsp","/mlist"}, method = RequestMethod.GET)
	public String mList(Model model) {
		
		model.addAttribute("banana", service.selectList());
		// 요청에 대한 인스턴스 mapping 
				
		return "member/memberList";
		// 최종 리턴 결과물 : View 의 경로
	}
	
	// ** Member Detail
	@RequestMapping(value = {"/mdetailsp","/mdetail"}, method = RequestMethod.GET)
	public String mDetil(Model model) {
		
		model.addAttribute("apple", service.selectOne("LeeMocha"));
		// 요청에 대한 인스턴스 mapping 
		
		return "member/memberDetail";
		// 최종 리턴 결과물 : View 의 경로
	}
	
	
	
} // class
