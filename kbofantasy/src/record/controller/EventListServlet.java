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

import record.dto.RecordDTO;
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
		year = "2016";
		month = "05";
		String pathurl = request.getParameter("pathurl");
		
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
		
		String forwardview = "";
		RecordService service = new RecordServiceImpl();
		
		ArrayList<RecordDTO> eventList = service.eventList(year, month);
		String eventTodayData = service.eventTodayData(tYear, tMonth, tDay);
	
		request.setAttribute("pathurl", pathurl);
		request.setAttribute("eventList", eventList);
		request.setAttribute("year", year);
		request.setAttribute("month", month);

		request.setAttribute("eventTodayData", eventTodayData);

		forwardview = "/layout/recordLayout.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}
