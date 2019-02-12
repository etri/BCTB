package kr.co.bctt.ssh.dto;

/**
 * @FileName : Vnfd.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버에서 받아오는 vnfd의 값을 저장하는 Dto
 */
public class Vnfd {
	private String vnfd_id;
	private String vnfd_name;
	private String image_name;
	private String image_id;
	private String flavor_name;
	private String package_name;
	private String package_pathname;
	private String description;
	private String vnfd;
	public Vnfd() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vnfd(String vnfd_id, String vnfd_name, String image_name,  String flavor_name, String package_name,
			String package_pathname, String description, String vnfd, String image_id) {
		super();
		this.vnfd_id = vnfd_id;
		this.vnfd_name = vnfd_name;
		this.image_name = image_name;
		this.flavor_name = flavor_name;
		this.package_name = package_name;
		this.package_pathname = package_pathname;
		this.description = description;
		this.vnfd = vnfd;
		this.image_id = image_id;
	}
	
	
	public Vnfd(String vnfd_id, String vnfd_name, String description) {
		super();
		this.vnfd_id = vnfd_id;
		this.vnfd_name = vnfd_name;
		this.description = description;
	}
	
	
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public String getVnfd_id() {
		return vnfd_id;
	}
	public void setVnfd_id(String vnfd_id) {
		this.vnfd_id = vnfd_id;
	}
	public String getVnfd_name() {
		return vnfd_name;
	}
	public void setVnfd_name(String vnfd_name) {
		this.vnfd_name = vnfd_name;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getFlavor_name() {
		return flavor_name;
	}
	public void setFlavor_name(String flavor_name) {
		this.flavor_name = flavor_name;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getPackage_pathname() {
		return package_pathname;
	}
	public void setPackage_pathname(String package_pathname) {
		this.package_pathname = package_pathname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVnfd() {
		return vnfd;
	}
	public void setVnfd(String vnfd) {
		this.vnfd = vnfd;
	}
	@Override
	public String toString() {
		return "Vnfd [vnfd_id=" + vnfd_id + ", vnfd_name=" + vnfd_name + ", image_name=" + image_name + ", flavor_name="
				+ flavor_name + ", package_name=" + package_name + ", package_pathname=" + package_pathname + ", description="
				+ description + ", vnfd=" + vnfd + "]";
	}
	
	
}
