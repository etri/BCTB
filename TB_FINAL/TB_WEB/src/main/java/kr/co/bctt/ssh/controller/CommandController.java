package kr.co.bctt.ssh.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;

import kr.co.bctt.ssh.dao.JSchUtil;
import kr.co.bctt.ssh.dao.CommandDao;
import kr.co.bctt.ssh.dao.CommonDAO;
import kr.co.bctt.ssh.dto.Ethereum;
import kr.co.bctt.ssh.dto.Flavor;
import kr.co.bctt.ssh.dto.FlavorInfo;
import kr.co.bctt.ssh.dto.FloatingIp;
import kr.co.bctt.ssh.dto.Hyperledger;
import kr.co.bctt.ssh.dto.Image;
import kr.co.bctt.ssh.dto.ImageInfo;
import kr.co.bctt.ssh.dto.Instance;
import kr.co.bctt.ssh.dto.NetList;
import kr.co.bctt.ssh.dto.Network;
import kr.co.bctt.ssh.dto.PackageInfo;
import kr.co.bctt.ssh.dto.Resource;
import kr.co.bctt.ssh.dto.Route;
import kr.co.bctt.ssh.dto.Subnet;
import kr.co.bctt.ssh.dto.Vdu;
import kr.co.bctt.ssh.dto.Vnc;
import kr.co.bctt.ssh.dto.Vnf;
import kr.co.bctt.ssh.dto.VnfInfo;
import kr.co.bctt.ssh.dto.Vnfd;
import kr.co.bctt.ssh.dto.VnfdInfo;
import kr.co.bctt.ssh.service.StatusService;
import kr.co.bctt.ssh.service.CommandService;

/**
 * @FileName : CommandController.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 : 2016. 2. 12.
 * @프로그램설명 :
 */
@Controller
public class CommandController {

	@Autowired
	private CommandService commandService;

	@Autowired
	private CommandDao commandDao;

	@Autowired
	private JSchUtil jschUtil;

	@Autowired
	private StatusService statusService;
	
	@RequestMapping("/command/loginSuccess.do")
	public String loginSuccess(HttpSession session, HttpServletRequest request) {
		String login_id = request.getParameter("login_id");
		session.setAttribute("login_id", login_id);
		session.setMaxInactiveInterval(86400);
		
		return "/state/resource";
	}
	
	@RequestMapping("/state/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("login_id");
		return "redirect:/state/login.do";
	}

	/**
	 * @MethodName : getVcscfList
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 : 2016. 2. 12.
	 * @Method설명 : 처음 image메뉴를 클릭 했을때 서버쪽에서 image list를 가지고옴
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/image.do")
	public String getVcscfList(Model model) {
		model.addAttribute("vlist", commandDao.vlist());
		return "/command/image";
	}

	/**
	 * @MethodName : getImage
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : image list를 json으로 return;
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/getImage.do")
	public String getImage(Model model){
		List<ImageInfo> list = commandDao.selectImageInfoList();
		model.addAttribute("vlist", commandDao.vlist());
		if(commandDao.selectImageInfoList() == null) model.addAttribute("result","fail");
		model.addAttribute("data",list);
		return "json";
	}
	
	
	
	/**
	* @MethodName : getProjectList
	* @작성일 : 2016. 2. 5. 
	* @작성자 : Park
	* @변경이력 :
	* @Method설명 : 프로젝트 아이디를 세션에 입력하기 위함
	* @param session
	* @return
	*/
	@RequestMapping("/command/getInstance.do")
	public String getProjectList(Model model, HttpSession session) {
		
		List<Instance> list = commandDao.getInstanceList();
		
		if(list == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data", list);	
		}
		
		return "json";
	}
	

	/**
	 * @MethodName : getImage
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : image를 추가
	 * @param imageInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/command/imageSave.do")
	public String getImage(ImageInfo imageInfo, MultipartRequest request, Model model){

		MultipartFile multipartFile = request.getFile("file");
		String filePath = "C:/test/"+multipartFile.getOriginalFilename();

		File file = new File(filePath);

		try {
			multipartFile.transferTo(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		String name = imageInfo.getName();
		String format = imageInfo.getFormat();
		String os = imageInfo.getOs();
		String desc = imageInfo.getDescription();
		
		String project_id = imageInfo.getProject_id();
		String status = imageInfo.getStatus();
		String visibility = imageInfo.getVisibility();
		String _protected = imageInfo.get_protected();
		
		
		Map<String,Object> map =commandService.getUploadImageInfo(filePath, name,format); //이미지추가
		String id= map.get("id").toString();
		String size = map.get("size").toString();
		
		imageInfo = new ImageInfo(id, name, format, size, os, desc, project_id, status, visibility, _protected);
		commandDao.insertImageInfoList(imageInfo);
		file.delete();
		model.addAttribute("vlist", commandDao.vlist());
		return "/command/image";
	}

	/**
	 * @MethodName : deleteImage
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : image를 삭제
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/imageDelete.do")
	public String deleteImage(@RequestParam("id") String id, Model model){
		commandDao.deleteImageInfoList(id);
		commandService.deleteImage("source admin-openrc; glance image-delete "+id);
		model.addAttribute("vlist", commandDao.vlist());
		return "/command/image";
	}

	/**
	 * @MethodName : getFlovorList
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : flavor list를 가지고와서 json으로 return;
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/getFlavorList.do")
	public String getFlovorList(Model model){
//		List<Flavor> list =commandService.getFlavorList("source admin-openrc; openstack flavor list");
//		List<FlavorInfo> flist = new ArrayList<FlavorInfo>();
//
//		FlavorInfo flavorInfo = null;
//		boolean flag = false;
//		Map<String,Object> map = null;
//		for(Flavor imsi : list){
//			String id = imsi.getId();
//			map =commandService.getFlavorInfo("source admin-openrc; openstack flavor show "+id);
//
//			if(map.get("swap").equals("")){
//				map.replace("swap","0");
//			}
//			
//			if(map.get("os-flavor-access:is_public").equals("True")){
//				map.replace("os-flavor-access:is_public","예");
//			}else{
//				map.replace("os-flavor-access:is_public","아니오");
//			}
//			
//			if(map.get("OS-FLV-DISABLED:disabled").equals("True")){
//				map.replace("OS-FLV-DISABLED:disabled","예");
//			}else{
//				map.replace("OS-FLV-DISABLED:disabled","아니오");
//			}
//			
//			
//			flavorInfo = new FlavorInfo(
//					map.get("id").toString(),
//					map.get("name").toString(),
//					map.get("vcpus").toString(),
//					map.get("ram").toString(),
//					map.get("disk").toString(),
//					map.get("OS-FLV-EXT-DATA:ephemeral").toString(),
//					map.get("swap").toString(),
//					map.get("rxtx_factor").toString(),
//					map.get("os-flavor-access:is_public").toString(),
//					map.get("OS-FLV-DISABLED:disabled").toString());
//			flist.add(flavorInfo);
//
//			commandDao.insertFlavorInfoList(flavorInfo);
//			
//		}	
		
		List<FlavorInfo> isCheck = commandDao.selectFlavorInfoList();
		if(isCheck == null) model.addAttribute("result","fail");
		model.addAttribute("data",isCheck);	
		return "json";
	}

	/**
	 * @MethodName : createFlavor
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : flavor를 추가 하지만 list에 같은 이름이 있을시 false를 return;
	 * @param flavorInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/createFlavor.do")
	public String createFlavor(FlavorInfo flavorInfo, Model model) {
		boolean flag = true;
		String name = flavorInfo.getName();

		List<FlavorInfo> isCheck = commandDao.flist();// 같은 이름이    
		// 있는지 검사.

		for (FlavorInfo info : isCheck) {
			if (info.getName().toString().equals(name)) {// db에 똑같은 name이 있으면 삽입
				// 불가.
				flag = false;
			}
		}
		if (flag) {
			String vcpus = flavorInfo.getVcpus();
			String ram = flavorInfo.getRam();
			String disk = flavorInfo.getRoot_disk();
			String swap = flavorInfo.getSwap_disk();

			String id = commandService.createFlavor("source admin-openrc; openstack flavor create --ram " + ram
					+ " --disk " + disk + " --swap " + swap + " --vcpus " + vcpus + " --public " + name);
			flavorInfo.setId(id);
			commandDao.insertFlavorInfoList(flavorInfo);
			System.out.println(flavorInfo);
			model.addAttribute("data", "success");
		} else {
			model.addAttribute("data", "fail");
		}
		List<FlavorInfo> flist = commandDao.flist();
		model.addAttribute("flist", flist);
		List<Vnfd> vlist=commandDao.vlist();
		model.addAttribute("vlist", vlist);
		return "command/flavor";

	}

	/**
	 * @MethodName : deleteFlavor
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : flavor를 삭제
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/deleteFlavor.do")
	public String deleteFlavor(@RequestParam("name") String name,Model model){		
		//VNFD_Table에서 해당 name이 연결되어 있지 않을 경우에만 삭제가 가능하다.
		boolean flag = true;
		List<Vnfd> list=commandDao.vlist();
		if(list!=null && list.size()>0){
			for(Vnfd vnfd : list){
				if(vnfd.getFlavor_name().equals(name)){
					flag = false; // 같은 이름이 있으면 삭제 불가능.
				}
			}
		}
		if(flag){
			String id = commandDao.selectFlavorId(name);
			jschUtil.start("source admin-openrc; openstack flavor delete "+id);
			commandDao.deleteFlavorInfo(id);
			model.addAttribute("data","success");
		}
		else{
			model.addAttribute("data","fail");
		}
		List<FlavorInfo> isCheck = commandDao.flist();
		List<Vnfd> vlist=commandDao.vlist();
		model.addAttribute("flist", isCheck);
		model.addAttribute("vlist", vlist);
		return "/command/flavor";
	}

	/**
	 * @MethodName : getPackageList
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : package를 받아 json으로 return;
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/getPackageList.do")
	public String getPackageList(Model model) {
		List<PackageInfo> list = commandDao.selectPackageInfoList();
		if(list == null) model.addAttribute("result","fail");
		model.addAttribute("data", list);
		return "json";
	}

	/**
	 * @MethodName : insertPackageInfo
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : package를 입력받아 생성 하지만 같은 이름이 있을경우 false를 return;
	 * @param packageInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/insertPackageInfo.do")
	public String insertPackageInfo(PackageInfo packageInfo, Model model) {
		List<PackageInfo> list = commandDao.plist();
		boolean flag = true;
		if(list !=null){
			for (PackageInfo info : list) { // package_name 중복 검사
				if (info.getPackage_name().equals(packageInfo.getPackage_name())) {
					flag = false;
				}
			}
		}
		if (flag) {
			commandDao.insertPackageInfo(packageInfo);
			model.addAttribute("msg", "success");

		} else {
			model.addAttribute("msg", "fail");
		}
		List<Vnfd> vlist = commandDao.vlist();
		List<PackageInfo> plist = commandDao.plist();
		model.addAttribute("plist", plist);
		model.addAttribute("vlist", vlist);
		return "/command/package";
	}

	/**
	 * @MethodName : deletePackageInfo
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : package 삭제
	 * @param packageName
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/deletePackageInfo.do")
	public String deletePackageInfo(@RequestParam("package_name") String package_name, Model model) {
		// VNFD_Table에서 해당 Package_name이 연결되어 있지 않을 경우에만 삭제가 가능하다.
		boolean flag = true;
		List<Vnfd> list = commandDao.selectVnfdList();
		if (list != null && list.size() > 0) {
			for (Vnfd vnfd : list) { // vnfd_table에 사용중인 패키지이름 조사
				if (vnfd.getPackage_name().equals(package_name)) {
					flag = false;
				}
			}
		}
		if (flag) {
			commandDao.deletePackageInfo(package_name);
			model.addAttribute("data", "success");
		}
		else {
			model.addAttribute("data", "fail");
		}
		List<PackageInfo> plist = commandDao.plist();
		model.addAttribute("plist", plist);

		return "/command/package";
	}


	/**
	 * @MethodName : getVnfdList
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnfd를 서버에서 받아서 json으로 뿌려주는 역할
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/getVnfdList.do")
	public String getVnfdList(Model model) {

		String vnfd_id = "";
		String vnfd_name = "";
		String description = "";

		List<VnfdInfo> vnfdInfoList = commandService.getVnfdList("source admin-openrc; tacker vnfd-list");
		List<Vnfd> isCheck = commandDao.vlist();

		for (VnfdInfo vnfdInfo : vnfdInfoList) {
			boolean flag = false;
			vnfd_id = vnfdInfo.getId();
			vnfd_name = vnfdInfo.getName();
			description = vnfdInfo.getDescription();

			if (isCheck != null) {
				for (Vnfd imsi : isCheck) {
					if (imsi.getVnfd_id().equals(vnfd_id)) { // id가 같은 것이 있으면  update하기 위함
						flag = true;
					}
				}
			}

			Vnfd vnfd = new Vnfd();
			vnfd.setDescription(description);
			vnfd.setVnfd_id(vnfd_id);
			vnfd.setVnfd_name(vnfd_name);
			if (flag) {
				commandDao.updateVnfd(vnfd);
			} 
			else {
				commandDao.insertVnfd(vnfd);
			}
		}
		List<Vnfd> list = commandDao.selectVnfdList(); // 최종 업데이트 된 리스트
		if(list == null) model.addAttribute("result","fail");
		model.addAttribute("data", list); //삭제할 때에 vnfd_id를 이용 
		return "json";
	}

	/**
	 * @MethodName : getVnfd
	 * @작성일 : 2016. 2. 15. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 :
	 * @param vnfd
	 * @return
	 */
	@RequestMapping(value="/command/getVnfd.do")
	public String getVnfd(Model model, Vnfd vnfd){
		Vnfd v_list = commandDao.getVnfd(vnfd);
		if(v_list != null){} else model.addAttribute("result","fail");
		
		System.out.println("v_list :" + v_list);
		
		model.addAttribute("list", v_list);
		return "json";
	}


