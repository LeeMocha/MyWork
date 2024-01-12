package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/01set")
public class Ex02_01setAttribute extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public Ex02_01setAttribute() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. request 처리
		// => form 없이 쿼리스트링으로 Test
		//	  ~~/01set?id=banana&name=홍길동&age=22
		// => 한글 처리부터(Post 요청에서는 필수.)
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		System.out.println("** setAttribute Test **" );
		System.out.printf("** Parameter id = %s, name = %s, age = %s", id, name, age);
		
		// 2. setAttribute
		// => 보관 가능한 객체 4가지 , Scope : Page < Request < Session < Application
		// 2.1 ) request
		request.setAttribute("rid", id);
		request.setAttribute("rname", name);
		request.setAttribute("rage", age);
		
		// 2.2) session
		HttpSession session = request.getSession();
		// 개발자측에서 의미로 만들 수 없다. request 객체를 통해서 session객체 생성 가능
		session.setAttribute("sid", id);
		session.setAttribute("sname", name);
		session.setAttribute("sage", age);
		
		// 3. 이동 후에 getAttribute
		// => Forward / Redirect
		String uri = "02get";
		// 3.1) Forward
//		request.getRequestDispatcher(uri).forward(request, response);
		
		// 3.1) Redirect
		response.sendRedirect(uri);
		// 1번째 set01 로들어온 리퀘스트 객체는 지워지고
		// sendRedirect 하며 만들어진 새 리퀘스트 객체가 요청을 하는것이기에
		// sesson 값들만 남겨진다.
		
	} // doGet
	
} // class
