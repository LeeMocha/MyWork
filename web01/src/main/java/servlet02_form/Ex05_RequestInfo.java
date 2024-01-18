package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reqinfo")
public class Ex05_RequestInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex05_RequestInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ** 화면 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print("<h2>** Request Information **</h2>");
		out.print("<h3>=> 주요 메서드</h3>");
		out.print("<h3> 1) Request Header Names & Values</h3>");
		out.print("<h3> 2) ContextPath: 웹애플리케이션의 최상위 경로 </h3>");
		out.print("<h3> 3) RealPath: 웹애플리케이션의 실행위치</h3>");
		out.print("<h3> 4) 기타등등 </h3>");
		out.print("<h3> => Console 창에서 확인하세요 ~~~</h3>");
		out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시 입력하기</a></h2><br>");

		// ** Console 출력
		System.out.println("** 1) Request Header Names & Values **");
		Enumeration<String> hNames = request.getHeaderNames();
		// Enumeration(열거) : 반복문을 처리 할 수 있는 자료의 형태를 처리해주는 클래스 Ex) List, 배열, resultSet 등등
		// Iterator(반복자) : Enumeration 과 비슷한 기능을 하며 더 최근에 나옴
		// next() 등의 메서드를 실행 할 수 있게 됨.

		while (hNames.hasMoreElements()) {
			String hName = hNames.nextElement();
			System.out.printf("%s  =  %s \n", hName, request.getHeader(hName));
		}

		System.out.println("** 2) ContextPath => " + request.getContextPath());
		// ContextPath : Application 을 찾아가는 최상위 경로. project 명이라 생각하면 됨.
		System.out.println("** 3) RealPath => " + request.getRealPath("/"));
		// deprecated : 지원하지 않는다. 
		// RealPath :build 해 놨던 소스코드들을 컴파일하게 하는 실행하는 실행코드들이 위치한 부분
		System.out.println("** 4) 기타등등 **");
		System.out.println("=> RemoteAddress: " + request.getRemoteAddr());
		// RemoteAddress
		System.out.println("=> Method: " + request.getMethod());
		System.out.println("=> RequestURL: " + request.getRequestURL());
		// Request URL : 전체 Location 경로 , 리소스에 대한 경로
		System.out.println("=> RequestURI: " + request.getRequestURI());
		// Request URI : 어플리케이션이 실행되는 경로, URL 보다 더 넓은 경로
		System.out.println("=> ServerName: " + request.getServerName());
		System.out.println("=> ServerPort: " + request.getServerPort());
		System.out.println("=> ServletPath: " + request.getServletPath());
	}
}
