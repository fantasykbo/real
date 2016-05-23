package record.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import record.dto.BatterDTO;
import record.dto.PitcherDTO;
import record.dto.PlayerDTO;
import record.service.RecordService;
import record.service.RecordServiceImpl;
// 경기일정/결과 서블릿
@WebServlet(name = "playerInfo", urlPatterns = { "/record/playerInfo.do" })
public class PlayerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String playerCode = request.getParameter("playerCode");
		String pathurl = request.getParameter("pathurl");
		
		RecordService service = new RecordServiceImpl();
		
		PlayerDTO playerInfoData = service.playerInfo(playerCode);
		
		// 선수 정보가 P, 투수일 경우와 아닐 경우(타자일 경우)로 분기하여 다른 리퀘스트를 뿌려줌
		if(playerInfoData.getPositionDetail().equals("P")) {
			PitcherDTO pitcherStatData = service.pitcherStat(playerCode);
			ArrayList<PitcherDTO> pitcherLastStatList = service.pitcherLastStat(playerCode);
			request.setAttribute("pitcherStatData", pitcherStatData);
			request.setAttribute("pitcherLastStatList", pitcherLastStatList);

		} else {
			BatterDTO batterStatData = service.batterStat(playerCode);
			ArrayList<BatterDTO> batterLastStatList = service.batterLastStat(playerCode);
			request.setAttribute("batterStatData", batterStatData);
			request.setAttribute("batterLastStatList", batterLastStatList);
		}
		
		request.setAttribute("playerInfoData", playerInfoData);
		request.setAttribute("pathurl", pathurl);

		String forwardview = "/layout/layout.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}
