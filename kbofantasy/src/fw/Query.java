package fw;

public class Query {
	
	public static String EVENT_LIST = "select e.EVENT_CD, e.EVENT_DT, e.A_TEAM_CD, e.H_TEAM_CD, e.STADIUM, e.A_SCORE, e.H_SCORE, e.EVENT_ST, e.CANCEL_FL, a.TEAM_SNM, h.TEAM_SNM, to_char(e.EVENT_DT, 'MM.DD(DY)') "
									+ "from EVENT_TB e, TEAM_TB a, TEAM_TB h "
									+ "where e.A_TEAM_CD = a.TEAM_CD and e.H_TEAM_CD = h.TEAM_CD "
									+ "and to_char(EVENT_DT, 'YYYYMM') = ? "
									+ "order by e.EVENT_DT, e.EVENT_CD";
	
	public static String MEMBER_LOGIN="select * from member_tb where email=? and password=?";
	public static String MEMBER_REGISTER="insert into member_tb values(aaa.nextval,?,?,?,?,?)";
	public static String MEMBER_REGISTER1="insert into member_tb values(MEMBER_TB_SEQ.nextval,?,?,?,?,?)";
	public static String emailCheck ="select * from member_tb where password=?";
	public static String passCheck ="select * from member_tb where password=? ";
	public static String MEMBER_LEAVE="delete from member_tb where password=? and member_nm=?";
}
