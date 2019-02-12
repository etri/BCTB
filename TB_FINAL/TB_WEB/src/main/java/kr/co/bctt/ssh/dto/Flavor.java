package kr.co.bctt.ssh.dto;

/**
 * @FileName : Flavor.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : Flavor를 받아오는 Dto
 */
public class Flavor {
	private String id;
	private String name;
	private String ram;
	private String disk;
	private String ephemeral;
	private String vcpus;
	public Flavor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Flavor(String id, String name, String ram, String disk, String ephemeral, String vcpus) {
		super();
		this.id = id;
		this.name = name;
		this.ram = ram;
		this.disk = disk;
		this.ephemeral = ephemeral;
		this.vcpus = vcpus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getDisk() {
		return disk;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}
	public String getEphemeral() {
		return ephemeral;
	}
	public void setEphemeral(String ephemeral) {
		this.ephemeral = ephemeral;
	}
	public String getVcpus() {
		return vcpus;
	}
	public void setVcpus(String vcpus) {
		this.vcpus = vcpus;
	}
	@Override
	public String toString() {
		return "Flavor [id=" + id + ", name=" + name + ", ram=" + ram + ", disk=" + disk + ", ephemeral=" + ephemeral
				+ ", vcpus=" + vcpus + "]";
	}
	
	
	
}
