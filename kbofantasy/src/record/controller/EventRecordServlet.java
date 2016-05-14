package record.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import record.dto.RecordDTO;
import record.service.RecordService;
import record.service.RecordServiceImpl;
// 세부경기결과페이지 서블릿
@WebServlet(name = "record", urlPatterns = { "/record/eventRecord.do" })
public class EventRecordServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		
		String eventId = request.getParameter("eventId");
//		String pathurl = req.getParameter("pathurl");
		String forwardview = "";
		RecordService service = new RecordServiceImpl();
		
		forwardview = "eventRecord.jsp";
		String eventData = service.eventRecordData(eventId);
		System.out.println("serrrrvlet : " + eventId + eventData);

//		req.setAttribute("pathurl", pathurl);
		request.setAttribute("eventData", eventData);

		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}