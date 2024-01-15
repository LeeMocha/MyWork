package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청 분석
		// => 한글, request 의 parameter 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		String uri = "";

		// 2. Service 처리
		// => Service 의 selectOne 메서드 사용 : sno 확인
		// => sno 가 존재하면 그에 해당하는 name 확인
		// => name 도 맞다면 index.html 로
		// => 실패하면 재로그인 화면
		// => sno 가 없다면 재로그인 화면으로
		StudentService ss = new StudentService();
		StudentDTO dto = new StudentDTO();

		try {
			dto = ss.selectOne(Integer.parseInt(sno));

			// 3. View ( Response ) : Forward
			// => 성공 : 첫 화면. /index.html
			// => 실패 : 재 로그인 유도. /servletTestForm/flow04_LoginForm.jsp
			
			if (dto.getSno() == Integer.parseInt(sno) && dto.getName().equals(name)) {
				uri = "home.jsp";
				HttpSession session = request.getSession();
				
				session.setAttribute("dto", dto);
				
				System.out.println("** 로그인 성공 **");
				response.sendRedirect(uri);
			} else {
				uri = "servletTestForm/flowEx04_LoginForm.jsp";
				System.out.println("** 로그인 실패 **");
				request.setAttribute("message", "로그인에 실패했습니다");
				request.getRequestDispatcher(uri).forward(request, response);
			}
		} catch (Exception e) {
			uri = "servletTestForm/flowEx04_LoginForm.jsp";
			System.out.println("** 정보가 비어있거나 Sno에 숫자가 아닌 값이 들어갔습니다 **");
			request.setAttribute("message", "로그인에 실패했습니다");
			request.getRequestDispatcher(uri).forward(request, response);
		}
	} // doPost

} // class
