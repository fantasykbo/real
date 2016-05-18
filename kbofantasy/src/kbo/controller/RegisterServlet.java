package kbo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kbo.login.dto.KBOLoginDTO;
import kbo.service.KBOService;
import kbo.service.KBOServiceImpl;

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
	    
	    service.register(register);
	      

	}

}
