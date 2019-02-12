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
import kr.co.bctt.ssh.dto.Flavor;
import kr.co.bctt.ssh.dto.FlavorInfo;
import kr.co.bctt.ssh.dto.Image;
import kr.co.bctt.ssh.dto.ImageInfo;
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
public class FlavorExecuter implements Job {
	
	CommandServiceImpl impl = null;
 
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // / 스케쥴러에 의해 실행될 Job
    	
    	impl = new CommandServiceImpl();
    	
    	flavorList();
    }
    
    private void flavorList(){
    	List<Flavor> flist = impl.getFlavorList("source admin-openrc; openstack flavor list");
    	
		List<FlavorInfo> list = new ArrayList<FlavorInfo>();

		FlavorInfo flavorInfo = null;
		boolean flag = false;
		Map<String,Object> map = null;
		
		for(Flavor imsi : flist){
			String id = imsi.getId();
			map = impl.getFlavorInfo("source admin-openrc; openstack flavor show "+id);
			
			if(map.size() > 0){
				if(map.get("swap").equals("")){
					map.replace("swap","0");
				}
				
				if(map.get("os-flavor-access:is_public").equals("True")){
					map.replace("os-flavor-access:is_public","예");
				}else{
					map.replace("os-flavor-access:is_public","아니오");
				}
				
				if(map.get("OS-FLV-DISABLED:disabled").equals("True")){
					map.replace("OS-FLV-DISABLED:disabled","예");
				}else{
					map.replace("OS-FLV-DISABLED:disabled","아니오");
				}
				
				flavorInfo = new FlavorInfo(
						map.get("id").toString(),
						map.get("name").toString(),
						map.get("vcpus").toString(),
						map.get("ram").toString(),
						map.get("disk").toString(),
						map.get("OS-FLV-EXT-DATA:ephemeral").toString(),
						map.get("swap").toString(),
						map.get("rxtx_factor").toString(),
						map.get("os-flavor-access:is_public").toString(),
						map.get("OS-FLV-DISABLED:disabled").toString());
				
				list.add(flavorInfo);
			}
		}	
		
		if(list.size() > 0){
			CommonDAO.insertFlavorList(list);	
		}
    }
}
