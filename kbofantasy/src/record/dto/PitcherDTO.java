package record.dto;

public class PitcherDTO {
	private String eventCode;	// 경기코드
	private String playerCode;	// 선수코드
	private String wlhs;			// 승패홀세
	private float era;			// 평균자책
	private int inn;				// 이닝
	private int pa;				// 상대타자
	private int bf;				// 투구수
	private int kk;				// 탈삼
	private int ht;				// 피안타
	private int hr;				// 피홈런
	private int bb;				// 4사구
	private int rs;				// 실점
	private int er;				// 자책점
	private int point;			// 포인트
	private String win;
	private String lose;
	private String hold;
	private String save;
	private int pl;


	@Override
	public String toString() {
		return "PitcherDTO [eventCode=" + eventCode + ", playerCode="
				+ playerCode + ", wlhs=" + wlhs + ", era=" + era + ", inn="
				+ inn + ", pa=" + pa + ", bf=" + bf + ", kk=" + kk + ", ht="
				+ ht + ", hr=" + hr + ", bb=" + bb + ", rs=" + rs + ", er="
				+ er + ", point=" + point + ", win=" + win + ", lose=" + lose
				+ ", hold=" + hold + ", save=" + save + ", pl=" + pl + "]";
	}
	public PitcherDTO() {}
	
	public PitcherDTO(int point, int pl, String win, String lose, String hold,
			String save, float era, int inn, int pa, int bf, int kk, int ht,
			int hr, int bb, int rs, int er) {
		super();
		this.point = point;
		this.pl = pl;
		this.win = win;
		this.lose = lose;
		this.hold = hold;
		this.save = save;
		this.era = era;
		this.inn = inn;
		this.pa = pa;
		this.bf = bf;
		this.kk = kk;
		this.ht = ht;
		this.hr = hr;
		this.bb = bb;
		this.rs = rs;
		this.er = er;
	}
	public PitcherDTO(String eventCode, String playerCode, String wlhs,
			float era, int inn, int pa, int bf, int kk, int ht, int hr,
			int bb, int rs, int er, int point) {
		super();
		this.eventCode = eventCode;
		this.playerCode = playerCode;
		this.wlhs = wlhs;
		this.era = era;
		this.inn = inn;
		this.pa = pa;
		this.bf = bf;
		this.kk = kk;
		this.ht = ht;
		this.hr = hr;
		this.bb = bb;
		this.rs = rs;
		this.er = er;
		this.point = point;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public String getPlayerCode() {
		return playerCode;
	}
	public void setPlayerCode(String playerCode) {
		this.playerCode = playerCode;
	}
	public String getWlhs() {
		return wlhs;
	}
	public void setWlhs(String wlhs) {
		this.wlhs = wlhs;
	}
	public float getEra() {
		return era;
	}
	public void setEra(float era) {
		this.era = era;
	}
	public int getInn() {
		return inn;
	}
	public void setInn(int inn) {
		this.inn = inn;
	}
	public int getPa() {
		return pa;
	}
	public void setPa(int pa) {
		this.pa = pa;
	}
	public int getBf() {
		return bf;
	}
	public void setBf(int bf) {
		this.bf = bf;
	}
	public int getKk() {
		return kk;
	}
	public void setKk(int kk) {
		this.kk = kk;
	}
	public int getHt() {
		return ht;
	}
	public void setHt(int ht) {
		this.ht = ht;
	}
	public int getHr() {
		return hr;
	}
	public void setHr(int hr) {
		this.hr = hr;
	}
	public int getBb() {
		return bb;
	}
	public void setBb(int bb) {
		this.bb = bb;
	}
	public int getRs() {
		return rs;
	}
	public void setRs(int rs) {
		this.rs = rs;
	}
	public int getEr() {
		return er;
	}
	public void setEr(int er) {
		this.er = er;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getLose() {
		return lose;
	}
	public void setLose(String lose) {
		this.lose = lose;
	}
	public String getHold() {
		return hold;
	}
	public void setHold(String hold) {
		this.hold = hold;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}
	public int getPl() {
		return pl;
	}
	public void setPl(int pl) {
		this.pl = pl;
	}
	
	
}

	
