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
 * Servlet implementation class MemberleaveServlet
 */
@WebServlet(name = "memberleave", urlPatterns = { "/memberleave.do" })
public class MemberleaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		System.out.println(email+":"+password);
		
		KBOService service=new KBOServiceImpl();
	    KBOLoginDTO dto= new KBOLoginDTO(email,password);
	    
	    service.memberleave(email,password);
	      
		
	}

}
