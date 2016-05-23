package fw;

public class Query {
	
	// 경기일정결과 목록 출력 쿼리
	public static String EVENT_LIST = "select e.EVENT_CD, e.EVENT_DT, e.A_TEAM_CD, e.H_TEAM_CD, e.STADIUM, e.A_SCORE, e.H_SCORE, e.EVENT_ST, e.CANCEL_FL, a.TEAM_SNM, h.TEAM_SNM, to_char(e.EVENT_DT, 'MM.DD(DY)') "
									+ "from EVENT_TB e, TEAM_TB a, TEAM_TB h "
									+ "where e.A_TEAM_CD = a.TEAM_CD and e.H_TEAM_CD = h.TEAM_CD "
									+ "and to_char(EVENT_DT, 'YYYYMM') = ? "
									+ "order by e.EVENT_DT, e.EVENT_CD";
	// 팀 순위를 위한 목록 출력 쿼리
	public static String EVENT_TABLE = "select to_char(e.EVENT_DT, 'YYYY/MM/DD') EVENT_DATE, e.A_TEAM_CD, e.H_TEAM_CD, e.A_SCORE, e.H_SCORE, a.TEAM_NM, h.TEAM_NM "
									 + "from EVENT_TB e, Team_TB a, TEAM_TB h "
									 + "where e.A_TEAM_CD = a.TEAM_CD "
									 + "and e.H_TEAM_CD = h.TEAM_CD "
									 + "and e.EVENT_ST = 4 "
									 + "order by EVENT_DATE";
	
	// 선수 정보 페이지 관련 쿼리들
	// 선수 정보 출력 쿼리
	public static String PLAYER_INFO = "select p.*, t.TEAM_NM from PLAYER_TB p, TEAM_TB t where p.TEAM_CD = t.TEAM_CD and p.PLAYER_CD = ?";
	// 타자 성적 출력 쿼리
	public static String B_STAT = "select sum(POINT) POINT, count(PLAYER_CD), "
										  + "round(nvl((sum(h1)+sum(h2)+sum(h3)+sum(hr))/decode((sum(pa)-sum(bb)-sum(ib)-sum(sh)),0,null,(sum(pa)-sum(bb)-sum(ib)-sum(sh))),0),3), "
										  + "round(nvl((sum(h1)+sum(h2)+sum(h3)+sum(hr)+sum(bb)+sum(ib))/decode((sum(pa)),0,null,(sum(pa))),0),3), "
										  + "round(nvl((sum(h1)+sum(h2)*2+sum(h3)*3+sum(hr)*4)/decode((sum(pa)-sum(bb)-sum(ib)-sum(sh)),0,null,(sum(pa)-sum(bb)-sum(ib)-sum(sh))),0),3), "
										  + "round(nvl((sum(h1)+sum(h2)+sum(h3)+sum(hr)+sum(bb)+sum(ib))/decode((sum(pa)),0,null,(sum(pa))),0) + "
										  + "nvl((sum(h1)+sum(h2)*2+sum(h3)*3+sum(hr)*4)/decode((sum(pa)-sum(bb)-sum(ib)-sum(sh)),0,null,(sum(pa)-sum(bb)-sum(ib)-sum(sh))),0),3), "
										  + "sum(PA), sum(H1), sum(H2), sum(H3), sum(HR), sum(RS), sum(RB), sum(BB), sum(IB), sum(SH), sum(OT), sum(SO), sum(DP) "
										  + "from RECORD_B_TB "
										  + "where PLAYER_CD = ?";
	// 투수 성적 출력 쿼리
	public static String P_STAT = "select sum(POINT), count(EVENT_CD), sum((decode(WLHS, 'W', 1))), sum((decode(WLHS, 'L', 1))), sum((decode(WLHS, 'H', 1))), sum((decode(WLHS, 'S', 1))), "
								+ "round((sum(ER)*9)/(sum(INN)/3),2) ERA, sum(INN), sum(PA), sum(BF), sum(KK), sum(HT), sum(HR), sum(BB), sum(RS), sum(ER) "
								+ "from RECORD_P_TB "
								+ "where PLAYER_CD = ?";
	
	// 타자 최근 10경기 출력 쿼리
	public static String B_LAST_STAT = "select b.* from (select * from RECORD_B_TB where PLAYER_CD = ? order by EVENT_CD DESC) b where rownum <= 10";	
	// 투수 최근 10경기 출력 쿼리
	public static String P_LAST_STAT = "select b.* from (select * from RECORD_P_TB where PLAYER_CD = ? order by EVENT_CD DESC) b where rownum <= 10";	

	
// timer 자동 실행 쿼리들
	// 경기결과 업데이트 쿼리
	public static String EVENT_UPDATE = "update EVENT_TB set A_SCORE = ?, H_SCORE = ?, EVENT_ST = ?, CANCEL_FL = ? where EVENT_CD = ?";
	// 경기별 타자기록 생성 쿼리
	public static String RECORD_B_INSERT = "insert into RECORD_B_TB values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 경기별 투수기록 생성 쿼리
	public static String RECORD_P_INSERT = "insert into RECORD_P_TB values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	
	// 경기별 타자기록에 의한 포인트를 선수정보로 업데이트하는 쿼리
	public static String PLAYER_B_POINT_MERGE = "merge into PLAYER_TB p "
											  + "using (select PLAYER_CD, sum(POINT) POINT from RECORD_B_TB group by PLAYER_CD) b "
											  + "on (p.PLAYER_CD = b.PLAYER_CD) "
											  + "when matched then "
											  + "update set p.POINT_SUM = b.POINT";
	
