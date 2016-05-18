package mediacenter.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
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

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@WebServlet(name = "search", urlPatterns = { "/search.do" })
public class NewsParseServlet extends HttpServlet{
	
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
			teamname = "kbo";
		}
		naverAPI naver = new naverAPI(teamname);
		String xml = naver.getNews();
		
		try {
        	InputSource is = new InputSource(new StringReader(xml));
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	        XPath xpath = XPathFactory.newInstance().newXPath();	//Xpath 생성
	       
	        // <item> 가져오기 
	        NodeList items = (NodeList)xpath.evaluate("//item", document, XPathConstants.NODESET);
	        //<item>안의 값 뽑아서 페이지에 출력하기
	        for( int i=0; i< items.getLength(); i++ ){
	        	
	        	NodeList cols = items.item(i).getChildNodes();
	        	String[] pubdate = split(cols.item(4).getTextContent());
	        	pw.print("<li class='media'>");
	        	pw.print("<a href='"+ cols.item(1).getTextContent()+"' class='pull-left'><img class='media-object' "
	        			+ "src='http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png' "
	        			+ "height='64' width='64'></a>");
	        	pw.print("<div class='media-body'>");
	        	pw.print("<h4 class='media-heading'>"+cols.item(0).getTextContent()
	        	+ "<small>"+ pubdate[3]+"년"+pubdate[2]+"월"+pubdate[1]+"일</small></h4>");
	        	pw.print("<p>"+ cols.item(3).getTextContent() +"</p>");
	        	pw.print("</div>");
	        	pw.print("</li>");
	        	
	        }
	        
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
  
	}
	private String[] split(String s) {
		String[] str = s.split(" ");
		switch (str[2]) {
		case "May":
			str[2]="5";
			break;

		default:
			break;
		}
		return str;
	}

}
