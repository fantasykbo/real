package kbo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kbo.service.KBOService;
import kbo.service.KBOServiceImpl;

/**
 * Servlet implementation class EmailCheckServlet
 */
@WebServlet(name = "emailcheck", urlPatterns = { "/emailcheck.do" })
public class EmailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		String email = request.getParameter("email");
		
		KBOService service = new KBOServiceImpl();
		boolean emailchk = service.passcheck(email);
		
		String result="";
		if(emailchk){
			result="중복된 이메일. 다시 입력하세요";
		}else{
			result="사용 가능.";
		}
		
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter pw =response.getWriter();
		pw.print(result);
		
		request.setAttribute("emailcheck", emailchk);
		
	}

}