	/**
	 * @MethodName : createVnfd
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnfd 생성
	 * @param vnfd2
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/createVnfd.do")
	public String createVnfd(Vnfd vnfd2,Model model){

		String vnfd_name=vnfd2.getVnfd_name();
		String image_name="";
		String image_id="";
		String flavor_name=vnfd2.getFlavor_name();
		String package_name=vnfd2.getPackage_name();
		String description =vnfd2.getDescription();
		String package_pathname ="";

		String []temp = vnfd2.getImage_name().split(",");;
		image_name = temp[0];
		image_id = temp[1];

		List<PackageInfo> info = commandDao.plist();

		for(PackageInfo imsi : info){
			if(imsi.getPackage_name().equals(package_name)){

				package_pathname = imsi.getPathName();
			}
		}

		String vnfd ="template_name: "+vnfd_name+"\n"
				+"description: "+description+"\n\n"
				+"service_properties: "+"\n"
				+"  Id: vcscf-vnfd"+"\n"
				+"  vendor: kbell"+"\n"
				+"  version: 1"+"\n\n"
				+"vdus: "+"\n"
				+"  vdu1: "+"\n"
				+"    id: vdu1"+"\n"
				+"    vm_image: "+image_name+"\n"
				+"    instance_type: "+flavor_name+"\n"
				+"    user_data_format: RAW"+"\n"
				+"    user_data: |"+"\n"
				+"        #!/bin/bash"+"\n"
				+"        sed -i -e '1,$ s/Defaults    requiretty/#Defaults    requiretty/' /etc/sudoers"+"\n"
				+"        echo \"my hostname is `hostname`\" > /tmp/hostname"+"\n"
				+"        echo \"`whoami`\" > /tmp/result_whoami"+"\n"
				+"        echo \"`pwd`\" > /tmp/result_pwd"+"\n"
				+"        route del default gw `route | grep default | awk '{print $2}'`"+"\n"
				+"        route add default gw 15.0.0.1"+"\n"
				+"        echo \"my ip addr is `ifconfig | grep \"15.0.0\" | sed -e 's/^.*inet //g' | awk '{print $1}'`\" > /tmp/ipaddress"+"\n"
				+"        sed -i -e '$,$ anameserver 8.8.8.8' /etc/resolv.conf"+"\n"
				+"        sed -i -e '$,$ anameserver 8.8.4.4' /etc/resolv.conf"+"\n"
				+"        service named restart"+"\n"
				+"        yum -y install wget > /tmp/result_yum"+"\n"
				+"        sudo -u centos wget ftp://centos:centos@15.0.0.110/"+package_pathname+" -O /home/centos/install_cscf.sh > /tmp/result_wget"+"\n"
				+"        chmod 744 /home/centos/install_cscf.sh"+"\n"
				+"        cd /home/centos"+"\n"
				+"        sudo -u centos ./install_cscf.sh > /tmp/result_install_cscf"+"\n\n"
				+"    network_interfaces: "+"\n"
				+"      management: "+"\n"
				+"        network: net_mgmt"+"\n"
				+"        management: True"+"\n"
				+"      pkt_in: "+"\n"
				+"        network: private-admin"+"\n"
				+"      pkt_out: "+"\n"
				+"        network: net1"+"\n\n"
				+"    placement_policy: "+"\n"
				+"      availability_zone: nova"+"\n\n"
				+"    auto-scaling: noop"+"\n\n"
				+"    monitoring_policy: noop"+"\n"
				+"    failure_policy: noop"+"\n\n"
				+"    config: "+"\n"
				+"      param0: key0"+"\n"
				+"      param1: key1";

		JSchUtil.start("source admin-openrc; echo \""+vnfd+"\" > test.yaml"); // test.yaml 파일 업로드
		Map<String,Object> map =commandService.getVnfdId("source admin-openrc; tacker vnfd-create --vnfd-file test.yaml"); //vnfd파일 생성

		String vnfd_id=map.get("id").toString();
		JSchUtil.start("source admin-openrc; rm -rf test.yaml"); // vnfd파일 제거
		commandDao.insertAllVnfd(new Vnfd(vnfd_id, vnfd_name, image_name,  flavor_name, package_name,  package_pathname, description, vnfd, image_id));
		model.addAttribute("data",new Vnfd(vnfd_id, vnfd_name, image_name, flavor_name, package_name, package_pathname, description, vnfd, image_id));
		//DB에 vnfd 입력

		List<ImageInfo> ilist = commandDao.ilist();
		model.addAttribute("image",ilist);
		List<FlavorInfo> flist = commandDao.flist();
		model.addAttribute("flavor",flist);
		List<PackageInfo> plist = commandDao.plist();
		model.addAttribute("Package", plist);

		return "/command/vnfd";

	}

	@RequestMapping("/command/getFlavor.do")
	public String getFlavor(Model model){
		
		List<FlavorInfo> flist = commandDao.flist();
		model.addAttribute("result", "success");
		model.addAttribute("network",commandDao.selectExtNetworkList());
		model.addAttribute("zone",commandDao.selectHostList());
		model.addAttribute("flavor",flist);
		model.addAttribute("image",commandDao.ilist());
		
		return "json";
	}
	
	@RequestMapping("/command/getFloatingIp.do")
	public String getFloatingIp(Model model){
		
		List<FloatingIp> ipList = commandService.getIpList("source admin-openrc; openstack floating ip list");
		
		List<FloatingIp> ipList2 = new ArrayList<>();
		
		for(int i = 0; i<ipList.size(); i++){
			System.out.println("id :" + ipList.get(i).getId() + ", ipaddr :" + ipList.get(i).getIp_addr());
			
			if(ipList.get(i).getFixed_ip().equals("None") && ipList.get(i).getPort().equals("None")){
				ipList2.add(new FloatingIp(ipList.get(i).getId(), ipList.get(i).getIp_addr(), ipList.get(i).getFixed_ip(), ipList.get(i).getPort()));
			}
		}
		
		
		model.addAttribute("result", "success");
		model.addAttribute("ip_list",ipList2);
		
		return "json";
	}
	
	/**
	 * @MethodName : deleteVnfd
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnfd삭제
	 * @param vnfd_id
	 * @return
	 */
	@RequestMapping("/command/deleteEthereum.do") //vnfd 삭제
	public String deleteEthereum(@RequestParam("idx") String idx, Model model){
		
		//Ethereum_table 에서 idx로 vnfd_id와 vnf_id를 가져와서 삭제 명령을 실행한다.
		//DB에서 해당idx를 이용하여 삭제한다.
		
		List<Ethereum> list = CommonDAO.selectEthereumInfo(idx);
		
		if(list.size() > 0){
			String result = JSchUtil.sendCommand("source admin-openrc; tacker vnf-delete " + list.get(0).getVnf_id()); //vnf삭제
			if(result.contains("success")){
				result = JSchUtil.sendCommand("source admin-openrc; tacker vnfd-delete "+list.get(0).getVnfd_id());
				if(result.contains("success")){
					CommonDAO.deleteEthereum(idx);			
				}
			}
		}

		if(commandDao.selectEthereum() == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",commandDao.selectEthereum());
		}
		
		instanceList();

		return "redirect:/command/ethereum.do";
//		return "/command/ethereum";
	}
	
	/**
	 * @MethodName : deleteVnfd
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnfd삭제
	 * @param vnfd_id
	 * @return
	 */
	@RequestMapping("/command/deleteHyperledger.do") //vnfd 삭제
	public String deleteHyperledger(@RequestParam("idx") String idx, Model model){
		
		//Ethereum_table 에서 idx로 vnfd_id와 vnf_id를 가져와서 삭제 명령을 실행한다.
		//DB에서 해당idx를 이용하여 삭제한다.
		
		List<Hyperledger> list = CommonDAO.selectHyperledgerInfo(idx);
		
		if(list.size() > 0){
			String result = JSchUtil.sendCommand("source admin-openrc; tacker vnf-delete " + list.get(0).getVnf_id()); //vnf삭제
			if(result.contains("success")){
				result = JSchUtil.sendCommand("source admin-openrc; tacker vnfd-delete "+list.get(0).getVnfd_id());
				
				if(result.contains("success")){
					CommonDAO.deleteHyperledger(idx);			
				}
			}
			
		}

		if(commandDao.selectHyperledger() == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",commandDao.selectHyperledger());
		}
		
		instanceList();

		return "redirect:/command/hlf.do";
//		return "/command/hlf";
	}
	
