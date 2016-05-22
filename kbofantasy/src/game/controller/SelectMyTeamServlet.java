package game.controller;

import game.dto.GamePlayerDTO;
import game.service.GameService;
import game.service.GameServiceImpl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

@WebServlet(name = "myteam", urlPatterns = { "/myteam.do" })
public class SelectMyTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req,
						  HttpServletResponse res)
						  throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int member_cd = Integer.parseInt(req.getParameter("member_cd"));	
		String pathurl = req.getParameter("pathurl");	
		String game_dt = req.getParameter("game_dt");
		
		String forwardview = "/layout/layout.jsp";
		
		GameService service = new GameServiceImpl();
		ArrayList<GamePlayerDTO> mypinfolist = service.SelectMyList(member_cd, game_dt);
		System.out.println(mypinfolist.toString());
		req.setAttribute("member_cd", member_cd);
		req.setAttribute("mypinfolist", mypinfolist);
		req.setAttribute("pathurl", pathurl);
		RequestDispatcher rd = req.getRequestDispatcher(forwardview);
		rd.forward(req, res);
	}

}
