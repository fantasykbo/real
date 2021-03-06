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
 * Servlet implementation class KBOLoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");

		String result = "";
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println(email + ":" + password);

		KBOService service = new KBOServiceImpl();
		KBOLoginDTO log = service.login(email, password);
		boolean loginchk = service.logincheck(email, password);
		
		System.out.println("로그인 객체" + log);

		if (loginchk) {
			HttpSession ses = request.getSession();
			ses.setAttribute("login", log);

			System.out.println("로그인");
			RequestDispatcher rd = request
					.getRequestDispatcher("/login/loginResult.jsp");
			rd.forward(request, response);
		}else{
			System.out.println("실패");
			RequestDispatcher rd = request
					.getRequestDispatcher("/login/loginFail.jsp");
			rd.forward(request, response);
		}

		/*
		 * RequestDispatcher rd = request
		 * .getRequestDispatcher("/login/loginResult.jsp"); rd.forward(request,
		 * response);
		 */

	}

}
