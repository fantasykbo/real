package game.controller;

import game.dto.GamePlayerDTO;
import game.service.GameService;
import game.service.GameServiceImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
		String forwardview = "/layout/layout.jsp";
		String game_dt = "";
		//날짜 세팅		
		if(req.getParameter("game_dt")==null){
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy/MM/dd", Locale.KOREA);
			Date currentDate = new Date ();
			game_dt = formatter.format (currentDate);
		}else{
			game_dt = req.getParameter("game_dt");
		}
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
