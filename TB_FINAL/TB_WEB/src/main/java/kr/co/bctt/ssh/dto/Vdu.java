package kr.co.bctt.ssh.dto;

public class Vdu {
	private int profile_id;
	private String vdu_name;
	private String org_type;
	private String org_name;
	private String host_type;
	private String host_name;
	private String vdu_ip;
	
	public Vdu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Vdu(int profile_id, String vdu_name, String org_type, String org_name, String host_type, String host_name) {
		super();
		this.profile_id = profile_id;
		this.vdu_name = vdu_name;
		this.org_type = org_type;
		this.org_name = org_name;
		this.host_type = host_type;
		this.host_name = host_name;
	}

	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}

	public String getVdu_name() {
		return vdu_name;
	}

	public void setVdu_name(String vdu_name) {
		this.vdu_name = vdu_name;
	}

	public String getOrg_type() {
		return org_type;
	}

	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getHost_type() {
		return host_type;
	}

	public void setHost_type(String host_type) {
		this.host_type = host_type;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getVdu_ip() {
		return vdu_ip;
	}

	public void setVdu_ip(String vdu_ip) {
		this.vdu_ip = vdu_ip;
	}
	
	
}
