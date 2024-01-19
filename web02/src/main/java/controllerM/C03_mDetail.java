package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mdetail")
public class C03_mDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C03_mDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => session 의 Attribute 처리
		// => id 처리
		MemberService ms = new MemberService();
		MemberDTO memberInfo = ms.selectOne((String)request.getSession().getAttribute("loginId"));
		String uri = "member/memberDetail.jsp";
		
		// => Update 요청시에는 uri 값을 updateForm.jsp 로
		if("U".equals(request.getParameter("jCode"))) {
			uri = "member/updateForm.jsp";
		}
		
		// 2. 서비스 처리
		// => Service, DTO 객체 생성
		// => 결과를 View 가 출력 가능하도록 Attribute에 저
		if(memberInfo != null) {
			request.setAttribute("apple" , memberInfo);
			request.getRequestDispatcher(uri).forward(request, response);
		} else {
			request.setAttribute("message", "정보를 불러오지 못 했습니다. 잠시 후 다시 해주세요!");
			request.getRequestDispatcher(uri).forward(request, response);
		}
		
	}
}
