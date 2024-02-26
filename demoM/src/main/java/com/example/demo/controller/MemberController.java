package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

//@Log4j -> Boot 에서는 2015년 이후 지원중단
@Log4j2
@AllArgsConstructor // 개별적인 @Autowired 생략 가능
@Controller
@RequestMapping(value = "/member")
public class MemberController {

	MemberService service;
	PasswordEncoder passwordEncoder; // DemoConfig 에 설정

	@GetMapping("/log4jTest")
	public String log4jTest() {
		String name = "banana";

		log.error("** Lombok @Log4j Test Error: name => " + name);
		log.warn("** Lombok @Log4j Test Warn: name => " + name);
		log.info("** Lombok @Log4j Test Info: name => " + name);
		log.debug("** Lombok @Log4j Test Debug: name => " + name);
		log.trace("** Lombok @Log4j Test Trace: name => " + name);

		return "redirect:/";
	}

	@RequestMapping(value = { "/loginForm" }, method = RequestMethod.GET)
	public void loginForm(Model model) {
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, MemberDTO dto) {
		// session을 받아오기 위한 request도 필요 없음
		// message 처리를 위한 Model

//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
		// 이 과정이 생략 되고 자동화로 다 넣어줌
		// @RequestMapping 처리된 메서드의 매개변수에다 지정한 객체들의 필드(맴버변수)가
		// 요청명과 같은 이름으로 존재한다면 그 값을 자동으로 대입해줌
		// 즉, 위에 id, password 대입하는 과정을 자동화 해줌.

		// 1. 요청분석
		// => requst 로 전달되는 id, password 처리:
		// 매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		// ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
		// => 전달된 password 보관

		String password = dto.getPassword();
		// 화면에 입력한 비밀번호 임으로 먼저 저장!

		String uri = "redirect:/home"; // 성공시 uri
		// redirect 요청시엔 위에 처럼 적어만 줘도 리다이렉트 됨
		// 명시 안할경우 forward

		// 2. 서비스 & 결과 처리
		// => id 확인
		// => 존재하면 Password 확인
		// => 성공: id, name은 session에 보관, home 으로
		// => 실패: 재로그인 유도

		dto = service.selectOne(dto.getId());

//	    if(dto != null && dto.getPassword().equals(password)){
		if (dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		} else {
			// 실패
			uri = "member/loginForm";
			model.addAttribute("message", "~~ id 또는 password 오류 !! 다시하세요 ~~");
		}
		return uri;
	} // login

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/";
	} // logout

	// ** Member Detail & Update
	// => 단일 Parameter 의 경우 @RequestParam("...") 활용
	// String jCode = request.getParameter("jCode") 과 동일
	// 단, 해당하는 Parameter 가 없으면 400 오류 발생
	// 그러므로 detail 요청에도 ?jCode=D 를 추가함.
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		// 1. 요청분석
		// => id: session 에서 get
		// => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별
		String id = (String) session.getAttribute("loginID");
		String uri = "member/memberDetail"; // detail

		// => update 요청 확인 후 uri 수정
		if ("U".equals(jCode))
			uri = "member/updateForm";

		// 2. Service & 결과 처리
		model.addAttribute("apple", service.selectOne(id));
		return uri;
	}

	// ** JoinForm
	@RequestMapping(value = { "/joinForm" }, method = RequestMethod.GET)
	public void joinForm(Model model) {

	} // joinForm

	// ** Member List
	@RequestMapping(value = { "/memberList" }, method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
		// 요청에 대한 인스턴스 mapping
	}

	// ** Join
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(HttpServletRequest request, Model model, MemberDTO dto) throws IOException {
		// 1. 요청 분석
		// => 이전 : 한글처리, request의 파라미터 값처리 -> dto 에 저장
		// => Spring : 한글 = 필터 , request 처리 = 파라미터로 자동화 , dto 저장 = 필드 이름별로 자동화
		String uri = "member/loginForm"; // 성공시

		// *** Upload File 처리
		// -> 전달된 파일 저장 : file1 (저장할 곳의 real-Path 가 필요하게 됨)
		// -> 전달된 파일명 Table 에 저장 : file2
		// -> MultipartFile : 위의 과정을 지원해주는 전용객체

		// 1) 물리적 실제저장위치 확인
		// 1.1) 현재 웹어플리케이션의 실행위치 확인
		// => 이클립스 개발환경 ( 배포전 ) : ~~.eclipse.~~
		// => 톰캣 서버 배포후
		String realPath = request.getRealPath("/");
		System.out.println("** realPath => " + realPath);

		// 1.2) realPath 를 이용해서 물리적 저장위치 (file1) 확인
		if (!realPath.contains("-tomcat-")) { // 개발중
			realPath += "\\resources\\uploadImages\\";
		} else {
			realPath = "C:\\MTest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";
		}

		// 1.3) 폴더 만들기 (없을수도 있음을 가정, File)
		// => File Type 객체 생성 : new File("경로"); => 전에 realPath 에 담긴 경로는 그냥 String 타입인데
		// 이제 File 객체로 경로로서 파일을 검증 가능
		// => file.exists()
		// -> 파일 또는 폴더가 존재하는지 리턴
		// -> 폴더가 아닌, 파일존재 확인하려면 file.isDirectory() 도 함께 체크해야함.
		// ( 참고: https://codechacha.com/ko/java-check-if-file-exists/ )
		// => file.isDirectory() : 폴더이면 true 그러므로 false 이면 file 이 존재 한다는 의미가 됨.
		// => file.isFile()
		// -> 파일이 존재하는 경우 true 리턴,
		// file의 Path 가 폴더인 경우는 false 리턴
		File file = new File(realPath);
		if (!file.exists())
			file.mkdir();
		// => 저장폴더가 존재하지 않는 경우 만들어줌

		// --------------------------------------------------------------------------------
		// ** File Copy 하기 (IO Stream)
		// => 기본이미지(basicman4.png) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
		// => IO 발생: Checked Exception 처리
		file = new File(realPath + "basicman1.jpg"); // uploadImages 폴더에 화일존재 확인을 위함
		if (!file.isFile()) { // 존재하지않는 경우
			String basicImagePath = "C:\\MTest\\MyWork\\demoM\\src\\main\\webapp\\resources\\images\\basicman1.jpg";
			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			// => basicImage 읽어 파일 입력바이트스트림 생성
			FileOutputStream fo = new FileOutputStream(file);
			// => 목적지 파일(realPath+"basicman1.png") 출력바이트스트림 생성
			FileCopyUtils.copy(fi, fo);
		}
		// --------------------------------------------------------------------------------
		// ** MultipartFile
		// => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
		// -> String getOriginalFilename(),
		// -> void transferTo(File destFile),
		// -> boolean isEmpty()

		// 1.4) 저장경로 완성
		String file1 = "", file2 = "basicman1.jpg";

		MultipartFile uploadfilef = dto.getUploadfilef();

		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => image_File 을 선택함
			// 1.4.1) 물리적위치 저장 (file1)
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)
			// 1.4.2) Table 저장경로 완성 (file2)
			file2 = uploadfilef.getOriginalFilename();
		}
		dto.setUploadfile(file2);

		// 2. Service & 결과 처리
		// => PasswordEncoder 적용
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		// ** *****************************************
		// ** Transaction_AOP 적용 *********************
		// 1. 준비: pom.xml (dependency) 확인
		// => AspectJ(기본제공), AspectJ Weaver(추가)

		// 2. servlet-context.xml AOP 설정

		// 3. Rollback Test
		// 3.1) Aop xml 적용전 => insert1 은 입력되고, insert2 에서 500_Dupl..Key 오류 발생
		// 3.2) Aop xml 적용후 => insert2 에서 오류발생시 모두 Rollback 되어 insert1, insert2 모두 입력 안됨

		// 3.1) Transaction 적용전 : 동일자료 2번 insert
		// => 첫번째는 입력완료(commit) 되고, 두번째자료 입력시 Key중복 오류발생 (500 발생)
		// 3.2) Transaction 적용후 : 동일자료 2번 insert
		// => 첫번째는 입력완료 되고, 두번째 자료입력시 Key중복 오류발생 하지만,
		// rollback 되어 둘다 입력 안됨
