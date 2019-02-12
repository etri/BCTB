package kr.co.bctt.ssh.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;

import kr.co.bctt.ssh.dao.CommandDao;
import kr.co.bctt.ssh.dao.CommonDAO;
import kr.co.bctt.ssh.dao.StatusDao;
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
public class ImageExecuter implements Job {
	
	CommandServiceImpl impl = null;
 
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // / 스케쥴러에 의해 실행될 Job
    	
    	impl = new CommandServiceImpl();
    	
    	imageList();
    }
    
    private void imageList(){
    	/** 프로젝트 **/
    	List<Image> imageList = impl.getImageList("source admin-openrc; openstack image list");
    	
    	List<ImageInfo> list = new ArrayList<ImageInfo>();
    	
		ImageInfo imageInfo = null;
		String id = "";
		String name = "";
		String format = "";
		String os = "";
		String size = "";
		String desc = "";
		
		String project_id = "";
		String status = "";
		String visibility = "";
		String _protected = "";

		for (Image image : imageList) {
			id = image.getId();
			name = image.getName();
			Map<String, Object> map = impl.getImageInfo("source admin-openrc; openstack image show " + id);
			
			if(map.size() > 0){
				format = map.get("disk_format").toString();
				size = map.get("size").toString();
				
				project_id = map.get("owner").toString();
				status = map.get("status").toString();
				visibility = map.get("visibility").toString();
				_protected = map.get("protected").toString();
				
				imageInfo = new ImageInfo(id, name, format, size, os, desc, project_id, status, visibility, _protected);
				
				list.add(imageInfo);
			}
		}
		
		if(list.size() > 0){
			CommonDAO.insertImageList(list);	
		}
    }
}
