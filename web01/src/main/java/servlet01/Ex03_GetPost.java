package servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** Get
//- request 의 header 영역의 url 뒤에 쿼리스트링으로 전달,
//- 일반적으로 256 byte 이내로 크기제한이 있는 것으로 알려져 있으나,
//	이 용량은 브라우져 또는 장비에 따라 다를수 있음
//- 결론은 이미지 등 무거운 자료의 전송은 대부분 불가능 하므로 이때는 post로 해야함. 

//** Post
//- request 의 body 영역에 담겨져 전달됨
//- 크기제한 없음, 보안성 유리 

@WebServlet("/getpost")

public class Ex03_GetPost extends HttpServlet {
	// HttpServlet : request 를 받아 처리 할 수 있는 클래스를 미리 만들어놓음
	// 클라이언트의 요청에 의해 자동 반응하여 doGet / doPost 메서드가 실행된다.
	
	private static final long serialVersionUID = 1L;
	
	// 생성자
    public Ex03_GetPost() {
        super();
    }
    
    //
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) request 의 parameter 처리
	    // => 한글처리, getParameter 전에 해야함
	    //   - Tomcat(WAS) 은 Get 방식요청에서는 "UTF-8" 을 default 로 적용함 
	    //   ( html 문서에서 "UTF-8" 작성되었고 , Get 방식으로 전송되면 생략가능
	    //     단, post 방식에서는 반드시 처리해야함 )
		// 	 - request 의 getParameter하기 전에 한글처리를 해줘야 함
		request.setCharacterEncoding("UTF-8");

		
		// => 해당하는 Parameter 가 없는 경우
		// 	 - Null 을 return
		//   - Parameter는 존재하지만 값이 없는 경우 : null은 아니지만 "" 빈문자열
		//    	Ex) http://localhost:8080/web01/getpost?id=banana&name=바나나&password=
		String password = request.getParameter("password");
		if(password != null && password.length() > 0) {
			System.out.println("** password => "+ password.toUpperCase());
		} else {
			System.out.println("** password is Null or Enpty");
		}
		
		
		
		String id = request.getParameter("id");
		// => name 이 id 인 input Tag의 Value 값을 return 
		String name = request.getParameter("name");

		
	    // ** 출력문 (response 객체에 html 문서를 담아줌)
	    // => 출력객체 생성 -> html 문서작성
	    // => 한글처리 해야함 (출력객체 생성전에 해야함)
		response.setContentType("text/html; charset=UTF-8");
		// response 이니 출력문에 대한 한글 처리임
	    // => 웹브라우져에게 처리할 데이터의 MIME 타입을 알려줌
	    // => MIME : Multipurpose Internet Mail Extensions
	    // => 데이터 송.수신시 자료의 형식에 대한 정보 
		// => Jsp 의 page 디렉티브의 contentType 속성값과 동일
		
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<h2 style='color : blue;'> ** Get/Post Test **</h2>");
		out.print("<h3> => 전달된 parameter 확인 </h3>");
		out.print("<h3> => ID : " + id +"</h3>");
		out.print("<h3> => Name : " + name + "</h3>");
		out.print("<h3> => password : " + password + "</h3>");
		out.print("</body></html>");
		
	}
	
	
	// 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // class


