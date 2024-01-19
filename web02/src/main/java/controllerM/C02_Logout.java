package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class C02_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C02_Logout() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String uri = "home.jsp";
		
		System.out.println("** 로그아웃 시도 **");
		
		request.getSession().invalidate();
		
		System.out.println("** 로그아웃 성공 **");

		response.sendRedirect(uri);
    	
    }

    
}
