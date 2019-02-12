package kr.co.bctt.ssh.dto;

/**
 * @FileName : HostResource.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버에서 받아온 Resource 값을 저장하는 Dto
 */
public class HostResource {
	
	private String host;
	private String project;
	private String cpu;
	private String memoryMb;
	private String diskGb;
	public HostResource() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HostResource(String host, String project, String cpu, String memoryMb, String diskGb) {
		super();
		this.host = host;
		this.project = project;
		this.cpu = cpu;
		this.memoryMb = memoryMb;
		this.diskGb = diskGb;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMemoryMb() {
		return memoryMb;
	}
	public void setMemoryMb(String memoryMb) {
		this.memoryMb = memoryMb;
	}
	public String getDiskGb() {
		return diskGb;
	}
	public void setDiskGb(String diskGb) {
		this.diskGb = diskGb;
	}
	@Override
	public String toString() {
		return "HostResource [host=" + host + ", project=" + project + ", cpu=" + cpu + ", memoryMb=" + memoryMb
				+ ", diskGb=" + diskGb + "]";
	}
	
	

}
