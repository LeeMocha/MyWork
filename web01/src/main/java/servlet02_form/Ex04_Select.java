package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/select")
public class Ex04_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex04_Select() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		request.setCharacterEncoding("UTF-8");

		// => CheckBox 처리
		// -> 하나의 Name 에 복수개의 Value 들이 있음
		// -> request.getParameterValues("gift") 를 이용해서 배열로 처리
		request.getParameterValues("job");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String[] interest = request.getParameterValues("interest");
		
		if(request.getParameter("job").length()> 0 && request.getParameter("interest")!=null) {
			out.print("<h2> ** 당신의 직업 => "+ request.getParameter("job")+"</h2>");
				out.print("<h2> ** 당신의 관심분야 => </h2>");
				for(String s : interest) {
					out.print("<h2>"+ s +"</h2>");
				}
		} else {
			out.print("<h1>직업, 관심분야를 선택해 주십시오.</h1>");			
		}
		
	}
}
