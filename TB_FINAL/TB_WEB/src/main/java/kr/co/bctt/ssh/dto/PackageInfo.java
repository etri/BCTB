package kr.co.bctt.ssh.dto;

/**
 * @FileName : PackageInfo.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : package에서 필요한 값만 저장하는 Dto
 */
public class PackageInfo {
	private String package_name;
	private String create_date;
	private String pathName;
	private String desc;
	public PackageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PackageInfo(String package_name, String create_date, String pathName, String desc) {
		super();
		this.package_name = package_name;
		this.create_date = create_date;
		this.pathName = pathName;
		this.desc = desc;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Package [package_name=" + package_name + ", create_date=" + create_date + ", pathName=" + pathName
				+ ", desc=" + desc + "]";
	}
	
	

}