//	    service.insert(dto); // Transaction_Test, insert1

		if (service.insert(dto) > 0) {
			// 성공 : 로그인 폼으로
			model.addAttribute("message", "** 회원가입이 완료되었습니다 !! 로그인 후 이용하세요 !! **");
		} else {
			// 실패 : 재가입 유도
			uri = "member/joinForm";
			model.addAttribute("message", "~~ 회원가입 실패 !! 다시하세요 ~~");
		}

		return uri;
	} // join

	// ** Join
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, MemberDTO dto, HttpSession session, HttpServletRequest request)
			throws IOException {
		// 1. 요청 분석
		// => 이전 : 한글처리, request의 파라미터 값처리 -> dto 에 저장
		// => Spring : 한글 = 필터 , request 처리 = 파라미터로 자동화 , dto 저장 = 필드 이름별로 자동화
		// => 성공 : memberDetail, 실패 : updateForm
		// => 두 경우 모두 dto 객체 값("apple") 필요하므로 보관
		String uri = "member/memberDetail"; // 성공시
		model.addAttribute("apple", dto);

		// ** uploadFile 처리
		// => newImage 선택 여부
		// => 선택 -> old Image 삭제, newImage 저장: uploadfilef 사용
		// => 선택하지 않은 경우 -> oldImage 가 uploadfile 에 전달되었으므로 사용.

		MultipartFile uploadfilef = dto.getUploadfilef();

		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => newImage 를 선택
			// 1.4.1) 물리적위치 저장 (file1)
			String realPath = request.getRealPath("/");
			String file1;

			// 2) realPath 를 이용해서 물리적 저장 위치 (file1) 확인
			if (!realPath.contains("-tomcat-")) { // 개발중
				realPath += "\\resources\\uploadImages\\";
			} else {
				realPath = "C:\\MTest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";
			}
			// 3) oldFile 삭제
			// => oldFile Name : dto.getUploadfile()
			// => 삭제경로 : realPath+dto.getUploadfile()
			File delFile = new File(realPath + dto.getUploadfile()); // 파일 타입으로 생성. Copy의 경우 input 태그를 사용하여 활용 가능.
			if (delFile.isFile()) {
//			 delFile.delete();
			}

			// 4) newFile 저장
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저징(붙여넣기)

			// 5) Table 저장경로 완성
			dto.setUploadfile(uploadfilef.getOriginalFilename());
		}

		// 2. Service & 결과 처리
		if (service.update(dto) > 0) {
			// 성공 : 디테일 폼으로
			model.addAttribute("message", "** 회원 정보가 수정되었습니다 !! **");
			session.setAttribute("loginName", dto.getName());
			// => name 을 수정할 경우를 대비하여 loginName update

		} else {
			// 실패 : 재수정 유도
			uri = "member/updateForm";
			model.addAttribute("message", "~~ 회원 정보 수정 오류 !! 다시하세요 ~~");
		}
		return uri;
	} // join

	// ** Delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String detail(HttpSession session, Model model, RedirectAttributes rttr) {
		// 1. 요청분석
		// => id: session 에서 get
		// => delete & session 처리도 같이 지워줘야함
		String id = (String) session.getAttribute("loginID");
		String uri = "redirect:/";

		// 2. Service & 결과 처리

		if (service.delete(id) > 0) {
//			model.addAttribute("message", "** 탈퇴 성공 !! 1개월 후 재가입 바랍니다 !! **");
			// => requestScope 의 message 를 redirect 시에도 유지하려면
			// session 에 보관했다가 사용후에는 삭제해야함
			// session 에 보관후 redirect 되어진 요청 처리 시에 requestScope 으로 옮기고, session 삭제
			// => 위 과정을 자동화하는 API 가 RedirectAttributes
			session.invalidate();
			rttr.addFlashAttribute("message", "** 탈퇴 성공 !! 1개월 후 재가입 바랍니다 !! **");
		} else {
//			model.addAttribute("message", "~~ 탈퇴 실패 !! 당신은 못 나간다!!! (관리자에게 연락하세요) ~~");
			rttr.addFlashAttribute("message", "~~ 탈퇴 실패 !! 당신은 못 나간다!!! (관리자에게 연락하세요) ~~");
		}
		return uri;
	}

	@GetMapping("/idDupCheck")
	public void idDupCheck(@RequestParam("id") String id, Model model) {
		// 1) newID 존재여부 확인 & 결과 모델에 처리
		if (null != service.selectOne(id)) {
			// => 사용 불가능
			model.addAttribute("idUse", "F");
		} else {
			// => 사용 가능
			model.addAttribute("idUse", "T");
		}
		;
	}

	// ** Password 수정 (PasswordEncorder 추가 후)
	// => Service, DAO 에 pwUpdate(dto) 메서드 추가
	// => 성공 : Session 무효와 & 로그인창으로 재로그인하러
	// 실패 : pwUpdate , 재수정 유도
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
	}

	@PostMapping("/pwUpdate")
	public String pwUpdate(Model model, MemberDTO dto, HttpSession session) {
		// 1) 요청분석
		// => id : session 에서
		// => password : 암호화
		dto.setId((String) session.getAttribute("loginID"));
		String uri = "member/loginForm"; // 성공시
		System.out.println(dto);
		System.out.println(dto.getPassword());
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		System.out.println(dto.getPassword());

		// 2) Service
		if (service.pwUpdate(dto) > 0) {
			// => 성공
			session.invalidate();
			model.addAttribute("message", "** 비밀번호가 변경되었습니다! 재로그인 후 이용하세요~! **");
		} else {
			model.addAttribute("message", "~~ 비밀번호 변경을 실패하였습니다. 다시 한번 확인 바랍니다 ~~");
			uri = "member/pwUpdate";
		}
		return uri;
	}

	@GetMapping("/mPageList")
	public void mPageList(Model model, SearchCriteria cri, PageMaker pageMaker, HttpServletRequest request) {
		// 1) Criteria 처리
		// => ver01: currPage, rowsPerPage 값들은 Parameter 로 전달되어 자동으로 cri에 set
		// => ver02: ver01 + searchType, keyword 도 동일하게 cri에 set 되어져 들어옴
		cri.setSnoEno();

		// 2) Service 처리
		// => 출력 대상인 Rows 를 select
		// => ver01, 02 모두 같은 Service 메서드를 사용,
		// mapper interface 에서 사용하는 sql 구문 교체
		// 즉, BoardMapper.xml 에 새로운 sql문 두개 추가, BoardMapper.java interface 수정
		model.addAttribute("banana", service.mPageList(cri));

		// 3) View 처리 : pageMaker 활용
		// => cri, totalRowsCount (read from DB)

		// => 요청명을 url 에 포함하기 위함
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		pageMaker.setCri(cri);
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		pageMaker.setMapptinName(mappingName);
		model.addAttribute("pageMaker", pageMaker);
	}

	@GetMapping("/mCheckList")
	public String mCheckList(Model model, SearchCriteria cri, PageMaker pageMaker, HttpServletRequest request) {

		String uri = "member/mPageList";

		// => 요청명을 url 에 포함하기 위함
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		// 1) Criteria 처리
		cri.setSnoEno();

		// 2) Service 처리
		// => check 의 값을 선택하지 않은경우 check 값을 null 로 확실하게 해줘야함.
		// mapper 에서 명확하게 구분할수 있도록해야 정확한 저리가능
		if (cri.getCheck() != null && cri.getCheck().length < 1) {
			cri.setCheck(null);
		}

		model.addAttribute("banana", service.mCheckList(cri));
		// 3) View 처리 : pageMaker 활용
		// => cri, totalRowsCount (read from DB)
		pageMaker.setCri(cri);
		pageMaker.setTotalRowsCount(service.checkRowsCount(cri));
		pageMaker.setMapptinName(mappingName);
		model.addAttribute("pageMaker", pageMaker);

		return uri;

	} // bCheckList

	@GetMapping(value="/axMemberList")
	public String axMemberList(Model model) {
		
		model.addAttribute("banana", service.selectList());
		
		return "axTest/axMemberList";
	}
	
	// ** Ajax Member_Paging
	// => ver01: "/axmcri" 만 구현 (Search 기능만 구현했었음)
	// => ver02: "/axmcheck" 이 요청도 처리할 수 있도록 구현  
	//		-> mappingName 에 "check" 가 포함되어있다면 service 를 아래 메서드로 처리하도록 변경
	//		   service.mCheckList(cri), mCheckRowsCount(cri) 호출
	@GetMapping({"/axmcri","/axmcheck"})
	public String axmcri(Model model,SearchCriteria cri, PageMaker pageMaker, HttpServletRequest request){
		// 1) Criteria 처리
		// => ver01: currPage, rowsPerPage 값들은 Parameter 로 전달되어 자동으로 cri에 set
		// => ver02: ver01 + searchType, keyword 도 동일하게 cri에 set 되어져 들어옴
		cri.setSnoEno();
		

		// 2) 요청 확인 & Service 처리
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
		pageMaker.setCri(cri);
		pageMaker.setMapptinName(mappingName);
		
		if(mappingName.contains("check")) {
			// => check 조건 처리
			model.addAttribute("banana", service.mCheckList(cri));
			pageMaker.setTotalRowsCount(service.checkRowsCount(cri));
			
		} else {
			// => Search 조건 처리
			model.addAttribute("banana", service.mPageList(cri));
			pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		}

		// 3) View 처리 : pageMaker 활용
		// => cri, totalRowsCount (read from DB)
		// => 요청명을 url 에 포함하기 위함

		model.addAttribute("pageMaker", pageMaker);
		
		return "axTest/axmPageList";
	} // axmcri
	
} // class
