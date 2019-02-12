package kr.co.bctt.ssh.dto;

/**
 * @FileName : NodeInformation.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 : 
 * @프로그램설명 : 서버에서 resource정보를 받아온 값중 필요한 값을 저장하는 dto
 */
public class NodeInformation {
	private String host;
	private String totalCpu;
	private String totalDisk;
	private String useCpu;
	private String useDisk;
	private String totalMem;
	private String useMem;
	public NodeInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NodeInformation(String host, String totalCpu, String totalDisk, String useCpu, String useDisk,
			String totalMem, String useMem) {
		super();
		this.host = host;
		this.totalCpu = totalCpu;
		this.totalDisk = totalDisk;
		this.useCpu = useCpu;
		this.useDisk = useDisk;
		this.totalMem = totalMem;
		this.useMem = useMem;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTotalCpu() {
		return totalCpu;
	}
	public void setTotalCpu(String totalCpu) {
		this.totalCpu = totalCpu;
	}
	public String getTotalDisk() {
		return totalDisk;
	}
	public void setTotalDisk(String totalDisk) {
		this.totalDisk = totalDisk;
	}
	public String getUseCpu() {
		return useCpu;
	}
	public void setUseCpu(String useCpu) {
		this.useCpu = useCpu;
	}
	public String getUseDisk() {
		return useDisk;
	}
	public void setUseDisk(String useDisk) {
		this.useDisk = useDisk;
	}
	public String getTotalMem() {
		return totalMem;
	}
	public void setTotalMem(String totalMem) {
		this.totalMem = totalMem;
	}
	public String getUseMem() {
		return useMem;
	}
	public void setUseMem(String useMem) {
		this.useMem = useMem;
	}
	@Override
	public String toString() {
		return "NodeInformation [host=" + host + ", totalCpu=" + totalCpu + ", totalDisk=" + totalDisk + ", useCpu="
				+ useCpu + ", useDisk=" + useDisk + ", totalMem=" + totalMem + ", useMem=" + useMem + "]";
	}
	
	
	
	
}
