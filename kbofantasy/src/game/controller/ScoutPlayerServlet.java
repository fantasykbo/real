package game.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.lang.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.dto.GamePlayerDTO;
import game.service.GameService;
import game.service.GameServiceImpl;

/**
 * Servlet implementation class ScoutPlayerServlet
 */
@WebServlet(name = "scoutplayer", urlPatterns = { "/scoutplayer.do" })
public class ScoutPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;          
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
						  throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");						
		String position_dt = request.getParameter("position_dt");
		String mypinfolist = request.getParameter("mypinfolist");
		/*String login = request.getParameter("login");*/
		int member_cd = 0;
		if(request.getParameter("member_cd")==null){
			member_cd = 100009;
		}else{
			member_cd = Integer.parseInt(request.getParameter("member_cd"));
		}
		
		String pathurl = "/game/game.jsp";
		/*String forwardview = "/game/game.jsp";*/ 
		String forwardview = "/layout/layout.jsp";
		System.out.println("선수영입 서블릿");
		
		
		GameService service = new GameServiceImpl();
		ArrayList<GamePlayerDTO> myscoutplayer = service.ScoutPlayer(position_dt);
		System.out.println(member_cd);
		
		System.out.println("포지션"+position_dt);		
		//request.setAttribute("member_cd", member_cd);
		//request.setAttribute("position_dt",position_dt);
		//request.setAttribute("member_cd", member_cd);
		//request.setAttribute("myscoutplayer", myscoutplayer);
		//request.setAttribute("pathurl", pathurl);
		//request.setAttribute("mypinfolist", mypinfolist);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw =response.getWriter();
		pw.print(myscoutplayer);
		/*for(int i=0;i<myscoutplayer.size();i++){
			pmyscoutplayer=myscoutplayer.get(i);
			pw.print(pmyscoutplayer);
			System.out.println();
	
		}*/
		/*RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);*/
		
	}

}
