package kr.co.bctt.ssh.dto;

/**
 * @FileName : HostInfo.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버에서 받아온 resources값을 저장하는 dto
 */
public class HostInfo {
	private String hostName;
	private String service;
	private String zone;
	public HostInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HostInfo(String hostName, String service, String zone) {
		super();
		this.hostName = hostName;
		this.service = service;
		this.zone = zone;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	@Override
	public String toString() {
		return "HostList [hostName=" + hostName + ", service=" + service + ", zone=" + zone + "]";
	}
	
	
	

}
