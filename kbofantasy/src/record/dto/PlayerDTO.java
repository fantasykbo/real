package record.dto;

public class PlayerDTO {
	public String eventCode;
	public String playerCode;
	public int pa;		// Ÿ��
	public float hra;	// Ÿ��
	public int h1;		// 1��Ÿ
	public int h2;		// 2��Ÿ
	public int h3;		// 3��Ÿ
	public int hr;		// Ȩ��
	public int rs;		// ����
	public int rb;		// Ÿ��
	public int bb;		// 4��
	public int ib;		// ����4��
	public int sh;		// ���Ÿ
	public int ot;		// �ƿ�
	public int so;		// ����
	public int dp;		// ����
	public int point;
	
	@Override
	public String toString() {
		return "PlayerDTO [eventCode=" + eventCode + ", playerCode="
				+ playerCode + ", pa=" + pa + ", hra=" + hra + ", h1=" + h1
				+ ", h2=" + h2 + ", h3=" + h3 + ", hr=" + hr + ", rs=" + rs
				+ ", rb=" + rb + ", bb=" + bb + ", ib=" + ib + ", sh=" + sh
				+ ", ot=" + ot + ", so=" + so + ", dp=" + dp + ", point="
				+ point + "]";
	}
	public PlayerDTO(){}
	public PlayerDTO(String eventCode, String playerCode, int pa, float hra,
			int h1, int h2, int h3, int hr, int rs, int rb, int bb, int ib,
			int sh, int ot, int so, int dp, int point) {
		super();
		this.eventCode = eventCode;
		this.playerCode = playerCode;
		this.pa = pa;
		this.hra = hra;
		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.hr = hr;
		this.rs = rs;
		this.rb = rb;
		this.bb = bb;
		this.ib = ib;
		this.sh = sh;
		this.ot = ot;
		this.so = so;
		this.dp = dp;
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
	public int getPa() {
		return pa;
	}
	public void setPa(int pa) {
		this.pa = pa;
	}
	public float getHra() {
		return hra;
	}
	public void setHra(float hra) {
		this.hra = hra;
	}
	public int getH1() {
		return h1;
	}
	public void setH1(int h1) {
		this.h1 = h1;
	}
	public int getH2() {
		return h2;
	}
	public void setH2(int h2) {
		this.h2 = h2;
	}
	public int getH3() {
		return h3;
	}
	public void setH3(int h3) {
		this.h3 = h3;
	}
	public int getHr() {
		return hr;
	}
	public void setHr(int hr) {
		this.hr = hr;
	}
	public int getRs() {
		return rs;
	}
	public void setRs(int rs) {
		this.rs = rs;
	}
	public int getRb() {
		return rb;
	}
	public void setRb(int rb) {
		this.rb = rb;
	}
	public int getBb() {
		return bb;
	}
	public void setBb(int bb) {
		this.bb = bb;
	}
	public int getIb() {
		return ib;
	}
	public void setIb(int ib) {
		this.ib = ib;
	}
	public int getSh() {
		return sh;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public int getOt() {
		return ot;
	}
	public void setOt(int ot) {
		this.ot = ot;
	}
	public int getSo() {
		return so;
	}
	public void setSo(int so) {
		this.so = so;
	}
	public int getDp() {
		return dp;
	}
	public void setDp(int dp) {
		this.dp = dp;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}

	
