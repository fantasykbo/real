package media.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import media.logic.XMLparser;

@WebServlet(name = "newssearch", urlPatterns = { "/newssearch.do" })
public class NewsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter pw = response.getWriter();
		
		String teamname = (String)request.getParameter("teamname");
		if(teamname.equals("전체보기")){
			teamname="kbo리그";
		}
		
		teamname = teamname.replaceAll("\\p{Space}", "");

		XMLparser parser = new XMLparser();
		String news = parser.getdata(teamname);
		
		pw.print(news);
		
	}

}
