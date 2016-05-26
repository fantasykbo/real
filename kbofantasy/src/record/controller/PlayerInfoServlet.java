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
// �������� ����
@WebServlet(name = "playerInfo", urlPatterns = { "/record/playerInfo.do" })
public class PlayerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String playerCode = request.getParameter("playerCode");
		String pathurl = request.getParameter("pathurl");
		
		RecordService service = new RecordServiceImpl();

		PlayerDTO playerInfoData = service.playerInfo(playerCode);
		// �ҷ��� ���������� ������ �������� response
		if(playerInfoData.getPositionDetail().equals("P")) {
			PitcherDTO pitcherStatData = service.pitcherStat(playerCode);
			ArrayList<PitcherDTO> pitcherLastStatList = service.pitcherLastStat(playerCode);
			request.setAttribute("pitcherStatData", pitcherStatData);
			request.setAttribute("pitcherLastStatList", pitcherLastStatList);
		// �ƴϸ� Ÿ������ response
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
