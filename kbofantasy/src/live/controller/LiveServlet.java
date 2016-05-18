package live.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import live.service.LiveService;
import live.service.LiveServiceImpl;

@WebServlet(name = "livetext", urlPatterns = { "/livetext.do" })
public class LiveServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int Inn;
		// 일정결과 페이지에서 문자중계 화면 띄울떄 default Inn값 설정
		if(request.getParameter("Inn")==null){
			Inn=0;
		}else{
			Inn=Integer.parseInt(request.getParameter("Inn"));
		}
		
		String eventId = request.getParameter("eventId");
		String month = request.getParameter("month");
		String forwardview="";
		
		LiveService logic = new LiveServiceImpl();
		String kbo = logic.printlivetext(eventId, month);
//		System.out.println(kbo+"first");
/*		try {
			kboObj = (JSONObject)jsonp.parse(kbo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		request.setAttribute("kbo", kbo);
		request.setAttribute("Inn", Inn);
		request.setAttribute("eventId", eventId);
		request.setAttribute("month", month);
		
		if(request.getParameter("path")==null){
			forwardview ="/liveText/Parse_nsd.jsp";
			
			RequestDispatcher rd = request.getRequestDispatcher(forwardview);
			rd.forward(request, response);
		}else{
			PrintWriter pw = response.getWriter();
			pw.write(kbo);
		}
		
	}
}

