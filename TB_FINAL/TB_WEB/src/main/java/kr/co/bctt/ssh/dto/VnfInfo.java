package kr.co.bctt.ssh.dto;

public class VnfInfo {
	private String vnf_id;
	private String vnf_name;
	private String vnfd_name;
	private String vnfd_id;
	private String status;
	private String description;
	public VnfInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VnfInfo(String vnf_id, String vnf_name, String vnfd_name, String vnfd_id, String status,
			String description) {
		super();
		this.vnf_id = vnf_id;
		this.vnf_name = vnf_name;
		this.vnfd_name = vnfd_name;
		this.vnfd_id = vnfd_id;
		this.status = status;
		this.description = description;
	}
	public String getVnf_id() {
		return vnf_id;
	}
	public void setVnf_id(String vnf_id) {
		this.vnf_id = vnf_id;
	}
	public String getVnf_name() {
		return vnf_name;
	}
	public void setVnf_name(String vnf_name) {
		this.vnf_name = vnf_name;
	}
	public String getVnfd_name() {
		return vnfd_name;
	}
	public void setVnfd_name(String vnfd_name) {
		this.vnfd_name = vnfd_name;
	}
	public String getVnfd_id() {
		return vnfd_id;
	}
	public void setVnfd_id(String vnfd_id) {
		this.vnfd_id = vnfd_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "VnfInfo [vnf_id=" + vnf_id + ", vnf_name=" + vnf_name + ", vnfd_name=" + vnfd_name + ", vnfd_id="
				+ vnfd_id + ", status=" + status + ", description=" + description + "]";
	}
	
	
}
