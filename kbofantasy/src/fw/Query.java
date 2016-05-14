package fw;

public class Query {
	
	public static String EVENT_LIST = "select e.*, a.TEAM_SNM, h.TEAM_SNM, to_char(e.EVENT_DT, 'MM.DD(DY)') "
									+ "from EVENT_TB e, TEAM_TB a, TEAM_TB h "
									+ "where e.A_TEAM_CD = a.TEAM_CD and e.H_TEAM_CD = h.TEAM_CD "
									+ "and to_char(EVENT_DT, 'YYYYMM') = ? "
									+ "order by e.EVENT_DT, e.EVENT_CD";
}
