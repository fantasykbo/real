package media.logic;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import media.logic.NaverSearchAPI;

public class XMLparser {
	
	public static String getdate(String pubdate){
		/* 년: 0
		 * 월: 1
		 * 일: 2
		 * Tue, 17 May 2016 10:46:00 +0900 
		*/
		String[] s = pubdate.split(" ");
		String year = s[3];
		String month= s[2];
		String date= s[1];
		
		switch (month) {
		case "May":
			month = "5";
			break;

		default:
			break;
		}
		
		String str = year+"년 "+month+"월 "+date+"일";
		return str;
	}
	
	public String getdata(String query){
		
		String xml = "";

		String news="";
		
		String title = "";
		String pubdate = "";
		String description = "";
		String link = "";

		NaverSearchAPI api = new NaverSearchAPI(query);

		try {
			xml = api.getXML();
			InputSource is = new InputSource(new StringReader(xml));
			Document document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);
			XPath xpath = XPathFactory.newInstance().newXPath(); // Xpath 생성

			/* <item> 가져오기 */
			NodeList items = (NodeList) xpath.evaluate("//item", document,
					XPathConstants.NODESET);
		
			// <item>안의 값 뽑아서 페이지에 출력하기
			for (int i = 0; i < items.getLength(); i++) {

				NodeList cols = items.item(i).getChildNodes();
				
				title = cols.item(0).getTextContent();
				link = cols.item(1).getTextContent();
				description = cols.item(3).getTextContent();
				pubdate = cols.item(4).getTextContent();
				
				String date = getdate(pubdate);
				
				String obj = title+";;; "+link+";;; "+date+";;; "+description+ ";;;;;;;;;;\n";
				news = news + obj;

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return news;

	}

}
