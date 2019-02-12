package kr.co.bctt.ssh.dto;

/**
 * @FileName : StackServer.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
public class StackServer {
	private String field;
	private String value;
	public StackServer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StackServer(String field, String value) {
		super();
		this.field = field;
		this.value = value;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "StackServer [field=" + field + ", value=" + value + "]";
	}
	
}
