package kr.co.bctt.ssh.dto;

/**
 * @FileName : ImageInfo.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 : 서버에서 받아온 값중 필요한값만 저장하는 dto
 * @프로그램설명 :
 */
public class ImageInfo {
	private String project_name;
	private String id;
	private String name;
	private String format;
	private String size;
	private String os;
	private String description;
	
	private String project_id;
	private String status;
	private String visibility;
	private String _protected;
	
	public ImageInfo() {
		super();
	}
	public ImageInfo(String id, String name, String format, String size, String os, String description, String project_id, String status, String visibility, String _protected) {
		super();
		this.id = id;
		this.name = name;
		this.format = format;
		this.size = size;
		this.os = os;
		this.description = description;
		this.project_id = project_id;
		this.status = status;
		this.visibility = visibility;
		this._protected = _protected;
		
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
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String get_protected() {
		return _protected;
	}
	public void set_protected(String _protected) {
		this._protected = _protected;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	
}
