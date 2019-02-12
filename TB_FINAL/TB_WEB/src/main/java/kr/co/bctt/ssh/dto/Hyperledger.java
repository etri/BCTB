package kr.co.bctt.ssh.dto;

/**
 * @FileName : Hyperledger.java
 * @Project : BCTT
 * @Date : 2018. 9. 06.
 * @작성자 : onceagain
 * @변경이력 :
 * @프로그램설명 : 서버에서 받아오는 Hyperledger의 값을 저장하는 Dto
 */
public class Hyperledger {
	
	private int idx;
	private int profile_id;
	private String profile_name;
	private String time_stamp;
	private String description;
	private int orderer_cnt;
	private int peer_org_cnt;
	private int org_peer_cnt;
	private String orderer_type;
	private int batch_timeout;
	private int max_message_cnt;
	private int absolute_max_bytes;
	private int preferred_max_bytes;
	
	private String network_select;
	private String network_id;
	private String network_name;
	private String flavor_select;
	private String flavor_id;
	private String flavor_name;
	private String vnfd_id;
	private String vnf_id;
	private String status;
	
	private String zone_select;
	
	private String zone_select_adminordererorg0;
	private String zone_select_orderer_0;
	private String zone_select_orderer_1;
	private String zone_select_orderer_2;
	private String zone_select_orderer_3;
	private String zone_select_kafka;
	private String zone_select_bcmanager;
	private String zone_select_peer_0;
	private String zone_select_peer_1;
	private String zone_select_peer_2;
	private String zone_select_peer_3;
	private String zone_select_peer_4;
	private String zone_select_peer_5;
	private String zone_select_peer_6;
	private String zone_select_peer_7;
	private String zone_select_peer_8;
	private String zone_select_peer_9;
	private String zone_select_peer_10;
	private String zone_select_peer_11;
	private String zone_select_peer_12;
	private String zone_select_peer_13;
	private String zone_select_peer_14;
	private String zone_select_peer_15;
	private String zone_select_adminorg_0;
	private String zone_select_adminorg_1;
	private String zone_select_adminorg_2;
	private String zone_select_adminorg_3;
	
	private String flavor_select_adminordererorg0;
	private String flavor_select_orderer_0;
	private String flavor_select_orderer_1;
	private String flavor_select_orderer_2;
	private String flavor_select_orderer_3;
	private String flavor_select_peer_0;
	private String flavor_select_peer_1;
	private String flavor_select_peer_2;
	private String flavor_select_peer_3;
	private String flavor_select_peer_4;
	private String flavor_select_peer_5;
	private String flavor_select_peer_6;
	private String flavor_select_peer_7;
	private String flavor_select_peer_8;
	private String flavor_select_peer_9;
	private String flavor_select_peer_10;
	private String flavor_select_peer_11;
	private String flavor_select_peer_12;
	private String flavor_select_peer_13;
	private String flavor_select_peer_14;
	private String flavor_select_peer_15;
	private String flavor_select_adminorg_0;
	private String flavor_select_adminorg_1;
	private String flavor_select_adminorg_2;
	private String flavor_select_adminorg_3;
	private String flavor_select_kafka;
	private String flavor_select_bcmanager;
	
	private int num_cpus = 0;
	private int mem_size = 0;
	private int disk_size = 0;
	
	private String adminordererorg0_num_cpus = "";
	private String orderer_num_cpus_0 = "";
	private String orderer_num_cpus_1 = "";
	private String orderer_num_cpus_2 = "";
	private String orderer_num_cpus_3 = "";
	private String kafka_num_cpus = "";
	private String bcmanager_num_cpus = "";
	private String peer_num_cpus_0 = "";
	private String peer_num_cpus_1 = "";
	private String peer_num_cpus_2 = "";
	private String peer_num_cpus_3 = "";
	private String peer_num_cpus_4 = "";
	private String peer_num_cpus_5 = "";
	private String peer_num_cpus_6 = "";
	private String peer_num_cpus_7 = "";
	private String peer_num_cpus_8 = "";
	private String peer_num_cpus_9 = "";
	private String peer_num_cpus_10 = "";
	private String peer_num_cpus_11 = "";
	private String peer_num_cpus_12 = "";
	private String peer_num_cpus_13 = "";
	private String peer_num_cpus_14 = "";
	private String peer_num_cpus_15 = "";
	private String adminorg_num_cpus_0 = "";
	private String adminorg_num_cpus_1 = "";
	private String adminorg_num_cpus_2 = "";
	private String adminorg_num_cpus_3 = "";
	
	private String adminordererorg0_mem_size = "";
	private String orderer_mem_size_0 = "";
	private String orderer_mem_size_1 = "";
	private String orderer_mem_size_2 = "";
	private String orderer_mem_size_3 = "";
	private String kafka_mem_size = "";
	private String bcmanager_mem_size = "";
	private String peer_mem_size_0 = "";
	private String peer_mem_size_1 = "";
	private String peer_mem_size_2 = "";
	private String peer_mem_size_3 = "";
	private String peer_mem_size_4 = "";
	private String peer_mem_size_5 = "";
	private String peer_mem_size_6 = "";
	private String peer_mem_size_7 = "";
	private String peer_mem_size_8 = "";
	private String peer_mem_size_9 = "";
	private String peer_mem_size_10 = "";
	private String peer_mem_size_11 = "";
	private String peer_mem_size_12 = "";
	private String peer_mem_size_13 = "";
	private String peer_mem_size_14 = "";
	private String peer_mem_size_15 = "";
	private String adminorg_mem_size_0 = "";
	private String adminorg_mem_size_1 = "";
	private String adminorg_mem_size_2 = "";
	private String adminorg_mem_size_3 = "";

