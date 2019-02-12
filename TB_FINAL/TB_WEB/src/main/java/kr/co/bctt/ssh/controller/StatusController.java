package kr.co.bctt.ssh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.bctt.ssh.dao.CommonDAO;
import kr.co.bctt.ssh.dao.StatusDao;
import kr.co.bctt.ssh.dto.HResource;
import kr.co.bctt.ssh.dto.HStack;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.HostResource;
import kr.co.bctt.ssh.dto.ImageInfo;
import kr.co.bctt.ssh.dto.NodeInformation;
import kr.co.bctt.ssh.dto.PackageInfo;
import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.dto.StackServer;
import kr.co.bctt.ssh.dto.User;
import kr.co.bctt.ssh.dto.VcscfInfo;
import kr.co.bctt.ssh.dto.VcscfInfo2;
import kr.co.bctt.ssh.dto.Vnf;
import kr.co.bctt.ssh.dto.Vnfd;
import kr.co.bctt.ssh.service.StatusService;

/**
 * @FileName : StatusController.java
 * @Project : BCTT
 * @Date : 2016. 2. 5.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
@Controller
public class StatusController {

	@Autowired
	private StatusDao commandDao;

	@Autowired
	private StatusService commandService;


	/**
	 * @MethodName : getProjectList
	 * @작성일 : 2016. 2. 5. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : 프로젝트 아이디를 세션에 입력하기 위함
	 * @param session
	 * @return
	 */
	@RequestMapping("/state/projectList.do")
	public String getProjectList(Model model, HttpSession session) {

		//		List<ProjectInfo> project_list = commandService.getProjectList("source admin-openrc; openstack project list");
		//		
		//		List<ProjectInfo> plist =null;
		//		ProjectInfo pInfo = null;
		//		
		//		String id ="";
		//		String name="";
		//		String description ="";
		//		String domain_id ="";
		//		String enabled ="";
		//		
		//		for(ProjectInfo project : project_list){
		//			
		//			Map<String, Object> map = commandService.getProjectResourceList("source admin-openrc; openstack project show "+project.getProject_id());
		//            
		//			id = map.get("id").toString();
		//			name = map.get("name").toString();
		//			description = map.get("description").toString();
		//			domain_id = map.get("domain_id").toString();
		//			enabled = map.get("enabled").toString();
		//			 
		//        	pInfo = new ProjectInfo(id, name, description, domain_id, enabled);
		//			
		//			if(project.getProject_name().equals("admin")){
		//				session.setAttribute("admin_id", project.getProject_id());
		//			}
		//			
		//			commandDao.insertProject(pInfo);	
		//		}

		List<ProjectInfo> list = commandDao.getProjectList();

		if(list == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data", list);	
		}

		System.out.println("list :" + list.size());


		return "json";
	}


	/**
	 * @MethodName : getHostList
	 * @작성일 : 2016. 2. 5. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : Resource의 호스트 json으로 return;
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/hostList.do")
	public String getHostList(Model model , HttpSession session) {

		List<NodeInformation> list = commandDao.getResourceInfo();

		if(list == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",list);	
		}

		return "json";
	}

	@RequestMapping("/state/getBasicList.do")
	public String getBasicList(Model model , HttpSession session) {
		
		List<NodeInformation> list = new ArrayList<NodeInformation>();
		
		NodeInformation node = new NodeInformation();
		node.setHost("키 페어");
		node.setUseCpu("-");
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("첨부한 파일 콘텐츠 바이트");
		node.setUseCpu("-");
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("첨부한 파일 경로 길이");
		node.setUseCpu("-");
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("첨부한 파일");
		node.setUseCpu("-");
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("인스턴스");
		node.setUseCpu("-");
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("메타데이터 항목");
		node.setUseCpu("-");
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("VCPUs");
		node.setUseCpu("-");
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("RAM(MB)");
		node.setUseCpu("-");
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("VNC IP");
		node.setUseCpu(CommonDAO.selectVncInfo().get(0).getIp_addr());
		list.add(node);
		
		node = new NodeInformation();
		node.setHost("VNC Port");
		node.setUseCpu(CommonDAO.selectVncInfo().get(0).getPort());
		list.add(node);
		
		model.addAttribute("result","success");
		model.addAttribute("data",list);

		return "json";
	}


	/**
	 * @MethodName : getVcscfList
	 * @작성일 : 2016. 2. 5. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vCSCF Info를 json으로 return;
	 * @param model
	 * @return
	 */
	@RequestMapping("/state/vCSCF.do")
	public String getVcscfList(Model model) {
		List<VcscfInfo> list = commandDao.getVcscfInfo();
		model.addAttribute("data",list);
		return "json";
	}

	/**
	 * @MethodName : getVcscfList2
	 * @작성일 : 2016. 2. 5. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vCSCF를 json으로 return;
	 * @param model
	 * @return
	 */
	@RequestMapping("/state/vCSCF2.do")
	public String getVcscfList2(Model model) {

		List<VcscfInfo2> list = new ArrayList<VcscfInfo2>();
		String description =null;
		String id =null;
		String status = null;
		String flavor = null;
		String image =null;
		String create_time=null;
		String update_time=null;
		String mgmtIp=null;
		String privateIp=null;
		String mac=null;

		String stack_id =null;
		String physical_resource_id=null;
		String private_admin=null;

		List<Vnf> vnfList = commandService.getVnf("source admin-openrc; tacker vnf-list");
		for(Vnf vnf : vnfList){
			id = vnf.getId();
			status = vnf.getStatus();
			description = vnf.getDescription();

			List<HStack> hsList = commandService.getHStack("source admin-openrc; heat stack-list");
			for(HStack hs : hsList){
				if(hs.getStack_name().substring(39).equals(id)){
					create_time = hs.getCreation_time();
					update_time = hs.getUpdated_time();
					stack_id = hs.getId();
					break;
				}
			}


			List<HResource> hrList = commandService.getHResource("source admin-openrc; heat resource-list "+stack_id);
			for(HResource hr : hrList){
				if(hr.getResource_type().substring(4,8).equals("Nova")){
					physical_resource_id = hr.getPhysical_resource_id();
					break;
				}
			}


			List<StackServer> ssList = commandService.getStackServer("source admin-openrc; openstack server show "+physical_resource_id);
			for(StackServer ss : ssList){

				if(ss.getField().equals("flavor")){
					flavor = ss.getValue();
				}
				else if(ss.getField().equals("image")){
					image = ss.getValue();
				}
				else if(ss.getField().equals("addresses")){
					String addresses = ss.getValue();
					String []addr = addresses.split(";");
					for(String addr2 : addr){
						if(addr2.substring(0,4).equals("priv")){
							int idx = addr2.indexOf("=");
							private_admin = addr2.substring(idx+1);
							privateIp = private_admin;	
						}
						else if(addr2.substring(0,4).equals("net_")){
							int idx = addr2.indexOf("=");
							mgmtIp = addr2.substring(idx+1);
						}
					}
				}
			}
			mac = commandService.getMac("source admin-openrc; neutron port-list | grep "+private_admin);
			list.add(new VcscfInfo2(description,id, status, flavor, image, create_time, update_time, mgmtIp, privateIp, mac));
		}	
		model.addAttribute("data", list);	
		return "json";
	}
}
