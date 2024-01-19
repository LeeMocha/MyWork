package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;


@WebServlet("/mupdate")
public class C05_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C05_mUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// 1. 요청분석
				// => request 의 Parameter 처리
				// => 성공 : memberDetail 로 (memberDetail.jsp)
				// => 실패 : 재수정 유도 (updateForm.jsp)
				// => 출력객체 (apple) 필요함
				//		-> redirect 또는 전달된 값들을 apple 에 저장
				// => post요청으로 들어오는 것이기에 한글화 필수 
				request.setCharacterEncoding("UTF-8");
				String uri = "member/memberDetail.jsp";
				// => 실패 : 재가입 유도 ( joinFrom.jsp )
				
				MemberDTO dto = new MemberDTO();
				
				dto.setId(request.getParameter("id"));
				dto.setPassword(request.getParameter("password"));
				dto.setName(request.getParameter("name"));
				dto.setAge(Integer.parseInt(request.getParameter("age")));
				dto.setJno(Integer.parseInt(request.getParameter("jno")));
				dto.setInfo(request.getParameter("info"));
				dto.setPoint(Double.parseDouble(request.getParameter("point")));
				dto.setBirthday(request.getParameter("birthday"));
				dto.setRid(request.getParameter("rid"));
				
				// => 결과 출력을 위해 apple 에 보관
				request.setAttribute("apple", dto);
				
				// 2. 서비스 처리
				// => Service, DTO 객체 생성 & 실행
				MemberService ms = new MemberService();
				
				if(ms.update(dto)>0) {
					// 성공
					request.setAttribute("message", "** 회원정보 수정 성공!!!! **");
					request.getSession().setAttribute("loginName", dto.getName());
					
				} else {
					// 실패
					request.setAttribute("message", "** 회원수정 실패!! 다시 하세요 ~~ ");
					uri = "member/updateForm.jsp";
					request.getRequestDispatcher(uri).forward(request, response);
				}
				// 3. View (Response) : Forward
				request.getRequestDispatcher(uri).forward(request, response);
				
			} // doGet
			
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
				
			} // doPost
}
