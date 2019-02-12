package kr.co.bctt.ssh.dto;

import org.json.simple.JSONArray;

/**
 * @FileName : User.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
public class User {
	
	private String id;
	private String name;
	private String mac_address;
	private JSONArray fixed_ips;
	public User(String id, String name, String mac_address, JSONArray fixed_ips) {
		super();
		this.id = id;
		this.name = name;
		this.mac_address = mac_address;
		this.fixed_ips = fixed_ips;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public JSONArray getFixed_ips() {
		return fixed_ips;
	}
	public void setFixed_ips(JSONArray fixed_ips) {
		this.fixed_ips = fixed_ips;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mac_address=" + mac_address + ", fixed_ips=" + fixed_ips + "]";
	}
	

}
