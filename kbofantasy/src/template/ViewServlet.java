package template;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "view", urlPatterns = { "/view.do" })
public class ViewServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		String pathurl=request.getParameter("pathurl");
		
		request.setAttribute("pathurl", pathurl);
		RequestDispatcher rd = request.getRequestDispatcher("/layout/layout.jsp");
		rd.forward(request, response);
	}
	

}
