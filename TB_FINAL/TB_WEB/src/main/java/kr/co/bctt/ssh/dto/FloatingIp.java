package kr.co.bctt.ssh.dto;

/**
 * @FileName : VnfdInfo.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버에서 받은 vnfd값중 필요한 값만 저장하는 dto
 */
public class FloatingIp {
	private String id;
	private String ip_addr;
	private String fixed_ip;
	private String port;
	
	public FloatingIp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FloatingIp(String id, String ip_addr, String fixed_ip, String port) {
		super();
		this.id = id;
		this.ip_addr = ip_addr;
		this.fixed_ip = fixed_ip;
		this.port = port;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public String getFixed_ip() {
		return fixed_ip;
	}
	public void setFixed_ip(String fixed_ip) {
		this.fixed_ip = fixed_ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
