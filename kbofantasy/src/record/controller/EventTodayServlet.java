package record.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import record.service.RecordService;
import record.service.RecordServiceImpl;
// nsd 파일 묶어서 리스트화(비사용)
@WebServlet(name = "eventList", urlPatterns = { "/record/eventToday.do" })
public class EventTodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");

//		String year = request.getParameter("year");
//		String month = request.getParameter("month");
//		String day = request.getParameter("day");

		Calendar c = Calendar.getInstance();
		String year = c.get(Calendar.YEAR) + "";
		String month = (c.get(Calendar.MONTH) + 1) + "";
		String day = c.get(Calendar.DATE) + "";

		if(month.length() == 1) { 
			month = "0" + month;
		}
		if(day.length() == 1) { 
			day = "0" + day;
		}	
		
		//		String pathurl = request.getParameter("pathurl");
		String forwardview = "";
		RecordService service = new RecordServiceImpl();
		
		forwardview = "record/json_eventList.jsp";
		String eventListData = service.eventTodayData(year, month, day);

//		req.setAttribute("pathurl", pathurl);
		request.setAttribute("eventListData", eventListData);

		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}
