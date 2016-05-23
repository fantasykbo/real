package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dto.KBOLoginDTO;
import member.service.KBOService;
import member.service.KBOServiceImpl;

/**
 * Servlet implementation class KBORegisterServlet
 */
@WebServlet(name = "register", urlPatterns = { "/register.do" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		
		
		String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    System.out.println(name+":"+email+":"+password);
	    
	    KBOService service=new KBOServiceImpl();
	    KBOLoginDTO register= new KBOLoginDTO(name,email,password);
	    int result = service.register1(register);
	    //boolean emailchk = service.emailcheck(email);
	    //service.register(register);
	    
	    
	    
	    if (result==1) {
	    	RequestDispatcher rd = request
					.getRequestDispatcher("/register/registerResult.jsp");
			rd.forward(request, response);
	    	
			
		}else{
			RequestDispatcher rd1 = request
					.getRequestDispatcher("/register/registerFail.jsp");
			rd1.forward(request, response);
			//response.sendRedirect("/register/registerFail.jsp");
			if(name==null){
				//response.sendRedirect("/register/registerFail.jsp");
	    		RequestDispatcher rd2 = request
						.getRequestDispatcher("/register/registerFail.jsp");
				rd2.forward(request, response);
	    	}else if(password==null){
	    		RequestDispatcher rd3 = request
						.getRequestDispatcher("/register/registerFail.jsp");
				rd3.forward(request, response);
	    		//response.sendRedirect("/register/registerFail.jsp");
	    	
		}
	      

	}

	}
}
