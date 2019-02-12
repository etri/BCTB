package kr.co.bctt.ssh.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.bctt.ssh.dao.CommonDAO;
import kr.co.bctt.ssh.dao.StatusDao;
import kr.co.bctt.ssh.dao.StatusDaoImpl;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.Hyperledger;
import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.service.CommandServiceImpl;
import kr.co.bctt.ssh.service.StatusService;
import kr.co.bctt.ssh.service.StatusServiceImpl;
 
/**
 *         Job 인터페이스의 execute() 를 구현함으로써 
 *         Quartz 의 스케쥴링을 이용할 수있다.
 * 
 *         @author onceagain 
 * */
public class HyperledgerExecuter implements Job {
	
	CommandServiceImpl impl = null;
	
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // / 스케쥴러에 의해 실행될 Job
    	
    	impl = new CommandServiceImpl();
    	
    	vnfList();
    }
    
    private void vnfList(){
    	
    	/** 프로젝트 **/
    	String status = "";
    	String mgmt_url = "";
    	
		List<Hyperledger> list = CommonDAO.selectHyperledgerWaitList(); /* hostName을 가져오기위함  ex)vcscf */
		
		for(Hyperledger _list : list){
			
			Map<String, Object> map = impl.getVnfInfo("source admin-openrc; openstack vnf show "+_list.getVnf_id());
			
			if(map.size() > 0){
				status = map.get("status").toString();
				
				if(status.equals("ACTIVE")){
					mgmt_url = map.get("mgmt_url").toString();	
				}
				
				CommonDAO.updateHlfStatus(mgmt_url, _list.getProfile_id());
//				CommonDAO.updateHlfStatus(mgmt_url, 6);	
			}
		}
    }
}
