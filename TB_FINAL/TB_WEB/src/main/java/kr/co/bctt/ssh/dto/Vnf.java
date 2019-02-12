package kr.co.bctt.ssh.dto;

/**
 * @FileName : Vnf.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
public class Vnf {
	private String id;
	private String name;
	private String description;
	private String mgmt_url;
	private String status;
	public Vnf() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vnf(String id, String name, String description, String mgmt_url, String status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.mgmt_url = mgmt_url;
		this.status = status;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMgmt_url() {
		return mgmt_url;
	}
	public void setMgmt_url(String mgmt_url) {
		this.mgmt_url = mgmt_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Vnf [id=" + id + ", name=" + name + ", description=" + description + ", mgmt_url=" + mgmt_url
				+ ", status=" + status + "]";
	}
	
	

}
