package mvcTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/list2")
public class Ex01_MVC02List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex01_MVC02List() {
		super();
	}

	// ** MVC 패턴1 StudentList 출력하기
	// => 요청 Service 처리
	// => 결과를 출력 (Java 스크립트)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// => 요청 Service 처리
		StudentService ss = new StudentService();
		List<StudentDTO> list = ss.selectList();

		// => 결과 출력 : JSP , Java 스크립트
		// => Service 결과물인 list를 JSP 가 출력할 수 있도록 Attribute에 보관
		// => 서버 안에서 가져와서 한번만 보내주면 되는것이기 때문에 request 객체에 보내기
		request.setAttribute("myList" , list);
		// => 요청을 다시 Jsp 쪽으로 보내야함
		// => requset의 Attribute에 담았기 때문에 포워드를 써야함
		request.getRequestDispatcher("mvcTestJsp/ex01_MVC02List.jsp").forward(request,response);
		
	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	} // doPost

}
