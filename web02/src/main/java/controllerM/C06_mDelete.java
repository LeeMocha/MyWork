package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public C06_mDelete() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	MemberService ms = new MemberService();
    	if(ms.delete((String)request.getSession().getAttribute("loginId"))>0) {
    		request.getSession().invalidate();
    		response.sendRedirect("home.jsp");
    	} else {
    		request.setAttribute("message", request.getSession().getAttribute("loginId")+"님 탈퇴 실패!! 관리자에게 문의 하세요!");
    	}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
