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
import kr.co.bctt.ssh.dto.Route;
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
public class RouterExecuter implements Job {
	
	CommandServiceImpl impl = null;
 
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // / 스케쥴러에 의해 실행될 Job
    	
    	impl = new CommandServiceImpl();
    	
    	routerList();
    }
    
    private void routerList(){
    	/** 프로젝트 **/
    	List<Route> rlist = impl.getRouteList("source admin-openrc; openstack router list");
		
		String router_id = "";

		List<Route> list = new ArrayList<Route>();
		
		Route router = null;
		
		for (Route rl : rlist) {
			router_id = rl.getId();
			
			// public network 정보가져오기
			Map<String, Object> map = impl.getNetIp("source admin-openrc; openstack router show " + router_id);
			
			if(map.size() > 0){
				String admin_state_up = map.get("admin_state_up").toString();
				String availability_zone_hints = map.get("availability_zone_hints").toString();
				String availability_zones = map.get("availability_zones").toString();
				String created_at = map.get("created_at").toString();
				String description = map.get("description").toString();
				String distributed = map.get("distributed").toString();
				String external_gateway_info = map.get("external_gateway_info").toString();
				String flavor_id = map.get("flavor_id").toString();
				String ha = map.get("ha").toString();
				String id = map.get("id").toString();
				String interfaces_info = map.get("interfaces_info").toString();
				String name = map.get("name").toString();
				String project_id = map.get("project_id").toString();
				String revision_number = map.get("revision_number").toString();
				String routes = map.get("routes").toString();
				String status = map.get("status").toString();
				String tags = map.get("tags").toString();
//				String updated_at = map.get("updated_at").toString();
				String updated_at = "";
				
				router = new Route(admin_state_up, availability_zone_hints, availability_zones,
						created_at, description, distributed, external_gateway_info, flavor_id,
						ha, id, interfaces_info, name, project_id, revision_number, routes,
						status, tags, updated_at);
				
				list.add(router);
			}
		}
		
		if(list.size() > 0){
			CommonDAO.insertRouterList(list);
		}
    }
}
