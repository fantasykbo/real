package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dto.KBOLoginDTO;
import member.service.KBOService;
import member.service.KBOServiceImpl;

/**
 * Servlet implementation class ChangepassServlet
 */
@WebServlet(name = "changepass", urlPatterns = { "/changepass.do" })
public class ChangepassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		String password=request.getParameter("password");
		
		KBOService service= new KBOServiceImpl();
		boolean passchk = service.emailcheck(password);
		
		System.out.println(password);
		
		String result="";
		if(passchk){
			result="암호를 확인하세요";
		}else{
			result="";
		}
		
		
		
	
	}
	
	

}
