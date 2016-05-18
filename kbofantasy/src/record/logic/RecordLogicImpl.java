package record.logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

import record.dto.PlayerDTO;

public class RecordLogicImpl implements RecordLogic{

	// ���ΰ���� ũ�Ѹ� ����
	@Override
	public String eventRecordData(String eventId) throws IOException {

		String eventRecordData = "";

		String defUrl = "http://sports.news.naver.com/gameCenter/gameRecord.nhn?gameId=" + eventId + "&category=kbo";	// �Ľ��� �ּ�
		Document doc = Jsoup.connect(defUrl)
						.timeout(5000)
						.referrer("http://sports.news.naver.com/gameCenter/gameResult.nhn?&gameId=" + eventId + "&category=kbo")	// ���۷� - ���̹��� �湮��� �뺸
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// ����������Ʈ ����
						.get();
		
		if(doc != null) {
			Element elmt = doc.select("script").get(39);

			Pattern p = Pattern.compile("(?is)_data : (.+?),\n\t_homeTeamCode");	// JSON ������ ���ӱ�� �������� ������ ���Խ����� ����
			Matcher m = p.matcher(elmt.html());	// ������ ������ ������ 40��° script �±װ� �ִ� html �ҽ� �ȿ��� ��Ī�ϵ��� ����
			
			while(m.find()) {
				eventRecordData = m.group(1);	// 
			}
		} else {
			System.out.println("������");
		}
		eventRecordData = eventRecordData.replace("\\r\\n", "");
		System.out.println("logic : " + eventId + eventRecordData);
		return eventRecordData;
	}
	
	// ���ð��������� ũ�Ѹ� ����
	@Override
	public String eventTodayData(String year, String month, String day) throws IOException {
		
		String eventTodayData = "";

		String defUrl = "http://sportsdata.naver.com/ndata//kbo/" + year + "/" + month + "/" + year + month + day + ".nsd";	// �Ľ��� �ּ�

		Document doc;
		doc = Jsoup.connect(defUrl)
							.timeout(5000)
							.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// ����������Ʈ ����
							.get();
		
		if(doc != null) {
			Element elmt = doc.select("script").first();
			eventTodayData = elmt.html();
			// �� �ڷ� JSON�� �ƴ� ��ũ��Ʈ ��� ����
			eventTodayData = eventTodayData.replace("document.domain=\"naver.com\";parent.sportscallback_gameList(document, ", "");
			eventTodayData = eventTodayData.replace(");", "");
		}
		System.out.println("logic : " + year + month + day + eventTodayData);
	    return eventTodayData;
	}

	@Override
	public ArrayList<PlayerDTO> playerRecordData(String eventId, String eventRecordData) throws ParseException {

		ArrayList<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PlayerDTO dto = null;

		// �Ѱܹ��� String JSON���� �Ľ�
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(eventRecordData.toString());
		JSONObject obj = (JSONObject) rawObj;

		// Ÿ�� ���ھ�ڽ� �Ľ�
		JSONObject batter = (JSONObject) obj.get("battersBoxscore");
		// ������ �Ľ�
		JSONArray batterAway = (JSONArray) batter.get("away");

		// �������� �����ϴ� for ����
		for (int k = 0; k < batterAway.size(); k++) {
			dto = new PlayerDTO();
			JSONObject batterAwayPlayer = (JSONObject) batterAway.get(k);
			
			dto.setEventCode(eventId);
			dto.setPlayerCode((String) batterAwayPlayer.get("playerCode"));
//			dto.setHra((float) batterAwayPlayer.get("hra"));
			

			// inn1 ~ inn12 ���� ������ ã�� for ����
			for (int j = 1; j <= 12; j++) {
				String str = (String) batterAwayPlayer.get("inn" + j);
				// �� ���� ��� �н�
				if (str.equals("")) {
				} else {
					// �� Ÿ�ڰ� �� �̴׿� �ι� Ÿ���� ���(/�� ���е� ���) �и�
					if (str.contains("/")) {
						StringTokenizer tok = new StringTokenizer(str, "/");
						while (tok.hasMoreElements()) {
							Categorize(tok.nextToken(), dto);
						}
					} else {
						Categorize(str, dto);
					}
				}
			}
			list.add(dto);
		}

		// ��� ���
		for (int z = 0; z < list.size(); z++) {
			System.out.println(list.get(z).toString());
		}
		return list;
	}
	
	
	// Ÿ�� ��� �����ϴ� �޼�Ʈ
	public static void Categorize(String bat, PlayerDTO dto) {

		int len = bat.length();

		if (bat.contains("��")) {
			dto.setSh(dto.getSh() + 1);
			System.out.println(bat + " : ���Ÿ");
		} else {

			switch (bat.substring((len - 1), len)) {
			case "��":
				dto.setH1(dto.getH1() + 1);
				System.out.println(bat + " : 1��Ÿ");
				break;

			case "2":
				dto.setH2(dto.getH2() + 1);
				System.out.println(bat + " : 2��Ÿ");
				break;

			case "3":
				dto.setH3(dto.getH3() + 1);
				System.out.println(bat + " : 3��Ÿ");
				break;

			case "Ȩ":
				dto.setHr(dto.getHr() + 1);
				System.out.println(bat + " : Ȩ��");
				break;

			case "4":
				dto.setBb(dto.getBb() + 1);
				System.out.println(bat + " : ����4��");
				break;

			case "��":
				dto.setIb(dto.getIb() + 1);
				System.out.println(bat + " : 4�籸");
				break;

			case "��":
			case "��":
				dto.setSo(dto.getSo() + 1);
				System.out.println(bat + " : ����");
				break;

			case "��":
			case "��":
				dto.setDp(dto.getDp() + 1);
				System.out.println(bat + " : ����Ÿ");
				break;

			case "��":
			case "��":
			case "��":
			case "��":
			case "��":
				dto.setOt(dto.getOt() + 1);
				System.out.println(bat + " : �ƿ�");
				break;

			default:
				break;
			}
		}

	}

	
	// nsd ��ü ũ�Ѹ� ����(������� ����)
	@Override
	public String eventMonthData(String year, String month) {
		
		String day = "";
		String data = "[";
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), (Integer.parseInt(month) - 1), 1);
		int lastDay = cal.getActualMaximum(Calendar.DATE);

		for (int i = 1; i <= lastDay; i++) {
		
			if(i < 10) {
				day = "0" + i;
			} else {
				day = "" + i;
			}
			String defUrl = "http://sportsdata.naver.com/ndata//kbo/" + year + "/" + month + "/" + year + month + day + ".nsd";	// �Ľ��� �ּ�
			Document doc;
			try {
				doc = Jsoup.connect(defUrl)
								.timeout(5000)
								.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// ����������Ʈ ����
								.get();
			} catch (IOException e) {
				doc = null;
			}
			String str = "";
			
			if(doc!=null) {
				Element elmt = doc.select("script").first();
				str = elmt.html();
				str = str.replace("document.domain=\"naver.com\";parent.sportscallback_gameList(document, ", "");
				str = str.replace(");", "");
				
			} else {
				str = "\"none\"";
			}

//			str = "\"" + year + month + "\":[" + str + ",";
			str = str + ",";
			data += str;
		}
		data = data.substring(0, data.length() - 1);
	    data += "]";

	    return data;
	}
}
