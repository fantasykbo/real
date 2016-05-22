package game.dto;

public class MyTeamDTO {
	private String sp_cd;
	private String rp_cd;
	private String dh_cd;
	private String c_cd;
	private String fb_cd;
	private String sb_cd;
	private String tb_cd;
	private String ss_cd;
	private String lf_cd;
	private String cf_cd;
	private String rf_cd;
	// 점수 자동 업데이트용 - 유병수 추가
	private int member_cd;
	private String game_dt;
	

	public int getMember_cd() {
		return member_cd;
	}

	public void setMember_cd(int member_cd) {
		this.member_cd = member_cd;
	}

	public String getGame_dt() {
		return game_dt;
	}

	public void setGame_dt(String game_dt) {
		this.game_dt = game_dt;
	}

	public MyTeamDTO(int member_cd, String game_dt) {
		super();
		this.member_cd = member_cd;
		this.game_dt = game_dt;
	}
	
	// 유병수 추가 끝
	
	
	public MyTeamDTO() {
		
	}
	
	public MyTeamDTO(String sp_cd, String rp_cd, String dh_cd, String c_cd,
			String fb_cd, String sb_cd, String tb_cd, String ss_cd,
			String lf_cd, String cf_cd, String rf_cd) {
		super();
		this.sp_cd = sp_cd;
		this.rp_cd = rp_cd;
		this.dh_cd = dh_cd;
		this.c_cd = c_cd;
		this.fb_cd = fb_cd;
		this.sb_cd = sb_cd;
		this.tb_cd = tb_cd;
		this.ss_cd = ss_cd;
		this.lf_cd = lf_cd;
		this.cf_cd = cf_cd;
		this.rf_cd = rf_cd;
	}

	public String getSp_cd() {
		return sp_cd;
	}

	public void setSp_cd(String sp_cd) {
		this.sp_cd = sp_cd;
	}

	public String getRp_cd() {
		return rp_cd;
	}

	public void setRp_cd(String rp_cd) {
		this.rp_cd = rp_cd;
	}

	public String getDh_cd() {
		return dh_cd;
	}

	public void setDh_cd(String dh_cd) {
		this.dh_cd = dh_cd;
	}

	public String getC_cd() {
		return c_cd;
	}

	public void setC_cd(String c_cd) {
		this.c_cd = c_cd;
	}

	public String getFb_cd() {
		return fb_cd;
	}

	public void setFb_cd(String fb_cd) {
		this.fb_cd = fb_cd;
	}

	public String getSb_cd() {
		return sb_cd;
	}

	public void setSb_cd(String sb_cd) {
		this.sb_cd = sb_cd;
	}

	public String getTb_cd() {
		return tb_cd;
	}

	public void setTb_cd(String tb_cd) {
		this.tb_cd = tb_cd;
	}

	public String getSs_cd() {
		return ss_cd;
	}

	public void setSs_cd(String ss_cd) {
		this.ss_cd = ss_cd;
	}

	public String getLf_cd() {
		return lf_cd;
	}

	public void setLf_cd(String lf_cd) {
		this.lf_cd = lf_cd;
	}

	public String getCf_cd() {
		return cf_cd;
	}

	public void setCf_cd(String cf_cd) {
		this.cf_cd = cf_cd;
	}

	public String getRf_cd() {
		return rf_cd;
	}

	public void setRf_cd(String rf_cd) {
		this.rf_cd = rf_cd;
	}

	@Override
	public String toString() {
		return "MyTeamDTO [sp_cd=" + sp_cd + ", rp_cd=" + rp_cd + ", dh_cd="
				+ dh_cd + ", c_cd=" + c_cd + ", fb_cd=" + fb_cd + ", sb_cd="
				+ sb_cd + ", tb_cd=" + tb_cd + ", ss_cd=" + ss_cd + ", lf_cd="
				+ lf_cd + ", cf_cd=" + cf_cd + ", rf_cd=" + rf_cd +"]";
	}
	
	
}
