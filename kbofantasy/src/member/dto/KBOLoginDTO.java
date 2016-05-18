package member.dto;

public class KBOLoginDTO {
	private int member_cd;
	private String name;
	private String email;
	private String password;
	private String team_cd;
	private int point;
	
	public KBOLoginDTO(){
		
	}
	
	
	
	public KBOLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}



	public KBOLoginDTO(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.member_cd = 0;
		this.team_cd = null;
		this.point = 0;
	}



	public KBOLoginDTO(int member_cd, String email, String password, String name, String team_cd, int point) {
		super();
		this.member_cd = member_cd;
		this.email = email;
		this.password = password;
		this.name = name;
		this.team_cd = team_cd;
		this.point = point;
	}



	public int getMember_cd() {
		return member_cd;
	}



	public void setMember_cd(int member_cd) {
		this.member_cd = member_cd;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTeam_cd() {
		return team_cd;
	}



	public void setTeam_cd(String team_cd) {
		this.team_cd = team_cd;
	}



	public int getPoint() {
		return point;
	}



	public void setPoint(int point) {
		this.point = point;
	}
	
}
