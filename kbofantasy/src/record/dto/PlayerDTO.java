package record.dto;

public class PlayerDTO {
	public String playerCode;
	public String backNo;
	public String playerName;
	public String teamCode;
	public String positionName;
	public String positionDetail;
	public String birth;
	public String spec;
	public String school;
	public String hand;
	public String salary;
	public int pointSum;
	public String teamName;
	
	
	
	public PlayerDTO(){}
	
	public PlayerDTO(String playerCode, String backNo, String playerName,
			String teamCode, String positionName, String positionDetail,
			String birth, String spec, String school, String hand,
			String salary, int pointSum, String teamName) {
		super();
		this.playerCode = playerCode;
		this.backNo = backNo;
		this.playerName = playerName;
		this.teamCode = teamCode;
		this.positionName = positionName;
		this.positionDetail = positionDetail;
		this.birth = birth;
		this.spec = spec;
		this.school = school;
		this.hand = hand;
		this.salary = salary;
		this.pointSum = pointSum;
		this.teamName = teamName;
	}
	public String getPlayerCode() {
		return playerCode;
	}
	public void setPlayerCode(String playerCode) {
		this.playerCode = playerCode;
	}
	public String getBackNo() {
		return backNo;
	}
	public void setBackNo(String backNo) {
		this.backNo = backNo;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionDetail() {
		return positionDetail;
	}
	public void setPositionDetail(String positionDetail) {
		this.positionDetail = positionDetail;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
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
	public int getPointSum() {
		return pointSum;
	}
	public void setPointSum(int pointSum) {
		this.pointSum = pointSum;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
}

	