	private void instanceList(){
    	List<Instance> instance_list = commandService.getInstanceList("source admin-openrc; openstack server list");
    	
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
			
			Map<String, Object> map = commandService.getInstanceResourceList("source admin-openrc; openstack server show "+instance.getId());
			
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
				
				String[] img = map.get("image").toString().split("\\(");
				
				if(img.length == 1){
					image_name = img[0].trim();	
				}else if(img.length == 2){
					image_id = img[1].replace(")", "").trim();	
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
				
				Map<String, Object> url_map = commandService.getInstanceUrlResourceList("source admin-openrc; openstack console url show "+instance.getId());
				
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

	/**
	 * @MethodName : getVnfList
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnf값을 json형태로 return;
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/getVnfList.do")
	public String getVnfList(Model model) {
		List<Vnf> list = statusService.getVnf("source admin-openrc; tacker vnf-list");
		List<VnfInfo> isCheck = commandDao.selectVnfInfoList();
		String vnf_id = "";
		String vnf_name = "";
		String description = "";
		String status = "";

		for (Vnf vnf : list) {
			boolean flag = false;
			vnf_id = vnf.getId();
			vnf_name = vnf.getName();
			description = vnf.getDescription();
			status = vnf.getStatus();

			if (isCheck != null) {
				for (VnfInfo imsi : isCheck) {
					if (imsi.getVnf_id().equals(vnf_id)) {
						flag = true;
					}
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();

			if (flag) {
				map.put("vnf_id", vnf_id);
				map.put("vnf_name", vnf_name);
				map.put("status", status);
				commandDao.updateVnfInfo(map); // 기존 Id값이 존재하면 update
			} else {
				commandDao.insertVnfInfo(new VnfInfo(vnf_id, vnf_name, "", "", status, description)); // 새로운																							// insert
			}
		}
		if(isCheck == null) model.addAttribute("result","fail");
		model.addAttribute("data", commandDao.selectVnfInfoList());
		return "json";
	}

	/**
	 * @MethodName : createVnf
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnf를 Deploy
	 * @param vnf_name
	 * @param vnfd_name
	 * @param vnfd_id
	 * @param description
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/createVnf.do")
	public String createVnf(@RequestParam("vnf_name") String vnf_name,
			@RequestParam("vnfd_name") String vnfd_nam,
			@RequestParam("description") String description,
			Model model) {

		String []temp = vnfd_nam.split(",");
		String vnfd_name =temp[0];
		String vnfd_id =temp[1];
		Map<String, Object> map = commandService.getVnfInfo("source admin-openrc; tacker vnf-create --name " + vnf_name + " --vnfd-id " + vnfd_id);
		String vnf_id = map.get("id").toString();
		String status = map.get("status").toString();

		commandDao.insertVnfInfo(new VnfInfo(vnf_id, vnf_name, vnfd_name, vnfd_id, status, description));

		List<Vnfd> vlist = commandDao.vlist();
		model.addAttribute("vnfd", vlist);
		return "/command/vnf";

	}

	/**
	 * @MethodName : deleteVnf
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnf 삭제
	 * @param vnf_id
	 * @return
	 */
	@RequestMapping("/command/deleteVnf.do")
	public String deleteVnf(@RequestParam("vnf_id") String vnf_id, Model model){
		JSchUtil.start("source admin-openrc; tacker vnf-delete " + vnf_id); //vnf삭제
		commandDao.deleteVnfInfo(vnf_id);

		List<Vnfd> vlist = commandDao.vlist();
		model.addAttribute("vnfd", vlist);// 리스트박스

		return "/command/vnf";
	}


	/**
	 * @MethodName : getNetworkList
	 * @작성일 : 2016. 2. 15. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : 서버에서 네트워크 소스를 받아서 json형태로 return함
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/getNetwork.do")
	public String getNetworkList(Model model) {
		
//		List<NetList> nlist = commandService.getNetList("source admin-openrc; openstack network list");
//		
//		String network_name = "";
//		String subnet = "";
//		String private_network = "";
//		
//		for (NetList netlist : nlist) {
//			network_name = netlist.getName();
//			subnet = netlist.getSubnets().substring(0, 36);
//			
//			// public network 정보가져오기
//			Map<String, Object> map = commandService.getNetIp("source admin-openrc; openstack subnet show " + subnet);
//			
//			String subnet_name = map.get("name").toString();
//			String project_id = map.get("project_id").toString();
//			String network_id = map.get("network_id").toString();
//			String subnet_id = map.get("id").toString();
//			String cidr = map.get("cidr").toString();
//			String gateway_ip = map.get("gateway_ip").toString();
//			String allocation_pools = map.get("allocation_pools").toString();
//			String dns_nameservers = map.get("dns_nameservers").toString();
//			String enable_dhcp = map.get("enable_dhcp").toString();
//			String ip_version = map.get("ip_version").toString();
//
//			commandDao.insertNetwork(new Network(network_name, network_id, subnet_id, cidr, gateway_ip, subnet_name, project_id, allocation_pools, dns_nameservers, enable_dhcp, ip_version));
//		}
		
		if(commandDao.selectNetworkList() == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",commandDao.selectNetworkList());
		}
		return "json";
	}
	
	@RequestMapping("/command/getSubnet.do")
	public String getSubnetList(Model model, @RequestParam("subnet_id") String subnet_id) {
		
//		List<Subnet> nlist = commandService.getSubnetList("source admin-openrc; openstack subnet list");
//		
//		String id = "";
//		
//		for (Subnet netlist : nlist) {
//			id = netlist.getId();
//			
//			// public network 정보가져오기
//			Map<String, Object> map = commandService.getNetIp("source admin-openrc; openstack subnet show " + id);
//			
//			String allocation_pools = map.get("allocation_pools").toString();
//			String cidr = map.get("cidr").toString();
//			String created_at = map.get("created_at").toString();
//			String description = map.get("description").toString();
//			String dns_nameservers = map.get("dns_nameservers").toString();
//			String enable_dhcp = map.get("enable_dhcp").toString();
//			String gateway_ip = map.get("gateway_ip").toString();
//			String host_routes = map.get("host_routes").toString();
//			String ip_version = map.get("ip_version").toString();
//			String ipv6_address_mode = map.get("ipv6_address_mode").toString();
//			String ipv6_ra_mode = map.get("ipv6_ra_mode").toString();
//			String name = map.get("name").toString();
//			String network_id = map.get("network_id").toString();
//			String project_id = map.get("project_id").toString();
//			String revision_number = map.get("revision_number").toString();
//			String segment_id = map.get("segment_id").toString();
//			String service_types = map.get("service_types").toString();
//			String subnetpool_id = map.get("subnetpool_id").toString();
//			String tags = map.get("tags").toString();
////			String updated_at = map.get("updated_at").toString();
//			String updated_at = "";
//
//			commandDao.insertSubnet(new Subnet(allocation_pools, cidr, created_at, description, dns_nameservers,
//					enable_dhcp, gateway_ip, host_routes, id, ip_version,
//					ipv6_address_mode, ipv6_ra_mode, name, network_id, project_id,
//					revision_number, segment_id, service_types, subnetpool_id, tags, updated_at));
//		}
		
		if(commandDao.selectSubnetList(subnet_id) == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",commandDao.selectSubnetList(subnet_id));
		}
		return "json";
	}
	
	
	/**
	 * @MethodName : getNetworkList
	 * @작성일 : 2016. 2. 15. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : 서버에서 네트워크 소스를 받아서 json형태로 return함
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/getRouter.do")
	public String getRouterList(Model model) {
		
//		List<Route> list = commandService.getRouteList("source admin-openrc; openstack router list");
//		
//		String router_id = "";
//		
//		for (Route rl : list) {
//			router_id = rl.getId();
//			
//			// public network 정보가져오기
//			Map<String, Object> map = commandService.getNetIp("source admin-openrc; openstack router show " + router_id);
//			
//			String admin_state_up = map.get("admin_state_up").toString();
//			String availability_zone_hints = map.get("availability_zone_hints").toString();
//			String availability_zones = map.get("availability_zones").toString();
//			String created_at = map.get("created_at").toString();
//			String description = map.get("description").toString();
//			String distributed = map.get("distributed").toString();
//			String external_gateway_info = map.get("external_gateway_info").toString();
//			String flavor_id = map.get("flavor_id").toString();
//			String ha = map.get("ha").toString();
//			String id = map.get("id").toString();
//			String interfaces_info = map.get("interfaces_info").toString();
//			String name = map.get("name").toString();
//			String project_id = map.get("project_id").toString();
//			String revision_number = map.get("revision_number").toString();
//			String routes = map.get("routes").toString();
//			String status = map.get("status").toString();
//			String tags = map.get("tags").toString();
////			String updated_at = map.get("updated_at").toString();
//			String updated_at = "";
//			
//			commandDao.insertRoute(new Route(admin_state_up, availability_zone_hints, availability_zones,
//					created_at, description, distributed, external_gateway_info, flavor_id,
//					ha, id, interfaces_info, name, project_id, revision_number, routes,
//					status, tags, updated_at));
//		}

		if(commandDao.selectRouteList() == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("data",commandDao.selectRouteList());
		}
		
		return "json";
	}
	
	@RequestMapping("/command/getRouterDetail.do")
	public String getRouterDetail(Model model, @RequestParam("router_id") String router_id) {
		
		if(commandDao.selectRouteDetail(router_id) == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",commandDao.selectRouteDetail(router_id));
		}
		return "json";
	}
	
	@RequestMapping("/command/getInstanceDetail.do")
	public String getInstanceDetail(Model model, @RequestParam("instance_id") String instance_id) {
		
		if(commandDao.selectInstanceDetail(instance_id) == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",commandDao.selectInstanceDetail(instance_id));
		}
		return "json";
	}
	
	@RequestMapping("/command/getVnfDetail.do")
	public String getVnfDetail(Model model, @RequestParam("vnf_id") String vnf_id) {
		
		Map<String, Object> map = commandService.getVnfInfo("source admin-openrc; openstack vnf show " + vnf_id);
		
		String attributes = map.get("attributes").toString();
		
		String created_at = map.get("created_at").toString();
		String description = map.get("description").toString();
		String error_reason = map.get("error_reason").toString();
		String id = map.get("id").toString();
		String instance_id = map.get("instance_id").toString();
		String mgmt_url = map.get("mgmt_url").toString();
		
		String temp = mgmt_url.replace("{", "").replace("}", "").replace("\"", ""); // {, }, " 제거
		
		String[] var = temp.split(",");
		
		List<Vdu> vdu_list = new ArrayList<>();
		
		for(int i = 0; i<var.length; i++){
			String[] var2 = var[i].split(":");
			
			Vdu vdu = new Vdu();
			vdu.setVdu_name(var2[0]);
			vdu.setVdu_ip(var2[1]);
			vdu_list.add(vdu);
		}
		
		String name = map.get("name").toString();
		
		String[] vim_name = map.get("placement_attr").toString().split("=");
		String placement_attr = vim_name[1];
		String project_id = map.get("project_id").toString();
		String status = map.get("status").toString();
		String updated_at = map.get("updated_at").toString();
		String vim_id = map.get("vim_id").toString();
		String vnfd_id = map.get("vnfd_id").toString();
		
		model.addAttribute("result","success");
		model.addAttribute("attributes",attributes);
		model.addAttribute("created_at",created_at);
		model.addAttribute("description",description);
		model.addAttribute("error_reason",error_reason);
		model.addAttribute("id",id);
		model.addAttribute("instance_id",instance_id);
		model.addAttribute("mgmt_url",mgmt_url);
		model.addAttribute("name",name);
		model.addAttribute("placement_attr",placement_attr);
		model.addAttribute("project_id",project_id);
		model.addAttribute("status",status);
		model.addAttribute("updated_at",updated_at);
		model.addAttribute("vim_id",vim_id);
		model.addAttribute("vnfd_id",vnfd_id);
		model.addAttribute("vdu_list", vdu_list);
			
		return "json";
	}
	
	@RequestMapping("/command/getHyperledger.do")
	public String getHlf(Model model) {
		
		if(commandDao.selectHyperledger() == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",commandDao.selectHyperledger());
		}
		
		return "json";
	}
	
	@RequestMapping("/command/getEthereum.do")
	public String getEthereum(Model model) {
		
		if(commandDao.selectEthereum() == null) {
			model.addAttribute("result","fail");
		}else{
			model.addAttribute("result","success");
			model.addAttribute("data",commandDao.selectEthereum());
		}
		
		return "json";
	}
	
	/**
	 * @MethodName : createVnfd
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnfd 생성
	 * @param vnfd2
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/createEthereum.do")
	public String createEthereum(Ethereum ethereum, Model model){
		
		String profile_name = ethereum.getProfile_name();
		String description = ethereum.getDescription();
		int node_cnt = 0;
		int client_cnt = 0;
		String chainid = "0";
		String difficulty = "0";
		String gaslimit = "0";
		
		node_cnt = ethereum.getNode_cnt();
		client_cnt = ethereum.getClient_cnt();
		chainid = ethereum.getChainid();
		difficulty = ethereum.getDifficulty();
		gaslimit = ethereum.getGaslimit();
		
		int total_cnt = node_cnt + client_cnt;
		
		String network_select = ethereum.getNetwork_select(); //private 또는 testnet
		String network_name = ethereum.getNet_select();//
//		String flavor_select = ethereum.getFlavor_select(); //직접입력 또는 flavor_name
		
//		int num_cpus = ethereum.getNum_cpus();
//		int mem_size = ethereum.getMem_size();
//		int disk_size = ethereum.getDisk_size();
		
		String peers = "";
//		String availability_zone = ethereum.getZone_select();
		String zone_select_node_0 = ethereum.getZone_select_node_0();
		String zone_select_node_1 = ethereum.getZone_select_node_1();
		String zone_select_node_2 = ethereum.getZone_select_node_2();
		String zone_select_node_3 = ethereum.getZone_select_node_3();
		String zone_select_node_4 = ethereum.getZone_select_node_4();
		String zone_select_node_5 = ethereum.getZone_select_node_5();
		String zone_select_node_6 = ethereum.getZone_select_node_6();
		String zone_select_node_7 = ethereum.getZone_select_node_7();
		
		String zone_select_client_0 = ethereum.getZone_select_client_0();
		String zone_select_client_1 = ethereum.getZone_select_client_1();
		String zone_select_client_2 = ethereum.getZone_select_client_2();
		String zone_select_client_3 = ethereum.getZone_select_client_3();
		String zone_select_client_4 = ethereum.getZone_select_client_4();
		String zone_select_client_5 = ethereum.getZone_select_client_5();
		String zone_select_client_6 = ethereum.getZone_select_client_6();
		String zone_select_client_7 = ethereum.getZone_select_client_7();
		
		String flavor_select_node_0 = ethereum.getFlavor_select_node_0();
		String flavor_select_node_1 = ethereum.getFlavor_select_node_1();
		String flavor_select_node_2 = ethereum.getFlavor_select_node_2();
		String flavor_select_node_3 = ethereum.getFlavor_select_node_3();
		String flavor_select_node_4 = ethereum.getFlavor_select_node_4();
		String flavor_select_node_5 = ethereum.getFlavor_select_node_5();
		String flavor_select_node_6 = ethereum.getFlavor_select_node_6();
		String flavor_select_node_7 = ethereum.getFlavor_select_node_7();
		
		String flavor_select_client_0 = ethereum.getFlavor_select_client_0();
		String flavor_select_client_1 = ethereum.getFlavor_select_client_1();
		String flavor_select_client_2 = ethereum.getFlavor_select_client_2();
		String flavor_select_client_3 = ethereum.getFlavor_select_client_3();
		String flavor_select_client_4 = ethereum.getFlavor_select_client_4();
		String flavor_select_client_5 = ethereum.getFlavor_select_client_5();
		String flavor_select_client_6 = ethereum.getFlavor_select_client_6();
		String flavor_select_client_7 = ethereum.getFlavor_select_client_7();
		
		
		
		int node_num_cpus_0 = ethereum.getNode_num_cpus_0();
		int node_num_cpus_1 = ethereum.getNode_num_cpus_1();
		int node_num_cpus_2 = ethereum.getNode_num_cpus_2();
		int node_num_cpus_3 = ethereum.getNode_num_cpus_3();
		int node_num_cpus_4 = ethereum.getNode_num_cpus_4();
		int node_num_cpus_5 = ethereum.getNode_num_cpus_5();
		int node_num_cpus_6 = ethereum.getNode_num_cpus_6();
		int node_num_cpus_7 = ethereum.getNode_num_cpus_7();
		
		int node_mem_size_0 = ethereum.getNode_mem_size_0();
		int node_mem_size_1 = ethereum.getNode_mem_size_1();
		int node_mem_size_2 = ethereum.getNode_mem_size_2();
		int node_mem_size_3 = ethereum.getNode_mem_size_3();
		int node_mem_size_4 = ethereum.getNode_mem_size_4();
		int node_mem_size_5 = ethereum.getNode_mem_size_5();
		int node_mem_size_6 = ethereum.getNode_mem_size_6();
		int node_mem_size_7 = ethereum.getNode_mem_size_7();
		
		int node_disk_size_0 = ethereum.getNode_disk_size_0();
		int node_disk_size_1 = ethereum.getNode_disk_size_1();
		int node_disk_size_2 = ethereum.getNode_disk_size_2();
		int node_disk_size_3 = ethereum.getNode_disk_size_3();
		int node_disk_size_4 = ethereum.getNode_disk_size_4();
		int node_disk_size_5 = ethereum.getNode_disk_size_5();
		int node_disk_size_6 = ethereum.getNode_disk_size_6();
		int node_disk_size_7 = ethereum.getNode_disk_size_7();
		
		
		
		int client_num_cpus_0 = ethereum.getClient_num_cpus_0();
		int client_num_cpus_1 = ethereum.getClient_num_cpus_1();
		int client_num_cpus_2 = ethereum.getClient_num_cpus_2();
		int client_num_cpus_3 = ethereum.getClient_num_cpus_3();
		int client_num_cpus_4 = ethereum.getClient_num_cpus_4();
		int client_num_cpus_5 = ethereum.getClient_num_cpus_5();
		int client_num_cpus_6 = ethereum.getClient_num_cpus_6();
		int client_num_cpus_7 = ethereum.getClient_num_cpus_7();
		
		int client_mem_size_0 = ethereum.getClient_mem_size_0();
		int client_mem_size_1 = ethereum.getClient_mem_size_1();
		int client_mem_size_2 = ethereum.getClient_mem_size_2();
		int client_mem_size_3 = ethereum.getClient_mem_size_3();
		int client_mem_size_4 = ethereum.getClient_mem_size_4();
		int client_mem_size_5 = ethereum.getClient_mem_size_5();
		int client_mem_size_6 = ethereum.getClient_mem_size_6();
		int client_mem_size_7 = ethereum.getClient_mem_size_7();
		
		int client_disk_size_0 = ethereum.getClient_disk_size_0();
		int client_disk_size_1 = ethereum.getClient_disk_size_1();
		int client_disk_size_2 = ethereum.getClient_disk_size_2();
		int client_disk_size_3 = ethereum.getClient_disk_size_3();
		int client_disk_size_4 = ethereum.getClient_disk_size_4();
		int client_disk_size_5 = ethereum.getClient_disk_size_5();
		int client_disk_size_6 = ethereum.getClient_disk_size_6();
		int client_disk_size_7 = ethereum.getClient_disk_size_7();
		
		
		List<String> node_list = new ArrayList<String>();
		node_list.add(zone_select_node_0);
		node_list.add(zone_select_node_1);
		node_list.add(zone_select_node_2);
		node_list.add(zone_select_node_3);
		node_list.add(zone_select_node_4);
		node_list.add(zone_select_node_5);
		node_list.add(zone_select_node_6);
		node_list.add(zone_select_node_7);
		
		List<String> client_list = new ArrayList<String>();
		client_list.add(zone_select_client_0);
		client_list.add(zone_select_client_1);
		client_list.add(zone_select_client_2);
		client_list.add(zone_select_client_3);
		client_list.add(zone_select_client_4);
		client_list.add(zone_select_client_5);
		client_list.add(zone_select_client_6);
		client_list.add(zone_select_client_7);
		
		
		
		List<String> flavor_node_list = new ArrayList<String>();
		flavor_node_list.add(flavor_select_node_0);
		flavor_node_list.add(flavor_select_node_1);
		flavor_node_list.add(flavor_select_node_2);
		flavor_node_list.add(flavor_select_node_3);
		flavor_node_list.add(flavor_select_node_4);
		flavor_node_list.add(flavor_select_node_5);
		flavor_node_list.add(flavor_select_node_6);
		flavor_node_list.add(flavor_select_node_7);
		
		List<String> flavor_client_list = new ArrayList<String>();
		flavor_client_list.add(flavor_select_client_0);
		flavor_client_list.add(flavor_select_client_1);
		flavor_client_list.add(flavor_select_client_2);
		flavor_client_list.add(flavor_select_client_3);
		flavor_client_list.add(flavor_select_client_4);
		flavor_client_list.add(flavor_select_client_5);
		flavor_client_list.add(flavor_select_client_6);
		flavor_client_list.add(flavor_select_client_7);
		
		
		List<Resource> node_resource_list = new ArrayList<Resource>();
		node_resource_list.add(new Resource(node_num_cpus_0, node_mem_size_0, node_disk_size_0));
		node_resource_list.add(new Resource(node_num_cpus_1, node_mem_size_1, node_disk_size_1));
		node_resource_list.add(new Resource(node_num_cpus_2, node_mem_size_2, node_disk_size_2));
		node_resource_list.add(new Resource(node_num_cpus_3, node_mem_size_3, node_disk_size_3));
		node_resource_list.add(new Resource(node_num_cpus_4, node_mem_size_4, node_disk_size_4));
		node_resource_list.add(new Resource(node_num_cpus_5, node_mem_size_5, node_disk_size_5));
		node_resource_list.add(new Resource(node_num_cpus_6, node_mem_size_6, node_disk_size_6));
		node_resource_list.add(new Resource(node_num_cpus_7, node_mem_size_7, node_disk_size_7));
		
		List<Resource> client_resource_list = new ArrayList<Resource>();
		client_resource_list.add(new Resource(client_num_cpus_0, client_mem_size_0, client_disk_size_0));
		client_resource_list.add(new Resource(client_num_cpus_1, client_mem_size_1, client_disk_size_1));
		client_resource_list.add(new Resource(client_num_cpus_2, client_mem_size_2, client_disk_size_2));
		client_resource_list.add(new Resource(client_num_cpus_3, client_mem_size_3, client_disk_size_3));
		client_resource_list.add(new Resource(client_num_cpus_4, client_mem_size_4, client_disk_size_4));
		client_resource_list.add(new Resource(client_num_cpus_5, client_mem_size_5, client_disk_size_5));
		client_resource_list.add(new Resource(client_num_cpus_6, client_mem_size_6, client_disk_size_6));
		client_resource_list.add(new Resource(client_num_cpus_7, client_mem_size_7, client_disk_size_7));
		
		
		
		String ip_address = "";
		
		StringBuffer sb = new StringBuffer(); 
		sb.append("tosca_definitions_version: tosca_simple_profile_for_nfv_1_0_0\n\n");
				
		sb.append("description: desc_"+description+"\n\n");
				
		sb.append("metadata: "+"\n");
		sb.append("  template_name: "+profile_name+"-vnfd"+"\n\n");
				
		sb.append("topology_template: "+"\n");
		sb.append("  node_templates: "+"\n");
				
		for(int i = 0; i<node_cnt; i++){
			
			sb.append("    VDU"+(i+1)+":" +"\n");
			sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
			
			if(flavor_node_list.get(i).equals("직접입력")){
				/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
				sb.append("      capabilities:"+"\n");
				sb.append("        nfv_compute:"+"\n");
				sb.append("          properties:"+"\n");
				sb.append("            num_cpus: "+node_resource_list.get(i).getNum_cpus()+"\n");
				sb.append("            mem_size: "+node_resource_list.get(i).getMem_size()+" MB\n");
				sb.append("            disk_size: "+node_resource_list.get(i).getDisk_size()+" GB\n");
				/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
			}
			
			sb.append("      properties:"+"\n");
			sb.append("        image: "+"ubuntu-geth-ethereum-1.8.15-base-16.04-LTS\n");
			sb.append("        name: node"+i+"("+profile_name+")\n");
			
			if(!flavor_node_list.get(i).equals("직접입력")){
				/* Flavor를 선택했을 경우 시작!!*/
				sb.append("        flavor: "+flavor_node_list.get(i)+"\n");
				/* Flavor를 선택했을 경우 끝!!*/	
			}
			
			sb.append("        availability_zone: "+node_list.get(i)+"\n");
			sb.append("        mgmt_driver: noop"+"\n");
			sb.append("        config: |"+"\n");
			sb.append("          param0: key1\n");
			sb.append("          param1: key2\n");
			
			if(network_select.equals("private")){
				
				sb.append("        user_data_format: RAW"+"\n");
				sb.append("        user_data: |"+"\n");
				sb.append("          #!/bin/sh"+"\n");
				sb.append("          mkdir /root/genesis"+"\n");
				sb.append("          echo \"'\"'{");
				sb.append("                     '\\'n'\\''\"'config'\\''\"':{'\\'n");
				sb.append("				        '\\''\"'chainId'\\''\"':"+chainid+",'\\'n");
				sb.append("				        '\\''\"'homesteadBlock'\\''\"':0,'\\'n");
				sb.append("				        '\\''\"'eip155Block'\\''\"':0,");
				sb.append("				        '\\''\"'eip158Block'\\''\"':0'\\'n");
				sb.append("				},'\\'n");
				sb.append("				    '\\''\"'difficulty'\\''\"':'\\''\"'"+difficulty+"'\\''\"','\\'n");
				sb.append("				    '\\''\"'gasLimit'\\''\"':'\\''\"'"+gaslimit+"'\\''\"','\\'n");
				sb.append("				    '\\''\"'alloc'\\''\"':{},");
				sb.append("				    '\\''\"'coinbase'\\''\"':'\\''\"'0x00000000000000000000000000000000000000'\\''\"','\\'n");
				sb.append("				    '\\''\"'extraData'\\''\"':'\\''\"''\\''\"',");
				sb.append("				    '\\''\"'nonce'\\''\"':'\\''\"'0x00000000000000000042'\\''\"','\\'n");
				sb.append("				    '\\''\"'mixhash'\\''\"':'\\''\"'0x0000000000000000000000000000000000000000000000000000'\\''\"',");
				sb.append("				    '\\''\"'parentHash'\\''\"':'\\''\"'0x0000000000000000000000000000000000000000000000000000'\\''\"',");
				sb.append("				    '\\''\"'timestamp'\\''\"':'\\''\"'0x00'\\''\"''\\'n");
				sb.append("} '\"'\" > /root/genesis/genesis.json");
				sb.append("\n");
			}
		}
		
