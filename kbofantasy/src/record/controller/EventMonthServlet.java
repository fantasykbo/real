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
// 한달치 nsd 불러오기 (DB 생성용)
@WebServlet(name = "eventMonth", urlPatterns = { "/record/eventMonth.do" })
public class EventMonthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");

//		String year = request.getParameter("year");
//		String month = request.getParameter("month");
//		String day = request.getParameter("day");
//		Calendar c = Calendar.getInstance();
//		String year = c.get(Calendar.YEAR) + "";
//		String month = (c.get(Calendar.MONTH) + 1) + "";
//		String day = c.get(Calendar.DATE) + "";
//
//		if(month.length() == 1) { 
//			month = "0" + month;
//		}
//		if(day.length() == 1) { 
//			day = "0" + day;
//		}	

		String year = "2016";
		String month = "09";
		
		//		String pathurl = request.getParameter("pathurl");
		String forwardview = "";
		RecordService service = new RecordServiceImpl();
		
		forwardview = "eventMonthList.jsp";
		String eventMonthData = service.eventMonthData(year, month);

//		req.setAttribute("pathurl", pathurl);
		request.setAttribute("eventMonthData", eventMonthData);
		System.out.println(eventMonthData);

		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}
