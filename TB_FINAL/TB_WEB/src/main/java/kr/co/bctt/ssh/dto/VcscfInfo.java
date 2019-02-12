package kr.co.bctt.ssh.dto;

/**
 * @FileName : VcscfInfo.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버에서 받은 vcscfinfo값중 필요한 값만 저장하는 Dto
 */
public class VcscfInfo {
	private String hss_ip;
	private String domain_name;
	private String virtual_ip;
	private String pcscf_domain;
	private String icscf_domain;
	private String scscf_domain;
	public VcscfInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VcscfInfo(String hss_ip, String domain_name, String virtual_ip, String pcscf_domain, String icscf_domain,
			String scscf_domain) {
		super();
		this.hss_ip = hss_ip;
		this.domain_name = domain_name;
		this.virtual_ip = virtual_ip;
		this.pcscf_domain = pcscf_domain;
		this.icscf_domain = icscf_domain;
		this.scscf_domain = scscf_domain;
	}
	public String getHss_ip() {
		return hss_ip;
	}
	public void setHss_ip(String hss_ip) {
		this.hss_ip = hss_ip;
	}
	public String getDomain_name() {
		return domain_name;
	}
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
	public String getVirtual_ip() {
		return virtual_ip;
	}
	public void setVirtual_ip(String virtual_ip) {
		this.virtual_ip = virtual_ip;
	}
	public String getPcscf_domain() {
		return pcscf_domain;
	}
	public void setPcscf_domain(String pcscf_domain) {
		this.pcscf_domain = pcscf_domain;
	}
	public String getIcscf_domain() {
		return icscf_domain;
	}
	public void setIcscf_domain(String icscf_domain) {
		this.icscf_domain = icscf_domain;
	}
	public String getScscf_domain() {
		return scscf_domain;
	}
	public void setScscf_domain(String scscf_domain) {
		this.scscf_domain = scscf_domain;
	}
	@Override
	public String toString() {
		return "vcscfInfo [hss_ip=" + hss_ip + ", domain_name=" + domain_name + ", virtual_ip=" + virtual_ip
				+ ", pcscf_domain=" + pcscf_domain + ", icscf_domain=" + icscf_domain + ", scscf_domain=" + scscf_domain
				+ "]";
	}
	
	


}
