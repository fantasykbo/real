package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "logout", urlPatterns = { "/logout.do" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	   HttpSession ses=request.getSession(false);
 	   if(ses!=null){
 		   //������ �����ϱ�
 		   ses.invalidate();
 		   System.out.println("�α׾ƿ�");
 	   }
 	   //ù��° �������� �����̷�Ʈ
 	   response.sendRedirect("/kbofantasy/index.jsp");
 	   
	}
}

	
	


