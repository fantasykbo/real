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
// 경기일정/결과 서블릿
@WebServlet(name = "recordEventList", urlPatterns = { "/record/eventList.do" })
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String pathurl = request.getParameter("pathurl");
		
		// 오늘 진행되는 경기는 별도의 JSON 데이터를 불러와야 하기 때문에 오늘의 날짜 생성
		Calendar c = Calendar.getInstance();
		String tYear = c.get(Calendar.YEAR) + "";
		String tMonth = (c.get(Calendar.MONTH) + 1) + "";
		String tDay = c.get(Calendar.DATE) + "";

		if(tMonth.length() == 1) { 
			tMonth = "0" + tMonth;
		}
		if(tDay.length() == 1) { 
			tDay = "0" + tDay;
		}
		
		RecordService service = new RecordServiceImpl();
		
		// DB에서 받아오는 일정
		ArrayList<EventDTO> eventList = service.eventList(year, month);
		// 실시간으로 웹에서 파싱해서 받아오는 오늘 일정 
		String eventTodayData = service.eventTodayData(tYear, tMonth, tDay);
	
		request.setAttribute("pathurl", pathurl);
		request.setAttribute("eventList", eventList);
		request.setAttribute("year", year);
		request.setAttribute("month", month);

		request.setAttribute("eventTodayData", eventTodayData);

		String forwardview = "/layout/layout.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}
