package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// 1. 요청 분석
		// => 한글, request 의 parameter 처리
		request.setCharacterEncoding("UTF-8");
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

			if (dto.getName().equals(name)) {
				System.out.println("** 로그인 성공 **");
				request.getRequestDispatcher(uri).forward(request, response);
			} else {
				System.out.println("** 로그인 실패 **");
				uri = "servletTestForm/flowEx04_LoginForm.jsp";
				request.getRequestDispatcher(uri).forward(request, response);
			}
		} catch (Exception e) {
			uri = "servletTestForm/flowEx04_LoginForm.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
			System.out.println("** 정보가 비어있거나 Sno에 숫자가 아닌 값이 들어갔습니다 **");
		}

		// 3. View ( Response ) : Forward
		// => 성공 : 첫 화면. /index.html
		// => 실패 : 재 로그인 유도. /servletTestForm/flow04_LoginForm.jsp

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	} // doPost

} // class