	private String adminordererorg0_disk_size = "";
	private String orderer_disk_size_0 = "";
	private String orderer_disk_size_1 = "";
	private String orderer_disk_size_2 = "";
	private String orderer_disk_size_3 = "";
	private String kafka_disk_size = "";
	private String bcmanager_disk_size = "";
	private String peer_disk_size_0 = "";
	private String peer_disk_size_1 = "";
	private String peer_disk_size_2 = "";
	private String peer_disk_size_3 = "";
	private String peer_disk_size_4 = "";
	private String peer_disk_size_5 = "";
	private String peer_disk_size_6 = "";
	private String peer_disk_size_7 = "";
	private String peer_disk_size_8 = "";
	private String peer_disk_size_9 = "";
	private String peer_disk_size_10 = "";
	private String peer_disk_size_11 = "";
	private String peer_disk_size_12 = "";
	private String peer_disk_size_13 = "";
	private String peer_disk_size_14 = "";
	private String peer_disk_size_15 = "";
	private String adminorg_disk_size_0 = "";
	private String adminorg_disk_size_1 = "";
	private String adminorg_disk_size_2 = "";
	private String adminorg_disk_size_3 = "";
	
	public Hyperledger() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Hyperledger(String profile_name, String description, 
			int orderer_cnt, int peer_org_cnt, 
			int org_peer_cnt, String orderer_type, int batch_timeout, int max_message_cnt, int absolute_max_bytes, 
			int preferred_max_bytes, String network_name, String flavor_name, int num_cpus, int mem_size, int disk_size, String availability_zone) {
		super();
		this.profile_name = profile_name;
		this.description = description;
		this.orderer_cnt = orderer_cnt;
		this.peer_org_cnt = peer_org_cnt;
		this.org_peer_cnt = org_peer_cnt;
		this.orderer_type = orderer_type;
		this.batch_timeout = batch_timeout;
		this.max_message_cnt = max_message_cnt;
		this.absolute_max_bytes = absolute_max_bytes;
		this.preferred_max_bytes = preferred_max_bytes;
		this.network_name = network_name;
		this.flavor_name = flavor_name;
		this.num_cpus = num_cpus;
		this.mem_size = mem_size;
		this.disk_size = disk_size;
		this.zone_select = availability_zone;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrderer_cnt() {
		return orderer_cnt;
	}

	public void setOrderer_cnt(int orderer_cnt) {
		this.orderer_cnt = orderer_cnt;
	}

	public int getPeer_org_cnt() {
		return peer_org_cnt;
	}

	public void setPeer_org_cnt(int peer_org_cnt) {
		this.peer_org_cnt = peer_org_cnt;
	}

	public int getOrg_peer_cnt() {
		return org_peer_cnt;
	}

	public void setOrg_peer_cnt(int org_peer_cnt) {
		this.org_peer_cnt = org_peer_cnt;
	}

	public String getOrderer_type() {
		return orderer_type;
	}

	public void setOrderer_type(String orderer_type) {
		this.orderer_type = orderer_type;
	}

	public int getBatch_timeout() {
		return batch_timeout;
	}

	public void setBatch_timeout(int batch_timeout) {
		this.batch_timeout = batch_timeout;
	}

	public int getMax_message_cnt() {
		return max_message_cnt;
	}

	public void setMax_message_cnt(int max_message_cnt) {
		this.max_message_cnt = max_message_cnt;
	}

	public int getAbsolute_max_bytes() {
		return absolute_max_bytes;
	}

	public void setAbsolute_max_bytes(int absolute_max_bytes) {
		this.absolute_max_bytes = absolute_max_bytes;
	}

	public int getPreferred_max_bytes() {
		return preferred_max_bytes;
	}

	public void setPreferred_max_bytes(int preferred_max_bytes) {
		this.preferred_max_bytes = preferred_max_bytes;
	}

	public String getNetwork_id() {
		return network_id;
	}

	public void setNetwork_id(String network_id) {
		this.network_id = network_id;
	}

	public String getNetwork_name() {
		return network_name;
	}

	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
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

	public String getNetwork_select() {
		return network_select;
	}

	public void setNetwork_select(String network_select) {
		this.network_select = network_select;
	}

	public String getFlavor_select() {
		return flavor_select;
	}

	public void setFlavor_select(String flavor_select) {
		this.flavor_select = flavor_select;
	}

	public String getZone_select() {
		return zone_select;
	}

	public void setZone_select(String zone_select) {
		this.zone_select = zone_select;
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

	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}


	public String getFlavor_select_adminordererorg0() {
		return flavor_select_adminordererorg0;
	}

	public void setFlavor_select_adminordererorg0(String flavor_select_adminordererorg0) {
		this.flavor_select_adminordererorg0 = flavor_select_adminordererorg0;
	}

	public String getFlavor_select_orderer_0() {
		return flavor_select_orderer_0;
	}

	public void setFlavor_select_orderer_0(String flavor_select_orderer_0) {
		this.flavor_select_orderer_0 = flavor_select_orderer_0;
	}

	public String getFlavor_select_orderer_1() {
		return flavor_select_orderer_1;
	}

	public void setFlavor_select_orderer_1(String flavor_select_orderer_1) {
		this.flavor_select_orderer_1 = flavor_select_orderer_1;
	}

	public String getFlavor_select_orderer_2() {
		return flavor_select_orderer_2;
	}

	public void setFlavor_select_orderer_2(String flavor_select_orderer_2) {
		this.flavor_select_orderer_2 = flavor_select_orderer_2;
	}

	public String getFlavor_select_orderer_3() {
		return flavor_select_orderer_3;
	}

	public void setFlavor_select_orderer_3(String flavor_select_orderer_3) {
		this.flavor_select_orderer_3 = flavor_select_orderer_3;
	}

	public String getFlavor_select_peer_0() {
		return flavor_select_peer_0;
	}

	public void setFlavor_select_peer_0(String flavor_select_peer_0) {
		this.flavor_select_peer_0 = flavor_select_peer_0;
	}

	public String getFlavor_select_peer_1() {
		return flavor_select_peer_1;
	}

	public void setFlavor_select_peer_1(String flavor_select_peer_1) {
		this.flavor_select_peer_1 = flavor_select_peer_1;
	}

	public String getFlavor_select_peer_2() {
		return flavor_select_peer_2;
	}

	public void setFlavor_select_peer_2(String flavor_select_peer_2) {
		this.flavor_select_peer_2 = flavor_select_peer_2;
	}

	public String getFlavor_select_peer_3() {
		return flavor_select_peer_3;
	}

	public void setFlavor_select_peer_3(String flavor_select_peer_3) {
		this.flavor_select_peer_3 = flavor_select_peer_3;
	}

	public String getFlavor_select_peer_4() {
		return flavor_select_peer_4;
	}

	public void setFlavor_select_peer_4(String flavor_select_peer_4) {
		this.flavor_select_peer_4 = flavor_select_peer_4;
	}

	public String getFlavor_select_peer_5() {
		return flavor_select_peer_5;
	}

	public void setFlavor_select_peer_5(String flavor_select_peer_5) {
		this.flavor_select_peer_5 = flavor_select_peer_5;
	}

	public String getFlavor_select_peer_6() {
		return flavor_select_peer_6;
	}

	public void setFlavor_select_peer_6(String flavor_select_peer_6) {
		this.flavor_select_peer_6 = flavor_select_peer_6;
	}

	public String getFlavor_select_peer_7() {
		return flavor_select_peer_7;
	}

	public void setFlavor_select_peer_7(String flavor_select_peer_7) {
		this.flavor_select_peer_7 = flavor_select_peer_7;
	}

	public String getFlavor_select_peer_8() {
		return flavor_select_peer_8;
	}

	public void setFlavor_select_peer_8(String flavor_select_peer_8) {
		this.flavor_select_peer_8 = flavor_select_peer_8;
	}

	public String getFlavor_select_peer_9() {
		return flavor_select_peer_9;
	}

	public void setFlavor_select_peer_9(String flavor_select_peer_9) {
		this.flavor_select_peer_9 = flavor_select_peer_9;
	}

	public String getFlavor_select_peer_10() {
		return flavor_select_peer_10;
	}

	public void setFlavor_select_peer_10(String flavor_select_peer_10) {
		this.flavor_select_peer_10 = flavor_select_peer_10;
	}

	public String getFlavor_select_peer_11() {
		return flavor_select_peer_11;
	}

	public void setFlavor_select_peer_11(String flavor_select_peer_11) {
		this.flavor_select_peer_11 = flavor_select_peer_11;
	}

	public String getFlavor_select_peer_12() {
		return flavor_select_peer_12;
	}

	public void setFlavor_select_peer_12(String flavor_select_peer_12) {
		this.flavor_select_peer_12 = flavor_select_peer_12;
	}

	public String getFlavor_select_peer_13() {
		return flavor_select_peer_13;
	}

	public void setFlavor_select_peer_13(String flavor_select_peer_13) {
		this.flavor_select_peer_13 = flavor_select_peer_13;
	}

	public String getFlavor_select_peer_14() {
		return flavor_select_peer_14;
	}

	public void setFlavor_select_peer_14(String flavor_select_peer_14) {
		this.flavor_select_peer_14 = flavor_select_peer_14;
	}

	public String getFlavor_select_peer_15() {
		return flavor_select_peer_15;
	}

	public void setFlavor_select_peer_15(String flavor_select_peer_15) {
		this.flavor_select_peer_15 = flavor_select_peer_15;
	}

	public String getFlavor_select_adminorg_0() {
		return flavor_select_adminorg_0;
	}

	public void setFlavor_select_adminorg_0(String flavor_select_adminorg_0) {
		this.flavor_select_adminorg_0 = flavor_select_adminorg_0;
	}

	public String getFlavor_select_adminorg_1() {
		return flavor_select_adminorg_1;
	}

	public void setFlavor_select_adminorg_1(String flavor_select_adminorg_1) {
		this.flavor_select_adminorg_1 = flavor_select_adminorg_1;
	}

	public String getFlavor_select_adminorg_2() {
		return flavor_select_adminorg_2;
	}

	public void setFlavor_select_adminorg_2(String flavor_select_adminorg_2) {
		this.flavor_select_adminorg_2 = flavor_select_adminorg_2;
	}

	public String getFlavor_select_adminorg_3() {
		return flavor_select_adminorg_3;
	}

	public void setFlavor_select_adminorg_3(String flavor_select_adminorg_3) {
		this.flavor_select_adminorg_3 = flavor_select_adminorg_3;
	}

	public String getFlavor_select_kafka() {
		return flavor_select_kafka;
	}

	public void setFlavor_select_kafka(String flavor_select_kafka) {
		this.flavor_select_kafka = flavor_select_kafka;
	}

	public String getFlavor_select_bcmanager() {
		return flavor_select_bcmanager;
	}

	public void setFlavor_select_bcmanager(String flavor_select_bcmanager) {
		this.flavor_select_bcmanager = flavor_select_bcmanager;
	}

	public String getAdminordererorg0_num_cpus() {
		return adminordererorg0_num_cpus;
	}

	public void setAdminordererorg0_num_cpus(String adminordererorg0_num_cpus) {
		this.adminordererorg0_num_cpus = adminordererorg0_num_cpus;
	}

	public String getOrderer_num_cpus_0() {
		return orderer_num_cpus_0;
	}

	public void setOrderer_num_cpus_0(String orderer_num_cpus_0) {
		this.orderer_num_cpus_0 = orderer_num_cpus_0;
	}

	public String getOrderer_num_cpus_1() {
		return orderer_num_cpus_1;
	}

	public void setOrderer_num_cpus_1(String orderer_num_cpus_1) {
		this.orderer_num_cpus_1 = orderer_num_cpus_1;
	}

	public String getOrderer_num_cpus_2() {
		return orderer_num_cpus_2;
	}

	public void setOrderer_num_cpus_2(String orderer_num_cpus_2) {
		this.orderer_num_cpus_2 = orderer_num_cpus_2;
	}

	public String getOrderer_num_cpus_3() {
		return orderer_num_cpus_3;
	}

	public void setOrderer_num_cpus_3(String orderer_num_cpus_3) {
		this.orderer_num_cpus_3 = orderer_num_cpus_3;
	}

	public String getKafka_num_cpus() {
		return kafka_num_cpus;
	}

	public void setKafka_num_cpus(String kafka_num_cpus) {
		this.kafka_num_cpus = kafka_num_cpus;
	}

	public String getPeer_num_cpus_0() {
		return peer_num_cpus_0;
	}

	public void setPeer_num_cpus_0(String peer_num_cpus_0) {
		this.peer_num_cpus_0 = peer_num_cpus_0;
	}

	public String getPeer_num_cpus_1() {
		return peer_num_cpus_1;
	}

	public void setPeer_num_cpus_1(String peer_num_cpus_1) {
		this.peer_num_cpus_1 = peer_num_cpus_1;
	}

	public String getPeer_num_cpus_2() {
		return peer_num_cpus_2;
	}

	public void setPeer_num_cpus_2(String peer_num_cpus_2) {
		this.peer_num_cpus_2 = peer_num_cpus_2;
	}

	public String getPeer_num_cpus_3() {
		return peer_num_cpus_3;
	}

	public void setPeer_num_cpus_3(String peer_num_cpus_3) {
		this.peer_num_cpus_3 = peer_num_cpus_3;
	}

	public String getPeer_num_cpus_4() {
		return peer_num_cpus_4;
	}

	public void setPeer_num_cpus_4(String peer_num_cpus_4) {
		this.peer_num_cpus_4 = peer_num_cpus_4;
	}

	public String getPeer_num_cpus_5() {
		return peer_num_cpus_5;
	}

	public void setPeer_num_cpus_5(String peer_num_cpus_5) {
		this.peer_num_cpus_5 = peer_num_cpus_5;
	}

	public String getPeer_num_cpus_6() {
		return peer_num_cpus_6;
	}

	public void setPeer_num_cpus_6(String peer_num_cpus_6) {
		this.peer_num_cpus_6 = peer_num_cpus_6;
	}

	public String getPeer_num_cpus_7() {
		return peer_num_cpus_7;
	}

	public void setPeer_num_cpus_7(String peer_num_cpus_7) {
		this.peer_num_cpus_7 = peer_num_cpus_7;
	}

	public String getPeer_num_cpus_8() {
		return peer_num_cpus_8;
	}

	public void setPeer_num_cpus_8(String peer_num_cpus_8) {
		this.peer_num_cpus_8 = peer_num_cpus_8;
	}

	public String getPeer_num_cpus_9() {
		return peer_num_cpus_9;
	}

	public void setPeer_num_cpus_9(String peer_num_cpus_9) {
		this.peer_num_cpus_9 = peer_num_cpus_9;
	}

	public String getPeer_num_cpus_10() {
		return peer_num_cpus_10;
	}

	public void setPeer_num_cpus_10(String peer_num_cpus_10) {
		this.peer_num_cpus_10 = peer_num_cpus_10;
	}

	public String getPeer_num_cpus_11() {
		return peer_num_cpus_11;
	}

	public void setPeer_num_cpus_11(String peer_num_cpus_11) {
		this.peer_num_cpus_11 = peer_num_cpus_11;
	}

	public String getPeer_num_cpus_12() {
		return peer_num_cpus_12;
	}

	public void setPeer_num_cpus_12(String peer_num_cpus_12) {
		this.peer_num_cpus_12 = peer_num_cpus_12;
	}

	public String getPeer_num_cpus_13() {
		return peer_num_cpus_13;
	}

	public void setPeer_num_cpus_13(String peer_num_cpus_13) {
		this.peer_num_cpus_13 = peer_num_cpus_13;
	}

	public String getPeer_num_cpus_14() {
		return peer_num_cpus_14;
	}

	public void setPeer_num_cpus_14(String peer_num_cpus_14) {
		this.peer_num_cpus_14 = peer_num_cpus_14;
	}

	public String getPeer_num_cpus_15() {
		return peer_num_cpus_15;
	}

	public void setPeer_num_cpus_15(String peer_num_cpus_15) {
		this.peer_num_cpus_15 = peer_num_cpus_15;
	}

	public String getAdminordererorg0_mem_size() {
		return adminordererorg0_mem_size;
	}

	public void setAdminordererorg0_mem_size(String adminordererorg0_mem_size) {
		this.adminordererorg0_mem_size = adminordererorg0_mem_size;
	}

	public String getOrderer_mem_size_0() {
		return orderer_mem_size_0;
	}

	public void setOrderer_mem_size_0(String orderer_mem_size_0) {
		this.orderer_mem_size_0 = orderer_mem_size_0;
	}

	public String getOrderer_mem_size_1() {
		return orderer_mem_size_1;
	}

	public void setOrderer_mem_size_1(String orderer_mem_size_1) {
		this.orderer_mem_size_1 = orderer_mem_size_1;
	}

	public String getOrderer_mem_size_2() {
		return orderer_mem_size_2;
	}

	public void setOrderer_mem_size_2(String orderer_mem_size_2) {
		this.orderer_mem_size_2 = orderer_mem_size_2;
	}

	public String getOrderer_mem_size_3() {
		return orderer_mem_size_3;
	}

	public void setOrderer_mem_size_3(String orderer_mem_size_3) {
		this.orderer_mem_size_3 = orderer_mem_size_3;
	}

	public String getKafka_mem_size() {
		return kafka_mem_size;
	}

	public void setKafka_mem_size(String kafka_mem_size) {
		this.kafka_mem_size = kafka_mem_size;
	}

	public String getPeer_mem_size_0() {
		return peer_mem_size_0;
	}

	public void setPeer_mem_size_0(String peer_mem_size_0) {
		this.peer_mem_size_0 = peer_mem_size_0;
	}

	public String getPeer_mem_size_1() {
		return peer_mem_size_1;
	}

	public void setPeer_mem_size_1(String peer_mem_size_1) {
		this.peer_mem_size_1 = peer_mem_size_1;
	}

	public String getPeer_mem_size_2() {
		return peer_mem_size_2;
	}

	public void setPeer_mem_size_2(String peer_mem_size_2) {
		this.peer_mem_size_2 = peer_mem_size_2;
	}

	public String getPeer_mem_size_3() {
		return peer_mem_size_3;
	}

	public void setPeer_mem_size_3(String peer_mem_size_3) {
		this.peer_mem_size_3 = peer_mem_size_3;
	}

	public String getPeer_mem_size_4() {
		return peer_mem_size_4;
	}

	public void setPeer_mem_size_4(String peer_mem_size_4) {
		this.peer_mem_size_4 = peer_mem_size_4;
	}

	public String getPeer_mem_size_5() {
		return peer_mem_size_5;
	}

	public void setPeer_mem_size_5(String peer_mem_size_5) {
		this.peer_mem_size_5 = peer_mem_size_5;
	}

	public String getPeer_mem_size_6() {
		return peer_mem_size_6;
	}

	public void setPeer_mem_size_6(String peer_mem_size_6) {
		this.peer_mem_size_6 = peer_mem_size_6;
	}

	public String getPeer_mem_size_7() {
		return peer_mem_size_7;
	}

	public void setPeer_mem_size_7(String peer_mem_size_7) {
		this.peer_mem_size_7 = peer_mem_size_7;
	}

	public String getPeer_mem_size_8() {
		return peer_mem_size_8;
	}

	public void setPeer_mem_size_8(String peer_mem_size_8) {
		this.peer_mem_size_8 = peer_mem_size_8;
	}

	public String getPeer_mem_size_9() {
		return peer_mem_size_9;
	}

	public void setPeer_mem_size_9(String peer_mem_size_9) {
		this.peer_mem_size_9 = peer_mem_size_9;
	}

	public String getPeer_mem_size_10() {
		return peer_mem_size_10;
	}

	public void setPeer_mem_size_10(String peer_mem_size_10) {
		this.peer_mem_size_10 = peer_mem_size_10;
	}

	public String getPeer_mem_size_11() {
		return peer_mem_size_11;
	}

	public void setPeer_mem_size_11(String peer_mem_size_11) {
		this.peer_mem_size_11 = peer_mem_size_11;
	}

	public String getPeer_mem_size_12() {
		return peer_mem_size_12;
	}

	public void setPeer_mem_size_12(String peer_mem_size_12) {
		this.peer_mem_size_12 = peer_mem_size_12;
	}

	public String getPeer_mem_size_13() {
		return peer_mem_size_13;
	}

	public void setPeer_mem_size_13(String peer_mem_size_13) {
		this.peer_mem_size_13 = peer_mem_size_13;
	}

	public String getPeer_mem_size_14() {
		return peer_mem_size_14;
	}

	public void setPeer_mem_size_14(String peer_mem_size_14) {
		this.peer_mem_size_14 = peer_mem_size_14;
	}

	public String getPeer_mem_size_15() {
		return peer_mem_size_15;
	}

	public void setPeer_mem_size_15(String peer_mem_size_15) {
		this.peer_mem_size_15 = peer_mem_size_15;
	}

	public String getAdminordererorg0_disk_size() {
		return adminordererorg0_disk_size;
	}

	public void setAdminordererorg0_disk_size(String adminordererorg0_disk_size) {
		this.adminordererorg0_disk_size = adminordererorg0_disk_size;
	}

	public String getOrderer_disk_size_0() {
		return orderer_disk_size_0;
	}

	public void setOrderer_disk_size_0(String orderer_disk_size_0) {
		this.orderer_disk_size_0 = orderer_disk_size_0;
	}

	public String getOrderer_disk_size_1() {
		return orderer_disk_size_1;
	}

	public void setOrderer_disk_size_1(String orderer_disk_size_1) {
		this.orderer_disk_size_1 = orderer_disk_size_1;
	}

	public String getOrderer_disk_size_2() {
		return orderer_disk_size_2;
	}

	public void setOrderer_disk_size_2(String orderer_disk_size_2) {
		this.orderer_disk_size_2 = orderer_disk_size_2;
	}

	public String getOrderer_disk_size_3() {
		return orderer_disk_size_3;
	}

	public void setOrderer_disk_size_3(String orderer_disk_size_3) {
		this.orderer_disk_size_3 = orderer_disk_size_3;
	}

	public String getKafka_disk_size() {
		return kafka_disk_size;
	}

	public void setKafka_disk_size(String kafka_disk_size) {
		this.kafka_disk_size = kafka_disk_size;
	}

	public String getPeer_disk_size_0() {
		return peer_disk_size_0;
	}

	public void setPeer_disk_size_0(String peer_disk_size_0) {
		this.peer_disk_size_0 = peer_disk_size_0;
	}

	public String getPeer_disk_size_1() {
		return peer_disk_size_1;
	}

	public void setPeer_disk_size_1(String peer_disk_size_1) {
		this.peer_disk_size_1 = peer_disk_size_1;
	}

	public String getPeer_disk_size_2() {
		return peer_disk_size_2;
	}

	public void setPeer_disk_size_2(String peer_disk_size_2) {
		this.peer_disk_size_2 = peer_disk_size_2;
	}

	public String getPeer_disk_size_3() {
		return peer_disk_size_3;
	}

	public void setPeer_disk_size_3(String peer_disk_size_3) {
		this.peer_disk_size_3 = peer_disk_size_3;
	}

	public String getPeer_disk_size_4() {
		return peer_disk_size_4;
	}

	public void setPeer_disk_size_4(String peer_disk_size_4) {
		this.peer_disk_size_4 = peer_disk_size_4;
	}

	public String getPeer_disk_size_5() {
		return peer_disk_size_5;
	}

	public void setPeer_disk_size_5(String peer_disk_size_5) {
		this.peer_disk_size_5 = peer_disk_size_5;
	}

	public String getPeer_disk_size_6() {
		return peer_disk_size_6;
	}

	public void setPeer_disk_size_6(String peer_disk_size_6) {
		this.peer_disk_size_6 = peer_disk_size_6;
	}

	public String getPeer_disk_size_7() {
		return peer_disk_size_7;
	}

	public void setPeer_disk_size_7(String peer_disk_size_7) {
		this.peer_disk_size_7 = peer_disk_size_7;
	}

	public String getPeer_disk_size_8() {
		return peer_disk_size_8;
	}

	public void setPeer_disk_size_8(String peer_disk_size_8) {
		this.peer_disk_size_8 = peer_disk_size_8;
	}

	public String getPeer_disk_size_9() {
		return peer_disk_size_9;
	}

	public void setPeer_disk_size_9(String peer_disk_size_9) {
		this.peer_disk_size_9 = peer_disk_size_9;
	}

	public String getPeer_disk_size_10() {
		return peer_disk_size_10;
	}

	public void setPeer_disk_size_10(String peer_disk_size_10) {
		this.peer_disk_size_10 = peer_disk_size_10;
	}

	public String getPeer_disk_size_11() {
		return peer_disk_size_11;
	}

	public void setPeer_disk_size_11(String peer_disk_size_11) {
		this.peer_disk_size_11 = peer_disk_size_11;
	}

	public String getPeer_disk_size_12() {
		return peer_disk_size_12;
	}

	public void setPeer_disk_size_12(String peer_disk_size_12) {
		this.peer_disk_size_12 = peer_disk_size_12;
	}

	public String getPeer_disk_size_13() {
		return peer_disk_size_13;
	}

	public void setPeer_disk_size_13(String peer_disk_size_13) {
		this.peer_disk_size_13 = peer_disk_size_13;
	}

	public String getPeer_disk_size_14() {
		return peer_disk_size_14;
	}

	public void setPeer_disk_size_14(String peer_disk_size_14) {
		this.peer_disk_size_14 = peer_disk_size_14;
	}

	public String getPeer_disk_size_15() {
		return peer_disk_size_15;
	}

	public void setPeer_disk_size_15(String peer_disk_size_15) {
		this.peer_disk_size_15 = peer_disk_size_15;
	}

	public String getZone_select_adminordererorg0() {
		return zone_select_adminordererorg0;
	}

	public void setZone_select_adminordererorg0(String zone_select_adminordererorg0) {
		this.zone_select_adminordererorg0 = zone_select_adminordererorg0;
	}

	public String getZone_select_orderer_0() {
		return zone_select_orderer_0;
	}

	public void setZone_select_orderer_0(String zone_select_orderer_0) {
		this.zone_select_orderer_0 = zone_select_orderer_0;
	}

	public String getZone_select_orderer_1() {
		return zone_select_orderer_1;
	}

	public void setZone_select_orderer_1(String zone_select_orderer_1) {
		this.zone_select_orderer_1 = zone_select_orderer_1;
	}

	public String getZone_select_orderer_2() {
		return zone_select_orderer_2;
	}

	public void setZone_select_orderer_2(String zone_select_orderer_2) {
		this.zone_select_orderer_2 = zone_select_orderer_2;
	}

	public String getZone_select_orderer_3() {
		return zone_select_orderer_3;
	}

	public void setZone_select_orderer_3(String zone_select_orderer_3) {
		this.zone_select_orderer_3 = zone_select_orderer_3;
	}

	public String getZone_select_kafka() {
		return zone_select_kafka;
	}

	public void setZone_select_kafka(String zone_select_kafka) {
		this.zone_select_kafka = zone_select_kafka;
	}

	public String getZone_select_bcmanager() {
		return zone_select_bcmanager;
	}

	public void setZone_select_bcmanager(String zone_select_bcmanager) {
		this.zone_select_bcmanager = zone_select_bcmanager;
	}

	public String getZone_select_peer_0() {
		return zone_select_peer_0;
	}

	public void setZone_select_peer_0(String zone_select_peer_0) {
		this.zone_select_peer_0 = zone_select_peer_0;
	}

	public String getZone_select_peer_1() {
		return zone_select_peer_1;
	}

	public void setZone_select_peer_1(String zone_select_peer_1) {
		this.zone_select_peer_1 = zone_select_peer_1;
	}

	public String getZone_select_peer_2() {
		return zone_select_peer_2;
	}

	public void setZone_select_peer_2(String zone_select_peer_2) {
		this.zone_select_peer_2 = zone_select_peer_2;
	}

	public String getZone_select_peer_3() {
		return zone_select_peer_3;
	}

	public void setZone_select_peer_3(String zone_select_peer_3) {
		this.zone_select_peer_3 = zone_select_peer_3;
	}

	public String getZone_select_peer_4() {
		return zone_select_peer_4;
	}

	public void setZone_select_peer_4(String zone_select_peer_4) {
		this.zone_select_peer_4 = zone_select_peer_4;
	}

	public String getZone_select_peer_5() {
		return zone_select_peer_5;
	}

	public void setZone_select_peer_5(String zone_select_peer_5) {
		this.zone_select_peer_5 = zone_select_peer_5;
	}

	public String getZone_select_peer_6() {
		return zone_select_peer_6;
	}

	public void setZone_select_peer_6(String zone_select_peer_6) {
		this.zone_select_peer_6 = zone_select_peer_6;
	}

	public String getZone_select_peer_7() {
		return zone_select_peer_7;
	}

	public void setZone_select_peer_7(String zone_select_peer_7) {
		this.zone_select_peer_7 = zone_select_peer_7;
	}

	public String getZone_select_peer_8() {
		return zone_select_peer_8;
	}

	public void setZone_select_peer_8(String zone_select_peer_8) {
		this.zone_select_peer_8 = zone_select_peer_8;
	}

	public String getZone_select_peer_9() {
		return zone_select_peer_9;
	}

	public void setZone_select_peer_9(String zone_select_peer_9) {
		this.zone_select_peer_9 = zone_select_peer_9;
	}

	public String getZone_select_peer_10() {
		return zone_select_peer_10;
	}

	public void setZone_select_peer_10(String zone_select_peer_10) {
		this.zone_select_peer_10 = zone_select_peer_10;
	}

	public String getZone_select_peer_11() {
		return zone_select_peer_11;
	}

	public void setZone_select_peer_11(String zone_select_peer_11) {
		this.zone_select_peer_11 = zone_select_peer_11;
	}

	public String getZone_select_peer_12() {
		return zone_select_peer_12;
	}

	public void setZone_select_peer_12(String zone_select_peer_12) {
		this.zone_select_peer_12 = zone_select_peer_12;
	}

	public String getZone_select_peer_13() {
		return zone_select_peer_13;
	}

	public void setZone_select_peer_13(String zone_select_peer_13) {
		this.zone_select_peer_13 = zone_select_peer_13;
	}

	public String getZone_select_peer_14() {
		return zone_select_peer_14;
	}

	public void setZone_select_peer_14(String zone_select_peer_14) {
		this.zone_select_peer_14 = zone_select_peer_14;
	}

	public String getZone_select_peer_15() {
		return zone_select_peer_15;
	}

	public void setZone_select_peer_15(String zone_select_peer_15) {
		this.zone_select_peer_15 = zone_select_peer_15;
	}

	public String getZone_select_adminorg_0() {
		return zone_select_adminorg_0;
	}

	public void setZone_select_adminorg_0(String zone_select_adminorg_0) {
		this.zone_select_adminorg_0 = zone_select_adminorg_0;
	}

	public String getZone_select_adminorg_1() {
		return zone_select_adminorg_1;
	}

	public void setZone_select_adminorg_1(String zone_select_adminorg_1) {
		this.zone_select_adminorg_1 = zone_select_adminorg_1;
	}

	public String getZone_select_adminorg_2() {
		return zone_select_adminorg_2;
	}

	public void setZone_select_adminorg_2(String zone_select_adminorg_2) {
		this.zone_select_adminorg_2 = zone_select_adminorg_2;
	}

	public String getZone_select_adminorg_3() {
		return zone_select_adminorg_3;
	}

	public void setZone_select_adminorg_3(String zone_select_adminorg_3) {
		this.zone_select_adminorg_3 = zone_select_adminorg_3;
	}

	public String getBcmanager_num_cpus() {
		return bcmanager_num_cpus;
	}

	public void setBcmanager_num_cpus(String bcmanager_num_cpus) {
		this.bcmanager_num_cpus = bcmanager_num_cpus;
	}

	public String getBcmanager_mem_size() {
		return bcmanager_mem_size;
	}

	public void setBcmanager_mem_size(String bcmanager_mem_size) {
		this.bcmanager_mem_size = bcmanager_mem_size;
	}

	public String getBcmanager_disk_size() {
		return bcmanager_disk_size;
	}

	public void setBcmanager_disk_size(String bcmanager_disk_size) {
		this.bcmanager_disk_size = bcmanager_disk_size;
	}

	public String getAdminorg_num_cpus_0() {
		return adminorg_num_cpus_0;
	}

	public void setAdminorg_num_cpus_0(String adminorg_num_cpus_0) {
		this.adminorg_num_cpus_0 = adminorg_num_cpus_0;
	}

	public String getAdminorg_num_cpus_1() {
		return adminorg_num_cpus_1;
	}

	public void setAdminorg_num_cpus_1(String adminorg_num_cpus_1) {
		this.adminorg_num_cpus_1 = adminorg_num_cpus_1;
	}

	public String getAdminorg_num_cpus_2() {
		return adminorg_num_cpus_2;
	}

	public void setAdminorg_num_cpus_2(String adminorg_num_cpus_2) {
		this.adminorg_num_cpus_2 = adminorg_num_cpus_2;
	}

	public String getAdminorg_num_cpus_3() {
		return adminorg_num_cpus_3;
	}

	public void setAdminorg_num_cpus_3(String adminorg_num_cpus_3) {
		this.adminorg_num_cpus_3 = adminorg_num_cpus_3;
	}

	public String getAdminorg_mem_size_0() {
		return adminorg_mem_size_0;
	}

	public void setAdminorg_mem_size_0(String adminorg_mem_size_0) {
		this.adminorg_mem_size_0 = adminorg_mem_size_0;
	}

	public String getAdminorg_mem_size_1() {
		return adminorg_mem_size_1;
	}

	public void setAdminorg_mem_size_1(String adminorg_mem_size_1) {
		this.adminorg_mem_size_1 = adminorg_mem_size_1;
	}

	public String getAdminorg_mem_size_2() {
		return adminorg_mem_size_2;
	}

	public void setAdminorg_mem_size_2(String adminorg_mem_size_2) {
		this.adminorg_mem_size_2 = adminorg_mem_size_2;
	}

	public String getAdminorg_mem_size_3() {
		return adminorg_mem_size_3;
	}

	public void setAdminorg_mem_size_3(String adminorg_mem_size_3) {
		this.adminorg_mem_size_3 = adminorg_mem_size_3;
	}

	public String getAdminorg_disk_size_0() {
		return adminorg_disk_size_0;
	}

	public void setAdminorg_disk_size_0(String adminorg_disk_size_0) {
		this.adminorg_disk_size_0 = adminorg_disk_size_0;
	}

	public String getAdminorg_disk_size_1() {
		return adminorg_disk_size_1;
	}

	public void setAdminorg_disk_size_1(String adminorg_disk_size_1) {
		this.adminorg_disk_size_1 = adminorg_disk_size_1;
	}

	public String getAdminorg_disk_size_2() {
		return adminorg_disk_size_2;
	}

	public void setAdminorg_disk_size_2(String adminorg_disk_size_2) {
		this.adminorg_disk_size_2 = adminorg_disk_size_2;
	}

	public String getAdminorg_disk_size_3() {
		return adminorg_disk_size_3;
	}

	public void setAdminorg_disk_size_3(String adminorg_disk_size_3) {
		this.adminorg_disk_size_3 = adminorg_disk_size_3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
