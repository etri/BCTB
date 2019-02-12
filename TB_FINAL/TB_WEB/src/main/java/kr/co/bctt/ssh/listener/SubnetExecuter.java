package kr.co.bctt.ssh.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.bctt.ssh.dao.CommandDao;
import kr.co.bctt.ssh.dao.CommonDAO;
import kr.co.bctt.ssh.dao.StatusDao;
import kr.co.bctt.ssh.dto.Image;
import kr.co.bctt.ssh.dto.ImageInfo;
import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.dto.Subnet;
import kr.co.bctt.ssh.service.CommandService;
import kr.co.bctt.ssh.service.CommandServiceImpl;
import kr.co.bctt.ssh.service.StatusService;
import kr.co.bctt.ssh.service.StatusServiceImpl;
 
/**
 *         Job 인터페이스의 execute() 를 구현함으로써 
 *         Quartz 의 스케쥴링을 이용할 수있다.
 * 
 *         @author onceagain 
 * */
public class SubnetExecuter implements Job {
	
	CommandServiceImpl impl = null;
 
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // / 스케쥴러에 의해 실행될 Job
    	
    	impl = new CommandServiceImpl();
    	
    	subnetList();
    }
    
    private void subnetList(){
    	List<Subnet> nlist = impl.getSubnetList("source admin-openrc; openstack subnet list");
		
		String id = "";
		
		List<Subnet> list = new ArrayList<Subnet>();
		
		Subnet subnet = null;
		
		for (Subnet netlist : nlist) {
			id = netlist.getId();
			
			// public network 정보가져오기
			Map<String, Object> map = impl.getNetIp("source admin-openrc; openstack subnet show " + id);
			
			if(map.size() > 0){
				String allocation_pools = map.get("allocation_pools").toString();
				String cidr = map.get("cidr").toString();
				String created_at = map.get("created_at").toString();
				String description = map.get("description").toString();
				String dns_nameservers = map.get("dns_nameservers").toString();
				String enable_dhcp = map.get("enable_dhcp").toString();
				String gateway_ip = map.get("gateway_ip").toString();
				String host_routes = map.get("host_routes").toString();
				String ip_version = map.get("ip_version").toString();
				String ipv6_address_mode = map.get("ipv6_address_mode").toString();
				String ipv6_ra_mode = map.get("ipv6_ra_mode").toString();
				String name = map.get("name").toString();
				String network_id = map.get("network_id").toString();
				String project_id = map.get("project_id").toString();
				String revision_number = map.get("revision_number").toString();
				String segment_id = map.get("segment_id").toString();
				String service_types = map.get("service_types").toString();
				String subnetpool_id = map.get("subnetpool_id").toString();
				String tags = map.get("tags").toString();
//				String updated_at = map.get("updated_at").toString();
				String updated_at = "";

				subnet = new Subnet(allocation_pools, cidr, created_at, description, dns_nameservers,
						enable_dhcp, gateway_ip, host_routes, id, ip_version,
						ipv6_address_mode, ipv6_ra_mode, name, network_id, project_id,
						revision_number, segment_id, service_types, subnetpool_id, tags, updated_at);
				
				list.add(subnet);
			}
		}
		
		if(list.size() > 0){
			CommonDAO.insertSubnetList(list);
		}
    }
}
