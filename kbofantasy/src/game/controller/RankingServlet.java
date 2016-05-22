package game.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.service.RankingService;
import game.service.RankingServiceImpl;

@WebServlet(name = "ranking", urlPatterns = { "/ranking.do" })
public class RankingServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter pw = response.getWriter();
		ArrayList<String[]> toplist = null;
		String selector= (String)request.getParameter("selector");
		
		RankingService service = new RankingServiceImpl();
		if(selector.equals("g")){
			toplist = service.getUser();
			pw.print("<thead><tr><th>순위</th><th>이름</th><th>포인트</th></thead>");
		}else if(selector.equals("b")){
			toplist = service.get_b_player();
			pw.print("<thead><tr><th>순위</th><th>이름</th><th>TEAM</th><th>POSITION</th><th>전체포인트</th></tr></thead>");
		}else if(selector.equals("p")){
			pw.print("<thead><tr><th>순위</th><th>이름</th><th>TEAM</th><th>POSITION</th><th>전체포인트</th></tr></thead>");
			toplist = service.get_p_player();
		}else{
			toplist = service.getUser();
			pw.print("<thead><tr><th>순위</th><th>이름</th><th>포인트</th></thead>");
		}
		pw.print("<tbody>");
		for(int i=0;i<toplist.size();i++){
			String[] dto= toplist.get(i);
			pw.print("<tr>");
			for(int j=0;j<dto.length;j++){
				pw.print("<td>"+dto[j]+"</td>");
			}
			pw.print("</tr>");
		}
		pw.print("</tbody>");

	}

}
