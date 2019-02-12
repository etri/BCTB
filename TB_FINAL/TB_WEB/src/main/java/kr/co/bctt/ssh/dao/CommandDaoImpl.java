package kr.co.bctt.ssh.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bctt.ssh.dto.Ethereum;
import kr.co.bctt.ssh.dto.FlavorInfo;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.Hyperledger;
import kr.co.bctt.ssh.dto.ImageInfo;
import kr.co.bctt.ssh.dto.Instance;
import kr.co.bctt.ssh.dto.Network;
import kr.co.bctt.ssh.dto.PackageInfo;
import kr.co.bctt.ssh.dto.Route;
import kr.co.bctt.ssh.dto.Subnet;
import kr.co.bctt.ssh.dto.Vnf;
import kr.co.bctt.ssh.dto.VnfInfo;
import kr.co.bctt.ssh.dto.Vnfd;

/**
 * @FileName : CommandDaoImpl.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 :
 */
@Repository
public class CommandDaoImpl implements CommandDao{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertImageInfoList(ImageInfo imageInfo) {
		int row = sqlSession.insert("command.insertImage",imageInfo);
	}
	@Override
	public List<ImageInfo> selectImageInfoList() {
	
		List<ImageInfo> list = sqlSession.selectList("command.selectImage");
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	@Override
	public void udpateImageInfoList(ImageInfo imageInfo) {
		int row = sqlSession.insert("command.updateImage",imageInfo);
	}
	@Override
	public void deleteImageInfoList(String id) {
		sqlSession.delete("command.deleteImage",id);
	}
	

	@Override
	public List<FlavorInfo> selectFlavorInfoList() {
		List<FlavorInfo> list = sqlSession.selectList("command.selectFlavor");
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	@Override
	public void udpateFlavorInfoList(FlavorInfo flavorInfo) {
		sqlSession.update("command.updateFlavor",flavorInfo);
	}
	@Override
	public void insertFlavorInfoList(FlavorInfo flavorInfo) {
		sqlSession.insert("command.insertFlavor",flavorInfo);
	}
	@Override
	public void deleteFlavorInfo(String id) {
		try{
		sqlSession.delete("command.deleteFlavor", id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Vnfd> selectVnfdList() {
		List<Vnfd> list = sqlSession.selectList("command.selectVnfd");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public String selectFlavorId(String name) {
		List<String> list = sqlSession.selectList("command.selectFlavorId", name);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public List<PackageInfo> selectPackageInfoList() {
		List<PackageInfo> list = sqlSession.selectList("command.selectPackageInfo");
		
		System.out.println("list :" + list.size());
		
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void deletePackageInfo(String pakageName) {
		sqlSession.selectList("command.deletePackageInfo",pakageName);
	}

	@Override
	public void insertPackageInfo(PackageInfo packageInfo) {
		sqlSession.selectList("command.insertPackageInfo",packageInfo);
	}
	
	//vnfd
	@Override
	public void insertVnfd(Vnfd vnfd) {
		sqlSession.insert("command.insertVnfd",vnfd);
	}

	@Override
	public void updateVnfd(Vnfd vnfd) {
		sqlSession.update("command.updateVnfd",vnfd);
	}

	@Override
	public void insertAllVnfd(Vnfd vnfd) {
		sqlSession.insert("command.insertAllVnfd",vnfd);
	}

	@Override
	public void deleteVnfd(String vnfd_id) {
		sqlSession.insert("command.deleteVnfd",vnfd_id);
	}
	
	@Override
	public List<VnfInfo> selectVnfInfoList() {
		List<VnfInfo> list = sqlSession.selectList("command.selectVnfInfo");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void insertVnfInfo(VnfInfo vnfInfo) {
		sqlSession.insert("command.insertVnfInfo",vnfInfo);
		
	}

	@Override
	public void updateVnfInfo(Map<String,Object> vnfInfo) {
		sqlSession.update("command.updateVnfInfo",vnfInfo);
	}

	@Override
	public void deleteVnfInfo(String vnf_id) {
		sqlSession.delete("command.deleteVnfInfo",vnf_id);
	}
	
	@Override
	public Vnfd getVnfd(Vnfd vnfd) {
		List<Vnfd> v_list = null;
		try{
			v_list = sqlSession.selectList("command.Vnfd", vnfd.getVnfd_id().toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return v_list.get(0);
	}
	
	
	// netwrok /////

	@Override
	public List<Network> selectNetworkList() {
		List<Network> list = sqlSession.selectList("command.selectNetwork");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void insertNetwork(Network network) {
		sqlSession.insert("command.insertNetwork",network);
		
	}

	@Override
	public void updatePublicNetwork(Network network) {
		sqlSession.update("command.updatePublicNetwork",network);
		
	}

	@Override
	public List<Network> selectPrivateNetworkList() {
		List<Network> list = sqlSession.selectList("command.selectPrivateNetwork");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void insertPrivateNetwork(Network network) {
		sqlSession.insert("command.insertPrivateNetwork",network);
		
	}

	@Override
	public void updatePrivateNetwork(Network network) {
		sqlSession.update("command.updatePrivateNetwork",network);
		
	}

	@Override
	public List<Route> selectRouteList() {
		List<Route> list = sqlSession.selectList("command.selectRoute");
		
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	@Override
	public List<Route> selectRouteDetail(String router_id) {
		List<Route> list = sqlSession.selectList("command.getRouterDetail", router_id);
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void insertRoute(Route route) {
		sqlSession.insert("command.insertRoute",route);
	}

	@Override
	public void updateRoute(Route route) {
		sqlSession.update("command.updateRoute",route);
	}
	
	
	
	//추출용
	@Override
	public List<FlavorInfo> flist() {
		List<FlavorInfo> list = sqlSession.selectList("command.getFlavor");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public List<Vnfd> vlist() {
		List<Vnfd> list = sqlSession.selectList("command.getVnfd");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public List<PackageInfo> plist() {
		List<PackageInfo> list = sqlSession.selectList("command.getPackage");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public List<ImageInfo> ilist() {
		List<ImageInfo> list = sqlSession.selectList("command.getImage");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public void insertInstance(Instance instance) {
		// TODO Auto-generated method stub
		sqlSession.insert("command.insertInstance",instance);
	}
	@Override
	public List<Instance> getInstanceList() {
		// TODO Auto-generated method stub
		List<Instance> list = sqlSession.selectList("command.getInstance");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public void insertSubnet(Subnet subnet) {
		sqlSession.insert("command.insertSubnet",subnet);
	}
	@Override
	public List<Subnet> selectSubnetList(String subnet_id) {
		List<Subnet> list = sqlSession.selectList("command.getSubnet", subnet_id);
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public List<Instance> selectInstanceDetail(String instance_id) {
		List<Instance> list = sqlSession.selectList("command.getInstanceDetail", instance_id);
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public void insertAllEthereum(Ethereum eth) {
		// TODO Auto-generated method stub
		sqlSession.insert("command.insertAllEthereum",eth);
	}
	@Override
	public List<Ethereum> selectEthereum() {
		List<Ethereum> list = sqlSession.selectList("command.getEthereum");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public List<HostInfo> selectHostList() {
		List<HostInfo> list = sqlSession.selectList("command.getHost");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public List<Network> selectExtNetworkList() {
		List<Network> list = sqlSession.selectList("command.selectExtNetwork");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public void insertAllHyperledger(Hyperledger hlf) {
		// TODO Auto-generated method stub
		sqlSession.insert("command.insertAllHyperledger", hlf);
	}
	@Override
	public List<Hyperledger> selectHyperledger() {
		List<Hyperledger> list = sqlSession.selectList("command.getHyperledger");
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public List<Vnf> selectVnfDetail(String vnf_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
