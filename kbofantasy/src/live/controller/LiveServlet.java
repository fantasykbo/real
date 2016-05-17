package live.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import live.logic.LiveLogic;
import live.service.LiveService;
import live.service.LiveServiceImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@WebServlet(name = "livescore", urlPatterns = { "/livescore.do" })
public class LiveServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//
		String Inn = request.getParameter("Inn");
		String forwardview="";
		LiveService logic = new LiveServiceImpl();
		String kbo = logic.printlivetext(nsd);
//		System.out.println(kbo+"first");
/*		try {
			kboObj = (JSONObject)jsonp.parse(kbo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		request.setAttribute("kbo", kbo);
//		System.out.println(kboObj.toJSONString()+"second");
		forwardview ="/liveScore/Parse_nsd.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}

