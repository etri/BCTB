package kr.co.bctt.ssh.dto;

/**
 * @FileName : VnfdInfo.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버에서 받은 vnfd값중 필요한 값만 저장하는 dto
 */
public class VnfdInfo {
	private String id;
	private String name;
	private String description;
	private String infra_drvier;
	private String mgmt_driver;
	public VnfdInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VnfdInfo(String id, String name, String description, String infra_drvier, String mgmt_driver) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.infra_drvier = infra_drvier;
		this.mgmt_driver = mgmt_driver;
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
	public String getInfra_drvier() {
		return infra_drvier;
	}
	public void setInfra_drvier(String infra_drvier) {
		this.infra_drvier = infra_drvier;
	}
	public String getMgmt_driver() {
		return mgmt_driver;
	}
	public void setMgmt_driver(String mgmt_driver) {
		this.mgmt_driver = mgmt_driver;
	}
	@Override
	public String toString() {
		return "VnfdInfo [id=" + id + ", name=" + name + ", description=" + description + ", infra_drvier="
				+ infra_drvier + ", mgmt_driver=" + mgmt_driver + "]";
	}
	

}