		for(int i = 0; i<client_cnt; i++){
			sb.append("    VDU"+(node_cnt+i+1)+":" +"\n");
			sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
			
			if(flavor_client_list.get(i).equals("직접입력")){
				/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
				sb.append("      capabilities:"+"\n");
				sb.append("        nfv_compute:"+"\n");
				sb.append("          properties:"+"\n");
				sb.append("            num_cpus: "+client_resource_list.get(i).getNum_cpus()+"\n");
				sb.append("            mem_size: "+client_resource_list.get(i).getMem_size()+" MB\n");
				sb.append("            disk_size: "+client_resource_list.get(i).getDisk_size()+" GB\n");
				/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
			}
			
			sb.append("      properties:"+"\n");
			sb.append("        image: "+"ubuntu-geth-ethereum-1.8.15-base-16.04-LTS\n");
			sb.append("        name: client"+i+"("+profile_name+")\n");
			
			if(!flavor_client_list.get(i).equals("직접입력")){
				/* Flavor를 선택했을 경우 시작!!*/
				sb.append("        flavor: "+flavor_client_list.get(i)+"\n");
				/* Flavor를 선택했을 경우 끝!!*/	
			}
			
			sb.append("        availability_zone: "+client_list.get(i)+"\n");
			sb.append("        mgmt_driver: noop"+"\n");
			sb.append("        config: |"+"\n");
			sb.append("          param0: key1\n");
			sb.append("          param1: key2\n");
			
			if(network_select.equals("private")){
				sb.append("        user_data_format: RAW"+"\n");
				sb.append("        user_data: |"+"\n");
				sb.append("          #!/bin/sh"+"\n");
				sb.append("          mkdir /root/genesis"+"\n");
				sb.append("          echo \"'\"'{");
				sb.append("                     '\\'n'\\''\"'config'\\''\"':{'\\'n");
				sb.append("				        '\\''\"'chainId'\\''\"':"+chainid+",'\\'n");
				sb.append("				        '\\''\"'homesteadBlock'\\''\"':0,'\\'n");
				sb.append("				        '\\''\"'eip155Block'\\''\"':0,");
				sb.append("				        '\\''\"'eip158Block'\\''\"':0'\\'n");
				sb.append("				},'\\'n");
				sb.append("				    '\\''\"'difficulty'\\''\"':'\\''\"'"+difficulty+"'\\''\"','\\'n");
				sb.append("				    '\\''\"'gasLimit'\\''\"':'\\''\"'"+gaslimit+"'\\''\"','\\'n");
				sb.append("				    '\\''\"'alloc'\\''\"':{},");
				sb.append("				    '\\''\"'coinbase'\\''\"':'\\''\"'0x00000000000000000000000000000000000000'\\''\"','\\'n");
				sb.append("				    '\\''\"'extraData'\\''\"':'\\''\"''\\''\"',");
				sb.append("				    '\\''\"'nonce'\\''\"':'\\''\"'0x00000000000000000042'\\''\"','\\'n");
				sb.append("				    '\\''\"'mixhash'\\''\"':'\\''\"'0x0000000000000000000000000000000000000000000000000000'\\''\"',");
				sb.append("				    '\\''\"'parentHash'\\''\"':'\\''\"'0x0000000000000000000000000000000000000000000000000000'\\''\"',");
				sb.append("				    '\\''\"'timestamp'\\''\"':'\\''\"'0x00'\\''\"''\\'n");
				sb.append("} '\"'\" > /root/genesis/genesis.json");
				sb.append("\n");
			}
		}
		
		for(int i = 0; i<node_cnt; i++){
			sb.append("    CP"+(i+1)+":" +"\n");
			sb.append("      type: tosca.nodes.nfv.CP.Tacker\n");
			sb.append("      properties:"+"\n");
			sb.append("        management: true"+"\n");
			/* IP를 직접입력 받았을 경우 시작*/
//			sb.append("        ip_address: "+ip_address+"\n");
			/* IP를 직접입력 받았을 경우 끝*/
			sb.append("        order: 0\n");
			sb.append("        anti_spoofing_protection: false\n");
			sb.append("      requirements:"+"\n");
			sb.append("        - virtualLink:"+"\n");
			sb.append("          node: VL1\n");
			sb.append("        - virtualBinding:"+"\n");
			sb.append("          node: VDU"+(i+1)+"\n");
		}
		
		for(int i = 0; i<client_cnt; i++){
			sb.append("    CP"+(node_cnt+i+1)+":" +"\n");
			sb.append("      type: tosca.nodes.nfv.CP.Tacker\n");
			sb.append("      properties:"+"\n");
			sb.append("        management: true"+"\n");
			/* IP를 직접입력 받았을 경우 시작*/
//			sb.append("        ip_address: "+ip_address+"\n");
			/* IP를 직접입력 받았을 경우 끝*/
			sb.append("        order: 0\n");
			sb.append("        anti_spoofing_protection: false\n");
			sb.append("      requirements:"+"\n");
			sb.append("        - virtualLink:"+"\n");
			sb.append("          node: VL1\n");
			sb.append("        - virtualBinding:"+"\n");
			sb.append("          node: VDU"+(node_cnt+i+1)+"\n");
		}
		
		sb.append("    VL1:" +"\n");
		sb.append("      type: tosca.nodes.nfv.VL\n");
		sb.append("      properties:"+"\n");
		sb.append("        network_name: "+network_name+"\n");
		sb.append("        vendor: Tacker"+"\n");
		
