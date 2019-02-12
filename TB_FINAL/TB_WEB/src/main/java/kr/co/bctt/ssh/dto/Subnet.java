package kr.co.bctt.ssh.dto;

/**
 * @FileName : Subnet.java
 * @Project : BCTT
 * @Date : 2018. 8. 28.
 * @작성자 : onceagain
 * @변경이력 : 서버에서 받아오는 subnet dto
 * @프로그램설명 :
 */
public class Subnet {
	
	private String allocation_pools;
	private String cidr;
	private String created_at;
	private String description;
	private String dns_nameservers;
	private String enable_dhcp;
	private String gateway_ip;
	private String host_routes;
	private String id;
	private String ip_version;
	private String ipv6_address_mode;
	private String ipv6_ra_mode;
	private String name;
	private String network_id;
	private String network_name;
	private String project_id;
	private String revision_number;
	private String segment_id;
	private String service_types;
	private String subnetpool_id;
	private String tags;
	private String updated_at;
	
	public Subnet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Subnet(String id) {
		super();
		this.id = id;
	}
	
	public Subnet(String allocation_pools, String cidr, String created_at, String description, String dns_nameservers,
			String enable_dhcp, String gateway_ip, String host_routes, String id, String ip_version,
			String ipv6_address_mode, String ipv6_ra_mode, String name, String network_id, String project_id,
			String revision_number, String segment_id, String service_types, String subnetpool_id, String tags, String updated_at) {
		super();
		this.allocation_pools = allocation_pools;
		this.cidr = cidr;
		this.created_at = created_at;
		this.description = description;
		this.dns_nameservers = dns_nameservers;
		this.enable_dhcp = enable_dhcp;
		this.gateway_ip = gateway_ip;
		this.host_routes = host_routes;
		this.id = id;
		this.ip_version = ip_version;
		this.ipv6_address_mode = ipv6_address_mode;
		this.ipv6_ra_mode = ipv6_ra_mode;
		this.name = name;
		this.network_id = network_id;
		this.project_id = project_id;
		this.revision_number = revision_number;
		this.segment_id = segment_id;
		this.service_types = service_types;
		this.subnetpool_id = subnetpool_id;
		this.tags = tags;
		this.updated_at = updated_at;
	}

	public String getAllocation_pools() {
		return allocation_pools;
	}

	public void setAllocation_pools(String allocation_pools) {
		this.allocation_pools = allocation_pools;
	}

	public String getCidr() {
		return cidr;
	}

	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getGateway_ip() {
		return gateway_ip;
	}

	public void setGateway_ip(String gateway_ip) {
		this.gateway_ip = gateway_ip;
	}

	public String getHost_routes() {
		return host_routes;
	}

	public void setHost_routes(String host_routes) {
		this.host_routes = host_routes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp_version() {
		return ip_version;
	}

	public void setIp_version(String ip_version) {
		this.ip_version = ip_version;
	}

	public String getIpv6_address_mode() {
		return ipv6_address_mode;
	}

	public void setIpv6_address_mode(String ipv6_address_mode) {
		this.ipv6_address_mode = ipv6_address_mode;
	}

	public String getIpv6_ra_mode() {
		return ipv6_ra_mode;
	}

	public void setIpv6_ra_mode(String ipv6_ra_mode) {
		this.ipv6_ra_mode = ipv6_ra_mode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetwork_id() {
		return network_id;
	}

	public void setNetwork_id(String network_id) {
		this.network_id = network_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getRevision_number() {
		return revision_number;
	}

	public void setRevision_number(String revision_number) {
		this.revision_number = revision_number;
	}

	public String getSegment_id() {
		return segment_id;
	}

	public void setSegment_id(String segment_id) {
		this.segment_id = segment_id;
	}

	public String getService_types() {
		return service_types;
	}

	public void setService_types(String service_types) {
		this.service_types = service_types;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getSubnetpool_id() {
		return subnetpool_id;
	}

	public void setSubnetpool_id(String subnetpool_id) {
		this.subnetpool_id = subnetpool_id;
	}

	public String getNetwork_name() {
		return network_name;
	}

	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}
	
}
