package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
       int result=0;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		
		if(password.equals(password1))
		{
			KBOService service= new KBOServiceImpl();
			KBOLoginDTO chpass= new KBOLoginDTO(email,password);
			
			System.out.println(password);
			result=service.changepass(email,password);
			System.out.println("비밀번호 변경 성공");
			
			RequestDispatcher rd1 = request
					.getRequestDispatcher("/mypage/changeResult.jsp");
			rd1.forward(request, response);
			
		
		}else{
			System.out.println("비밀번호 변경 실패");
			RequestDispatcher rd2 = request
					.getRequestDispatcher("/mypage/changeFail.jsp");
			rd2.forward(request, response);
			
		}
		
		
		
		
		
		

	}
		
		
		
		
	
	
	
	

}