		JSchUtil.sendCommand("source admin-openrc; echo \""+sb.toString()+"\" > "+profile_name+".yaml"); // test.yaml 파일 업로드
		
		Map<String,Object> vnfd_map = commandService.getVnfdId("source admin-openrc; tacker vnfd-create --vnfd-file "+profile_name+".yaml "+profile_name+"-vnfd"); //vnfd파일 생성
		if(vnfd_map != null){
			String vnfd_id = vnfd_map.get("id").toString();
			
			Map<String,Object> vnf_map = commandService.getVnfInfo("source admin-openrc; tacker vnf-create --vnfd-name "+profile_name+"-vnfd "+profile_name+"-vnf"); //vnfd파일 생성
			
			if(vnf_map != null){
				String vnf_id = vnf_map.get("id").toString();
				
				JSchUtil.start("source admin-openrc; rm -rf "+profile_name+".yaml"); // vnfd파일 제거
				
				commandDao.insertAllEthereum(new Ethereum(vnfd_id, vnf_id, profile_name, description, network_select, node_cnt, client_cnt, 0, 0, 0, network_select, network_name, chainid, difficulty, gaslimit, "", "", "PENDING_CREATE"));
				
				model.addAttribute("result", "success");
				//DB에 vnfd 입력
				
				instanceList();	
			}
		}

