package fw;

public class Query {
	
	public static String EVENT_LIST = "select e.EVENT_CD, e.EVENT_DT, e.A_TEAM_CD, e.H_TEAM_CD, e.STADIUM, e.A_SCORE, e.H_SCORE, e.EVENT_ST, e.CANCEL_FL, a.TEAM_SNM, h.TEAM_SNM, to_char(e.EVENT_DT, 'MM.DD(DY)') "
									+ "from EVENT_TB e, TEAM_TB a, TEAM_TB h "
									+ "where e.A_TEAM_CD = a.TEAM_CD and e.H_TEAM_CD = h.TEAM_CD "
									+ "and to_char(EVENT_DT, 'YYYYMM') = ? "
									+ "order by e.EVENT_DT, e.EVENT_CD";
}
