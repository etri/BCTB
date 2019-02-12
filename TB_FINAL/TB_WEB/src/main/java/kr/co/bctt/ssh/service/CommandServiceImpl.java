package kr.co.bctt.ssh.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bctt.ssh.dao.JSchUtil;
import kr.co.bctt.ssh.dao.CommandDao;
import kr.co.bctt.ssh.dto.Flavor;
import kr.co.bctt.ssh.dto.FloatingIp;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.Image;
import kr.co.bctt.ssh.dto.Instance;
import kr.co.bctt.ssh.dto.NetList;
import kr.co.bctt.ssh.dto.Route;
import kr.co.bctt.ssh.dto.StackServer;
import kr.co.bctt.ssh.dto.Subnet;
import kr.co.bctt.ssh.dto.VnfdInfo;

/**
 * @FileName : CommandServiceImpl.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
@Service
public class CommandServiceImpl implements CommandService{
	@Autowired
	private CommandDao statusDao;

	@Autowired
	private JSchUtil jSchUtil;

	@Override
	public List<Image> getImageList(String cmd) {
		List<Image> list = new ArrayList<Image>();

		String txt = "";
		String []parsingData = null;

		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할
		} catch (Exception e) {
			e.printStackTrace();
		}

		String id ="";
		String name ="";
		String subnets="";
		Image image = new Image();

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 4; //
			//System.out.println(seq);
			if (seq != 0 && !(i >= 1 && i <= 3)) {	
				if (i == parsingData.length - 2) {	
					subnets= parsingData[i];
					image = new Image(id,name,subnets);
					list.add(image);
					break;
				}
				
				if (seq == 1) { // id 저장
					if (i > 6) { 
						image = new Image(id,name,subnets);
						id= parsingData[i];
						list.add(image);
						continue;
					}
					id = parsingData[i];	
				}
				if (seq == 2) { // name 저장
					name = parsingData[i];
				}
				if (seq == 3) {
					subnets = parsingData[i];
				}
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> getImageInfo(String cmd) {
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String []parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}


		String field ="";
		String value="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 3; //
			
			if (seq != 0 && !(i >= 1 && i <= 2)) {

				if (i == parsingData.length -2) {

					value= parsingData[i];
					map.put(field, value);

					break;
				}
				if (seq == 1) { // id 저장
					if (i > 4) { 
						map.put(field, value);
						field= parsingData[i];
						continue;
					}
					field = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}
			}
		}
		return map;
	}
	@Override
	public Map<String, Object> getUploadImageInfo(String path, String name,String format) {
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String []parsingData = null;
		try {
			txt = jSchUtil.imageUpload(path, name,format);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할
		} catch (Exception e) {
			e.printStackTrace();
		}

		String property ="";
		String value="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 3; //
			//System.out.println(seq);
			if (seq != 0 && !(i >= 1 && i <= 2)) {
				if (i == parsingData.length -2) {
					value= parsingData[i];
					map.put(property, value);
					break;
				}
				if (seq == 1) { // id 저장
					if (i > 4) { 
						map.put(property, value);
						property= parsingData[i];
						continue;
					}
					property = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}
			}
		}
		return map;
	}

	@Override
	public boolean deleteImage(String cmd) {
		System.out.println(JSchUtil.sendCommand(cmd));
		return true;
	}

	@Override
	public List<Flavor> getFlavorList(String cmd) {
		List<Flavor> list = new ArrayList<Flavor>();
		String txt = "";
		String []parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할
		} catch (Exception e) {
			e.printStackTrace();
		}

		String id ="";
		String name="";
		String ram="";
		String disk="";
		String ephemeral="";
		String vcpus="";
		Flavor flavor = null;
		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 8; 
			if (seq != 0 && !(i >= 1 && i <= 6)) {
				if (i == parsingData.length - 2) {
					vcpus= parsingData[i];
					flavor = new Flavor(id, name, ram, disk, ephemeral, vcpus);
					list.add(flavor);
					break;
				}
				if (seq == 1) { // id 저장
					if (i > 9) { 
						flavor = new Flavor(id, name, ram, disk, ephemeral, vcpus);
						id= parsingData[i];
						list.add(flavor);
						continue;
					}
					id = parsingData[i];
					continue;
				}
				else if (seq == 2) { // name 저장
					name = parsingData[i];
					continue;
				}
				else if (seq == 3) {
					ram = parsingData[i];
					continue;
				}
				else if (seq == 4) {
					disk = parsingData[i];
					continue;
				}
				else if (seq == 5) {
					ephemeral = parsingData[i];
					continue;
				}
				else if (seq == 6) {
					vcpus = parsingData[i];
					continue;
				}
			}
		}	
		return list;
	}
	@Override
	public Map<String,Object> getFlavorInfo(String cmd) {
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String []parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}


		String field ="";
		String value="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 3; 
			if (seq != 0 && !(i >= 1 && i <= 2)) {
				if (i == parsingData.length -2) {	
					value= parsingData[i];
					map.put(field, value);
					break;
				}
				if (seq == 1) { // id 저장
					if (i > 4) { 
						map.put(field, value);
						field= parsingData[i];
						continue;
					}
					field = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}
			}
		}	
		return map;
	}


	@Override
	public String createFlavor(String cmd) {
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String []parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}

		String field ="";
		String value="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 3; //
			//System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 2)) {

				if (i == parsingData.length -2) {

					value= parsingData[i];
					map.put(field, value);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 4) { 
						map.put(field, value);
						field= parsingData[i];
						continue;
					}
					field = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}

			}

		}

		return map.get("id").toString();
	}


	@Override
	public List<VnfdInfo> getVnfdList(String cmd) {
		List<VnfdInfo> list = new ArrayList<VnfdInfo>();
		VnfdInfo vnfdInfo = new VnfdInfo();
		String txt = "";
		String[] parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			// 불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}
		String id = null;
		String name = null;
		String description = null;
		String infra_driver = null;
		String mgmt_driver = null;

		for (int i = 0; i < parsingData.length; i++) {
			int seq = i % 6; //
			// System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 5)) {

				if (i == parsingData.length - 2) {

					mgmt_driver = parsingData[i];
					vnfdInfo = new VnfdInfo(id, name, description, infra_driver, mgmt_driver);
					list.add(vnfdInfo);

					break;
				}

				if (seq == 1) { // id 저장

					if (i > 7) {
						vnfdInfo = new VnfdInfo(id, name, description, infra_driver, mgmt_driver);
						id = parsingData[i];
						list.add(vnfdInfo);
						continue;
					}
					id = parsingData[i];

				}
				if (seq == 2) { // name 저장
					name = parsingData[i];
				}

				if (seq == 3) {
					description = parsingData[i];
				}

				if (seq == 4) {
					infra_driver = parsingData[i];
				}

				if (seq == 5) {
					mgmt_driver = parsingData[i];
				}

			}

		}
		return list;
	}

	@Override
	public Map<String, Object> getVnfdId(String cmd) {
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String[] parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			// 불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}

		String field = "";
		String value = "";

		for (int i = 0; i < parsingData.length; i++) {
			int seq = i % 3; //
			// System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 2)) {

				if (i == parsingData.length - 2) {

					value = parsingData[i];
					map.put(field, value);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 4) {
						map.put(field, value);
						field = parsingData[i];
						continue;
					}
					field = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}

			}

		}

		return map;
	}
	@Override
	public Map<String, Object> getVnfInfo(String cmd) {
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String[] parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			// 불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}

		String field = "";
		String value = "";

		for (int i = 0; i < parsingData.length; i++) {
			
			int seq = i % 3; //


			if (seq != 0 && !(i >= 1 && i <= 2)) {

				if (i == parsingData.length - 2) {

					value = parsingData[i];
					map.put(field, value);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 4) {
						map.put(field, value);
						field = parsingData[i];
						continue;
					}
					field = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}

			}

		}

		return map;
	}

	@Override
	public List<NetList> getNetList(String cmd) {
		List<NetList> list = new ArrayList<NetList>();
		String txt = "";
		String[] parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			// 불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String id = "";
		String name = "";
		String subnet = "";
		
		NetList netList = new NetList();

		for (int i = 0; i < parsingData.length; i++) {
			int seq = i % 4; //
			// System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 3)) {

				if (i == parsingData.length - 2) {

					netList = new NetList(id, name, parsingData[i]);
					list.add(netList);
					
					break;
				}

				if (seq == 1) { // id 저장
					
					if (parsingData[i].equals("")) { // 데이터가 없다.. 객체를 만들지 않도록
						continue;
					}
					
					if (i > 6) {
						
						netList = new NetList(id, name, subnet);		
						list.add(netList);
					}
					id = parsingData[i];

				}
				if (seq == 2) { // name 저장
					if (!parsingData[i].equals("")) {
						name = parsingData[i];
					}
				}

				if (seq == 3) {
					if (!parsingData[i].equals("")) {
						subnet = parsingData[i];
					}
				}
			}
		}

		return list;
	}



	@Override
	public Map<String, Object> getNetIp(String cmd) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String[] parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			
			// 불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}

		String field = "";
		String value = "";
		
		for (int i = 0; i < parsingData.length; i++) {
			int seq = i % 3; //
			
			if (seq != 0 && !(i >= 1 && i <= 2)) {

				if (i == parsingData.length - 1) {

					value = parsingData[i];
					map.put(field, value);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 4) {
						map.put(field, value);
						field = parsingData[i];
						continue;
					}
					field = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}
			}
		}

		return map;
	}

	@Override
	public List<Route> getRouteList(String cmd) {
		List<Route> list = new ArrayList<Route>();
		Route routeList = new Route();
		String txt = "";
		String[] parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			// 불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}

		String id = null;
		
		for (int i = 0; i < parsingData.length; i++) {
			int seq = i % 8; //
			
			if (seq != 0 && !(i >= 1 && i <= 8)) {

				if (i == parsingData.length - 1) {

					routeList = new Route(id);
					list.add(routeList);

					break;
				}

				if (seq == 1) { // id 저장

					if (i > 8) {
						
						id = parsingData[i];
						routeList = new Route(id);
						list.add(routeList);
						continue;
					}
					
					id = parsingData[i];

				}
			}

		}
		return list;
	}

	@Override
	public List<Instance> getInstanceList(String cmd) {
		List<Instance> list = new ArrayList<Instance>();

		String txt = "";
		String []parsingData = null;

		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String id ="";
		Instance instance = new Instance();

		for (int i = 0; i < parsingData.length; i++) {
			
			int seq = i % 7; //
			//System.out.println(seq);
			if (seq != 0 && !(i >= 1 && i <= 6)) {	
				if (i == parsingData.length - 1) {
					
					instance = new Instance(id);
					list.add(instance);
					break;
				}
				
				if (seq == 1) { // id 저장
					if (i > 6) {
						
						id = parsingData[i];
						
						instance = new Instance(id);
						list.add(instance);
						continue;
					}
					id = parsingData[i];	
				}
			}
		}
		
		return list;
	}

	@Override
	public Map<String, Object> getInstanceResourceList(String cmd) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String []parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}


		String field ="";
		String value="";

		for (int i = 0; i < parsingData.length; i++) { 
			
			int seq = i % 3; //
			
			if (seq != 0 && !(i >= 1 && i <= 2)) {

				if (i == parsingData.length - 1) {

					value= parsingData[i];
					map.put(field, value);

					break;
				}
				if (seq == 1) { // id 저장
					if (i > 4) { 
						map.put(field, value);
						field= parsingData[i];
						continue;
					}
					field = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}
			}
		}
		return map;
	}
	
	@Override
	public Map<String, Object> getInstanceUrlResourceList(String cmd) {
		Map<String, Object> map = new HashMap<String, Object>();
		String txt = "";
		String []parsingData = null;
		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}


		String field ="";
		String value="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 3; //
			
			if (seq != 0 && !(i >= 1 && i <= 2)) {

				if (i == parsingData.length -2) {

					value= parsingData[i];
					map.put(field, value);

					break;
				}
				if (seq == 1) { // id 저장
					if (i > 4) { 
						map.put(field, value);
						field= parsingData[i];
						continue;
					}
					field = parsingData[i];
				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}
			}
		}
		return map;
	}
	
	@Override
	public List<Subnet> getSubnetList(String cmd) {
		List<Subnet> list = new ArrayList<Subnet>();

		String txt = "";
		String []parsingData = null;

		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String id ="";
		Subnet instance = new Subnet();

		for (int i = 0; i < parsingData.length; i++) {
			
			int seq = i % 5; //
			//System.out.println(seq);
			if (seq != 0 && !(i >= 1 && i <= 4)) {	
				if (i == parsingData.length - 1) {
					
					instance = new Subnet(id);
					list.add(instance);
					break;
				}
				
				if (seq == 1) { // id 저장
					if (i > 4) {
						
						id = parsingData[i];
						
						instance = new Subnet(id);
						list.add(instance);
						continue;
					}
					id = parsingData[i];	
				}
			}
		}
		
		return list;
	}
	
	@Override
	public List<FloatingIp> getIpList(String cmd) {
		List<FloatingIp> list = new ArrayList<FloatingIp>();

		String txt = "";
		String []parsingData = null;

		try {
			txt = JSchUtil.sendCommand(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String id ="";
		String ip_addr ="";
		String fixed_ip ="";
		String port ="";
		
		FloatingIp instance = new FloatingIp();

		for (int i = 0; i < parsingData.length; i++) {
			
			int seq = i % 7; //
			//System.out.println(seq);
			if (seq != 0 && !(i >= 1 && i <= 6)) {	
				if (i == parsingData.length - 1) { //마지막 데이터이면!!
					
					System.out.println("마지막 데이터 - id : " + id +", ip_addr :" + ip_addr);
					
					instance = new FloatingIp(id, ip_addr, fixed_ip, port);
					list.add(instance);
					break;
				}
				
				if (seq == 1) { // id 저장
					id = parsingData[i];	
				}if (seq == 2) { // name 저장
					ip_addr = parsingData[i];
				}if (seq == 3) { // name 저장
					fixed_ip = parsingData[i];
				}if (seq == 4) { // name 저장
					port = parsingData[i];
					
					if (i > 6) {
						instance = new FloatingIp(id, ip_addr, fixed_ip, port);
						list.add(instance);
						continue;
					}
				}
			}
		}
		
		return list;
	}
}
