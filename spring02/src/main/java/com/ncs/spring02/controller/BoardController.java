package com.ncs.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	BoardService service;
	
	// ** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana", service.selectList());
	} // boardList
	
	// ** Board Detail
	@GetMapping("/detail")
	public String boardDetail(@RequestParam("jCode") String jCode,
			@RequestParam("seq") int seq, Model model) {
		
		String uri = "board/boardDetail";
		
		if("U".equals(jCode)) {
			uri="board/baordUpdate";
		}
		
		model.addAttribute("apple", service.selectOne(seq));
		
		return uri;
	} // boardDetail
	
	@GetMapping("/boardInsert")
	public void boardInsert() {
	}
	
	@PostMapping("/insert")
	public String insert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		
		model.addAttribute("apple", service.selectOne(dto.getSeq()));
		
		if( dto.getTitle().length()>0 && service.insert(dto)>0) {
			rttr.addFlashAttribute("message", "** 글 작성이 완료되었습니다!! **");
		} else {
			model.addAttribute("message", "~~ 누락된 항목이 없는지 확인하세요 ~~");
			uri = "board/boardInsert";
		}
		return uri;
	}
//	
//	@PostMapping("/update")
//	public String update(Model model, BoardDTO) {
//		
//	}
	
}
