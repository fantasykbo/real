package record.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import record.dto.EventDTO;
import record.service.RecordService;
import record.service.RecordServiceImpl;
// ÆÀ ¼øÀ§Ç¥ ¼­ºí¸´
@WebServlet(name = "recordTeamTable", urlPatterns = { "/record/teamTable.do" })
public class TeamTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String pathurl = request.getParameter("pathurl");
		
		RecordService service = new RecordServiceImpl();
		String teamTableData = service.teamTableData();
	
		request.setAttribute("pathurl", pathurl);
		request.setAttribute("teamTableData", teamTableData);

		String forwardview = "/layout/layout.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);

	}
}
