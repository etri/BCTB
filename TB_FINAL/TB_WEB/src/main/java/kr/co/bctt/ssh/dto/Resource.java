package kr.co.bctt.ssh.dto;

/**
 * @FileName : Ethereum.java
 * @Project : BCTT
 * @Date : 2018. 9. 06.
 * @작성자 : onceagain
 * @변경이력 :
 * @프로그램설명 : 서버에서 받아오는 Ehtereum의 값을 저장하는 Dto
 */
public class Resource {
	
	private int num_cpus = 0;
	private int mem_size = 0;
	private int disk_size = 0;
	
	public Resource(int num_cpus, int mem_size, int disk_size) {
		super();
		this.num_cpus = num_cpus;
		this.mem_size = mem_size;
		this.disk_size = disk_size;
	}

	public int getNum_cpus() {
		return num_cpus;
	}

	public void setNum_cpus(int num_cpus) {
		this.num_cpus = num_cpus;
	}

	public int getMem_size() {
		return mem_size;
	}

	public void setMem_size(int mem_size) {
		this.mem_size = mem_size;
	}

	public int getDisk_size() {
		return disk_size;
	}

	public void setDisk_size(int disk_size) {
		this.disk_size = disk_size;
	}
	
}
