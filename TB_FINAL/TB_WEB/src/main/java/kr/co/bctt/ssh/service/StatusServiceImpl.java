package kr.co.bctt.ssh.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bctt.ssh.dao.StatusDao;
import kr.co.bctt.ssh.dao.JSchUtil;
import kr.co.bctt.ssh.dto.HResource;
import kr.co.bctt.ssh.dto.HStack;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.HostResource;
import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.dto.StackServer;
import kr.co.bctt.ssh.dto.User;
import kr.co.bctt.ssh.dto.Vnf;

/**
 * @FileName : StatusServiceImpl.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
@Service
public class StatusServiceImpl implements StatusService{

	protected Log log = LogFactory.getLog(StatusServiceImpl.class);

	@Autowired
	private StatusDao commandDao;

	@Autowired
	private JSchUtil jSchUtil;

	public List<User> parsingData(String cmd) {

		List<User> list = new ArrayList<User>();
		boolean flag = false;
		User user = null;
		String id = "";
		String name = "";
		String mac_address = "";
		String preId = "";
		String txt = "";
		JSONArray jsonObj = null;
		ArrayList<String> fixed_ips = new ArrayList<String>();

		String []parsingData = null;
		try {
			txt = jSchUtil.start(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}



		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 5; //

			if (seq != 0 && !(i >= 1 && i <= 4)) {

				// addlastData
				if (i == parsingData.length - 1) {
					if (seq == 4) {
						if (parsingData[i] != null) {
							fixed_ips.add(parsingData[i]);
						}

					}

					if (id.equals("")) {
						user = new User(preId, name, mac_address, jsonObj);
						list.add(user);
					} else {
						user = new User(id, name, mac_address, jsonObj);
						list.add(user);
					}

					break;

				} // addlastData

				if (seq == 1) { // id 저장

					if (parsingData[i].equals("")) { // 데이터가 없다.. 객체를 만들지 않도록 flase
						flag = false;
					} else { // 데이터가 있다.
						if (!id.equals("")) {
							preId = id;
						}
						id = parsingData[i];
						flag = true;
					}

					if (i > 6 && flag) { // 6번째 이후 부터 실제 데이터를 저장하게 됩니다.

						jsonObj = (JSONArray) JSONValue.parse(fixed_ips.toString());
						user = new User(preId, name, mac_address, jsonObj);
						list.add(user); // 유처 정보 추가.
						fixed_ips = new ArrayList<String>();

					}

				}

				if (seq == 2) { // name 저장
					if (parsingData[i] != null) {
						name = parsingData[i];
					}

				}

				if (seq == 3) { // mac_address 저장
					if (parsingData[i] != null) {
						mac_address = parsingData[i];
					}

				}

				if (seq == 4) { // ip 저장, 2개 이상일 경우가 있기 때문에 배열에 저장. 
					if (parsingData[i] != null) {
						fixed_ips.add(parsingData[i]);
					}

				}

			}

		}


		return list;


	}

	public List<ProjectInfo> getProjectList(String cmd) {

		List<ProjectInfo> list = new ArrayList<ProjectInfo>();

		String txt = "";
		//JSONArray jsonObj = null;
		ProjectInfo projectInfo = null;
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
		String preId;
		String name="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 3; //
			//System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 2)) {

				if (i == parsingData.length - 2) {

					name= parsingData[i];
					projectInfo = new ProjectInfo(id, name, "", "", "");
					list.add(projectInfo);

					break;
				}


				if (seq == 1) { // id 저장

					preId = id;
					id = parsingData[i];
					if (i > 4) { // 6번째 이후 부터 실제 데이터를 저장하게 됩니다.
						id = parsingData[i];
						projectInfo = new ProjectInfo(preId, name, "", "", "");
						list.add(projectInfo);
					}
				}
				if (seq == 2) { // name 저장
					name = parsingData[i];
				}
			}
		}

		return list;
	}
	
	@Override
	public Map<String, Object> getProjectResourceList(String cmd) {
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
	public List<HostInfo> getHostList(String cmd) {
		List<HostInfo> list = new ArrayList<HostInfo>();

		String txt = "";
		//JSONArray jsonObj = null;
		HostInfo hostInfo = null;
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


		String hostName ="";
		String service ="";
		String zone="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 4; //
			//System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 3)) {

				if (i == parsingData.length - 2) {

					zone= parsingData[i];
					hostInfo = new HostInfo(hostName,service,zone);
					list.add(hostInfo);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 6) { 
						hostInfo = new HostInfo(hostName,service,zone);
						hostName= parsingData[i];
						list.add(hostInfo);
						continue;
					}
					hostName = parsingData[i];


				}
				if (seq == 2) { // name 저장
					service = parsingData[i];
				}

				if (seq == 3) {
					zone = parsingData[i];
				}


			}

		}

		return list;
	}


	@Override
	public List<HostResource> getHostResourceList(String cmd) {
		List<HostResource> list = new ArrayList<HostResource>();

		String txt = "";
		//JSONArray jsonObj = null;
		HostResource hostResource = null;
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

		String host ="";
		String project ="";
		String cpu="";
		String memoryMb ="";
		String diskGb ="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 6; //
			//System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 5)) {

				if (i == parsingData.length -2) {

					diskGb= parsingData[i];
					hostResource = new HostResource(host,project,cpu,memoryMb,diskGb);
					list.add(hostResource);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 7) { 
						hostResource = new HostResource(host,project,cpu,memoryMb,diskGb);
						host= parsingData[i];
						list.add(hostResource);
						continue;
					}
					host = parsingData[i];


				}
				if (seq == 2) { // name 저장
					project = parsingData[i];
				}

				if (seq == 3) {
					cpu = parsingData[i];
				}

				if (seq == 4) {
					memoryMb = parsingData[i];
				}

				if (seq == 5) {
					diskGb = parsingData[i];
				}

			}

		}
		return list;
	}

	@Override
	public List<Vnf> getVnf(String cmd) {
		List<Vnf> list = new ArrayList<Vnf>();
		Vnf vnf = new Vnf();
		String txt = "";
		String []parsingData = null;
		try {
			txt = jSchUtil.start(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}


		String id =null;
		String name=null;
		String description=null;
		String mgmt_url=null;
		String status=null;

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 6; //
			//System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 5)) {

				if (i == parsingData.length -2) {

					status= parsingData[i];
					vnf = new Vnf(id,name,description,mgmt_url,status);
					list.add(vnf);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 7) { 
						vnf = new Vnf(id,name,description,mgmt_url,status);
						id= parsingData[i];
						list.add(vnf);
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
					mgmt_url = parsingData[i];
				}

				if (seq == 5) {
					status = parsingData[i];
				}

			}

		}
		return list;
	}


	public List<HStack> getHStack(String cmd){
		List<HStack> list = new ArrayList<HStack>();
		HStack hStack = null;
		String txt = "";
		String []parsingData = null;
		try {
			txt = jSchUtil.start(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}

		String id =null;
		String stack_name=null;
		String stack_status=null;
		String creation_time=null;
		String updated_time=null;

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 6; //
			//System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 5)) {

				if (i == parsingData.length -2) {

					updated_time= parsingData[i];
					hStack = new HStack(id, stack_name, stack_status, creation_time, updated_time);
					list.add(hStack);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 7) { 
						hStack = new HStack(id, stack_name, stack_status, creation_time, updated_time);
						id= parsingData[i];
						list.add(hStack);
						continue;
					}
					id = parsingData[i];


				}
				if (seq == 2) { // name 저장
					stack_name = parsingData[i];
				}

				if (seq == 3) {
					stack_status = parsingData[i];
				}

				if (seq == 4) {
					creation_time = parsingData[i];
				}

				if (seq == 5) {
					updated_time = parsingData[i];
				}

			}

		}
		return list;

	}

	@Override
	public List<HResource> getHResource(String cmd) {
		List<HResource> list = new ArrayList<HResource>();
		HResource hResource = null;
		String txt = "";
		String []parsingData = null;
		try {
			txt = jSchUtil.start(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}


		String resource_name ="";
		String physical_resource_id="";
		String resource_type="";
		String resource_status="";
		String updated_time="";

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 6; //
			//System.out.println(seq);

			if (seq != 0 && !(i >= 1 && i <= 5)) {

				if (i == parsingData.length -2) {

					updated_time= parsingData[i];
					hResource  = new HResource(resource_name, physical_resource_id, resource_type, resource_status, updated_time);
					list.add(hResource);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 7) { 
						hResource  = new HResource(resource_name, physical_resource_id, resource_type, resource_status, updated_time);
						resource_name= parsingData[i];
						list.add(hResource);
						continue;
					}
					resource_name = parsingData[i];


				}
				if (seq == 2) { // name 저장
					physical_resource_id = parsingData[i];
				}

				if (seq == 3) {
					resource_type = parsingData[i];
				}

				if (seq == 4) {
					resource_status = parsingData[i];
				}

				if (seq == 5) {
					updated_time = parsingData[i];
				}

			}

		}

		return list;
	}

	@Override
	public List<StackServer> getStackServer(String cmd) {
		List<StackServer> list = new ArrayList<StackServer>();
		StackServer stackServer = null;
		String txt = "";
		String []parsingData = null;
		try {
			txt = jSchUtil.start(cmd);
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
					stackServer  = new StackServer(field, value);
					list.add(stackServer);

					break;
				}

				if (seq == 1) { // id 저장
					if (i > 4) { 
						stackServer  = new StackServer(field, value);
						field= parsingData[i];
						list.add(stackServer);
						continue;
					}

					field = parsingData[i];
					System.out.println(i+" "+field);


				}
				if (seq == 2) { // name 저장
					value = parsingData[i];
				}

			}

		}
		System.out.println(parsingData.length);

		return list;
	}

	@Override
	public String getMac(String cmd) {

		String txt = "";
		String []parsingData = null;
		String mac = "";
		try {
			txt = jSchUtil.start(cmd);
			//불필요한 문자제거
			txt = txt.replace(" ", "");
			txt = txt.replace("\n", "");
			txt = txt.replace("\r", "");
			txt = txt.replace("+", "");
			parsingData = txt.split("\\|"); // '|'를 기준으로 분할

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < parsingData.length; i++) { 
			int seq = i % 5; //

			if (seq == 3) {
				mac= parsingData[i];
			}

		}
		return mac;

	}



}
