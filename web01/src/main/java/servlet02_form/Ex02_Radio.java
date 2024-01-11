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
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print("<h2>** 성별 : " + request.getParameter("gender") + "</h2>");
		out.print("<h2>** 수신 동의 : " + request.getParameter("mailcheck") + "</h2>");
		out.print("<h2>** 가입인사 : " + request.getParameter("content") + "</h2>");

	} // do goet
} // class
