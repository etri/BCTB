package kr.co.bctt.ssh.service;

import java.util.List;
import java.util.Map;

import kr.co.bctt.ssh.dto.Flavor;
import kr.co.bctt.ssh.dto.FloatingIp;
import kr.co.bctt.ssh.dto.Image;
import kr.co.bctt.ssh.dto.Instance;
import kr.co.bctt.ssh.dto.NetList;
import kr.co.bctt.ssh.dto.Route;
import kr.co.bctt.ssh.dto.Subnet;
import kr.co.bctt.ssh.dto.VnfdInfo;

/**
 * @FileName : CommandService.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 명령에서 사용하는 Service
 */
public interface CommandService {
	
	public List<Image> getImageList(String cmd);
	public Map<String,Object> getImageInfo(String cmd);
	
	public List<Instance> getInstanceList(String cmd);
	public Map<String,Object> getInstanceResourceList(String cmd);
	public Map<String,Object> getInstanceUrlResourceList(String cmd);
	
	
	public Map<String,Object> getUploadImageInfo(String path, String name,String format);
	public boolean deleteImage(String cmd);
	
	public List<Flavor>getFlavorList(String cmd);
	public Map<String,Object> getFlavorInfo(String cmd);
	
	public String createFlavor(String cmd);
	
	public List<VnfdInfo> getVnfdList(String cmd);
	public Map<String,Object> getVnfdId(String cmd);
	public Map<String,Object> getVnfInfo(String cmd);
	//******************************************** network
	public List<NetList> getNetList(String cmd);
	public Map<String,Object> getNetIp(String cmd);
	public List<Route> getRouteList(String cmd);
	
	
	public List<Subnet> getSubnetList(String cmd);
	
	public List<FloatingIp> getIpList(String cmd);
}
