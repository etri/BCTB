package kr.co.bctt.ssh.dto;

/**
 * @FileName : FlavorInfo.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버에서 받아온 flavor 리스트를 수정하여 필요한 값만 넣은 Dto
 */
public class FlavorInfo {
	private String id;
	private String name;
	private String vcpus;
	private String ram;
	private String root_disk;
	private String ephemeral_disk;
	private String swap_disk;
	private String rxtx_factor;
	private String is_public;
	private String disabled;
	
	public FlavorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FlavorInfo(String id, String name, String vcpus, String ram, String root_disk, String ephemeral_disk, String swap_disk, String rxtx_factor, String is_public, String disabled) {
		super();
		this.id = id;
		this.name = name;
		this.vcpus = vcpus;
		this.ram = ram;
		this.root_disk = root_disk;
		this.ephemeral_disk = ephemeral_disk;
		this.swap_disk = swap_disk;
		this.rxtx_factor = rxtx_factor;
		this.is_public = is_public;
		this.disabled = disabled;
	}
	
	@Override
	public String toString() {
		return "FlavorInfo [id=" + id + ", name=" + name + ", vcpus=" + vcpus + ", ram=" + ram + ", root_disk="
				+ root_disk + ", ephemeral_disk=" + ephemeral_disk + ", swap_disk=" + swap_disk + ", rxtx_factor="
				+ rxtx_factor + ", is_public=" + is_public + ", disabled=" + disabled + "]";
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

	public String getVcpus() {
		return vcpus;
	}

	public void setVcpus(String vcpus) {
		this.vcpus = vcpus;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getRoot_disk() {
		return root_disk;
	}

	public void setRoot_disk(String root_disk) {
		this.root_disk = root_disk;
	}

	public String getEphemeral_disk() {
		return ephemeral_disk;
	}

	public void setEphemeral_disk(String ephemeral_disk) {
		this.ephemeral_disk = ephemeral_disk;
	}

	public String getSwap_disk() {
		return swap_disk;
	}

	public void setSwap_disk(String swap_disk) {
		this.swap_disk = swap_disk;
	}

	public String getRxtx_factor() {
		return rxtx_factor;
	}

	public void setRxtx_factor(String rxtx_factor) {
		this.rxtx_factor = rxtx_factor;
	}

	public String getIs_public() {
		return is_public;
	}

	public void setIs_public(String is_public) {
		this.is_public = is_public;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
}