	// 경기별 투수기록에 의한 포인트를 선수정보로 업데이트하는 쿼리
	public static String PLAYER_P_POINT_MERGE = "merge into PLAYER_TB p "
											  + "using (select PLAYER_CD, sum(POINT) POINT from RECORD_P_TB group by PLAYER_CD) b "
											  + "on (p.PLAYER_CD = b.PLAYER_CD) "
											  + "when matched then "
											  + "update set p.POINT_SUM = b.POINT";
	
	// 내 팀 선수가 당일 받은 포인트를 내 포인트로 저장하는 쿼리
	public static String GAME_POINT_MERGE = "merge into GAME_TB g "
										  + "using (select sum(point_sum) point from player_tb "
										  + "where player_cd = (select sp_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select rp_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select dh_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select c_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select fb_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select sb_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select tb_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select ss_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select lf_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select cf_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD')) "
										  + "or player_cd = (select rf_cd from game_tb where MEMBER_CD = 100009 and GAME_DT = to_date('20160521', 'YYYYMMDD'))) s "
										  + "on (g.MEMBER_CD = 100009) "
										  + "when matched then "
										  + "update set g.POINT = s.POINT";
	
	// 내 게임에서 얻은 누적 포인트를 내 포인트로 업데이트하는 쿼리
	public static String MEMBER_POINT_MERGE = "merge into MEMBER_TB m "
											+ "using(select MEMBER_CD, SUM(POINT) POINT from GAME_TB group by MEMBER_CD) g "
											+ "on (m.MEMBER_CD = g.MEMBER_CD) "
											+ "when matched then "
											+ "update set m.POINT = g.POINT";
	
	
	
	
	public static String MEMBER_LOGIN="select * from member_tb where email=? and password=?";
	public static String MEMBER_REGISTER="insert into member_tb values(aaa.nextval,?,?,?,?,?)";
	public static String MEMBER_REGISTER1="insert into member_tb values(MEMBER_TB_SEQ.nextval,?,?,?,?,?)";
	public static String emailCheck ="select * from member_tb where email=?";
	public static String passCheck ="select * from member_tb where password=? ";
	public static String MEMBER_LEAVE="delete from member_tb where email=? and password=?";
	public static String CHANGE_PASSWORD="update member_tb set password=? where email=?";
	
	
	public static String MY_LIST = "select sp_cd, rp_cd, dh_cd, c_cd, fb_cd, sb_cd, tb_cd, ss_cd, lf_cd, cf_cd, rf_cd "
			 + "from game_tb "
			 + "where member_cd = ? and game_dt = to_date(?, 'YYYY/MM/DD')";
	
	public static String MY_PINFOLIST = "select player_cd, back_no, player_nm, team_cd, position_dt, hand, salary "
					  + "from player_tb "
					  + "where player_cd in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static String My_PINFO = "select player_cd, back_no, player_nm, team_cd, position_dt, hand, salary "
				  + "from player_tb "
				  + "where player_cd = ?";
	
	public static String DT_LIST = "select player_cd, player_nm, team_cd, hand, salary "
				 + "from player_tb"
				 + "where position_dt = ?";
	
	public static String GET_TOP_USER = "select rownum, member_nm, point from (select * from member_tb order by point desc) where rownum<=20";
	
	public static String GET_B_TOP_PLAYER = "select * from (select rownum, r.* "
			+ "from (select p.PLAYER_NM PNAME, t.TEAM_SNM TEAM, p.POSITION_DT POS, sum(b.POINT) POINT "
			+ "from RECORD_B_TB b, PLAYER_TB p, TEAM_TB t " + "where b.PLAYER_CD = p.PLAYER_CD "
			+ "and p.TEAM_CD = t.TEAM_CD " + "group by b.PLAYER_CD, p.PLAYER_NM, t.TEAM_SNM, p.POSITION_DT "
			+ "order by POINT desc) r " + "where rownum <= 20)";
	
	public static String GET_P_TOP_PLAYER ="select * from (select rownum, r.* "
	+ "from (select p.PLAYER_NM PNAME, t.TEAM_SNM TEAM, p.POSITION_DT POS, sum(b.POINT) POINT "
	+ "from RECORD_P_TB b, PLAYER_TB p, TEAM_TB t "
	+ "where b.PLAYER_CD = p.PLAYER_CD "
	+ "and p.TEAM_CD = t.TEAM_CD "
	+ "group by b.PLAYER_CD, p.PLAYER_NM, t.TEAM_SNM, p.POSITION_DT "
	+ "order by POINT desc) r "
	+ "where rownum <= 20)";	
	
	public static String SCOUT_LIST1="select player_cd, back_no, player_nm, "
			+ "team_cd, position_dt, hand, salary "
			+ "from player_tb "
			+ "where position_dt=? ";
	
	public static String SCOUT_LIST2="select player_cd, back_no, player_nm, "
			+ "team_cd, position_dt, hand, salary "
			+ "from player_tb "
			+ "where position_dt=? or position_dt=? ";
	
	public static String SCOUT_LIST3="select player_cd, back_no, player_nm, "
			+ "team_cd, position_dt, hand, salary "
			+ "from player_tb "
			+ "where position_dt <>=?";

	
	
	
}
