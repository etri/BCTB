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
import kr.co.bctt.ssh.dto.NetList;
import kr.co.bctt.ssh.dto.Network;
import kr.co.bctt.ssh.dto.ProjectInfo;
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
public class NetworkExecuter implements Job {
	
	CommandServiceImpl impl = null;
 
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // / 스케쥴러에 의해 실행될 Job
    	
    	impl = new CommandServiceImpl();
    	
    	networkList();
    }
    
    private void networkList(){
    	
    	List<NetList> nlist = impl.getNetList("source admin-openrc; openstack network list");
    	
    	List<Network> list = new ArrayList<Network>();
    	
    	Network network = null;
		
    	String network_id = "";
		String network_name = "";
		String subnet = "";
		String private_network = "";
		
		for (NetList netlist : nlist) {
			network_id = netlist.getId();
			network_name = netlist.getName();
			subnet = netlist.getSubnets().substring(0, 36);
			
			// public network 정보가져오기
			Map<String, Object> map = impl.getNetIp("source admin-openrc; openstack subnet show " + subnet);
			
			Map<String, Object> net_map = impl.getNetIp("source admin-openrc; openstack network show " + network_id);
			
			String subnet_name = map.get("name").toString();
			String project_id = map.get("project_id").toString();
			
			String external = "";
			if(net_map.get("router:external") == null){
				external = "";
			}else{
				external = net_map.get("router:external").toString();
			}
			
			String subnet_id = map.get("id").toString();
			String cidr = map.get("cidr").toString();
			String gateway_ip = map.get("gateway_ip").toString();
			String allocation_pools = map.get("allocation_pools").toString();
			String dns_nameservers = map.get("dns_nameservers").toString();
			String enable_dhcp = map.get("enable_dhcp").toString();
			String ip_version = map.get("ip_version").toString();

			network = new Network(network_name, network_id, subnet_id, cidr, gateway_ip, subnet_name, project_id, allocation_pools, dns_nameservers, enable_dhcp, ip_version, external);
			
			list.add(network);
		}
		
		CommonDAO.insertNetworkList(list);
    }
}
