package kr.co.bctt.ssh.dto;

/**
 * @FileName : Ethereum.java
 * @Project : BCTT
 * @Date : 2018. 9. 06.
 * @작성자 : onceagain
 * @변경이력 :
 * @프로그램설명 : 서버에서 받아오는 Ehtereum의 값을 저장하는 Dto
 */
public class Ethereum {
	private int idx;
	private String profile_name;
	private String time_stamp;
	private String description;
	private int node_cnt;
	private int client_cnt;
	private String chainid;
	private String difficulty;
	private String gaslimit;
	private String network_select;
	private String flavor_select;
	private String network_type;
	private String net_select;
	private String flavor_id;
	private String flavor_name;
	private String network_name;
	private String network_id;
	private String vnfd_id;
	private String vnf_id;
	
	private String status;
	
	private String zone_select;
	
	private String zone_select_node_0;
	private String zone_select_node_1;
	private String zone_select_node_2;
	private String zone_select_node_3;
	private String zone_select_node_4;
	private String zone_select_node_5;
	private String zone_select_node_6;
	private String zone_select_node_7;
	
	private String zone_select_client_0;
	private String zone_select_client_1;
	private String zone_select_client_2;
	private String zone_select_client_3;
	private String zone_select_client_4;
	private String zone_select_client_5;
	private String zone_select_client_6;
	private String zone_select_client_7;
	
	private String flavor_select_node_0;
	private String flavor_select_node_1;
	private String flavor_select_node_2;
	private String flavor_select_node_3;
	private String flavor_select_node_4;
	private String flavor_select_node_5;
	private String flavor_select_node_6;
	private String flavor_select_node_7;
	
	private String flavor_select_client_0;
	private String flavor_select_client_1;
	private String flavor_select_client_2;
	private String flavor_select_client_3;
	private String flavor_select_client_4;
	private String flavor_select_client_5;
	private String flavor_select_client_6;
	private String flavor_select_client_7;
	
	private int num_cpus = 0;
	private int mem_size = 0;
	private int disk_size = 0;
	
	private int node_num_cpus_0 = 0;
	private int node_mem_size_0 = 0;
	private int node_disk_size_0 = 0;
	
	private int node_num_cpus_1 = 0;
	private int node_mem_size_1 = 0;
	private int node_disk_size_1 = 0;
	
	private int node_num_cpus_2 = 0;
	private int node_mem_size_2 = 0;
	private int node_disk_size_2 = 0;
	
	private int node_num_cpus_3 = 0;
	private int node_mem_size_3 = 0;
	private int node_disk_size_3 = 0;
	
	private int node_num_cpus_4 = 0;
	private int node_mem_size_4 = 0;
	private int node_disk_size_4 = 0;
	
	private int node_num_cpus_5 = 0;
	private int node_mem_size_5 = 0;
	private int node_disk_size_5 = 0;
	
	private int node_num_cpus_6 = 0;
	private int node_mem_size_6 = 0;
	private int node_disk_size_6 = 0;
	
	private int node_num_cpus_7 = 0;
	private int node_mem_size_7 = 0;
	private int node_disk_size_7 = 0;
	
	private int client_num_cpus_0 = 0;
	private int client_mem_size_0 = 0;
	private int client_disk_size_0 = 0;
	
	private int client_num_cpus_1 = 0;
	private int client_mem_size_1 = 0;
	private int client_disk_size_1 = 0;
	
	private int client_num_cpus_2 = 0;
	private int client_mem_size_2 = 0;
	private int client_disk_size_2 = 0;
	
	private int client_num_cpus_3 = 0;
	private int client_mem_size_3 = 0;
	private int client_disk_size_3 = 0;
	
	private int client_num_cpus_4 = 0;
	private int client_mem_size_4 = 0;
	private int client_disk_size_4 = 0;
	
	private int client_num_cpus_5 = 0;
	private int client_mem_size_5 = 0;
	private int client_disk_size_5 = 0;
	
	private int client_num_cpus_6 = 0;
	private int client_mem_size_6 = 0;
	private int client_disk_size_6 = 0;
	
