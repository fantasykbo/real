package record.logic;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import record.dto.EventDTO;
import record.dto.PitcherDTO;

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
<<<<<<< HEAD
				eventRecordData = m.group(1);	// ��Ī�� ������ �Ѱ��� String�� ����
=======
				eventRecordData = m.group(1);
>>>>>>> branch 'master' of https://github.com/fantasykbo/real.git
			}
<<<<<<< HEAD
		} else {
=======
>>>>>>> branch 'master' of https://github.com/fantasykbo/real.git
		}
		// �Էµ� ������ �� ������ �Ǵ� ���� ã�Ƽ� ����
		eventRecordData = eventRecordData.replace("\\r\\n", "");
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
	    return eventTodayData;
	}
	
// ���� ���������� �Ľ� ����
	@Override
	public ArrayList<EventDTO> dailyRecordData(String eventTodayData)
			throws ParseException {

		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		EventDTO dto = null;
		
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(eventTodayData.toString());
		JSONObject obj = (JSONObject) rawObj;
		JSONArray games = (JSONArray) obj.get("games");
		
		int size = games.size();
		for(int i = 0; i < size; i++) {

			dto = new EventDTO();
			
			JSONObject game = (JSONObject) games.get(i);
			JSONObject score = (JSONObject) game.get("score");
			
			dto.setEventCode((String) game.get("gameId"));
			dto.setaScore((Long) score.get("aScore") + "");
			dto.sethScore((Long) score.get("hScore") + "");
			dto.setEventStatus((String) game.get("statusCode"));
			dto.setCancelFlag((String) game.get("cancelFlag"));
			
			list.add(dto);
		}
		return list;
	}
	
	// �� ���� ���̺� JSON ��ȯ ����
	@SuppressWarnings("unchecked")
	@Override
	public String teamTableData(ArrayList<EventDTO> list)
			throws ParseException {
		
		String teamTableData = new String();

		JSONObject teamTable = new JSONObject();
		JSONArray teamList = new JSONArray();
		JSONObject teamData = null;
		
		// �� �ڵ带 ��� ���� ArrayList ����
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("LG");
		temp.add("SK");
		temp.add("KT");
		temp.add("WO");
		temp.add("OB");
		temp.add("HH");
		temp.add("HT");
		temp.add("NC");
		temp.add("SS");
		temp.add("LT");
		// �� �� ������� �����ؼ� ��� ���� �ݺ��� 
		for(int i = 0; i < temp.size(); i++) {
			teamData = new JSONObject();
			teamData.put("teamCode", temp.get(i));
			teamData.put("totalWin", 0);
			teamData.put("totalDraw", 0);
			teamData.put("totalLose", 0);
			teamData.put("awayWin", 0);
			teamData.put("awayDraw", 0);
			teamData.put("awayLose", 0);
			teamData.put("homeWin", 0);
			teamData.put("homeDraw", 0);
			teamData.put("homeLose", 0);
			teamData.put("wra", 0);
			teamData.put("gap", 0);
			teamData.put("teamName", "");
			teamList.add(teamData);
		}
		// ������� ����� ��� ����Ʈ�� for ����
		int size = list.size();
		for(int i = 0; i < size; i++) {
			EventDTO dto = list.get(i);
			// �� DTO�� �ִ� ������ ���� �̿��Ͽ� �¹��� ����
			int wdl = Integer.parseInt(dto.getaScore()) - Integer.parseInt(dto.gethScore());
			
			int listsize = teamList.size();
			for(int j = 0; j < listsize; j++) {
				
				teamData = (JSONObject) teamList.get(j);
				
				// �� ���ڵ忡�� �������� �� �¹��� �����Ͽ� ����
				if(dto.getaTeamCode().equals(teamData.get("teamCode"))) {
					teamData.put("teamName", dto.getaTeamSName());
					if(wdl > 0) {
						teamData.put("awayWin", (int) teamData.get("awayWin") + 1);
					} else if(wdl == 0) {
						teamData.put("awayDraw", (int) teamData.get("awayDraw") + 1);
					} else if(wdl < 0) {
						teamData.put("awayLose", (int) teamData.get("awayLose") + 1);
					}
				// Ȩ���� �� �¹��� �����Ͽ� ���� 
				} else if(dto.gethTeamCode().equals(teamData.get("teamCode"))) {
					teamData.put("teamName", dto.gethTeamSName());
					if(wdl > 0) {
						teamData.put("homeLose", (int) teamData.get("homeLose") + 1);
					} else if(wdl == 0) {
						teamData.put("homeDraw", (int) teamData.get("homeDraw") + 1);
					} else if(wdl < 0) {
						teamData.put("homeWin", (int) teamData.get("homeWin") + 1);
					}
				}
				// Ȩ�¹��� + �����¹��� �Ͽ� �� �¹��з� put
				teamData.put("totalWin", (int) teamData.get("homeWin") + (int) teamData.get("awayWin"));
				teamData.put("totalDraw", (int) teamData.get("homeDraw") + (int) teamData.get("awayDraw"));
				teamData.put("totalLose", (int) teamData.get("homeLose") + (int) teamData.get("awayLose"));

				// ������ �·��� �����ϱ� ������, �·��� ����ؼ� ����־�� �Ѵ�
				// DivideByZeroException�� ���ϱ� ���� ����� ������ 0���� ǥ��
				if(teamData.get("totalWin").equals(0) & teamData.get("totalLose").equals(0)) {
					teamData.put("wra", "0.000");
				// �·��� �¸�Ƚ�� / (��ü���� - ���º�)�� ���
				} else {
					int temp1 = (int) teamData.get("totalWin") + (int) teamData.get("totalLose");
					double wra = (int) teamData.get("totalWin") / (double) temp1;
					// �Ҽ��� �Ʒ� ���ڸ����� �����Ͽ� ǥ���ϱ� ���� ������ ����
					DecimalFormat df = new DecimalFormat("0.000");
					teamData.put("wra", df.format(wra));
				}
				teamList.set(j, teamData);
			}
		}
		teamTable.put("teamList", teamList);
		teamTableData = teamTable.toJSONString();

		return teamTableData;
	}
	
	// ���� ��� �Ľ� ����
	@Override
	public ArrayList<PitcherDTO> pitcherRecordData(String eventId, String eventRecordData) throws ParseException {

		ArrayList<PitcherDTO> list = new ArrayList<PitcherDTO>();
		PitcherDTO dto = null;

		// �Ѱܹ��� String JSON���� �Ľ�
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(eventRecordData.toString());
		JSONObject obj = (JSONObject) rawObj;

		// ���� ���ھ�ڽ� �Ľ�
		JSONObject pitcher = (JSONObject) obj.get("pitchersBoxscore");
		
		// �޼ҵ� ȣ��
		PitcherData(eventId, "away", list, pitcher, dto);
		PitcherData(eventId, "home", list, pitcher, dto);
		
		return list;
	}
	
	// ������� Ȩ/���� ���� �Ľ��ϴ� �޼ҵ�
	public static void PitcherData(String eventId, String awayHome, ArrayList<PitcherDTO> list, JSONObject pitcher, PitcherDTO dto) {
		// Ȩ/���� �Ľ�
		JSONArray pitcherRecord = (JSONArray) pitcher.get(awayHome);

		// ������ ������������ �����ϴ� for ����
		for (int k = 0; k < pitcherRecord.size(); k++) {
			dto = new PitcherDTO();
			int point = 0;
			
			JSONObject pitcherRecordPlayer = (JSONObject) pitcherRecord.get(k);
			
			dto.setEventCode(eventId);	// ����ڵ�
			dto.setPlayerCode((String) pitcherRecordPlayer.get("pCode"));		// �����ڵ�
			
			// ����Ȧ�� ���� �� ����Ʈ
			String wlhs = "";
			wlhs = (String) pitcherRecordPlayer.get("wls");
			switch(wlhs) {
				case "��" :
					if(k == 0) {
						point += 125;
					} else {
						point += 100;
					}
					dto.setWlhs("W");
					break;
				case "��" :
					point += -25;
					dto.setWlhs("L");
					break;
				case "Ȧ" :
					point += 40;
					dto.setWlhs("H");
					break;
				case "��" :
					point += 50;
					dto.setWlhs("S");
					break;
				case "" :
					break;
			}
			
			float era = Float.valueOf((String) pitcherRecordPlayer.get("era"));	// �����å��
			dto.setEra(era);
			
			// �̴� ���
			int inn = 0;
			String innStr = (String) pitcherRecordPlayer.get("inn");
			if(innStr.contains(" ")) {		// 1/3, 2/3�� ��� �и��ؼ� ����
				String[] innSplt = innStr.split(" ", 2);
				inn += Integer.parseInt(innSplt[0]) * 3;
				if(innSplt[1].equals("��")) {
					inn += 1;
				} else if(innSplt[1].equals("��")) {
					inn += 2;
				}
			} else {
				inn += Integer.parseInt(innStr) * 3;
			}
			dto.setInn(inn);												// �̴�����

			dto.setPa(((Long)pitcherRecordPlayer.get("pa")).intValue());	// ���Ÿ��
			dto.setBf(((Long)pitcherRecordPlayer.get("bf")).intValue());	// ������

			dto.setKk(((Long)pitcherRecordPlayer.get("kk")).intValue());	// Ż����
			dto.setHt(((Long)pitcherRecordPlayer.get("hit")).intValue());	// �Ǿ�Ÿ
			dto.setHr(((Long)pitcherRecordPlayer.get("hr")).intValue());	// ��Ȩ��
			dto.setBb(((Long)pitcherRecordPlayer.get("bbhp")).intValue());	// 4�籸
			dto.setRs(((Long)pitcherRecordPlayer.get("r")).intValue());		// ����
			dto.setEr(((Long)pitcherRecordPlayer.get("er")).intValue());	// ��å��

			point += (dto.getInn() * 4)
				   + (dto.getKk() * 10)
				   + (dto.getHt() * -7)
				   + (dto.getHr() * -10)
				   + (dto.getBb() * -5)
				   + (dto.getRs() * -5)
				   + (dto.getEr() * -10);
			
			dto.setPoint(point);	// �������

			list.add(dto);
		}
	}

	
	// Ÿ�� ��� �Ľ� ����
	@Override
	public ArrayList<BatterDTO> batterRecordData(String eventId, String eventRecordData) throws ParseException {

		ArrayList<BatterDTO> list = new ArrayList<BatterDTO>();
		BatterDTO dto = null;

		// �Ѱܹ��� String JSON���� �Ľ�
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(eventRecordData.toString());
		JSONObject obj = (JSONObject) rawObj;

		// Ÿ�� ���ھ�ڽ� �Ľ�
		JSONObject batter = (JSONObject) obj.get("battersBoxscore");
		
		// static method ȣ��
		BatterData(eventId, "away", list, batter, dto);
		BatterData(eventId, "home", list, batter, dto);
		
		return list;
	}
	
	// Ÿ�ڱ��	Ȩ/���� ���� �Ľ��ϴ� �޼ҵ�
	public static void BatterData(String eventId, String awayHome, ArrayList<BatterDTO> list, JSONObject batter, BatterDTO dto) {
		// Ȩ/���� �Ľ�
		JSONArray batterRecord = (JSONArray) batter.get(awayHome);

		// �������� �����ϴ� for ����
		for (int k = 0; k < batterRecord.size(); k++) {
			dto = new BatterDTO();
			JSONObject batterRecordPlayer = (JSONObject) batterRecord.get(k);
			
			dto.setEventCode(eventId);	// ����ڵ�
			dto.setPlayerCode((String) batterRecordPlayer.get("playerCode"));	// �����ڵ�
			float hra = Float.valueOf((String) batterRecordPlayer.get("hra"));	// Ÿ��
			dto.setHra(hra);

			// inn1 ~ inn12 ���� ������ ã�� for ����
			for (int j = 1; j <= 12; j++) {
				String str = (String) batterRecordPlayer.get("inn" + j);
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
					
					int ab = ((Long)batterRecordPlayer.get("ab")).intValue();		
					dto.setPa(ab + dto.getIb() + dto.getBb() + dto.getSh());		// Ÿ��
					dto.setRs(((Long)batterRecordPlayer.get("run")).intValue());	// ����
					dto.setRb(((Long)batterRecordPlayer.get("rbi")).intValue());	// Ÿ��
					
					dto.setPoint((dto.getPa()) + (dto.getRs() * 5) + (dto.getRb() * 10));	// �������
				}
			}
			list.add(dto);
		}
	}
	
	// Ÿ�� ��� �����ϴ� �޼ҵ�
	public static void Categorize(String bat, BatterDTO dto) {

		int len = bat.length();

		if (bat.contains("��")) {
			dto.setSh(dto.getSh() + 1);
			dto.setPoint(dto.getPoint() + dto.getSh() * 5);
//			System.out.println(bat + " : ���Ÿ");
		} else {

			switch (bat.substring((len - 1), len)) {
			case "��":
				dto.setH1(dto.getH1() + 1);
				dto.setPoint(dto.getPoint() + dto.getH1() * 10);
//				System.out.println(bat + " : 1��Ÿ");
				break;

			case "2":
				dto.setH2(dto.getH2() + 1);
				dto.setPoint(dto.getPoint() + dto.getH2() * 20);
//				System.out.println(bat + " : 2��Ÿ");
				break;

			case "3":
				dto.setH3(dto.getH3() + 1);
				dto.setPoint(dto.getPoint() + dto.getH3() * 30);
//				System.out.println(bat + " : 3��Ÿ");
				break;

			case "Ȩ":
				dto.setHr(dto.getHr() + 1);
				dto.setPoint(dto.getPoint() + dto.getHr() * 50);
//				System.out.println(bat + " : Ȩ��");
				break;

			case "��":
				dto.setBb(dto.getBb() + 1);
				dto.setPoint(dto.getPoint() + dto.getBb() * 5);
//				System.out.println(bat + " : 4�籸");
				break;

			case "4":
				dto.setIb(dto.getIb() + 1);
				dto.setPoint(dto.getPoint() + dto.getIb() * 10);
//				System.out.println(bat + " : ����4��");
				break;

			case "��":
			case "��":
				dto.setSo(dto.getSo() + 1);
				dto.setPoint(dto.getPoint() + dto.getSo() * -10);
//				System.out.println(bat + " : ����");
				break;

			case "��":
			case "��":
				dto.setDp(dto.getDp() + 1);
				dto.setPoint(dto.getPoint() + dto.getDp() * -10);
//				System.out.println(bat + " : ����Ÿ");
				break;

			case "��":
			case "��":
			case "��":
			case "��":
			case "��":
				dto.setOt(dto.getOt() + 1);
				dto.setPoint(dto.getPoint() + dto.getOt() * -5);
//				System.out.println(bat + " : �ƿ�");
				break;

			default:
				break;
			}
		}
	}
}
