package kr.co.bctt.ssh.listener;

import java.util.ArrayList;
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
import kr.co.bctt.ssh.dto.HostResource;
import kr.co.bctt.ssh.dto.NodeInformation;
import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.service.StatusService;
import kr.co.bctt.ssh.service.StatusServiceImpl;
 
/**
 *         Job 인터페이스의 execute() 를 구현함으로써 
 *         Quartz 의 스케쥴링을 이용할 수있다.
 * 
 *         @author onceagain 
 * */
public class ProjectExecuter implements Job {
	
	StatusServiceImpl impl = null;
 
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // / 스케쥴러에 의해 실행될 Job
    	
    	impl = new StatusServiceImpl();
    	 
    	projectList();
    }
    
    private void projectList(){
    	/** 프로젝트 **/
    	
    	List<ProjectInfo> project_list = impl.getProjectList("source admin-openrc; openstack project list");
		
		List<ProjectInfo> list = new ArrayList<ProjectInfo>();
		ProjectInfo pInfo = null;
		
		String id ="";
		String name="";
		String description ="";
		String domain_id ="";
		String enabled ="";
		
		List<HostInfo> host_list = impl.getHostList("source admin-openrc; openstack host list"); /* hostName을 가져오기위함  ex)vcscf */
    	CommonDAO.insertHostList(host_list);	
    	
    	
    	String host ="";
        String totalCpu ="";
        String totalGb ="";
        String totalMem ="";
        String useCpu="";
        String useGb="";
        String useMem ="";
        NodeInformation nodeInfo = null;
        List<NodeInformation> nlist = new ArrayList<NodeInformation>();
        
    	for(HostInfo imsi : host_list){
            if(imsi.getService().equals("compute")){
            	List<HostResource> hlist = impl.getHostResourceList("source admin-openrc; openstack host show "+imsi.getHostName());
            	
                for(HostResource hr : hlist){ //1개 이상의 host정보가 있음.
                    host = hr.getHost();    
                    
                    if(hr.getProject().equals("(total)")){
                        totalCpu = hr.getCpu();
                        totalGb = hr.getDiskGb();
                        totalMem = hr.getMemoryMb();
                    }
                    
                    if(hr.getProject().equals("(used_now)")){
                        useCpu = hr.getCpu();
                        useGb = hr.getDiskGb();
                        useMem = hr.getMemoryMb();
                        nodeInfo = new NodeInformation(host,totalCpu,totalGb,useCpu,useGb,totalMem,useMem);
                        nlist.add(nodeInfo);
                    }
                }
            }    
        }
    	
    	CommonDAO.insertResourceList(nlist);
		
		for(ProjectInfo project : project_list){
			
			Map<String, Object> map = impl.getProjectResourceList("source admin-openrc; openstack project show "+project.getProject_id());
            
			id = map.get("id").toString();
			name = map.get("name").toString();
			description = map.get("description").toString();
			domain_id = map.get("domain_id").toString();
			enabled = map.get("enabled").toString();
			 
        	pInfo = new ProjectInfo(id, name, description, domain_id, enabled);
        	
        	list.add(pInfo);
		}
		
		CommonDAO.insertProjectList(list);
    }
}
