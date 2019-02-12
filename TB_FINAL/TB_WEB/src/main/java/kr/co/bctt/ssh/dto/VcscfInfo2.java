package kr.co.bctt.ssh.dto;

/**
 * @FileName : VcscfInfo2.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버에서 받아온 vcscfinfo의 값을 저장하는 Dto
 */
public class VcscfInfo2 {
	private String description;
	private String id;
	private String status;
	private String flavor;
	private String image;
	private String create_time;
	private String update_time;
	private String mgmtIp;
	private String privateIp;
	private String mac;
	public VcscfInfo2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VcscfInfo2(String description, String id, String status, String flavor, String image, String create_time,
			String update_time, String mgmtIp, String privateIp, String mac) {
		super();
		this.description = description;
		this.id = id;
		this.status = status;
		this.flavor = flavor;
		this.image = image;
		this.create_time = create_time;
		this.update_time = update_time;
		this.mgmtIp = mgmtIp;
		this.privateIp = privateIp;
		this.mac = mac;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getMgmtIp() {
		return mgmtIp;
	}
	public void setMgmtIp(String mgmtIp) {
		this.mgmtIp = mgmtIp;
	}
	public String getPrivateIp() {
		return privateIp;
	}
	public void setPrivateIp(String privateIp) {
		this.privateIp = privateIp;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@Override
	public String toString() {
		return "VcscfInfo2 [description=" + description + ", id=" + id + ", status=" + status + ", flavor=" + flavor
				+ ", image=" + image + ", create_time=" + create_time + ", update_time=" + update_time + ", mgmtIp="
				+ mgmtIp + ", privateIp=" + privateIp + ", mac=" + mac + "]";
	}
	
	
	
	
	
}
