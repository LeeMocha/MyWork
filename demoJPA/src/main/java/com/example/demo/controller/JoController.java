package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/jo")
@AllArgsConstructor // => 모든 필드값을 초기화 하겠다는 의미, 고로 필드들에 @Autowired 가 필요 없어짐
public class JoController {
	
	JoService service;
	MemberService mService;
	
	
	@GetMapping("/joList")
	public void joList (Model model) {
		model.addAttribute("banana", service.selectList());
	} // joList
	
	@GetMapping("/detail")
	public String joDetail (@RequestParam("jCode")int jCode, Model model ) {
		String uri = "jo/joDetail";
		
		if(jCode < 0) {
			uri = "jo/updateForm";
			jCode *= -1;
		}
		
		model.addAttribute("apple", service.selectOne(jCode));
//		MemberService mService = new MemberService();
		// 이 경우 NullPointException 뜸. 이유는 모든 인스턴스를 Spring(lombok) 이 관리하기 때문
		model.addAttribute("members", mService.findByJno(jCode));
		
		return uri;
		
	} // detail
	
	@GetMapping("/insertForm")
	public void joInsertFrom() {
	}  // insertForm
	
	@PostMapping("/insert")
	public String joInsert(Model model, Jo entity) {
		String uri = "jo/joDetail";
		
		model.addAttribute("apple", entity);
		
		if(service.selectOne(entity.getJno())!=null) {
			log.info("!! DB 에 이미 생성된 Jno에 해당하는 Jo가 있습니다.");
			model.addAttribute("message", "!! DB 에 이미 생성된 Jno에 해당하는 Jo가 있습니다.");
			uri = "jo/insertForm";
			return uri;
		}
		
		try {
			log.info("** Jo insert 성공 => "+ service.save(entity));
			model.addAttribute("message", "** 새로운 조가 생겼습니다!! 축하드립니다!! **");
		} catch (Exception e) {
			log.info("** Jo insert 실패 => "+ e.toString());
			uri = "jo/insertForm";
			model.addAttribute("message", "~~ 조 생성에 실패했습니다. 조 번호 중복되는지 확인하세요! ~~");
		}
	
		return uri;
	} // insert
	
	@PostMapping("/update")
	public String joUpdate(Model model, Jo entity) {
		String uri = "jo/joDetail";
		
		model.addAttribute("apple", entity);
		
		try {
			log.info("** Jo update 성공 => "+ service.save(entity));
			model.addAttribute("message", "** 조 정보가 수정되었습니다 !! **");
		} catch (Exception e) {
			log.info("** Jo update 실패 => "+ e.toString());
			uri = "jo/updateForm";
			model.addAttribute("message", "~~ 조 정보 수정 오류 !! 다시하세요 ~~");
		}
	
		return uri;
	} // update
	
	@GetMapping("/delete")
	public String joDelete(Model model, @RequestParam("jCode")int jCode, RedirectAttributes rttr) {
		String uri = "redirect:/jo/joList";
		
		try {
			service.deleteById(jCode);
			log.info("** Jo DeleteById 성공 => " + jCode + "조");
			rttr.addFlashAttribute("message", "** 조가 삭제되었습니다 ** ");
		} catch (Exception e) {
			rttr.addFlashAttribute("message", "~~ 조를 삭제하는데 오류가 생겼습니다. 관리자에게 연락하세요 ~~");
		}

		return uri;
		
	} // delete
	
} // class
