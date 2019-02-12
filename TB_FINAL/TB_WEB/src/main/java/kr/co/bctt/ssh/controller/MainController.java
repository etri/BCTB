package kr.co.bctt.ssh.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.bctt.ssh.dao.CommandDao;
import kr.co.bctt.ssh.dto.FlavorInfo;
import kr.co.bctt.ssh.dto.ImageInfo;
import kr.co.bctt.ssh.dto.PackageInfo;
import kr.co.bctt.ssh.dto.Vnfd;

/**
 * @FileName : MainController.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 뷰 연결 컨트롤러
 */
@Controller
public class MainController {
	
	@Autowired
	private CommandDao statusDao;
	
	@RequestMapping(value = "/state/login")
	public String login() {
		return "state/login";
	}
	@RequestMapping(value = "/command/resource")
	public String resource() {
		return "state/resource";
	}
	@RequestMapping(value = "/state/project")
	public String project() {
		return "state/project";
	}
	@RequestMapping(value = "/command/instance")
	public String instance() {
		return "command/instance";
	}
	@RequestMapping(value = "/command/instanceDetail")
	public String instance_detail() {
		return "command/instance_detail";
	}
	
	@RequestMapping(value = "/command/subnet")
	public String subnet() {
		return "command/subnet";
	}
	@RequestMapping(value = "/command/routerDetail")
	public String router_detail() {
		return "command/router_detail";
	}
	@RequestMapping(value = "/state/vcscfinfo")
	public String vcscfinfo() {
		return "state/vcscfinfo";
	}
	@RequestMapping(value = "/command/network")
	public String network() {
		return "command/network";
	}
	@RequestMapping(value = "/command/router")
	public String router() {
		return "command/router";
	}
	@RequestMapping(value = "/command/hlf")
	public String hlf() {
		return "command/hlf";
	}
	@RequestMapping(value = "/command/ethereum")
	public String ethereum() {
		return "command/ethereum";
	}
	@RequestMapping(value = "/command/vnfDetail")
	public String vnf_detail() {
		return "command/vnf_detail";
	}
	@RequestMapping(value = "/state/basic")
	public String basic() {
		return "state/basic";
	}
	/**
	* @MethodName : flavor
	* @작성일 : 2016. 2. 12. 
	* @작성자 : Park
	* @변경이력 :
	* @Method설명 : flavor 연결 및 입력시 list 출력
	* @param model
	* @return
	*/
	@RequestMapping(value = "/command/flavor")
	public String flavor(Model model) {
		List<FlavorInfo> flist = statusDao.flist();
		model.addAttribute("flist", flist);
		List<Vnfd> vlist=statusDao.vlist();
		model.addAttribute("vlist", vlist);
		return "command/flavor";
	}

	/**
	* @MethodName : packages
	* @작성일 : 2016. 2. 12. 
	* @작성자 : Park
	* @변경이력 :
	* @Method설명 : package 연결 및 입력시 list 출력
	* @param model
	* @return
	*/
	@RequestMapping(value = "/command/package")
	public String packages(Model model) {
		List<PackageInfo> plist = statusDao.plist();
		List<Vnfd> vlist = statusDao.vlist();
		model.addAttribute("plist", plist);
		model.addAttribute("vlist", vlist);
		return "command/package";
	}
	
	/**
	* @MethodName : vnfd
	* @작성일 : 2016. 2. 12. 
	* @작성자 : Park
	* @변경이력 :
	* @Method설명 : vnfd에서 추가시 image,flavor,package 리스트를 받아와야함
	* @param model
	* @return
	*/
	@RequestMapping(value = "/command/vnfd")
	public String vnfd(Model model) {
		List<FlavorInfo> flist = statusDao.flist();
		List<PackageInfo> plist = statusDao.plist();
		List<ImageInfo> ilist = statusDao.ilist();
		
		model.addAttribute("image",ilist); //name을 뽑아쓰고
		model.addAttribute("flavor",flist); //name을 뽑아쓰고
		model.addAttribute("Package",plist); //package_name 을 뽑아쓰시오
		return "command/vnfd";
	}
	/**
	* @MethodName : vnf
	* @작성일 : 2016. 2. 12. 
	* @작성자 : Park
	* @변경이력 :
	* @Method설명 :
	* @param model
	* @return
	*/
	@RequestMapping(value = "/command/vnf")
	public String vnf(Model model) {
		List<Vnfd> vlist = statusDao.vlist();
		model.addAttribute("vnfd", vlist);// 리스트박스
		// vnfd_name 사용
		return "command/vnf";
	}	
}
