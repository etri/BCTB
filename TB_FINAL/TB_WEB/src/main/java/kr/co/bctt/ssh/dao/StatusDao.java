package kr.co.bctt.ssh.dao;

import java.util.List;

import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.dto.VcscfInfo;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.NodeInformation;

/**
 * @FileName : CommandDao.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 상태에서 사용하는 Dao
 */
public interface StatusDao {

	public List<VcscfInfo> getVcscfInfo();
	public List<ProjectInfo> getProjectList();
	
	public void insertProject(ProjectInfo project);
	
	public void insertHost(HostInfo host);
	
	public List<NodeInformation> getResourceInfo();
}
