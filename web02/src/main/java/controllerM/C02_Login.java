package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C02_Login() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리
		// => id, password 처
		String id = (String)request.getParameter("id");
		String password = (String)request.getParameter("password");
		
		MemberService ms = new MemberService();
		MemberDTO member = ms.selectOne(id);
		
		// 2. 서비스 처리
		// => Service, DTO 객체 생성
		// => id 확인 : Service의 selectOne()
		// => id확인 되면 password 일치 여부 확
		// => 성공 : id, name 을 session에 보관, home으로
		//    실패 : loginForm 으로, message 출력, 재로그인 유
		
		if(member != null) {
			if(member.getId().equals(id) && member.getPassword().equals(password)) {
				
				request.getSession().setAttribute("loginId", member.getId());
				request.getSession().setAttribute("loginName", member.getName());
				
				// 3. View (Response) :Forward
				
				response.sendRedirect("home.jsp");
			} else {
			request.setAttribute("message", "로그인에 실패했습니다. id와 password를 다시 확인해서 입력해주십시오.");
			request.getRequestDispatcher("member/loginForm.jsp").forward(request, response);
			
			}
		} else {
			request.setAttribute("message", "로그인에 실패했습니다. id와 password를 다시 확인해서 입력해주십시오.");
			request.getRequestDispatcher("member/loginForm.jsp").forward(request, response);
		}
	}
}
