package fw;
public class Query {
	
	public static String MEMBER_LOGIN="select * from member_tb where email=? and password=?";
	
	public static String MEMBER_REGISTER="insert into member_tb values(aaa.nextval,?,?,?,?,?)";
	public static String MEMBER_REGISTER1="insert into member_tb values(MEMBER_TB_SEQ.nextval,?,?,?,?,?)";
	public static String emailCheck ="select * from member_tb where password=?";//db컬럼네임 바꿔야함
	public static String passCheck ="select * from member_tb where password=? ";
}
