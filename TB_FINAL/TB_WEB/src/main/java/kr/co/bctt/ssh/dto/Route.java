package kr.co.bctt.ssh.dto;

public class Route {
	
	private String admin_state_up;
	private String availability_zone_hints;
	private String availability_zones;
	private String created_at;
	private String description;
	private String distributed;
	private String external_gateway_info;
	private String flavor_id;
	private String ha;
	private String id;
	private String interfaces_info;
	private String name;
	private String project_id;
	private String project_name;
	private String revision_number;
	private String routes;
	private String status;
	private String tags;
	private String updated_at;
	
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Route(String id) {
		super();
		this.id = id;
	}
	
	public Route(String admin_state_up, String availability_zone_hints, String availability_zones,
			String created_at, String description, String distributed, String external_gateway_info, String flavor_id,
			String ha, String id, String interfaces_info, String name, String project_id, String revision_number, String routes,
			String status, String tags, String updated_at) {
		super();
		this.admin_state_up = admin_state_up;
		this.availability_zone_hints = availability_zone_hints;
		this.availability_zones = availability_zones;
		this.created_at = created_at;
		this.description = description;
		this.distributed = distributed;
		this.external_gateway_info = external_gateway_info;
		this.flavor_id = flavor_id;
		this.ha = ha;
		this.id = id;
		this.interfaces_info = interfaces_info;
		this.name = name;
		this.project_id = project_id;
		this.revision_number = revision_number;
		this.routes = routes;
		this.status = status;
		this.tags = tags;
		this.updated_at = updated_at;
	}

	public String getAdmin_state_up() {
		return admin_state_up;
	}

	public void setAdmin_state_up(String admin_state_up) {
		this.admin_state_up = admin_state_up;
	}

	public String getAvailability_zone_hints() {
		return availability_zone_hints;
	}

	public void setAvailability_zone_hints(String availability_zone_hints) {
		this.availability_zone_hints = availability_zone_hints;
	}

	public String getAvailability_zones() {
		return availability_zones;
	}

	public void setAvailability_zones(String availability_zones) {
		this.availability_zones = availability_zones;
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

	public String getDistributed() {
		return distributed;
	}

	public void setDistributed(String distributed) {
		this.distributed = distributed;
	}

	public String getExternal_gateway_info() {
		return external_gateway_info;
	}

	public void setExternal_gateway_info(String external_gateway_info) {
		this.external_gateway_info = external_gateway_info;
	}

	public String getFlavor_id() {
		return flavor_id;
	}

	public void setFlavor_id(String flavor_id) {
		this.flavor_id = flavor_id;
	}

	public String getHa() {
		return ha;
	}

	public void setHa(String ha) {
		this.ha = ha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterfaces_info() {
		return interfaces_info;
	}

	public void setInterfaces_info(String interfaces_info) {
		this.interfaces_info = interfaces_info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRevision_number() {
		return revision_number;
	}

	public void setRevision_number(String revision_number) {
		this.revision_number = revision_number;
	}

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
