package kr.co.bctt.ssh.dto;

/**
 * @FileName : ProjectInfo.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
public class ProjectInfo {
	private String project_id;
	private String project_name;
	private String description;
	private String domain_id;
	private String enabled;
	
	public ProjectInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProjectInfo(String project_id, String project_name, String description, String domain_id, String enabled) {
		super();
		this.project_id = project_id;
		this.project_name = project_name;
		this.description = description;
		this.domain_id = domain_id;
		this.enabled = enabled;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain_id() {
		return domain_id;
	}

	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}
