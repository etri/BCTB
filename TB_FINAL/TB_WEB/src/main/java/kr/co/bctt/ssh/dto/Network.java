package kr.co.bctt.ssh.dto;

public class Network {
	
	private String network_name;
	private String network_id;
	private String subnet_id;
	private String cidr;
	private String gateway_ip;
	private String subnet_name;
	private String project_id;
	private String project_name;
	private String allocation_pools;
	private String dns_nameservers;
	private String enable_dhcp;
	private String ip_version;
	private String external;
	
	public Network() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Network(String network_name, String network_id, String subnet_id, String cidr, String gateway_ip, 
			String subnet_name, String project_id, String allocation_pools, String dns_nameservers, String enable_dhcp, String ip_version, String external) {
		super();
		this.network_name = network_name;
		this.network_id = network_id;
		this.subnet_id = subnet_id;
		this.cidr = cidr;
		this.gateway_ip = gateway_ip;
		this.subnet_name = subnet_name;
		this.project_id = project_id;
		this.allocation_pools = allocation_pools;
		this.dns_nameservers = dns_nameservers;
		this.enable_dhcp = enable_dhcp;
		this.ip_version = ip_version;
		this.external = external;
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

	public String getSubnet_id() {
		return subnet_id;
	}

	public void setSubnet_id(String subnet_id) {
		this.subnet_id = subnet_id;
	}

	public String getCidr() {
		return cidr;
	}

	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public String getGateway_ip() {
		return gateway_ip;
	}

	public void setGateway_ip(String gateway_ip) {
		this.gateway_ip = gateway_ip;
	}

	public String getSubnet_name() {
		return subnet_name;
	}

	public void setSubnet_name(String subnet_name) {
		this.subnet_name = subnet_name;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getAllocation_pools() {
		return allocation_pools;
	}

	public void setAllocation_pools(String allocation_pools) {
		this.allocation_pools = allocation_pools;
	}

	public String getDns_nameservers() {
		return dns_nameservers;
	}

	public void setDns_nameservers(String dns_nameservers) {
		this.dns_nameservers = dns_nameservers;
	}

	public String getEnable_dhcp() {
		return enable_dhcp;
	}

	public void setEnable_dhcp(String enable_dhcp) {
		this.enable_dhcp = enable_dhcp;
	}

	public String getIp_version() {
		return ip_version;
	}

	public void setIp_version(String ip_version) {
		this.ip_version = ip_version;
	}

	public String getExternal() {
		return external;
	}

	public void setExternal(String external) {
		this.external = external;
	}
}
