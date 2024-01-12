package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessioni")
public class Ex03_SessionInfo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public Ex03_SessionInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 1. Session 인스턴스 생성
	    // => Session 객체는 클라이언트가 접속과 동시에 서버에서 자동 생성됨
	    //    이 값을 코드내에서 사용하기위해 전달받음
		HttpSession session = request.getSession();
		
		// 2. Session Info 출력
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2>** Session Info **</h2>");
		out.printf("<h3> Session_ID : %s </h3>", session.getId());
		out.printf("<h3> 현재 시간 : %s </h3>", formatter.format(now));
		// => Session 생성시간
		now.setTime(session.getCreationTime()); 
		out.printf("<h3> Session_CreationTime : %s </h3>", formatter.format(now));
		// => 클라이언트의 마지막 접근 시간
		now.setTime(session.getLastAccessedTime());
		out.printf("<h3> Session_LastAccessedTime : %s </h3>", formatter.format(now));
		
		// 3. Session Time(제한 시간) 설정
		// => 메서드 : session.setMaxInactiveInterval(int interval) , 단위는 초, 1시간(60*60)
		// => 설정파일 ( web.xml )
		//    Tag session-config 의 subTag session-timeout
//		session.setMaxInactiveInterval(10);	// 10초
		
		
		// 4. Session 무효화(종료)
	    // => invalidate : 무효화
	    //    세션객체 자체를 소멸시키는것이 아니라, 세션을 초기화하고 무효화시킴.
	    //    session 이 null 이 아니고 session = ""
	    // => 퀴리스트링으로 테스트 ( ~~/sessioni?jCode=D )
		//    request객체의 jCode 파라미터 값에 D가 들어올 경우 로그아웃을 가정.
		//    쿼리스트링에 파라미터 jCode를 안주게되면 .equals 에서 nullPointException이 뜨게됨.
	    // => 주의: jCode 라는 Parameter 가 없는 경우 null 을 return 
	    //         -> NullPointerException 예방 하도록 작성
		
		if("D".equals(request.getParameter("jCode"))) {
			// request.getParameter("jCode").equals("D") 원래라면 이렇게 되어야 하는게 맞지만
			// 비교 대상이 Null 일 경우 nullPointException 이 일어날 수 있기 때문에 도치 시켜서
			// 미연에 간단하게 방지
			session.invalidate();
			System.out.println("** Session 무효화 성공 **");
			out.print("<h3>** Session 종료 !!! **</h3>");
			return;
		}
		out.print("<h3>** Session Info 정상 종료 **</h3>");
		
		
		
	} // doGet

} // class
