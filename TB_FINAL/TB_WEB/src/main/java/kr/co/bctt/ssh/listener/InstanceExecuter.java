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
import kr.co.bctt.ssh.dto.Instance;
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
public class InstanceExecuter implements Job {
	
	CommandServiceImpl impl = null;
 
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // / 스케쥴러에 의해 실행될 Job
    	
    	impl = new CommandServiceImpl();
    	
    	instanceList();
    }
    
    private void instanceList(){
    	List<Instance> instance_list = impl.getInstanceList("source admin-openrc; openstack server list");
    	
    	List<Instance> list = new ArrayList<Instance>();
		
		Instance _instance = null;
		
		String diskConfig = "";
		String availability_zone = "";
		String host = "";
		String hypervisor_hostname = "";
		String instance_name = "";
		String power_state = "";
		String task_state = "";
		String vm_state = "";
		String launched_at = "";
		String terminated_at = "";
		String accessIPv4 = "";
		String accessIPv6 = "";
		String addresses = "";
		String config_drive = "";
		String created = "";
		String flavor_name = "";
		String flavor_id = "";
		String hostId = "";
		String id = "";
		String image_name = "";
		String image_id = "";
		String key_name = "";
		String name = "";
		String progress = "";
		String project_id = "";
		String properties = "";
		String security_groups = "";
		String status = "";
		String updated = "";
		String user_id = "";
		String volumes_attached = "";
		
		for(Instance instance : instance_list){
			
			Map<String, Object> map = impl.getInstanceResourceList("source admin-openrc; openstack server show "+instance.getId());
			
			if(map.size() > 0){
				diskConfig = map.get("OS-DCF:diskConfig").toString();
				availability_zone = map.get("OS-EXT-AZ:availability_zone").toString();
				host = map.get("OS-EXT-SRV-ATTR:host").toString();
				hypervisor_hostname = map.get("OS-EXT-SRV-ATTR:hypervisor_hostname").toString();
				instance_name = map.get("OS-EXT-SRV-ATTR:instance_name").toString();
				power_state = map.get("OS-EXT-STS:power_state").toString();
				task_state = map.get("OS-EXT-STS:task_state").toString();
				vm_state = map.get("OS-EXT-STS:vm_state").toString();
				launched_at = map.get("OS-SRV-USG:launched_at").toString();
				terminated_at = map.get("OS-SRV-USG:terminated_at").toString();
				accessIPv4 = map.get("accessIPv4").toString();
				accessIPv6 = map.get("accessIPv6").toString();
				addresses = map.get("addresses").toString();
				config_drive = map.get("config_drive").toString();
				created = map.get("created").toString();
				
				String flavor = map.get("flavor").toString();
				
				String[] flv = flavor.split("\\(");
				
				flavor_name = flv[0];
				flavor_id = flv[1].replace(")", "");
				
				hostId = map.get("hostId").toString();
				id = map.get("id").toString();
				
				if(map.get("image").toString().contains("(")){
					String[] img = map.get("image").toString().split("\\(");
					image_name = img[0].trim();
					image_id = img[1].replace(")", "").trim();
				}else{
				}
				
				key_name = map.get("key_name").toString();
				name = map.get("name").toString();
				progress = "";
				project_id = map.get("project_id").toString();
				properties = map.get("properties").toString();
				if(map.get("security_groups") == null){
					security_groups = "";	
				}else{
					security_groups = map.get("security_groups").toString();
				}
				
				status = map.get("status").toString();
				updated = map.get("updated").toString();
				user_id = map.get("user_id").toString();
				volumes_attached = "";
				
				Map<String, Object> url_map = impl.getInstanceUrlResourceList("source admin-openrc; openstack console url show "+instance.getId());
				
				String url = "";
				
				if(url_map.size() == 0){
				}else{
					url = url_map.get("url").toString();
				}
				
				if(url.contains("//")){
					String[] _url = url.split("/");
					
					if(_url.length == 4){
						url = _url[3];	
					}
				}

				_instance = new Instance(diskConfig, availability_zone, host, hypervisor_hostname, instance_name, power_state, 
						task_state, vm_state, launched_at, terminated_at, accessIPv4, accessIPv6, 
						addresses, config_drive, created, flavor_name, flavor_id, hostId, id, 
						image_name, image_id, key_name, name, progress, project_id, properties, 
						security_groups, status, updated, user_id, volumes_attached, url);
				
				list.add(_instance);
			}
			
			
		}
		
		if (list.size() > 0) {
			CommonDAO.insertInstanceList(list);	
		}
    }
}
