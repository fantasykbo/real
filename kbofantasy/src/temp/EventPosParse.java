package temp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import record.dto.BatterDTO;

public class EventPosParse {

	public static void main(String[] args) {
		EventPosParse aaa = new EventPosParse();
		try {
			aaa.playerRecordData("eventId");
			
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<BatterDTO> playerRecordData(String eventId) throws IOException, ParseException {

		ArrayList<BatterDTO> list = new ArrayList<BatterDTO>();
	
		System.out.println(dump().size());
		for(int i = 0; i < dump().size(); i++) {
			
		eventId = dump().get(i);
		
		String rawData = "";
		String defUrl = "http://sports.news.naver.com/gameCenter/gameRecord.nhn?gameId=" + eventId + "&category=kbo";	// 파싱할 주소
		Document doc = Jsoup.connect(defUrl)
						.timeout(5000)
						.referrer("http://sports.news.naver.com/gameCenter/gameResult.nhn?&gameId=" + eventId + "&category=kbo")	// 리퍼러 - 네이버에 방문경로 통보
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// 유저에이전트 정의
						.get();
		
		if(doc != null) {
			Element elmt = doc.select("script").get(39);

			Pattern p = Pattern.compile("(?is)_data : (.+?),\n\t_homeTeamCode");	// JSON 형태의 게임기록 데이터의 패턴을 정규식으로 정의
			Matcher m = p.matcher(elmt.html());	// 위에서 정의한 패턴을 40번째 script 태그가 있는 html 소스 안에서 매칭하도록 정의
			
			while(m.find()) {
				rawData = m.group(1);	// 
			}
		} else {
			System.out.println("로직널");
		}
		rawData = rawData.replace("\\r\\n", "");
		
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(rawData.toString());
		JSONObject obj = (JSONObject) rawObj;
		JSONObject batter = (JSONObject) obj.get("battersBoxscore");
		String temp = "";
		JSONArray batteraway = (JSONArray) batter.get("away");
		for (int k = 0; k < batteraway.size(); k++) {
			JSONObject batterawayPlayer = (JSONObject) batteraway.get(k);
				String str = (String) batterawayPlayer.get("pos");
				if(str.equals("")) {
				} else {
					temp += eventId + "," + batterawayPlayer.get("playerCode") + "," + str + "\n";
				}
		}
		JSONArray batterhome = (JSONArray) batter.get("home");
		for (int k = 0; k < batterhome.size(); k++) {
			JSONObject batterhomePlayer = (JSONObject) batterhome.get(k);
				String str = (String) batterhomePlayer.get("pos");
				if(str.equals("")) {
				} else {
					temp += eventId + "," + batterhomePlayer.get("playerCode") + "," + str + "\n";
				}
		}

		
		File file = new File("pos.txt");
		FileWriter fw = new FileWriter(file, true);
		fw.write(temp);
		fw.flush();
		fw.close();
		System.out.println(temp);
	}
//		System.out.println(batterAway.size());
		
		return list;
	}

	public ArrayList<String> dump() {
		ArrayList<String> dump = new ArrayList<String>();
		dump.add("20160401HHLG02016");
		dump.add("20160401HTNC02016");
		dump.add("20160401KTSK02016");
		dump.add("20160401LTWO02016");
		dump.add("20160401OBSS02016");
		dump.add("20160402HHLG02016");
		dump.add("20160402HTNC02016");
		dump.add("20160402KTSK02016");
		dump.add("20160402LTWO02016");
		dump.add("20160402OBSS02016");


		dump.add("20160403KTSK02016");
		dump.add("20160403LTWO02016");

		dump.add("20160405LGHT02016");
		dump.add("20160405NCOB02016");
		dump.add("20160405SKLT02016");
		dump.add("20160405SSKT02016");
		dump.add("20160405WOHH02016");

		dump.add("20160406NCOB02016");
		dump.add("20160406SKLT02016");
		dump.add("20160406SSKT02016");
		dump.add("20160406WOHH02016");
		dump.add("20160407LGHT02016");
		dump.add("20160407NCOB02016");
		dump.add("20160407SKLT02016");
		dump.add("20160407SSKT02016");
		dump.add("20160407WOHH02016");
		dump.add("20160408HHNC02016");
		dump.add("20160408HTKT02016");
		dump.add("20160408LGSK02016");
		dump.add("20160408SSLT02016");
		dump.add("20160408WOOB02016");
		dump.add("20160409HHNC02016");
		dump.add("20160409HTKT02016");
		dump.add("20160409LGSK02016");
		dump.add("20160409SSLT02016");
		dump.add("20160409WOOB02016");
		dump.add("20160410HHNC02016");
		dump.add("20160410HTKT02016");
		dump.add("20160410LGSK02016");
		dump.add("20160410SSLT02016");
		dump.add("20160410WOOB02016");
		dump.add("20160412HTSK02016");
		dump.add("20160412KTWO02016");
		dump.add("20160412LTLG02016");
		dump.add("20160412NCSS02016");
		dump.add("20160412OBHH02016");
		dump.add("20160413HTSK02016");
		dump.add("20160413KTWO02016");
		dump.add("20160413LTLG02016");
		dump.add("20160413NCSS02016");
		dump.add("20160413OBHH02016");
		dump.add("20160414HTSK02016");
		dump.add("20160414KTWO02016");
		dump.add("20160414LTLG02016");
		dump.add("20160414NCSS02016");
		dump.add("20160414OBHH02016");
		dump.add("20160415LGHH02016");
		dump.add("20160415LTNC02016");
		dump.add("20160415SKKT02016");
		dump.add("20160415SSOB02016");
		dump.add("20160415WOHT02016");





		dump.add("20160417LGHH02016");
		dump.add("20160417LTNC02016");
		dump.add("20160417SKKT02016");
		dump.add("20160417SSOB02016");
		dump.add("20160417WOHT02016");
		dump.add("20160419HHLT02016");
		dump.add("20160419NCLG02016");
		dump.add("20160419OBKT02016");
		dump.add("20160419SSHT02016");
		dump.add("20160419WOSK02016");
		dump.add("20160420HHLT02016");
		dump.add("20160420NCLG02016");
		dump.add("20160420OBKT02016");
		dump.add("20160420SSHT02016");
		dump.add("20160420WOSK02016");
		dump.add("20160421HHLT02016");
		dump.add("20160421NCLG02016");
		dump.add("20160421OBKT02016");
		dump.add("20160421SSHT02016");
		dump.add("20160421WOSK02016");
		dump.add("20160422HHOB02016");
		dump.add("20160422HTLT02016");
		dump.add("20160422KTSS02016");
		dump.add("20160422LGWO02016");
		dump.add("20160422NCSK02016");
		dump.add("20160423HHOB02016");
		dump.add("20160423HTLT02016");
		dump.add("20160423KTSS02016");
		dump.add("20160423LGWO02016");
		dump.add("20160423NCSK02016");
		dump.add("20160424HHOB02016");
		dump.add("20160424HTLT02016");
		dump.add("20160424KTSS02016");
		dump.add("20160424LGWO02016");
		dump.add("20160424NCSK02016");
		dump.add("20160426HTHH02016");
		dump.add("20160426LGSS02016");
		dump.add("20160426LTKT02016");
		dump.add("20160426SKOB02016");
		dump.add("20160426WONC02016");


		dump.add("20160427LTKT02016");
		dump.add("20160427SKOB02016");

		dump.add("20160428HTHH02016");
		dump.add("20160428LGSS02016");
		dump.add("20160428LTKT02016");
		dump.add("20160428SKOB02016");
		dump.add("20160428WONC02016");
		dump.add("20160429KTLG02016");
		dump.add("20160429NCLT02016");
		dump.add("20160429OBHT02016");
		dump.add("20160429SKWO02016");
		dump.add("20160429SSHH02016");
		dump.add("20160430KTLG02016");
		dump.add("20160430NCLT02016");
		dump.add("20160430OBHT02016");
		dump.add("20160430SKWO02016");
		dump.add("20160430SSHH02016");
		dump.add("20160501KTLG02016");
		dump.add("20160501NCLT02016");
		dump.add("20160501OBHT02016");
		dump.add("20160501SKWO02016");
		dump.add("20160501SSHH02016");
		dump.add("20160503HHSK02016");
		dump.add("20160503LTHT02016");


		dump.add("20160503WOSS02016");
		dump.add("20160504HHSK02016");
		dump.add("20160504LTHT02016");
		dump.add("20160504NCKT02016");
		dump.add("20160504OBLG02016");
		dump.add("20160504WOSS02016");
		dump.add("20160505HHSK02016");
		dump.add("20160505LTHT02016");
		dump.add("20160505NCKT02016");
		dump.add("20160505OBLG02016");
		dump.add("20160505WOSS02016");
		dump.add("20160506HHKT02016");
		dump.add("20160506HTWO02016");
		dump.add("20160506LGNC02016");
		dump.add("20160506LTOB02016");
		dump.add("20160506SKSS02016");
		dump.add("20160507HHKT02016");
		dump.add("20160507HTWO02016");
		dump.add("20160507LGNC02016");
		dump.add("20160507LTOB02016");
		dump.add("20160507SKSS02016");
		dump.add("20160508HHKT02016");
		dump.add("20160508HTWO02016");
		dump.add("20160508LGNC02016");
		dump.add("20160508LTOB02016");
		dump.add("20160508SKSS02016");


		dump.add("20160510OBSK02016");
		dump.add("20160510SSLG02016");

		dump.add("20160511KTHT02016");
		dump.add("20160511NCHH02016");
		dump.add("20160511OBSK02016");
		dump.add("20160511SSLG02016");
		dump.add("20160511WOLT02016");
		dump.add("20160512KTHT02016");
		dump.add("20160512NCHH02016");
		dump.add("20160512OBSK02016");
		dump.add("20160512SSLG02016");
		dump.add("20160512WOLT02016");
		dump.add("20160513HHHT02016");
		dump.add("20160513KTNC02016");
		dump.add("20160513LTSS02016");
		dump.add("20160513OBWO02016");
		dump.add("20160513SKLG02016");
		dump.add("20160514HHHT02016");
		dump.add("20160514KTNC02016");
		dump.add("20160514LTSS02016");
		dump.add("20160514OBWO02016");
		dump.add("20160514SKLG02016");
		dump.add("20160515HHHT02016");
		dump.add("20160515KTNC02016");
		dump.add("20160515LTSS02016");
		dump.add("20160515OBWO02016");

		dump.add("20160517HHSS02016");
		dump.add("20160517HTOB02016");
		dump.add("20160517LGKT02016");
		dump.add("20160517LTSK02016");
		dump.add("20160517NCWO02016");		
		return dump;
		
	}
	
}
