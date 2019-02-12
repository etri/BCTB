package kr.co.bctt.ssh.dto;

/**
 * @FileName : HStack.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
public class HStack {
	private String id;
	private String stack_name;
	private String stack_status;
	private String creation_time;
	private String updated_time;
	
	public HStack() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HStack(String id, String stack_name, String stack_status, String creation_time, String updated_time) {
		super();
		this.id = id;
		this.stack_name = stack_name;
		this.stack_status = stack_status;
		this.creation_time = creation_time;
		this.updated_time = updated_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStack_name() {
		return stack_name;
	}
	public void setStack_name(String stack_name) {
		this.stack_name = stack_name;
	}
	public String getStack_status() {
		return stack_status;
	}
	public void setStack_status(String stack_status) {
		this.stack_status = stack_status;
	}
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
	public String getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}
	
	@Override
	public String toString() {
		return "HStack [id=" + id + ", stack_name=" + stack_name + ", stack_status=" + stack_status + ", creation_time="
				+ creation_time + ", updated_time=" + updated_time + "]";
	}
	
	
}
