package kr.co.bctt.ssh.dto;

/**
 * @FileName : HResource.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
public class HResource {
	private String resource_name;
	private String physical_resource_id;
	private String resource_type;
	private String resource_status;
	private String updated_time;
	public HResource() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HResource(String resource_name, String physical_resource_id, String resource_type, String resource_status,
			String updated_time) {
		super();
		this.resource_name = resource_name;
		this.physical_resource_id = physical_resource_id;
		this.resource_type = resource_type;
		this.resource_status = resource_status;
		this.updated_time = updated_time;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getPhysical_resource_id() {
		return physical_resource_id;
	}
	public void setPhysical_resource_id(String physical_resource_id) {
		this.physical_resource_id = physical_resource_id;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getResource_status() {
		return resource_status;
	}
	public void setResource_status(String resource_status) {
		this.resource_status = resource_status;
	}
	public String getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}
	@Override
	public String toString() {
		return "HResource [resource_name=" + resource_name + ", physical_resource_id=" + physical_resource_id
				+ ", resource_type=" + resource_type + ", resource_status=" + resource_status + ", updated_time="
				+ updated_time + "]";
	}
	
	

}
