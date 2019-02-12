package kr.co.bctt.ssh.dto;

public class NetList { // cmd 임시 저장 바구니
	String id;
	String name;
	String subnets;
	public NetList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NetList(String id, String name, String subnets) {
		super();
		this.id = id;
		this.name = name;
		this.subnets = subnets;
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
	public String getSubnets() {
		return subnets;
	}
	public void setSubnets(String subnets) {
		this.subnets = subnets;
	}
	@Override
	public String toString() {
		return "NetList [id=" + id + ", name=" + name + ", subnets=" + subnets + "]";
	}
	
	
	
}
