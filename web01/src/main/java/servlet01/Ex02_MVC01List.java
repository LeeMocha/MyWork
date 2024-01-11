package servlet01;

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

@WebServlet("/list")
public class Ex02_MVC01List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex02_MVC01List() {
		super();
	}

	// ** MVC 패턴1 StudentList 출력하기
	// => 요청 Service 처리
	// => 결과를 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// => 요청 Service 처리
		StudentService ss = new StudentService();
		List<StudentDTO> list = ss.selectList();

		// => 결과 출력 : 출력 내용을 Response 객체의 Body 영역에 Write
		// -> 한글 처리
		// -> 출력 객체 & 생성
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print("<html><body>");
		out.print("<h2 style='color:blue;'>** Servlet_MVC1 StudentList **</h2>");
		
		out.print("<table border = 1>");
		out.print("<tr>");
		out.print("<th>Sno</th>");
		out.print("<th>Name</th>");
		out.print("<th>Age</th>");
		out.print("<th>Jno</th>");
		out.print("<th>Info</th>");
		out.print("<th>Point</th>");
		out.print("</tr>");
		
		if (list != null) {
			for(StudentDTO s : list ) {
				out.print("<tr><td>" + s.getSno() + "</td>");
				out.print("<td>" + s.getName() + "</td>");
				out.print("<td>" + s.getAge() + "</td>");
				out.print("<td>" + s.getJno() + "</td>");
				out.print("<td>" + s.getInfo() + "</td>");
				out.print("<td>" + s.getPoint() + "</td></tr>");
			}
			
//			for (int i = 0; i < list.size(); i++) {
//				out.print("<tr>");
//				out.print("<td>" + list.get(i).getSno() + "</td>");
//				out.print("<td>" + list.get(i).getName() + "</td>");
//				out.print("<td>" + list.get(i).getAge() + "</td>");
//				out.print("<td>" + list.get(i).getJno() + "</td>");
//				out.print("<td>" + list.get(i).getInfo() + "</td>");
//				out.print("<td>" + list.get(i).getPoint() + "</td>");
//				out.print("</tr>");
//			}
			out.print("</table>");
			
		} else {
			out.print("<h2>~~ 출력할 Data 가 없습니다. ~~</h2>");
		}
		out.print("</body></html>");

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	} // doPost

}
