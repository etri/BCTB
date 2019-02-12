package kr.co.bctt.ssh.dto;

/**
 * @FileName : Image.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 : 서버에서 받아오는 image dto
 * @프로그램설명 :
 */
public class Image {
	private String id;
	private String name;
	private String subnets;
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Image(String id, String name, String subnets) {
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
		return "Image [id=" + id + ", name=" + name + ", subnets=" + subnets + "]";
	}
	
}
