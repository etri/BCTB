package kr.co.bctt.ssh.service;

import java.util.List;
import java.util.Map;

import kr.co.bctt.ssh.dto.HResource;
import kr.co.bctt.ssh.dto.HStack;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.HostResource;
import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.dto.StackServer;
import kr.co.bctt.ssh.dto.User;
import kr.co.bctt.ssh.dto.Vnf;

/**
 * @FileName : StatusService.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 상태에서 사용하는 Service
 */
public interface StatusService {

	public List<User> parsingData(String cmd); 
	public List<ProjectInfo> getProjectList(String cmd);
	public Map<String, Object> getProjectResourceList(String cmd);
	public List<HostInfo> getHostList(String cmd);
	public List<HostResource> getHostResourceList(String cmd);

	//resource page vcscf관련 정보 수집 메서드 
	public List<Vnf> getVnf(String cmd);
	public List<HStack> getHStack(String cmd);
	public List<HResource> getHResource(String cmd);
	public List<StackServer> getStackServer(String cmd);
	public String getMac(String cmd);
}

