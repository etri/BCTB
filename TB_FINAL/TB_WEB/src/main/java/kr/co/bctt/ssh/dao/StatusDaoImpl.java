package kr.co.bctt.ssh.dao;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.dto.VcscfInfo;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.NodeInformation;

/**
 * @FileName : CommandDaoImpl.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
@Repository
public class StatusDaoImpl implements StatusDao {

	@Autowired
	public SqlSession sqlSession;

	@Override
	public List<VcscfInfo> getVcscfInfo() {
		List<VcscfInfo> list = sqlSession.selectList("status.getVcscfInfo");
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	
	@Override
	public List<ProjectInfo> getProjectList() {
		List<ProjectInfo> list = sqlSession.selectList("status.getProjectList");
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public void insertProject(ProjectInfo project) {
		sqlSession.insert("command.insertProject", project);
	}

	@Override
	public void insertHost(HostInfo host) {
		// TODO Auto-generated method stub
		sqlSession.insert("command.insertHost", host);
	}

	@Override
	public List<NodeInformation> getResourceInfo() {
		List<NodeInformation> list = sqlSession.selectList("status.getResourceInfo");
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}
}
