package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/radio")
public class Ex02_Radio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex02_Radio() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1) 요청 분석
		// => 한글처리
		// => 돌아오는 Value값에 한글이 있기 때문에 중요함.
		request.setCharacterEncoding("UTF-8");

		String gender = request.getParameter("gender");
		String mailcheck = request.getParameter("mailcheck");
		String content = request.getParameter("content");
		
		// 2) Service 처리
		if(mailcheck.equals("Yes")) mailcheck ="수신동의";
		else mailcheck = "수신거절";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print("<h2>** 성별 : " + gender + "</h2>");
		out.print("<h2>** 수신 동의 : " + mailcheck + "</h2>");
		out.print("<h2>** 가입인사 : " + content + "</h2>");
		out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시 입력하기 </a></h2>");
		// javascript:history.go(-1) : 바로 전 스크립트로 가기 (-1)
		// a 안에 있으니 온클릭 이벤트 중
		// 자바스크립트에서 제공하는 객체 history가 있는데 이는 뒤로가기 앞으로가기에 대한 제어를 함
		// buffer에 내가 지나온 페이지들을 기억해 두고 있음. go함수 호출시 0(현재)를 기준으로
		// 뒤로가기는 -1, 앞으로 가기는 +1
		// 브라우저에서 몇개의 뒤로가기를 기억할 것인가를 설정 할 수 있음
		// 자바스크립트에서 최상위 개체는 Window
		
	} // do goet
} // class
