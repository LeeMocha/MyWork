package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// ** getAttribute 
// => 전달된 Attribute 확인 출력

@WebServlet("/02get")
public class Ex02_02getAttribute extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public Ex02_02getAttribute() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Attribute 확인
		// => getAttribute

		// => request
		String rid = (String)request.getAttribute("rid");
		String rname = (String)request.getAttribute("rname");
		String rage = (String)request.getAttribute("rage");
		
		// => session
		HttpSession session = request.getSession(); 
		String sid = (String)session.getAttribute("sid");
		String sname = (String)session.getAttribute("sname");
		String sage = (String)session.getAttribute("sage");
		
		// 2. View 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2>** 1) Parameter 값 확인 **</h2>");
		out.print("<h3>=> request객체에 담겨있는 Parameter값이 유지되고 있는지 확인</h3>");
		out.printf("<h3> I D : %s </h3>", request.getParameter("id"));
		out.printf("<h3> NAME : %s </h3>", request.getParameter("name"));
		out.printf("<h3> AGE : %s </h3>", request.getParameter("age"));
		out.print("<h2>** 2) request Attribute 값 확인 **</h2>");
		out.printf("<h3> I D : %s </h3>", rid);
		out.printf("<h3> NAME : %s </h3>", rname);
		out.printf("<h3> AGE : %s </h3>", rage);
		out.print("<h2>** 3) session Attribute 값 확인 **</h2>");
		out.printf("<h3> I D : %s </h3>", sid);
		out.printf("<h3> NAME : %s </h3>", sname);
		out.printf("<h3> AGE : %s </h3>", sage);
		// 과정을 보면 , 클라이언트 요청은 set01 > get01 > response 가 되는건데
		// get01 에서만 서버를 돌려보면 session값은 계속 남아 있는 것을 확인 할 수 있음
		
	} // doGet
	
} // class
