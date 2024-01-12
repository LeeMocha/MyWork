package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/check")
public class Ex03_Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex03_Check() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// => CheckBox 처리
		// -> 하나의 Name 에 복수개의 Value 들이 있음
		// -> 또는 하나도 안들어 있을 수 있음 (예외 처리 꼭 해줘야 함)
		// -> request.getParameterValues("gift") 를 이용해서 배열로 처리
		String[] gift = request.getParameterValues("gift");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if (gift != null && gift.length>0) {
			out.print("<h2>** 당신이 원하는 것 : </h2>");
			for (String s : gift) {
				out.printf("<h3> %s </h3>" , s);
			}
		} else {
			out.print("아무 것도 원하지 않습니다.");
		}

	}

}
