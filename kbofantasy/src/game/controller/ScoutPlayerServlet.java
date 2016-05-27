package game.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.event.ListSelectionEvent;

import com.google.gson.Gson;

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
		Gson gson = new Gson();
		String position_dt = request.getParameter("position_dt");
		GameService service = new GameServiceImpl();
		
		ArrayList<GamePlayerDTO> myscoutplayer = service.ScoutPlayer(position_dt);	
		String json = gson.toJson(myscoutplayer);
		System.out.println(json);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw =response.getWriter();
		pw.print(json);	
	}

}