		return "redirect:/command/ethereum.do";

	}
	
	@RequestMapping("/command/createHyperledger.do")
	public String createHyperledger(Hyperledger hyperledger, Model model){
		
		String profile_name = hyperledger.getProfile_name();
		String description = hyperledger.getDescription();
		int orderer_cnt = 0;
		int peer_org_cnt = 0;
		int org_peer_cnt = 0;
		int batch_timeout = 0;
		int max_message_cnt = 0;
		int absolute_max_bytes = 0;
		int preferred_max_bytes = 0;
		
		orderer_cnt = hyperledger.getOrderer_cnt();
		peer_org_cnt = hyperledger.getPeer_org_cnt();
		org_peer_cnt = hyperledger.getOrg_peer_cnt();
		batch_timeout = hyperledger.getBatch_timeout();
		max_message_cnt = hyperledger.getMax_message_cnt();
		absolute_max_bytes = hyperledger.getAbsolute_max_bytes();
		preferred_max_bytes = hyperledger.getPreferred_max_bytes();
		
		int total_cnt = orderer_cnt + peer_org_cnt;
		
		int org_orderer_cnt = orderer_cnt;
		
		String orderer_type = hyperledger.getOrderer_type(); //kafka 또는 solo
		String network_name = hyperledger.getNetwork_select();//
//		String flavor_name = hyperledger.getFlavor_select(); //직접입력 또는 flavor_name
		
//		int num_cpus = hyperledger.getNum_cpus();
//		int mem_size = hyperledger.getMem_size();
//		int disk_size = hyperledger.getDisk_size();
		
		String peers = "";
//		String availability_zone = hyperledger.getZone_select();
		
		String zone_select_adminordererorg0 = hyperledger.getZone_select_adminordererorg0();
		
		String zone_select_orderer_0 = hyperledger.getZone_select_orderer_0();
		String zone_select_orderer_1 = hyperledger.getZone_select_orderer_1();
		String zone_select_orderer_2 = hyperledger.getZone_select_orderer_2();
		String zone_select_orderer_3 = hyperledger.getZone_select_orderer_3();
		
		String zone_select_kafka = hyperledger.getZone_select_kafka();
		String zone_select_bcmanager = hyperledger.getZone_select_bcmanager();
		
		String zone_select_peer_0 = hyperledger.getZone_select_peer_0();
		String zone_select_peer_1 = hyperledger.getZone_select_peer_1();
		String zone_select_peer_2 = hyperledger.getZone_select_peer_2();
		String zone_select_peer_3 = hyperledger.getZone_select_peer_3();
		String zone_select_peer_4 = hyperledger.getZone_select_peer_4();
		String zone_select_peer_5 = hyperledger.getZone_select_peer_5();
		String zone_select_peer_6 = hyperledger.getZone_select_peer_6();
		String zone_select_peer_7 = hyperledger.getZone_select_peer_7();
		String zone_select_peer_8 = hyperledger.getZone_select_peer_8();
		String zone_select_peer_9 = hyperledger.getZone_select_peer_9();
		String zone_select_peer_10 = hyperledger.getZone_select_peer_10();
		String zone_select_peer_11 = hyperledger.getZone_select_peer_11();
		String zone_select_peer_12 = hyperledger.getZone_select_peer_12();
		String zone_select_peer_13 = hyperledger.getZone_select_peer_13();
		String zone_select_peer_14 = hyperledger.getZone_select_peer_14();
		String zone_select_peer_15 = hyperledger.getZone_select_peer_15();
		
		String zone_select_adminorg_0 = hyperledger.getZone_select_adminorg_0();
		String zone_select_adminorg_1 = hyperledger.getZone_select_adminorg_1();
		String zone_select_adminorg_2 = hyperledger.getZone_select_adminorg_2();
		String zone_select_adminorg_3 = hyperledger.getZone_select_adminorg_3();
		
		
		List<String> orderer_zone_list = new ArrayList<String>();
		orderer_zone_list.add(zone_select_orderer_0);
		orderer_zone_list.add(zone_select_orderer_1);
		orderer_zone_list.add(zone_select_orderer_2);
		orderer_zone_list.add(zone_select_orderer_3);
		
		List<String> peer_zone_list = new ArrayList<String>();
		peer_zone_list.add(zone_select_peer_0);
		peer_zone_list.add(zone_select_peer_1);
		peer_zone_list.add(zone_select_peer_2);
		peer_zone_list.add(zone_select_peer_3);
		peer_zone_list.add(zone_select_peer_4);
		peer_zone_list.add(zone_select_peer_5);
		peer_zone_list.add(zone_select_peer_6);
		peer_zone_list.add(zone_select_peer_7);
		peer_zone_list.add(zone_select_peer_8);
		peer_zone_list.add(zone_select_peer_9);
		peer_zone_list.add(zone_select_peer_10);
		peer_zone_list.add(zone_select_peer_11);
		peer_zone_list.add(zone_select_peer_12);
		peer_zone_list.add(zone_select_peer_13);
		peer_zone_list.add(zone_select_peer_14);
		peer_zone_list.add(zone_select_peer_15);
		
		List<String> adminorg_zone_list = new ArrayList<String>();
		adminorg_zone_list.add(zone_select_adminorg_0);
		adminorg_zone_list.add(zone_select_adminorg_1);
		adminorg_zone_list.add(zone_select_adminorg_2);
		adminorg_zone_list.add(zone_select_adminorg_3);
		
		
		
		/** flavor **/
		String flavor_select_adminordererorg0 = hyperledger.getFlavor_select_adminordererorg0();
		
		String flavor_select_orderer_0 = hyperledger.getFlavor_select_orderer_0();
		String flavor_select_orderer_1 = hyperledger.getFlavor_select_orderer_1();
		String flavor_select_orderer_2 = hyperledger.getFlavor_select_orderer_2();
		String flavor_select_orderer_3 = hyperledger.getFlavor_select_orderer_3();
		
		String flavor_select_kafka = hyperledger.getFlavor_select_kafka();
		String flavor_select_bcmanager = hyperledger.getFlavor_select_bcmanager();
		
		String flavor_select_peer_0 = hyperledger.getFlavor_select_peer_0();
		String flavor_select_peer_1 = hyperledger.getFlavor_select_peer_1();
		String flavor_select_peer_2 = hyperledger.getFlavor_select_peer_2();
		String flavor_select_peer_3 = hyperledger.getFlavor_select_peer_3();
		String flavor_select_peer_4 = hyperledger.getFlavor_select_peer_4();
		String flavor_select_peer_5 = hyperledger.getFlavor_select_peer_5();
		String flavor_select_peer_6 = hyperledger.getFlavor_select_peer_6();
		String flavor_select_peer_7 = hyperledger.getFlavor_select_peer_7();
		String flavor_select_peer_8 = hyperledger.getFlavor_select_peer_8();
		String flavor_select_peer_9 = hyperledger.getFlavor_select_peer_9();
		String flavor_select_peer_10 = hyperledger.getFlavor_select_peer_10();
		String flavor_select_peer_11 = hyperledger.getFlavor_select_peer_11();
		String flavor_select_peer_12 = hyperledger.getFlavor_select_peer_12();
		String flavor_select_peer_13 = hyperledger.getFlavor_select_peer_13();
		String flavor_select_peer_14 = hyperledger.getFlavor_select_peer_14();
		String flavor_select_peer_15 = hyperledger.getFlavor_select_peer_15();
		
		String flavor_select_adminorg_0 = hyperledger.getFlavor_select_adminorg_0();
		String flavor_select_adminorg_1 = hyperledger.getFlavor_select_adminorg_1();
		String flavor_select_adminorg_2 = hyperledger.getFlavor_select_adminorg_2();
		String flavor_select_adminorg_3 = hyperledger.getFlavor_select_adminorg_3();
		
		List<String> orderer_flavor_list = new ArrayList<String>();
		orderer_flavor_list.add(flavor_select_orderer_0);
		orderer_flavor_list.add(flavor_select_orderer_1);
		orderer_flavor_list.add(flavor_select_orderer_2);
		orderer_flavor_list.add(flavor_select_orderer_3);
		
		List<String> peer_flavor_list = new ArrayList<String>();
		peer_flavor_list.add(flavor_select_peer_0);
		peer_flavor_list.add(flavor_select_peer_1);
		peer_flavor_list.add(flavor_select_peer_2);
		peer_flavor_list.add(flavor_select_peer_3);
		peer_flavor_list.add(flavor_select_peer_4);
		peer_flavor_list.add(flavor_select_peer_5);
		peer_flavor_list.add(flavor_select_peer_6);
		peer_flavor_list.add(flavor_select_peer_7);
		peer_flavor_list.add(flavor_select_peer_8);
		peer_flavor_list.add(flavor_select_peer_9);
		peer_flavor_list.add(flavor_select_peer_10);
		peer_flavor_list.add(flavor_select_peer_11);
		peer_flavor_list.add(flavor_select_peer_12);
		peer_flavor_list.add(flavor_select_peer_13);
		peer_flavor_list.add(flavor_select_peer_14);
		peer_flavor_list.add(flavor_select_peer_15);
		
		List<String> adminorg_flavor_list = new ArrayList<String>();
		adminorg_flavor_list.add(flavor_select_adminorg_0);
		adminorg_flavor_list.add(flavor_select_adminorg_1);
		adminorg_flavor_list.add(flavor_select_adminorg_2);
		adminorg_flavor_list.add(flavor_select_adminorg_3);
		
		
		
		String adminordererorg_num_cpus_0 = hyperledger.getAdminordererorg0_num_cpus();
		
		String orderer_num_cpus_0 = hyperledger.getOrderer_num_cpus_0();
		String orderer_num_cpus_1 = hyperledger.getOrderer_num_cpus_1();
		String orderer_num_cpus_2 = hyperledger.getOrderer_num_cpus_2();
		String orderer_num_cpus_3 = hyperledger.getOrderer_num_cpus_3();
		
		String kafka_num_cpus = hyperledger.getKafka_num_cpus();
		String bcmanager_num_cpus = hyperledger.getBcmanager_num_cpus();
		
		String peer_num_cpus_0 = hyperledger.getPeer_num_cpus_0();
		String peer_num_cpus_1 = hyperledger.getPeer_num_cpus_1();
		String peer_num_cpus_2 = hyperledger.getPeer_num_cpus_2();
		String peer_num_cpus_3 = hyperledger.getPeer_num_cpus_3();
		String peer_num_cpus_4 = hyperledger.getPeer_num_cpus_4();
		String peer_num_cpus_5 = hyperledger.getPeer_num_cpus_5();
		String peer_num_cpus_6 = hyperledger.getPeer_num_cpus_6();
		String peer_num_cpus_7 = hyperledger.getPeer_num_cpus_7();
		String peer_num_cpus_8 = hyperledger.getPeer_num_cpus_8();
		String peer_num_cpus_9 = hyperledger.getPeer_num_cpus_9();
		String peer_num_cpus_10 = hyperledger.getPeer_num_cpus_10();
		String peer_num_cpus_11 = hyperledger.getPeer_num_cpus_11();
		String peer_num_cpus_12 = hyperledger.getPeer_num_cpus_12();
		String peer_num_cpus_13 = hyperledger.getPeer_num_cpus_13();
		String peer_num_cpus_14 = hyperledger.getPeer_num_cpus_14();
		String peer_num_cpus_15 = hyperledger.getPeer_num_cpus_15();
		
		String adminorg_num_cpus_0 = hyperledger.getAdminorg_num_cpus_0();
		String adminorg_num_cpus_1 = hyperledger.getAdminorg_num_cpus_1();
		String adminorg_num_cpus_2 = hyperledger.getAdminorg_num_cpus_2();
		String adminorg_num_cpus_3 = hyperledger.getAdminorg_num_cpus_3();
		
		String adminordererorg_mem_size_0 = hyperledger.getAdminordererorg0_mem_size();
		String orderer_mem_size_0 = hyperledger.getOrderer_mem_size_0();
		String orderer_mem_size_1 = hyperledger.getOrderer_mem_size_1();
		String orderer_mem_size_2 = hyperledger.getOrderer_mem_size_2();
		String orderer_mem_size_3 = hyperledger.getOrderer_mem_size_3();
		
		String kafka_mem_size = hyperledger.getKafka_mem_size();
		String bcmanager_mem_size = hyperledger.getBcmanager_mem_size();
		
		String peer_mem_size_0 = hyperledger.getPeer_mem_size_0();
		String peer_mem_size_1 = hyperledger.getPeer_mem_size_1();
		String peer_mem_size_2 = hyperledger.getPeer_mem_size_2();
		String peer_mem_size_3 = hyperledger.getPeer_mem_size_3();
		String peer_mem_size_4 = hyperledger.getPeer_mem_size_4();
		String peer_mem_size_5 = hyperledger.getPeer_mem_size_5();
		String peer_mem_size_6 = hyperledger.getPeer_mem_size_6();
		String peer_mem_size_7 = hyperledger.getPeer_mem_size_7();
		String peer_mem_size_8 = hyperledger.getPeer_mem_size_8();
		String peer_mem_size_9 = hyperledger.getPeer_mem_size_9();
		String peer_mem_size_10 = hyperledger.getPeer_mem_size_10();
		String peer_mem_size_11 = hyperledger.getPeer_mem_size_11();
		String peer_mem_size_12 = hyperledger.getPeer_mem_size_12();
		String peer_mem_size_13 = hyperledger.getPeer_mem_size_13();
		String peer_mem_size_14 = hyperledger.getPeer_mem_size_14();
		String peer_mem_size_15 = hyperledger.getPeer_mem_size_15();
		
		String adminorg_mem_size_0 = hyperledger.getAdminorg_mem_size_0();
		String adminorg_mem_size_1 = hyperledger.getAdminorg_mem_size_1();
		String adminorg_mem_size_2 = hyperledger.getAdminorg_mem_size_2();
		String adminorg_mem_size_3 = hyperledger.getAdminorg_mem_size_3();
		
		String adminordererorg_disk_size_0 = hyperledger.getAdminordererorg0_disk_size();
		String orderer_disk_size_0 = hyperledger.getOrderer_disk_size_0();
		String orderer_disk_size_1 = hyperledger.getOrderer_disk_size_1();
		String orderer_disk_size_2 = hyperledger.getOrderer_disk_size_2();
		String orderer_disk_size_3 = hyperledger.getOrderer_disk_size_3();
		
		String kafka_disk_size = hyperledger.getKafka_disk_size();
		String bcmanager_disk_size = hyperledger.getBcmanager_disk_size();
		
		String peer_disk_size_0 = hyperledger.getPeer_disk_size_0();
		String peer_disk_size_1 = hyperledger.getPeer_disk_size_1();
		String peer_disk_size_2 = hyperledger.getPeer_disk_size_2();
		String peer_disk_size_3 = hyperledger.getPeer_disk_size_3();
		String peer_disk_size_4 = hyperledger.getPeer_disk_size_4();
		String peer_disk_size_5 = hyperledger.getPeer_disk_size_5();
		String peer_disk_size_6 = hyperledger.getPeer_disk_size_6();
		String peer_disk_size_7 = hyperledger.getPeer_disk_size_7();
		String peer_disk_size_8 = hyperledger.getPeer_disk_size_8();
		String peer_disk_size_9 = hyperledger.getPeer_disk_size_9();
		String peer_disk_size_10 = hyperledger.getPeer_disk_size_10();
		String peer_disk_size_11 = hyperledger.getPeer_disk_size_11();
		String peer_disk_size_12 = hyperledger.getPeer_disk_size_12();
		String peer_disk_size_13 = hyperledger.getPeer_disk_size_13();
		String peer_disk_size_14 = hyperledger.getPeer_disk_size_14();
		String peer_disk_size_15 = hyperledger.getPeer_disk_size_15();
		
		String adminorg_disk_size_0 = hyperledger.getAdminorg_disk_size_0();
		String adminorg_disk_size_1 = hyperledger.getAdminorg_disk_size_1();
		String adminorg_disk_size_2 = hyperledger.getAdminorg_disk_size_2();
		String adminorg_disk_size_3 = hyperledger.getAdminorg_disk_size_3();
		
		
		List<String> orderer_num_cpu_list = new ArrayList<String>();
		orderer_num_cpu_list.add(orderer_num_cpus_0);
		orderer_num_cpu_list.add(orderer_num_cpus_1);
		orderer_num_cpu_list.add(orderer_num_cpus_2);
		orderer_num_cpu_list.add(orderer_num_cpus_3);
		
		List<String> peer_num_cpu_list = new ArrayList<String>();
		peer_num_cpu_list.add(peer_num_cpus_0);
		peer_num_cpu_list.add(peer_num_cpus_1);
		peer_num_cpu_list.add(peer_num_cpus_2);
		peer_num_cpu_list.add(peer_num_cpus_3);
		peer_num_cpu_list.add(peer_num_cpus_4);
		peer_num_cpu_list.add(peer_num_cpus_5);
		peer_num_cpu_list.add(peer_num_cpus_6);
		peer_num_cpu_list.add(peer_num_cpus_7);
		peer_num_cpu_list.add(peer_num_cpus_8);
		peer_num_cpu_list.add(peer_num_cpus_9);
		peer_num_cpu_list.add(peer_num_cpus_10);
		peer_num_cpu_list.add(peer_num_cpus_11);
		peer_num_cpu_list.add(peer_num_cpus_12);
		peer_num_cpu_list.add(peer_num_cpus_13);
		peer_num_cpu_list.add(peer_num_cpus_14);
		peer_num_cpu_list.add(peer_num_cpus_15);
		
		List<String> adminorg_num_cpu_list = new ArrayList<String>();
		adminorg_num_cpu_list.add(adminorg_num_cpus_0);
		adminorg_num_cpu_list.add(adminorg_num_cpus_1);
		adminorg_num_cpu_list.add(adminorg_num_cpus_2);
		adminorg_num_cpu_list.add(adminorg_num_cpus_3);
		
		
		List<String> orderer_mem_size_list = new ArrayList<String>();
		orderer_mem_size_list.add(orderer_mem_size_0);
		orderer_mem_size_list.add(orderer_mem_size_1);
		orderer_mem_size_list.add(orderer_mem_size_2);
		orderer_mem_size_list.add(orderer_mem_size_3);
		
		List<String> peer_mem_size_list = new ArrayList<String>();
		peer_mem_size_list.add(peer_mem_size_0);
		peer_mem_size_list.add(peer_mem_size_1);
		peer_mem_size_list.add(peer_mem_size_2);
		peer_mem_size_list.add(peer_mem_size_3);
		peer_mem_size_list.add(peer_mem_size_4);
		peer_mem_size_list.add(peer_mem_size_5);
		peer_mem_size_list.add(peer_mem_size_6);
		peer_mem_size_list.add(peer_mem_size_7);
		peer_mem_size_list.add(peer_mem_size_8);
		peer_mem_size_list.add(peer_mem_size_9);
		peer_mem_size_list.add(peer_mem_size_10);
		peer_mem_size_list.add(peer_mem_size_11);
		peer_mem_size_list.add(peer_mem_size_12);
		peer_mem_size_list.add(peer_mem_size_13);
		peer_mem_size_list.add(peer_mem_size_14);
		peer_mem_size_list.add(peer_mem_size_15);
		
		List<String> adminorg_mem_size_list = new ArrayList<String>();
		adminorg_mem_size_list.add(adminorg_mem_size_0);
		adminorg_mem_size_list.add(adminorg_mem_size_1);
		adminorg_mem_size_list.add(adminorg_mem_size_2);
		adminorg_mem_size_list.add(adminorg_mem_size_3);
		
		List<String> orderer_disk_size_list = new ArrayList<String>();
		orderer_disk_size_list.add(orderer_disk_size_0);
		orderer_disk_size_list.add(orderer_disk_size_1);
		orderer_disk_size_list.add(orderer_disk_size_2);
		orderer_disk_size_list.add(orderer_disk_size_3);
		
		List<String> peer_disk_size_list = new ArrayList<String>();
		peer_disk_size_list.add(peer_disk_size_0);
		peer_disk_size_list.add(peer_disk_size_1);
		peer_disk_size_list.add(peer_disk_size_2);
		peer_disk_size_list.add(peer_disk_size_3);
		peer_disk_size_list.add(peer_disk_size_4);
		peer_disk_size_list.add(peer_disk_size_5);
		peer_disk_size_list.add(peer_disk_size_6);
		peer_disk_size_list.add(peer_disk_size_7);
		peer_disk_size_list.add(peer_disk_size_8);
		peer_disk_size_list.add(peer_disk_size_9);
		peer_disk_size_list.add(peer_disk_size_10);
		peer_disk_size_list.add(peer_disk_size_11);
		peer_disk_size_list.add(peer_disk_size_12);
		peer_disk_size_list.add(peer_disk_size_13);
		peer_disk_size_list.add(peer_disk_size_14);
		peer_disk_size_list.add(peer_disk_size_15);
		
		List<String> adminorg_disk_size_list = new ArrayList<String>();
		adminorg_disk_size_list.add(adminorg_disk_size_0);
		adminorg_disk_size_list.add(adminorg_disk_size_1);
		adminorg_disk_size_list.add(adminorg_disk_size_2);
		adminorg_disk_size_list.add(adminorg_disk_size_3);
		
		String ip_address = "";
		
		
		/* DB삽입 후 생성된 profile_id(idx)값을 user_data 스크립트에 써주기 위함. */
		int profile_id = CommonDAO.insertHyperledger(new Hyperledger(profile_name, description, 
				orderer_cnt, peer_org_cnt, 
				org_peer_cnt, orderer_type, batch_timeout, max_message_cnt, absolute_max_bytes, 
				preferred_max_bytes, network_name, "", 0, 0, 0, ""));
		
		
		List<Vdu> vdu_list = new ArrayList<Vdu>();
		Vdu vdu = new Vdu();
		
		StringBuffer sb = new StringBuffer(); 
		sb.append("tosca_definitions_version: tosca_simple_profile_for_nfv_1_0_0\n\n");
				
		sb.append("description: desc_"+description+"\n\n");
				
		sb.append("metadata: "+"\n");
		sb.append("  template_name: "+profile_name+"-vnfd"+"\n\n");
				
		sb.append("topology_template: "+"\n");
		sb.append("  node_templates: "+"\n");
		
		
		/** 20181101 bcmanager생성을 1번으로 함. **/	
		vdu = new Vdu();
		vdu.setProfile_id(profile_id);
		vdu.setVdu_name("VDU1");
//		vdu.setVdu_name("VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 2));
		vdu.setOrg_type("manager");
		vdu.setOrg_name("-");
		vdu.setHost_type("manager");
		vdu.setHost_name("bcmanager");
		vdu_list.add(vdu);
		
		/** bcmanager **/
		sb.append("    VDU1:"+"\n");
//		sb.append("    VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 2)+":" +"\n");
		sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
		
		if(flavor_select_bcmanager.equals("직접입력")){
			/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
			sb.append("      capabilities:"+"\n");
			sb.append("        nfv_compute:"+"\n");
			sb.append("          properties:"+"\n");
			sb.append("            num_cpus: "+bcmanager_num_cpus+"\n");
			sb.append("            mem_size: "+bcmanager_mem_size+" MB\n");
			sb.append("            disk_size: "+bcmanager_disk_size+" GB\n");
			/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
		}
		
		sb.append("      properties:"+"\n");
		sb.append("        image: "+"ubuntu-hyperledger-fabric-1.1-base-16.04-LTS\n");
		sb.append("        name: bcmanager("+profile_name+")\n");
		
		if(!flavor_select_bcmanager.equals("직접입력")){
			/* Flavor를 선택했을 경우 시작!!*/
			sb.append("        flavor: "+flavor_select_bcmanager+"\n");
			/* Flavor를 선택했을 경우 끝!!*/	
		}
		
		sb.append("        availability_zone: "+zone_select_bcmanager+"\n");
		sb.append("        mgmt_driver: noop"+"\n");
		sb.append("        config: |"+"\n");
		sb.append("          param0: key1\n");
		sb.append("          param1: key2\n");
		
		sb.append("        user_data_format: RAW"+"\n");
		sb.append("        user_data: |"+"\n");
		sb.append("          #!/bin/sh"+"\n");
		sb.append("          mkdir -p /root/BCNET/cfg"+"\n");
		
		sb.append("          echo ");
		sb.append("\"");
		sb.append("DB_ADDRESS = 129.254.194.67");
		sb.append("\n");
		sb.append("          DB_PORT = 3306");
		sb.append("\n");
		sb.append("          PEER_ORG_COUNT = "+peer_org_cnt);
		sb.append("\n");
		sb.append("          PROFILE_ID = "+profile_id);
		sb.append("\n");
		sb.append("          PEER_PER_ORG_COUNT = "+org_peer_cnt);
		sb.append("\n");
		sb.append("          HOST_NAME = bcmanager");
		sb.append("\n");
		sb.append("          CRYPTO_DEL_MODE  = 0"); // 테스트용 스크립트 추가
		sb.append("\"");
		sb.append(" > /root/BCNET/cfg/bcnet.cfg");
		
		sb.append("\n");
		sb.append("          /bin/bash /root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log");
		sb.append("\n");
		
		
		
		/** 20181102 kafka생성을 2번으로 함. **/	
		vdu = new Vdu();
		vdu.setProfile_id(profile_id);
		vdu.setVdu_name("VDU2");
//		vdu.setVdu_name("VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 1));
		vdu.setOrg_type("kafka");
		vdu.setOrg_name("-");
		vdu.setHost_type("kafka");
		vdu.setHost_name("kafka-zookeeper");
		vdu_list.add(vdu);
		
		/** kafka **/
		sb.append("    VDU2:"+"\n");
//		sb.append("    VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 1)+":" +"\n");
		sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
		
		if(flavor_select_kafka.equals("직접입력")){
			/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
			sb.append("      capabilities:"+"\n");
			sb.append("        nfv_compute:"+"\n");
			sb.append("          properties:"+"\n");
			sb.append("            num_cpus: "+kafka_num_cpus+"\n");
			sb.append("            mem_size: "+kafka_mem_size+" MB\n");
			sb.append("            disk_size: "+kafka_disk_size+" GB\n");
			/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
		}
		
		sb.append("      properties:"+"\n");
		sb.append("        image: "+"ubuntu-hyperledger-fabric-1.1-base-16.04-LTS\n");
		sb.append("        name: kafka-zookeeper("+profile_name+")\n");
		
		if(!flavor_select_kafka.equals("직접입력")){
			/* Flavor를 선택했을 경우 시작!!*/
			sb.append("        flavor: "+flavor_select_kafka+"\n");
			/* Flavor를 선택했을 경우 끝!!*/	
		}
		
		sb.append("        availability_zone: "+zone_select_kafka+"\n");
		sb.append("        mgmt_driver: noop"+"\n");
		sb.append("        config: |"+"\n");
		sb.append("          param0: key1\n");
		sb.append("          param1: key2\n");
		
		sb.append("        user_data_format: RAW"+"\n");
		sb.append("        user_data: |"+"\n");
		sb.append("          #!/bin/sh"+"\n");
		sb.append("          mkdir -p /root/BCNET/cfg"+"\n");
		
		sb.append("          echo ");
		sb.append("\"");
		sb.append("DB_ADDRESS = 129.254.194.67");
		sb.append("\n");
		sb.append("          DB_PORT = 3306");
		sb.append("\n");
		sb.append("          PEER_ORG_COUNT = "+peer_org_cnt);
		sb.append("\n");
		sb.append("          PROFILE_ID = "+profile_id);
		sb.append("\n");
		sb.append("          PEER_PER_ORG_COUNT = "+org_peer_cnt);
		sb.append("\n");
		sb.append("          HOST_NAME = kafka-zookeeper");
		sb.append("\"");
		sb.append(" > /root/BCNET/cfg/bcnet.cfg");
		sb.append("\n");
		sb.append("          /bin/bash /root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log");
		sb.append("\n");
		
		
		/** orderer n**/
		for(int i = 0; i<orderer_cnt; i++){
			
			vdu = new Vdu();
			vdu.setProfile_id(profile_id);
			vdu.setVdu_name("VDU"+(i+1+1+1));
//			vdu.setVdu_name("VDU"+(i+1));
			vdu.setOrg_type("orderer");
			vdu.setOrg_name("ordererorg0");
			vdu.setHost_type("orderer");
			vdu.setHost_name("orderer"+i);
			vdu_list.add(vdu);
			
			sb.append("    VDU"+(i+1+1+1)+":" +"\n");
//			sb.append("    VDU"+(i+1)+":" +"\n");
			sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
			
			if(orderer_flavor_list.get(i).equals("직접입력")){
				/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
				sb.append("      capabilities:"+"\n");
				sb.append("        nfv_compute:"+"\n");
				sb.append("          properties:"+"\n");
				sb.append("            num_cpus: "+orderer_num_cpu_list.get(i)+"\n");
				sb.append("            mem_size: "+orderer_mem_size_list.get(i)+" MB\n");
				sb.append("            disk_size: "+orderer_disk_size_list.get(i)+" GB\n");
				/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
			}
			
			sb.append("      properties:"+"\n");
			sb.append("        image: "+"ubuntu-hyperledger-fabric-1.1-base-16.04-LTS\n");  // <---------------------- 이미지 명 바뀌어야 함.
			
			sb.append("        name: orderer"+i+"("+profile_name+")\n");
			
			if(!orderer_flavor_list.get(i).equals("직접입력")){
				/* Flavor를 선택했을 경우 시작!!*/
				sb.append("        flavor: "+orderer_flavor_list.get(i)+"\n");
				/* Flavor를 선택했을 경우 끝!!*/	
			}
			
			sb.append("        availability_zone: "+orderer_zone_list.get(i)+"\n");
			sb.append("        mgmt_driver: noop"+"\n");
			sb.append("        config: |"+"\n");
			sb.append("          param0: key1\n");
			sb.append("          param1: key2\n");
			
			/** user_data 생성 취소 - 20181101 vm 양이 많아지면 생성이 안되는 케이스가 있어서 주석처리함. **/
//			sb.append("        user_data_format: RAW"+"\n");
//			sb.append("        user_data: |"+"\n");
//			sb.append("          #!/bin/sh"+"\n");
//			sb.append("          mkdir -p /root/BCNET/cfg"+"\n");
//			sb.append("          echo ");
//			sb.append("\"");
//			sb.append("DB_ADDRESS = 129.254.194.67");
//			sb.append("\n");
//			sb.append("          DB_PORT = 3306");
//			sb.append("\n");
//			sb.append("          PEER_ORG_COUNT = "+peer_org_cnt);
//			sb.append("\n");
//			sb.append("          PROFILE_ID = "+profile_id);
//			sb.append("\n");
//			sb.append("          PEER_PER_ORG_COUNT = "+org_peer_cnt);
//			sb.append("\n");
//			sb.append("          HOST_NAME = orderer"+i+"");
//			sb.append("\"");
//			sb.append(" > /root/BCNET/cfg/bcnet.cfg");
//			sb.append("\n");
//			sb.append("          /bin/bash /root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log");
//			sb.append("\n");
		}
		
		/** adminordererorg0**/
		sb.append("    VDU"+(orderer_cnt+1+1+1)+":" +"\n");
//		sb.append("    VDU"+(orderer_cnt+1)+":" +"\n");
		sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
		
		if(flavor_select_adminordererorg0.equals("직접입력")){
			/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
			sb.append("      capabilities:"+"\n");
			sb.append("        nfv_compute:"+"\n");
			sb.append("          properties:"+"\n");
			sb.append("            num_cpus: "+adminordererorg_num_cpus_0+"\n");
			sb.append("            mem_size: "+adminordererorg_mem_size_0+" MB\n");
			sb.append("            disk_size: "+adminordererorg_disk_size_0+" GB\n");
			/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
		}
		
		sb.append("      properties:"+"\n");
		sb.append("        image: "+"ubuntu-hyperledger-fabric-1.1-base-16.04-LTS\n");
		sb.append("        name: adminordererorg0("+profile_name+")\n");
		
		if(!flavor_select_adminordererorg0.equals("직접입력")){
			/* Flavor를 선택했을 경우 시작!!*/
			sb.append("        flavor: "+flavor_select_adminordererorg0+"\n");
			/* Flavor를 선택했을 경우 끝!!*/	
		}
		
		sb.append("        availability_zone: "+zone_select_adminordererorg0+"\n");
		sb.append("        mgmt_driver: noop"+"\n");
		sb.append("        config: |"+"\n");
		sb.append("          param0: key1\n");
		sb.append("          param1: key2\n");
		
		/** user_data 생성 취소 - 20181101 vm 양이 많아지면 생성이 안되는 케이스가 있어서 주석처리함. **/
//		sb.append("        user_data_format: RAW"+"\n");
//		sb.append("        user_data: |"+"\n");
//		sb.append("          #!/bin/sh"+"\n");
//		sb.append("          mkdir -p /root/BCNET/cfg"+"\n");
//		
//		sb.append("          echo ");
//		sb.append("\"");
//		sb.append("DB_ADDRESS = 129.254.194.67");
//		sb.append("\n");
//		sb.append("          DB_PORT = 3306");
//		sb.append("\n");
//		sb.append("          PEER_ORG_COUNT = "+peer_org_cnt);
//		sb.append("\n");
//		sb.append("          PROFILE_ID = "+profile_id);
//		sb.append("\n");
//		sb.append("          PEER_PER_ORG_COUNT = "+org_peer_cnt);
//		sb.append("\n");
//		sb.append("          HOST_NAME = adminordererorg0");
//		sb.append("\"");
//		sb.append(" > /root/BCNET/cfg/bcnet.cfg");
//		sb.append("\n");
//		sb.append("          /bin/bash /root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log");
//		sb.append("\n");
		
		
		
		vdu = new Vdu();
		vdu.setProfile_id(profile_id);
		vdu.setVdu_name("VDU"+(orderer_cnt+1+1+1));
//		vdu.setVdu_name("VDU"+(orderer_cnt+1));
		vdu.setOrg_type("orderer");
		vdu.setOrg_name("ordererorg0");
		vdu.setHost_type("user");
		vdu.setHost_name("adminordererorg0");
		vdu_list.add(vdu);
		
		int k = 0;
		
		/** peer org n **/
		for(int i = 0; i<(peer_org_cnt); i++){
			for(int j = 0; j<(org_peer_cnt); j++){
				
				vdu = new Vdu();
				vdu.setProfile_id(profile_id);
				vdu.setVdu_name("VDU"+(orderer_cnt + 1 + ((j + i) +1) +1+1));
//				vdu.setVdu_name("VDU"+(orderer_cnt + 1 + ((j + i) +1)));
				vdu.setOrg_type("peer");
				vdu.setOrg_name("org"+i);
				vdu.setHost_type("peer");
				vdu.setHost_name("peer"+k);
				vdu_list.add(vdu);
				
				sb.append("    VDU"+(orderer_cnt + 1 + ((j + i) +1)+1+1)+":" +"\n");
//				sb.append("    VDU"+(orderer_cnt + 1 + ((j + i) +1))+":" +"\n");
				sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
				
				if(peer_flavor_list.get(j + (i*peer_org_cnt)).equals("직접입력")){
					/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
					sb.append("      capabilities:"+"\n");
					sb.append("        nfv_compute:"+"\n");
					sb.append("          properties:"+"\n");
					sb.append("            num_cpus: "+peer_num_cpu_list.get(j + (i*peer_org_cnt))+"\n");
					sb.append("            mem_size: "+peer_mem_size_list.get(j + (i*peer_org_cnt))+" MB\n");
					sb.append("            disk_size: "+peer_disk_size_list.get(j + (i*peer_org_cnt))+" GB\n");
					/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
				}
				
				sb.append("      properties:"+"\n");
				sb.append("        image: "+"ubuntu-hyperledger-fabric-1.1-base-16.04-LTS\n");
				sb.append("        name: peer"+(k)+"("+profile_name+")\n");
				
				if(!peer_flavor_list.get(j + (i*peer_org_cnt)).equals("직접입력")){
					/* Flavor를 선택했을 경우 시작!!*/
					sb.append("        flavor: "+peer_flavor_list.get(j + (i*peer_org_cnt))+"\n");
					/* Flavor를 선택했을 경우 끝!!*/	
				}
				
				sb.append("        availability_zone: "+peer_zone_list.get((i*org_peer_cnt) + j)+"\n");
				sb.append("        mgmt_driver: noop"+"\n");
				sb.append("        config: |"+"\n");
				sb.append("          param0: key1\n");
				sb.append("          param1: key2\n");
				
				/** user_data 생성 취소 - 20181101 vm 양이 많아지면 생성이 안되는 케이스가 있어서 주석처리함. **/
//				sb.append("        user_data_format: RAW"+"\n");
//				sb.append("        user_data: |"+"\n");
//				sb.append("          #!/bin/sh"+"\n");
//				sb.append("          mkdir -p /root/BCNET/cfg"+"\n");
//				
//				sb.append("          echo ");
//				sb.append("\"");
//				sb.append("DB_ADDRESS = 129.254.194.67");
//				sb.append("\n");
//				sb.append("          DB_PORT = 3306");
//				sb.append("\n");
//				sb.append("          PEER_ORG_COUNT = "+peer_org_cnt);
//				sb.append("\n");
//				sb.append("          PROFILE_ID = "+profile_id);
//				sb.append("\n");
//				sb.append("          PEER_PER_ORG_COUNT = "+org_peer_cnt);
//				sb.append("\n");
//				sb.append("          HOST_NAME = peer"+j);
//				sb.append("\"");
//				sb.append(" > /root/BCNET/cfg/bcnet.cfg");
//				sb.append("\n");
//				sb.append("          /bin/bash /root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log");
//				sb.append("\n");
				
				if(j == (org_peer_cnt - 1)){
					
					vdu = new Vdu();
					vdu.setProfile_id(profile_id);
					vdu.setVdu_name("VDU"+(orderer_cnt + 1 + ((j + i) + 1 + 1 + 1+1)));
//					vdu.setVdu_name("VDU"+(orderer_cnt + 1 + ((j + i) + 1 + 1)));
					vdu.setOrg_type("peer");
					vdu.setOrg_name("org"+i);
					vdu.setHost_type("user");
					vdu.setHost_name("adminorg"+i);
					vdu_list.add(vdu);
					
					sb.append("    VDU"+(orderer_cnt + 1 + ((j + i) + 1 + 1 + 1+1))+":" +"\n");
//					sb.append("    VDU"+(orderer_cnt + 1 + ((j + i) + 1 + 1))+":" +"\n");
					sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
					
					if(adminorg_flavor_list.get(i).equals("직접입력")){
						/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
						sb.append("      capabilities:"+"\n");
						sb.append("        nfv_compute:"+"\n");
						sb.append("          properties:"+"\n");
						sb.append("            num_cpus: "+adminorg_num_cpu_list.get(i)+"\n");
						sb.append("            mem_size: "+adminorg_mem_size_list.get(i)+" MB\n");
						sb.append("            disk_size: "+adminorg_disk_size_list.get(i)+" GB\n");
						/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
					}
					
					sb.append("      properties:"+"\n");
					sb.append("        image: "+"ubuntu-hyperledger-fabric-1.1-base-16.04-LTS\n");
					sb.append("        name: adminorg"+(i)+"("+profile_name+")\n");
					
					if(!adminorg_flavor_list.get(i).equals("직접입력")){
						/* Flavor를 선택했을 경우 시작!!*/
						sb.append("        flavor: "+adminorg_flavor_list.get(i)+"\n");
						/* Flavor를 선택했을 경우 끝!!*/	
					}
					
					sb.append("        availability_zone: "+adminorg_zone_list.get(i)+"\n");
					sb.append("        mgmt_driver: noop"+"\n");
					sb.append("        config: |"+"\n");
					sb.append("          param0: key1\n");
					sb.append("          param1: key2\n");
					
					/** user_data 생성 취소 - 20181101 vm 양이 많아지면 생성이 안되는 케이스가 있어서 주석처리함. **/
//					sb.append("        user_data_format: RAW"+"\n");
//					sb.append("        user_data: |"+"\n");
//					sb.append("          #!/bin/sh"+"\n");
//					sb.append("          mkdir -p /root/BCNET/cfg"+"\n");
//					
//					sb.append("          echo ");
//					sb.append("\"");
//					sb.append("DB_ADDRESS = 129.254.194.67");
//					sb.append("\n");
//					sb.append("          DB_PORT = 3306");
//					sb.append("\n");
//					sb.append("          PEER_ORG_COUNT = "+peer_org_cnt);
//					sb.append("\n");
//					sb.append("          PROFILE_ID = "+profile_id);
//					sb.append("\n");
//					sb.append("          PEER_PER_ORG_COUNT = "+org_peer_cnt);
//					sb.append("\n");
//					sb.append("          HOST_NAME = adminorg"+i);
//					sb.append("\"");
//					sb.append(" > /root/BCNET/cfg/bcnet.cfg");
//					sb.append("\n");
//					sb.append("          /bin/bash /root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log");
//					sb.append("\n");
					
					orderer_cnt = org_peer_cnt + orderer_cnt;
				}
				
				k++;
			}
		}
		
		
//		vdu = new Vdu();
//		vdu.setProfile_id(profile_id);
//		vdu.setVdu_name("VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 1 + 1));
////		vdu.setVdu_name("VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 1));
//		vdu.setOrg_type("kafka");
//		vdu.setOrg_name("-");
//		vdu.setHost_type("kafka");
//		vdu.setHost_name("kafka-zookeeper");
//		vdu_list.add(vdu);
//		
//		/** kafka **/
//		sb.append("    VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 1 + 1)+":" +"\n");
////		sb.append("    VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 1)+":" +"\n");
//		sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
//		
//		if(flavor_select_kafka.equals("직접입력")){
//			/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
//			sb.append("      capabilities:"+"\n");
//			sb.append("        nfv_compute:"+"\n");
//			sb.append("          properties:"+"\n");
//			sb.append("            num_cpus: "+kafka_num_cpus+"\n");
//			sb.append("            mem_size: "+kafka_mem_size+" MB\n");
//			sb.append("            disk_size: "+kafka_disk_size+" GB\n");
//			/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
//		}
//		
//		sb.append("      properties:"+"\n");
//		sb.append("        image: "+"ubuntu-hyperledger-fabric-1.1-base-16.04-LTS\n");
//		sb.append("        name: kafka-zookeeper\n");
//		
//		if(!flavor_select_kafka.equals("직접입력")){
//			/* Flavor를 선택했을 경우 시작!!*/
//			sb.append("        flavor: "+flavor_select_kafka+"\n");
//			/* Flavor를 선택했을 경우 끝!!*/	
//		}
//		
//		sb.append("        availability_zone: "+zone_select_kafka+"\n");
//		sb.append("        mgmt_driver: noop"+"\n");
//		sb.append("        config: |"+"\n");
//		sb.append("          param0: key1\n");
//		sb.append("          param1: key2\n");
//		
//		/** user_data 생성 취소 - 20181101 vm 양이 많아지면 생성이 안되는 케이스가 있어서 주석처리함. **/
////		sb.append("        user_data_format: RAW"+"\n");
////		sb.append("        user_data: |"+"\n");
////		sb.append("          #!/bin/sh"+"\n");
////		sb.append("          mkdir -p /root/BCNET/cfg"+"\n");
////		
////		sb.append("          echo ");
////		sb.append("\"");
////		sb.append("DB_ADDRESS = 129.254.194.67");
////		sb.append("\n");
////		sb.append("          DB_PORT = 3306");
////		sb.append("\n");
////		sb.append("          PEER_ORG_COUNT = "+peer_org_cnt);
////		sb.append("\n");
////		sb.append("          PROFILE_ID = "+profile_id);
////		sb.append("\n");
////		sb.append("          PEER_PER_ORG_COUNT = "+org_peer_cnt);
////		sb.append("\n");
////		sb.append("          HOST_NAME = kafka-zookeeper");
////		sb.append("\"");
////		sb.append(" > /root/BCNET/cfg/bcnet.cfg");
////		sb.append("\n");
////		sb.append("          /bin/bash /root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log");
////		sb.append("\n");
		
		
		
		
//		vdu = new Vdu();
//		vdu.setProfile_id(profile_id);
//		vdu.setVdu_name("VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 2));
//		vdu.setOrg_type("manager");
//		vdu.setOrg_name("-");
//		vdu.setHost_type("manager");
//		vdu.setHost_name("bcmanager");
//		vdu_list.add(vdu);
//		
//		/** bcmanager **/
//		sb.append("    VDU"+(1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 2)+":" +"\n");
//		sb.append("      type: tosca.nodes.nfv.VDU.Tacker\n");
//		
//		if(flavor_select_bcmanager.equals("직접입력")){
//			/* Flavor를 직접 입력으로 선택했을 경우 시작!!*/
//			sb.append("      capabilities:"+"\n");
//			sb.append("        nfv_compute:"+"\n");
//			sb.append("          properties:"+"\n");
//			sb.append("            num_cpus: "+bcmanager_num_cpus+"\n");
//			sb.append("            mem_size: "+bcmanager_mem_size+" MB\n");
//			sb.append("            disk_size: "+bcmanager_disk_size+" GB\n");
//			/* Flavor를 직접 입력으로 선택했을 경우 끝!!*/	
//		}
//		
//		sb.append("      properties:"+"\n");
//		sb.append("        image: "+"ubuntu-hyperledger-fabric-1.1-base-16.04-LTS\n");
//		sb.append("        name: bcmanager\n");
//		
//		if(!flavor_select_bcmanager.equals("직접입력")){
//			/* Flavor를 선택했을 경우 시작!!*/
//			sb.append("        flavor: "+flavor_select_bcmanager+"\n");
//			/* Flavor를 선택했을 경우 끝!!*/	
//		}
//		
//		sb.append("        availability_zone: "+zone_select_bcmanager+"\n");
//		sb.append("        mgmt_driver: noop"+"\n");
//		sb.append("        config: |"+"\n");
//		sb.append("          param0: key1\n");
//		sb.append("          param1: key2\n");
//		
//		sb.append("        user_data_format: RAW"+"\n");
//		sb.append("        user_data: |"+"\n");
//		sb.append("          #!/bin/sh"+"\n");
//		sb.append("          mkdir -p /root/BCNET/cfg"+"\n");
//		
//		sb.append("          echo ");
//		sb.append("\"");
//		sb.append("DB_ADDRESS = 129.254.194.67");
//		sb.append("\n");
//		sb.append("          DB_PORT = 3306");
//		sb.append("\n");
//		sb.append("          PEER_ORG_COUNT = "+peer_org_cnt);
//		sb.append("\n");
//		sb.append("          PROFILE_ID = "+profile_id);
//		sb.append("\n");
//		sb.append("          PEER_PER_ORG_COUNT = "+org_peer_cnt);
//		sb.append("\n");
//		sb.append("          HOST_NAME = bcmanager");
//		sb.append("\n");
//		sb.append("          CRYPTO_DEL_MODE  = 0"); // 테스트용 스크립트 추가
//		sb.append("\"");
//		sb.append(" > /root/BCNET/cfg/bcnet.cfg");
//		
//		sb.append("\n");
//		sb.append("          /bin/bash /root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log");
//		sb.append("\n");
		
		/** CP N **/
		for(int i = 0; i<1 + org_orderer_cnt + (peer_org_cnt * (org_peer_cnt + 1)) + 2; i++){
			sb.append("    CP"+(i+1)+":" +"\n");
			sb.append("      type: tosca.nodes.nfv.CP.Tacker\n");
			sb.append("      properties:"+"\n");
			sb.append("        management: true"+"\n");
			sb.append("        order: 0\n");
			sb.append("        anti_spoofing_protection: false\n");
			sb.append("      requirements:"+"\n");
			sb.append("        - virtualLink:"+"\n");
			sb.append("          node: VL1\n");
			sb.append("        - virtualBinding:"+"\n");
			sb.append("          node: VDU"+(i+1)+"\n");
		}
		
		sb.append("    VL1:" +"\n");
		sb.append("      type: tosca.nodes.nfv.VL\n");
		sb.append("      properties:"+"\n");
		sb.append("        network_name: "+network_name+"\n");
		sb.append("        vendor: Tacker"+"\n");
		
		CommonDAO.insertVduList(vdu_list);
		
		JSchUtil.sendCommand("source admin-openrc; echo '"+sb.toString()+"' > "+profile_name+".yaml"); // test.yaml 파일 업로드
		
		Map<String,Object> vnfd_map = commandService.getVnfdId("source admin-openrc; tacker vnfd-create --vnfd-file "+profile_name+".yaml "+profile_name+"-vnfd"); //vnfd파일 생성
		if(vnfd_map != null){
			String vnfd_id = vnfd_map.get("id").toString();
			
			Map<String,Object> vnf_map = commandService.getVnfInfo("source admin-openrc; tacker vnf-create --vnfd-name "+profile_name+"-vnfd "+profile_name+"-vnf"); //vnfd파일 생성
			
			if(vnf_map != null){
				String vnf_id = vnf_map.get("id").toString();
				
				CommonDAO.updateHlfList(profile_id, vnfd_id, vnf_id);
				
//				JSchUtil.start("source admin-openrc; rm -rf "+profile_name+".yaml"); // vnfd파일 제거
				
				model.addAttribute("result", "success");
				//DB에 vnfd 입력
				
				instanceList();	
			}
		}

		return "redirect:/command/hlf.do";

	}
	
	@RequestMapping("/command/changeName.do")
	public String changeName(Instance instance, Model model){
		
		String instance_name = instance.getInstance_name();
		String instance_id = instance.getInstance_id();
		
		System.out.println("name :" + instance_name + ", id :" + instance_id);
		
		JSchUtil.sendCommand("source admin-openrc; openstack server set --name " + instance_name + " " + instance_id); // test.yaml 파일 업로드
		
		instanceList();	
		
//		return "redirect:/command/instanceDetail.do?instance_id=%22"+instance_id+"%22&instance_name=%22"+instance_name+"%22";
		return "redirect:/command/instance.do";
	}
	
	@RequestMapping("/state/changeVnc.do")
	public String changeVnc(Vnc vnc, Model model){
		
		String ip_addr = vnc.getIp_addr();
		String port = vnc.getPort();
		
		CommonDAO.updateVncList(ip_addr, port);
		
		return "redirect:/state/basic.do";
	}
	
	/**
	 * @MethodName : createVnfd
	 * @작성일 : 2016. 2. 12. 
	 * @작성자 : Park
	 * @변경이력 :
	 * @Method설명 : vnfd 생성
	 * @param vnfd2
	 * @param model
	 * @return
	 */
	@RequestMapping("/command/createInstance.do")
	public String createInstance(Instance instance, Model model){
		
		String instance_name= instance.getInstance_name();
		String flavor_name = instance.getFlavor_name();
		String network_select = instance.getNetwork_id(); //private 또는 testnet
		String image_id = instance.getImage_id(); //private 또는 testnet
		String zone_name = instance.getZone_name(); //private 또는 testnet
		
		Map<String,Object> vnfd_map = 
				commandService.getVnfdId("source admin-openrc; openstack server create --flavor "+flavor_name+" --image " + image_id + " --nic net-id=" + network_select+" --availability-zone " + zone_name + " " + instance_name); //vnfd파일 생성
		if(vnfd_map != null){
			model.addAttribute("result", "success");
		}
		
		return "redirect:/command/instance.do";
	}
	
	@RequestMapping("/command/deleteInstance.do") //vnfd 삭제
	public String deleteInstance(@RequestParam("id") String id, Model model){
		
		//Ethereum_table 에서 idx로 vnfd_id와 vnf_id를 가져와서 삭제 명령을 실행한다.
		//DB에서 해당idx를 이용하여 삭제한다.
		
		String result = JSchUtil.sendCommand("source admin-openrc; openstack server delete " + id); //vnf삭제
		
		model.addAttribute("result","success");
		model.addAttribute("data",commandDao.getInstanceList());

		return "redirect:/command/instance.do";
	}
	
	@RequestMapping("/command/releaseFlotingIp.do")
	public String releaseIP(Instance instance, Model model){
		
		String instance_id = instance.getId();
		String ip_addr = instance.getIp_addr();
		
		System.out.println("aaasdasd :" + "source admin-openrc; openstack server remove floating ip "+instance_id+" " + ip_addr);
		
		String result = JSchUtil.sendCommand("source admin-openrc; openstack server remove floating ip "+instance_id+" " + ip_addr); //vnfd파일 생성
		
		return "redirect:/command/instance.do";
	}
	
	@RequestMapping("/command/matchFlotingIp.do")
	public String matchIP(Instance instance, Model model){
		
		String instance_id = instance.getId();
		String ip_addr = instance.getIp_addr();
		String float_ip = instance.getFloat_ip();
		
		System.out.println("aaasdasd :" + "openstack server add floating ip --fixed-ip-address "+ip_addr+" "+instance_id+" "+float_ip);
		
		String result = JSchUtil.sendCommand("source admin-openrc; openstack server add floating ip --fixed-ip-address "+ip_addr+" "+instance_id+" "+float_ip); //vnfd파일 생성
		
		return "redirect:/command/instance.do";
	}

}
