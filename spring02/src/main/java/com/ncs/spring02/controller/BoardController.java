package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

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
	// => 글요청 처리중, 글을 읽기 전 상태
	// => 조회수 증가
	// 	  -> loginID 와 board의 id가 다른 경우
	@GetMapping("/detail")
	public String boardDetail(@RequestParam("jCode") String jCode,
			@RequestParam("seq") int seq, Model model, HttpSession session) {
		String uri = "board/boardDetail";
		BoardDTO dto = service.selectOne(seq);
		
		
		if("U".equals(jCode)) {
			uri="board/baordUpdate";
		} else if(!dto.getId().equals((String)session.getAttribute("loginID"))){
			// => 조회수 증가 조건 만족
			dto.setCnt(dto.getCnt()+1);
			service.update(dto);
		}

		model.addAttribute("apple", dto);
		
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
	
	@PostMapping("/update")
	public String update(Model model, BoardDTO dto) {
		String uri = "board/boardDetail";
		
		if(service.update(dto)>0) {
			model.addAttribute("message", "** 수정이 성공적으로 완료되었습니다! **");
		} else {
			model.addAttribute("message", "~~ 수정 오류!!! 다시 한번 확인하세요~!! ~~");
			uri = "board/boardUpdate";
		}
		
		model.addAttribute("apple", service.selectOne(dto.getSeq()));
		
		return uri;
	}
	
	@GetMapping("/delete")
	public String delete(Model model, BoardDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		
		if(service.delete(dto)>0){
			rttr.addFlashAttribute("message", "** 게시글이 성공적으로 삭제됐습니다. **");
		} else {
			rttr.addFlashAttribute("message", "~~ 게시글 삭제 오류!! 관리자와 연락하세요 ~~");
			uri = "board/boardDetail";
		}
		
		return uri;
	}
	
	//** Reply Insert
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) {
		// => 답글 처리를 위해 부모글의 root, step, indent 를 인자로 전달받으면
		// 	  이 인자에 담겨진 값은 requestScope과 생명주기가 동일함
		// => 그러므로 response 전송 전까지는 서버(Jsp)에서 사용가능
		//    단, 이 객체명의 첫문자를 소문자로 접근 가능함 ex) boardDTO.~~
	}
	
	// => 메서드명과 요청명이 위의 메서드와 동일하지만,
	// => Post 요청이고 인자가 다르기 때문에 허용됨
	@PostMapping("/replyInsert")
	public String replyInsert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		// ** 답글 등록
		String uri = "redirect:boardList";
		
		// => dto 값을 확인
		// 	-> id, title, content : 그대로 사용 가능
		// 	-> 부모글의 root : 사용가능
		//  -> 부모글의 step, indent : 부모보다 1씩 증가
		// => sql 처리
		//  -> replyInsert, 전체 답글의 step update
		
		dto.setStep(dto.getStep()+1);
		dto.setIndent(dto.getIndent()+1);
		
		if(service.rinsert(dto)>0) {
			rttr.addFlashAttribute("message" , "** 답글을 성공적으로 달았습니다! **");
		} else {
			model.addAttribute("message" , "** 답글 달기를 실패했습니다! 관리자에게 문의하세요~! **");
			uri = "board/replyInsert";
		}
		return uri;
	}
	
}
