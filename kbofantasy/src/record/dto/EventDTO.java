package record.dto;

public class EventDTO {
	public String eventCode;
	public String eventDate;
	public String aTeamCode;
	public String hTeamCode;
	public String stadium;
	public String aScore;
	public String hScore;
	public String eventStatus;
	public String cancelFlag;
	public String aTeamSName;
	public String hTeamSName;
	public String sEventDate;
	
	public EventDTO() {}
	
	public EventDTO(String eventCode, String aTeamCode, String hTeamCode,
			String aScore, String hScore, String aTeamSName, String hTeamSName) {
		super();
		this.eventCode = eventCode;
		this.aTeamCode = aTeamCode;
		this.hTeamCode = hTeamCode;
		this.aScore = aScore;
		this.hScore = hScore;
		this.aTeamSName = aTeamSName;
		this.hTeamSName = hTeamSName;
	}



	public EventDTO(String eventCode, String aScore, String hScore, String eventStatus, String cancelFlag) {
		super();
		this.eventCode = eventCode;
		this.aScore = aScore;
		this.hScore = hScore;
		this.eventStatus = eventStatus;
		this.cancelFlag = cancelFlag;
	}

	public EventDTO(String eventCode, String eventDate, String aTeamCode,
			String hTeamCode, String stadium, String aScore, String hScore,
			String eventStatus, String cancelFlag, String aTeamSName, String hTeamSName, String sEventDate) {
		super();
		this.eventCode = eventCode;
		this.eventDate = eventDate;
		this.aTeamCode = aTeamCode;
		this.hTeamCode = hTeamCode;
		this.stadium = stadium;
		this.aScore = aScore;
		this.hScore = hScore;
		this.eventStatus = eventStatus;
		this.cancelFlag = cancelFlag;
		this.aTeamSName = aTeamSName;
		this.hTeamSName = hTeamSName;
		this.sEventDate = sEventDate;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getaTeamCode() {
		return aTeamCode;
	}
	public void setaTeamCode(String aTeamCode) {
		this.aTeamCode = aTeamCode;
	}
	public String gethTeamCode() {
		return hTeamCode;
	}
	public void sethTeamCode(String hTeamCode) {
		this.hTeamCode = hTeamCode;
	}
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	public String getaScore() {
		return aScore;
	}
	public void setaScore(String aScore) {
		this.aScore = aScore;
	}
	public String gethScore() {
		return hScore;
	}
	public void sethScore(String hScore) {
		this.hScore = hScore;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getaTeamSName() {
		return aTeamSName;
	}
	public void setaTeamSName(String aTeamSName) {
		this.aTeamSName = aTeamSName;
	}
	public String gethTeamSName() {
		return hTeamSName;
	}
	public void sethTeamSName(String hTeamSName) {
		this.hTeamSName = hTeamSName;
	}
	public String getsEventDate() {
		return sEventDate;
	}
	public void setsEventDate(String sEventDate) {
		this.sEventDate = sEventDate;
	}
	@Override
	public String toString() {
		return "RecordDTO [eventCode=" + eventCode + ", eventDate=" + eventDate
				+ ", aTeamCode=" + aTeamCode + ", hTeamCode=" + hTeamCode
				+ ", stadium=" + stadium + ", aScore=" + aScore + ", hScore="
				+ hScore + ", eventStatus=" + eventStatus + ", cancelFlag="
				+ cancelFlag + ", aTeamSName=" + aTeamSName + ", hTeamSName="
				+ hTeamSName + ", sEventDate=" + sEventDate + "]";
	}
	
	
	
}
