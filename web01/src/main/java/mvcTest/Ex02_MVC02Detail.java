package mvcTest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/detail")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_MVC02Detail() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService ss = new StudentService();
		
		int sno = (Integer)request.getSession().getAttribute("loginID");
		
		request.setAttribute("apple",ss.selectOne(sno));
		request.getRequestDispatcher("mvcTestJsp/ex3_MVC02Detail.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