	private int client_num_cpus_7 = 0;
	private int client_mem_size_7 = 0;
	private int client_disk_size_7 = 0;
	
	public Ethereum() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ethereum(int num_cpus, int mem_size, int disk_size) {
		super();
		this.num_cpus = num_cpus;
		this.mem_size = mem_size;
		this.disk_size = disk_size;
	}
	
	public Ethereum(String vnfd_id, String vnf_id, String profile_name, String description, String network_select, 
			int node_cnt, int client_cnt, 
			int node_num_cpus_0, int node_mem_size_0, int node_disk_size_0, String net_select, String network_name, 
			String chainid, String difficulty, String gaslimit, String flavor_select, String flavor_name, String status) {
		super();
		this.vnfd_id = vnfd_id;
		this.vnf_id = vnf_id;
		this.profile_name = profile_name;
		this.description = description;
		this.network_select = network_select;
		this.node_cnt = node_cnt;
		this.client_cnt = client_cnt;
		this.node_num_cpus_0 = node_num_cpus_0;
		this.node_mem_size_0 = node_mem_size_0;
		this.node_disk_size_0 = node_disk_size_0;
		this.net_select = net_select;
		this.network_name = network_name;
		this.chainid = chainid;
		this.difficulty = difficulty;
		this.gaslimit = gaslimit;
		this.flavor_select = flavor_select;
		this.status = status;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNode_cnt() {
		return node_cnt;
	}

	public void setNode_cnt(int node_cnt) {
		this.node_cnt = node_cnt;
	}

	public String getChainid() {
		return chainid;
	}

	public void setChainid(String chainid) {
		this.chainid = chainid;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getGaslimit() {
		return gaslimit;
	}

	public void setGaslimit(String gaslimit) {
		this.gaslimit = gaslimit;
	}

	public String getFlavor_select() {
		return flavor_select;
	}

	public void setFlavor_select(String flavor_select) {
		this.flavor_select = flavor_select;
	}

	public String getNet_select() {
		return net_select;
	}

	public void setNet_select(String net_select) {
		this.net_select = net_select;
	}

	public int getClient_cnt() {
		return client_cnt;
	}

	public void setClient_cnt(int client_cnt) {
		this.client_cnt = client_cnt;
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

	public String getNetwork_select() {
		return network_select;
	}

	public void setNetwork_select(String network_select) {
		this.network_select = network_select;
	}

	public String getNetwork_type() {
		return network_type;
	}

	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
	}

	public String getFlavor_id() {
		return flavor_id;
	}

	public void setFlavor_id(String flavor_id) {
		this.flavor_id = flavor_id;
	}

	public String getFlavor_name() {
		return flavor_name;
	}

	public void setFlavor_name(String flavor_name) {
		this.flavor_name = flavor_name;
	}

	public String getNetwork_name() {
		return network_name;
	}

	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}

	public String getNetwork_id() {
		return network_id;
	}

	public void setNetwork_id(String network_id) {
		this.network_id = network_id;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getZone_select() {
		return zone_select;
	}

	public void setZone_select(String zone_select) {
		this.zone_select = zone_select;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getVnfd_id() {
		return vnfd_id;
	}

	public void setVnfd_id(String vnfd_id) {
		this.vnfd_id = vnfd_id;
	}

	public String getVnf_id() {
		return vnf_id;
	}

	public void setVnf_id(String vnf_id) {
		this.vnf_id = vnf_id;
	}

	public String getZone_select_node_0() {
		return zone_select_node_0;
	}

	public void setZone_select_node_0(String zone_select_node_0) {
		this.zone_select_node_0 = zone_select_node_0;
	}

	public String getZone_select_node_1() {
		return zone_select_node_1;
	}

	public void setZone_select_node_1(String zone_select_node_1) {
		this.zone_select_node_1 = zone_select_node_1;
	}

	public String getZone_select_node_2() {
		return zone_select_node_2;
	}

	public void setZone_select_node_2(String zone_select_node_2) {
		this.zone_select_node_2 = zone_select_node_2;
	}

	public String getZone_select_node_3() {
		return zone_select_node_3;
	}

	public void setZone_select_node_3(String zone_select_node_3) {
		this.zone_select_node_3 = zone_select_node_3;
	}

	public String getZone_select_node_4() {
		return zone_select_node_4;
	}

	public void setZone_select_node_4(String zone_select_node_4) {
		this.zone_select_node_4 = zone_select_node_4;
	}

	public String getZone_select_node_5() {
		return zone_select_node_5;
	}

	public void setZone_select_node_5(String zone_select_node_5) {
		this.zone_select_node_5 = zone_select_node_5;
	}

	public String getZone_select_node_6() {
		return zone_select_node_6;
	}

	public void setZone_select_node_6(String zone_select_node_6) {
		this.zone_select_node_6 = zone_select_node_6;
	}

	public String getZone_select_node_7() {
		return zone_select_node_7;
	}

	public void setZone_select_node_7(String zone_select_node_7) {
		this.zone_select_node_7 = zone_select_node_7;
	}

	public String getZone_select_client_0() {
		return zone_select_client_0;
	}

	public void setZone_select_client_0(String zone_select_client_0) {
		this.zone_select_client_0 = zone_select_client_0;
	}

	public String getZone_select_client_1() {
		return zone_select_client_1;
	}

	public void setZone_select_client_1(String zone_select_client_1) {
		this.zone_select_client_1 = zone_select_client_1;
	}

	public String getZone_select_client_2() {
		return zone_select_client_2;
	}

	public void setZone_select_client_2(String zone_select_client_2) {
		this.zone_select_client_2 = zone_select_client_2;
	}

	public String getZone_select_client_3() {
		return zone_select_client_3;
	}

	public void setZone_select_client_3(String zone_select_client_3) {
		this.zone_select_client_3 = zone_select_client_3;
	}

	public String getZone_select_client_4() {
		return zone_select_client_4;
	}

	public void setZone_select_client_4(String zone_select_client_4) {
		this.zone_select_client_4 = zone_select_client_4;
	}

	public String getZone_select_client_5() {
		return zone_select_client_5;
	}

	public void setZone_select_client_5(String zone_select_client_5) {
		this.zone_select_client_5 = zone_select_client_5;
	}

	public String getZone_select_client_6() {
		return zone_select_client_6;
	}

	public void setZone_select_client_6(String zone_select_client_6) {
		this.zone_select_client_6 = zone_select_client_6;
	}

	public String getZone_select_client_7() {
		return zone_select_client_7;
	}

	public void setZone_select_client_7(String zone_select_client_7) {
		this.zone_select_client_7 = zone_select_client_7;
	}

	public int getNode_num_cpus_0() {
		return node_num_cpus_0;
	}

	public void setNode_num_cpus_0(int node_num_cpus_0) {
		this.node_num_cpus_0 = node_num_cpus_0;
	}

	public int getNode_mem_size_0() {
		return node_mem_size_0;
	}

	public void setNode_mem_size_0(int node_mem_size_0) {
		this.node_mem_size_0 = node_mem_size_0;
	}

	public int getNode_disk_size_0() {
		return node_disk_size_0;
	}

	public void setNode_disk_size_0(int node_disk_size_0) {
		this.node_disk_size_0 = node_disk_size_0;
	}

	public int getNode_num_cpus_1() {
		return node_num_cpus_1;
	}

	public void setNode_num_cpus_1(int node_num_cpus_1) {
		this.node_num_cpus_1 = node_num_cpus_1;
	}

	public int getNode_mem_size_1() {
		return node_mem_size_1;
	}

	public void setNode_mem_size_1(int node_mem_size_1) {
		this.node_mem_size_1 = node_mem_size_1;
	}

	public int getNode_disk_size_1() {
		return node_disk_size_1;
	}

	public void setNode_disk_size_1(int node_disk_size_1) {
		this.node_disk_size_1 = node_disk_size_1;
	}

	public int getNode_num_cpus_2() {
		return node_num_cpus_2;
	}

	public void setNode_num_cpus_2(int node_num_cpus_2) {
		this.node_num_cpus_2 = node_num_cpus_2;
	}

	public int getNode_mem_size_2() {
		return node_mem_size_2;
	}

	public void setNode_mem_size_2(int node_mem_size_2) {
		this.node_mem_size_2 = node_mem_size_2;
	}

	public int getNode_disk_size_2() {
		return node_disk_size_2;
	}

	public void setNode_disk_size_2(int node_disk_size_2) {
		this.node_disk_size_2 = node_disk_size_2;
	}

	public int getNode_num_cpus_3() {
		return node_num_cpus_3;
	}

	public void setNode_num_cpus_3(int node_num_cpus_3) {
		this.node_num_cpus_3 = node_num_cpus_3;
	}

	public int getNode_mem_size_3() {
		return node_mem_size_3;
	}

	public void setNode_mem_size_3(int node_mem_size_3) {
		this.node_mem_size_3 = node_mem_size_3;
	}

	public int getNode_disk_size_3() {
		return node_disk_size_3;
	}

	public void setNode_disk_size_3(int node_disk_size_3) {
		this.node_disk_size_3 = node_disk_size_3;
	}

	public int getNode_num_cpus_4() {
		return node_num_cpus_4;
	}

	public void setNode_num_cpus_4(int node_num_cpus_4) {
		this.node_num_cpus_4 = node_num_cpus_4;
	}

	public int getNode_mem_size_4() {
		return node_mem_size_4;
	}

	public void setNode_mem_size_4(int node_mem_size_4) {
		this.node_mem_size_4 = node_mem_size_4;
	}

	public int getNode_disk_size_4() {
		return node_disk_size_4;
	}

	public void setNode_disk_size_4(int node_disk_size_4) {
		this.node_disk_size_4 = node_disk_size_4;
	}

	public int getNode_num_cpus_5() {
		return node_num_cpus_5;
	}

	public void setNode_num_cpus_5(int node_num_cpus_5) {
		this.node_num_cpus_5 = node_num_cpus_5;
	}

	public int getNode_mem_size_5() {
		return node_mem_size_5;
	}

	public void setNode_mem_size_5(int node_mem_size_5) {
		this.node_mem_size_5 = node_mem_size_5;
	}

	public int getNode_disk_size_5() {
		return node_disk_size_5;
	}

	public void setNode_disk_size_5(int node_disk_size_5) {
		this.node_disk_size_5 = node_disk_size_5;
	}

	public int getNode_num_cpus_6() {
		return node_num_cpus_6;
	}

	public void setNode_num_cpus_6(int node_num_cpus_6) {
		this.node_num_cpus_6 = node_num_cpus_6;
	}

	public int getNode_mem_size_6() {
		return node_mem_size_6;
	}

	public void setNode_mem_size_6(int node_mem_size_6) {
		this.node_mem_size_6 = node_mem_size_6;
	}

	public int getNode_disk_size_6() {
		return node_disk_size_6;
	}

	public void setNode_disk_size_6(int node_disk_size_6) {
		this.node_disk_size_6 = node_disk_size_6;
	}

	public int getNode_num_cpus_7() {
		return node_num_cpus_7;
	}

	public void setNode_num_cpus_7(int node_num_cpus_7) {
		this.node_num_cpus_7 = node_num_cpus_7;
	}

	public int getNode_mem_size_7() {
		return node_mem_size_7;
	}

	public void setNode_mem_size_7(int node_mem_size_7) {
		this.node_mem_size_7 = node_mem_size_7;
	}

	public int getNode_disk_size_7() {
		return node_disk_size_7;
	}

	public void setNode_disk_size_7(int node_disk_size_7) {
		this.node_disk_size_7 = node_disk_size_7;
	}

	public int getClient_num_cpus_0() {
		return client_num_cpus_0;
	}

	public void setClient_num_cpus_0(int client_num_cpus_0) {
		this.client_num_cpus_0 = client_num_cpus_0;
	}

	public int getClient_mem_size_0() {
		return client_mem_size_0;
	}

	public void setClient_mem_size_0(int client_mem_size_0) {
		this.client_mem_size_0 = client_mem_size_0;
	}

	public int getClient_disk_size_0() {
		return client_disk_size_0;
	}

	public void setClient_disk_size_0(int client_disk_size_0) {
		this.client_disk_size_0 = client_disk_size_0;
	}

	public int getClient_num_cpus_1() {
		return client_num_cpus_1;
	}

	public void setClient_num_cpus_1(int client_num_cpus_1) {
		this.client_num_cpus_1 = client_num_cpus_1;
	}

	public int getClient_mem_size_1() {
		return client_mem_size_1;
	}

	public void setClient_mem_size_1(int client_mem_size_1) {
		this.client_mem_size_1 = client_mem_size_1;
	}

	public int getClient_disk_size_1() {
		return client_disk_size_1;
	}

	public void setClient_disk_size_1(int client_disk_size_1) {
		this.client_disk_size_1 = client_disk_size_1;
	}

	public int getClient_num_cpus_2() {
		return client_num_cpus_2;
	}

	public void setClient_num_cpus_2(int client_num_cpus_2) {
		this.client_num_cpus_2 = client_num_cpus_2;
	}

	public int getClient_mem_size_2() {
		return client_mem_size_2;
	}

	public void setClient_mem_size_2(int client_mem_size_2) {
		this.client_mem_size_2 = client_mem_size_2;
	}

	public int getClient_disk_size_2() {
		return client_disk_size_2;
	}

	public void setClient_disk_size_2(int client_disk_size_2) {
		this.client_disk_size_2 = client_disk_size_2;
	}

	public int getClient_num_cpus_3() {
		return client_num_cpus_3;
	}

	public void setClient_num_cpus_3(int client_num_cpus_3) {
		this.client_num_cpus_3 = client_num_cpus_3;
	}

	public int getClient_mem_size_3() {
		return client_mem_size_3;
	}

	public void setClient_mem_size_3(int client_mem_size_3) {
		this.client_mem_size_3 = client_mem_size_3;
	}

	public int getClient_disk_size_3() {
		return client_disk_size_3;
	}

	public void setClient_disk_size_3(int client_disk_size_3) {
		this.client_disk_size_3 = client_disk_size_3;
	}

	public int getClient_num_cpus_4() {
		return client_num_cpus_4;
	}

	public void setClient_num_cpus_4(int client_num_cpus_4) {
		this.client_num_cpus_4 = client_num_cpus_4;
	}

	public int getClient_mem_size_4() {
		return client_mem_size_4;
	}

	public void setClient_mem_size_4(int client_mem_size_4) {
		this.client_mem_size_4 = client_mem_size_4;
	}

	public int getClient_disk_size_4() {
		return client_disk_size_4;
	}

	public void setClient_disk_size_4(int client_disk_size_4) {
		this.client_disk_size_4 = client_disk_size_4;
	}

	public int getClient_num_cpus_5() {
		return client_num_cpus_5;
	}

	public void setClient_num_cpus_5(int client_num_cpus_5) {
		this.client_num_cpus_5 = client_num_cpus_5;
	}

	public int getClient_mem_size_5() {
		return client_mem_size_5;
	}

	public void setClient_mem_size_5(int client_mem_size_5) {
		this.client_mem_size_5 = client_mem_size_5;
	}

	public int getClient_disk_size_5() {
		return client_disk_size_5;
	}

	public void setClient_disk_size_5(int client_disk_size_5) {
		this.client_disk_size_5 = client_disk_size_5;
	}

	public int getClient_num_cpus_6() {
		return client_num_cpus_6;
	}

	public void setClient_num_cpus_6(int client_num_cpus_6) {
		this.client_num_cpus_6 = client_num_cpus_6;
	}

	public int getClient_mem_size_6() {
		return client_mem_size_6;
	}

	public void setClient_mem_size_6(int client_mem_size_6) {
		this.client_mem_size_6 = client_mem_size_6;
	}

	public int getClient_disk_size_6() {
		return client_disk_size_6;
	}

	public void setClient_disk_size_6(int client_disk_size_6) {
		this.client_disk_size_6 = client_disk_size_6;
	}

	public int getClient_num_cpus_7() {
		return client_num_cpus_7;
	}

	public void setClient_num_cpus_7(int client_num_cpus_7) {
		this.client_num_cpus_7 = client_num_cpus_7;
	}

	public int getClient_mem_size_7() {
		return client_mem_size_7;
	}

	public void setClient_mem_size_7(int client_mem_size_7) {
		this.client_mem_size_7 = client_mem_size_7;
	}

	public int getClient_disk_size_7() {
		return client_disk_size_7;
	}

	public void setClient_disk_size_7(int client_disk_size_7) {
		this.client_disk_size_7 = client_disk_size_7;
	}

	public String getFlavor_select_node_0() {
		return flavor_select_node_0;
	}

	public void setFlavor_select_node_0(String flavor_select_node_0) {
		this.flavor_select_node_0 = flavor_select_node_0;
	}

	public String getFlavor_select_node_1() {
		return flavor_select_node_1;
	}

	public void setFlavor_select_node_1(String flavor_select_node_1) {
		this.flavor_select_node_1 = flavor_select_node_1;
	}

	public String getFlavor_select_node_2() {
		return flavor_select_node_2;
	}

	public void setFlavor_select_node_2(String flavor_select_node_2) {
		this.flavor_select_node_2 = flavor_select_node_2;
	}

	public String getFlavor_select_node_3() {
		return flavor_select_node_3;
	}

	public void setFlavor_select_node_3(String flavor_select_node_3) {
		this.flavor_select_node_3 = flavor_select_node_3;
	}

	public String getFlavor_select_node_4() {
		return flavor_select_node_4;
	}

	public void setFlavor_select_node_4(String flavor_select_node_4) {
		this.flavor_select_node_4 = flavor_select_node_4;
	}

	public String getFlavor_select_node_5() {
		return flavor_select_node_5;
	}

	public void setFlavor_select_node_5(String flavor_select_node_5) {
		this.flavor_select_node_5 = flavor_select_node_5;
	}

	public String getFlavor_select_node_6() {
		return flavor_select_node_6;
	}

	public void setFlavor_select_node_6(String flavor_select_node_6) {
		this.flavor_select_node_6 = flavor_select_node_6;
	}

	public String getFlavor_select_node_7() {
		return flavor_select_node_7;
	}

	public void setFlavor_select_node_7(String flavor_select_node_7) {
		this.flavor_select_node_7 = flavor_select_node_7;
	}

	public String getFlavor_select_client_0() {
		return flavor_select_client_0;
	}

	public void setFlavor_select_client_0(String flavor_select_client_0) {
		this.flavor_select_client_0 = flavor_select_client_0;
	}

	public String getFlavor_select_client_1() {
		return flavor_select_client_1;
	}

	public void setFlavor_select_client_1(String flavor_select_client_1) {
		this.flavor_select_client_1 = flavor_select_client_1;
	}

	public String getFlavor_select_client_2() {
		return flavor_select_client_2;
	}

	public void setFlavor_select_client_2(String flavor_select_client_2) {
		this.flavor_select_client_2 = flavor_select_client_2;
	}

	public String getFlavor_select_client_3() {
		return flavor_select_client_3;
	}

	public void setFlavor_select_client_3(String flavor_select_client_3) {
		this.flavor_select_client_3 = flavor_select_client_3;
	}

	public String getFlavor_select_client_4() {
		return flavor_select_client_4;
	}

	public void setFlavor_select_client_4(String flavor_select_client_4) {
		this.flavor_select_client_4 = flavor_select_client_4;
	}

	public String getFlavor_select_client_5() {
		return flavor_select_client_5;
	}

	public void setFlavor_select_client_5(String flavor_select_client_5) {
		this.flavor_select_client_5 = flavor_select_client_5;
	}

	public String getFlavor_select_client_6() {
		return flavor_select_client_6;
	}

	public void setFlavor_select_client_6(String flavor_select_client_6) {
		this.flavor_select_client_6 = flavor_select_client_6;
	}

	public String getFlavor_select_client_7() {
		return flavor_select_client_7;
	}

	public void setFlavor_select_client_7(String flavor_select_client_7) {
		this.flavor_select_client_7 = flavor_select_client_7;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
