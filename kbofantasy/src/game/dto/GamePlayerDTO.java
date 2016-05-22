package game.dto;

public class GamePlayerDTO {
	private String player_Cd;
	private String back_No;
	private String player_Name;
	private String team_Cd;
	private String position_Dt;
	private String hand;
	private String salary;
	
	public GamePlayerDTO() {
		
	}

	public GamePlayerDTO(String player_Cd, String back_No, String player_Name,
			String team_Cd, String position_Dt, String hand, String salary) {
		super();
		this.player_Cd = player_Cd;
		this.back_No = back_No;
		this.player_Name = player_Name;
		this.team_Cd = team_Cd;
		this.position_Dt = position_Dt;
		this.hand = hand;
		this.salary = salary;
	}

	public String getPlayer_Cd() {
		return player_Cd;
	}

	public void setPlayer_Cd(String player_Cd) {
		this.player_Cd = player_Cd;
	}

	public String getBack_No() {
		return back_No;
	}

	public void setBack_No(String back_No) {
		this.back_No = back_No;
	}

	public String getPlayer_Name() {
		return player_Name;
	}

	public void setPlayer_Name(String player_Name) {
		this.player_Name = player_Name;
	}

	public String getTeam_Cd() {
		return team_Cd;
	}

	public void setTeam_Cd(String team_Cd) {
		this.team_Cd = team_Cd;
	}

	public String getPosition_Dt() {
		return position_Dt;
	}

	public void setPosition_Dt(String position_Dt) {
		this.position_Dt = position_Dt;
	}

	public String getHand() {
		return hand;
	}

	public void setHand(String hand) {
		this.hand = hand;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "GamePlayerDTO [player_Cd=" + player_Cd + ", back_No=" + back_No
				+ ", player_Name=" + player_Name + ", team_Cd=" + team_Cd
				+ ", position_Dt=" + position_Dt + ", hand=" + hand
				+ ", salary=" + salary + "]";
	}	
	
	
	
}
