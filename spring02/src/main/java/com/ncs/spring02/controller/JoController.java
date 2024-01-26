package com.ncs.spring02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.service.JoService;

@Controller
@RequestMapping(value = "/jo")
public class JoController {
	
	@Autowired(required = false)
	JoService service;
	
	@RequestMapping(value = "/joList" , method = RequestMethod.GET)
	public void joList (Model model) {
		model.addAttribute("banana", service.selectList());
	}
	
	@RequestMapping(value ="/detail" , method=RequestMethod.GET)
	public String joDetail (@RequestParam("jCode")int jCode, Model model ) {
		
		String uri = "jo/joDetail";
		
		model.addAttribute("apple", service.selectOne(jCode));
		
		if(jCode < 0) {
			uri = "jo/updateForm";
			jCode *= -1;
		}
		
		model.addAttribute("apple", service.selectOne(jCode));
		return uri;
	}
	
	@RequestMapping(value = "/insertForm", method = RequestMethod.GET)
	public void joInsertFrom() {
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String joInsert(Model model, JoDTO dto) {
		String uri = "jo/joDetail";
		
		
		model.addAttribute("apple", dto);
		if(service.insert(dto)>0) {
			model.addAttribute("message", "** 새로운 조가 생겼습니다!! 축하드립니다!! **");
		} else {
			System.out.println(dto);
			System.out.println(model);
			uri = "jo/insertForm";
			model.addAttribute("message", "~~ 조 생성에 실패했습니다. 조 번호 중복되는지 확인하세요! ~~");
		}
		
		return uri;
	}
	
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String joUpdate(Model model, JoDTO dto) {
		String uri = "jo/joDetail";
		
		model.addAttribute("apple", dto);
		
		if(service.update(dto) > 0) {
			// 성공 : 디테일 폼으로
			model.addAttribute("message", "** 조 정보가 수정되었습니다 !! **");
		} else {
			// 실패 : 재수정 유도
			model.addAttribute("message", "~~ 조 정보 수정 오류 !! 다시하세요 ~~");
			uri = "jo/updateForm";
		}
		
		return uri;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String joDelete(Model model, @RequestParam("jCode")int jCode, RedirectAttributes rttr) {
		String uri = "redirect:/jo/joList";
		
		if(service.delete(jCode)>0) {
			rttr.addFlashAttribute("message", "** 조가 삭제되었습니다 ** ");
		} else {
			rttr.addFlashAttribute("message", "~~ 조를 삭제하는데 오류가 생겼습니다. 관리자에게 연락하세요 ~~");
		}

		return uri;
	}
	
	
	
}
