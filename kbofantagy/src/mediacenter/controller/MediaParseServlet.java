package mediacenter.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import openapi.naverAPI;
import openapi.youtubeAPI;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import media.dto.MediaDTO;

@WebServlet(name = "mediasearch", urlPatterns = { "/mediasearch.do" })
public class MediaParseServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter pw = response.getWriter();
		
		String teamname = request.getParameter("teamname");
		if(teamname.equals("전체보기")){
			teamname="kbo리그";
		}
		youtubeAPI youtube = new youtubeAPI();
		ArrayList<MediaDTO> media = youtube.parser(teamname);
		
		for(int i=0;i<media.size();i++){
			
			MediaDTO m = media.get(i);
			pw.print("<div class='col-md-4'>");
			pw.print("<div class='thumbnail'>");
			pw.print("<a href='https://www.youtube.com/watch?v="+m.getVideoId().toString()+"'><img src='"+m.getImage().toString()+"' class='img-responsive'>");
	        pw.print("<div class='caption'>");
	        pw.print("<h3>"+m.getTitle().toString()+"</h3>");  
	        pw.print("<p class='text-muted'>"+m.getDescription().toString()+"</p>");    
	        pw.print("</div></div></div>"); 

		}
		if(media.size()<6){
			for(int i=0;i<6-media.size();i++){
				pw.print("<div class='col-md-4'><div class='thumbnail'>"
						+ "<img src='http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png' class='img-responsive'><div class='caption'>"
						+ "<h3>제목</h3><p class='text-muted'>날짜</p></div></div></div>");
				
			}
		}
        pw.print("<div class='row'><div class='col-md-12'><ul class='pager'>"
        		+ "<li><a href='#'>← Prev</a></li><li><a href='#'>Next →</a></li>"
        		+ "</ul></div></div>");
		
          
	}

}
