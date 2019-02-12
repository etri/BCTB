package kr.co.bctt.ssh.dto;

/**
 * @FileName : Instance.java
 * @Project : BCTT
 * @Date : 2018. 8. 28.
 * @작성자 : onceagain
 * @변경이력 : 서버에서 받아오는 intance dto
 * @프로그램설명 :
 */
public class Instance {
	
	private String diskConfig;
	private String availability_zone;
	private String host;
	private String hypervisor_hostname;
	private String instance_name;
	private String power_state;
	private String task_state;
	private String vm_state;
	private String launched_at;
	private String terminated_at;
	private String accessIPv4;
	private String accessIPv6;
	private String addresses;
	private String config_drive;
	private String created;
	private String flavor_name;
	private String network_id;
	private String zone_name;
	private String flavor_id;
	private String hostId;
	private String id;
	private String instance_id;
	private String image_name;
	private String image_id;
	private String key_name;
	private String name;
	private String progress;
	private String project_id;
	private String project_name;
	private String properties;
	private String security_groups;
	private String status;
	private String updated;
	private String user_id;
	private String volumes_attached;
	
	private String ram;
	private String vcpus;
	private String root_disk;
	private String url;
	
	private String ip_addr;
	private String port;
	private String float_ip;
	
	public Instance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Instance(String id) {
		super();
		this.id = id;
	}
	
	public Instance(String diskConfig, String availability_zone, String host, String hypervisor_hostname, String instance_name, String power_state, 
			String task_state, String vm_state, String launched_at, String terminated_at, String accessIPv4, String accessIPv6, 
			String addresses, String config_drive, String created, String flavor_name, String flavor_id, String hostId, String id, 
			String image_name, String image_id, String key_name, String name, String progress, String project_id, String properties, 
			String security_groups, String status, String updated, String user_id, String volumes_attached, String url) {
		super();
		this.diskConfig = diskConfig;
		this.availability_zone = availability_zone;
		this.host = host;
		this.hypervisor_hostname = hypervisor_hostname;
		this.instance_name = instance_name;
		this.power_state = power_state;
		this.task_state = task_state;
		this.vm_state = vm_state;
		this.launched_at = launched_at;
		this.terminated_at = terminated_at;
		this.accessIPv4 = accessIPv4;
		this.accessIPv6 = accessIPv6;
		this.addresses = addresses;
		this.config_drive = config_drive;
		this.created = created;
		this.flavor_name = flavor_name;
		this.flavor_id = flavor_id;
		this.hostId = hostId;
		this.id = id;
		this.image_name = image_name;
		this.image_id = image_id;
		this.key_name = key_name;
		this.name = name;
		this.progress = progress;
		this.project_id = project_id;
		this.properties = properties;
		this.security_groups = security_groups;
		this.status = status;
		this.updated = updated;
		this.user_id = user_id;
		this.volumes_attached = volumes_attached;
		this.url = url;
	}
	public String getDiskConfig() {
		return diskConfig;
	}
	public void setDiskConfig(String diskConfig) {
		this.diskConfig = diskConfig;
	}
	public String getAvailability_zone() {
		return availability_zone;
	}
	public void setAvailability_zone(String availability_zone) {
		this.availability_zone = availability_zone;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHypervisor_hostname() {
		return hypervisor_hostname;
	}
	public void setHypervisor_hostname(String hypervisor_hostname) {
		this.hypervisor_hostname = hypervisor_hostname;
	}
	public String getInstance_name() {
		return instance_name;
	}
	public void setInstance_name(String instance_name) {
		this.instance_name = instance_name;
	}
	public String getPower_state() {
		return power_state;
	}
	public void setPower_state(String power_state) {
		this.power_state = power_state;
	}
	public String getTask_state() {
		return task_state;
	}
	public void setTask_state(String task_state) {
		this.task_state = task_state;
	}
	public String getVm_state() {
		return vm_state;
	}
	public void setVm_state(String vm_state) {
		this.vm_state = vm_state;
	}
	public String getLaunched_at() {
		return launched_at;
	}
	public void setLaunched_at(String launched_at) {
		this.launched_at = launched_at;
	}
	public String getTerminated_at() {
		return terminated_at;
	}
	public void setTerminated_at(String terminated_at) {
		this.terminated_at = terminated_at;
	}
	public String getAccessIPv4() {
		return accessIPv4;
	}
	public void setAccessIPv4(String accessIPv4) {
		this.accessIPv4 = accessIPv4;
	}
	public String getAccessIPv6() {
		return accessIPv6;
	}
	public void setAccessIPv6(String accessIPv6) {
		this.accessIPv6 = accessIPv6;
	}
	public String getAddresses() {
		return addresses;
	}
	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}
	public String getConfig_drive() {
		return config_drive;
	}
	public void setConfig_drive(String config_drive) {
		this.config_drive = config_drive;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getFlavor_name() {
		return flavor_name;
	}
	public void setFlavor_name(String flavor_name) {
		this.flavor_name = flavor_name;
	}
	public String getFlavor_id() {
		return flavor_id;
	}
	public void setFlavor_id(String flavor_id) {
		this.flavor_id = flavor_id;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public String getKey_name() {
		return key_name;
	}
	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getSecurity_groups() {
		return security_groups;
	}
	public void setSecurity_groups(String security_groups) {
		this.security_groups = security_groups;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getVolumes_attached() {
		return volumes_attached;
	}

	public void setVolumes_attached(String volumes_attached) {
		this.volumes_attached = volumes_attached;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getVcpus() {
		return vcpus;
	}

	public void setVcpus(String vcpus) {
		this.vcpus = vcpus;
	}

	public String getRoot_disk() {
		return root_disk;
	}

	public void setRoot_disk(String root_disk) {
		this.root_disk = root_disk;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp_addr() {
		return ip_addr;
	}

	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public String getNetwork_id() {
		return network_id;
	}

	public void setNetwork_id(String network_id) {
		this.network_id = network_id;
	}

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public String getFloat_ip() {
		return float_ip;
	}

	public void setFloat_ip(String float_ip) {
		this.float_ip = float_ip;
	}
	
}
