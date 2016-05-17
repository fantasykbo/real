package live.logic;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LiveLogicImpl implements LiveLogic {

	@Override
	public String printlivetext(String eventId) {
		String Url = "http://sportsdata.naver.com/ndata//kbo/2016/05/"+eventId+".nsd";	// ÆÄ½ÌÇÒ ÁÖ¼Ò
		Document doc = null;
		try {
			doc = Jsoup.connect(Url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Elements script = doc.getElementsByTag("script");
/*		JSONObject kboObj = null;
		JSONParser jsonp = new JSONParser();*/
		String kbo = script.html();
		kbo = kbo.replace("document.domain=\"naver.com\";parent.sportscallback_relay(document, ", "");
		kbo = kbo.replace(");", "");
		return kbo;
	}
	
}
