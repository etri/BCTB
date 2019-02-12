package kr.co.bctt.ssh.dao;

import java.util.List;
import java.util.Map;

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
 * @FileName : CommandDao.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 명령에서 사용하는 Dao
 */
public interface CommandDao {
	
	//image
	public List<ImageInfo> selectImageInfoList();
	public void udpateImageInfoList(ImageInfo imageInfo);
	public void insertImageInfoList(ImageInfo imageInfo);
	public void deleteImageInfoList(String id);
	
	//flavor
	public List<FlavorInfo> selectFlavorInfoList();
	public void udpateFlavorInfoList(FlavorInfo flavorInfo);
	public void insertFlavorInfoList(FlavorInfo flavorInfo);
	public List<Vnfd> selectVnfdList();
	public String selectFlavorId(String name);
	public void deleteFlavorInfo(String id);
	
	//package
	public List<PackageInfo> selectPackageInfoList();
	public void deletePackageInfo(String pakageName);
	public void insertPackageInfo(PackageInfo packageInfo);
	
	public void insertInstance(Instance instance);
	
	//vnfd
	public void insertVnfd(Vnfd vnfd);
	public void insertAllVnfd(Vnfd vnfd); /*추가 */
	public void updateVnfd(Vnfd vnfd);
	public void deleteVnfd(String vnfd_id);
	public Vnfd getVnfd(Vnfd vnfd);
	
	public List<VnfInfo> selectVnfInfoList();
	public void insertVnfInfo(VnfInfo vnfInfo);
	public void updateVnfInfo(Map<String,Object> vnfInfo);
	public void deleteVnfInfo(String vnf_id);
	
	///public_network
	public List<Network> selectNetworkList();
	public List<Network> selectExtNetworkList();
	public void insertNetwork(Network network);
	public void updatePublicNetwork(Network network);
	
	///private_network
	public List<Network> selectPrivateNetworkList();
	public void insertPrivateNetwork(Network network);
	public void updatePrivateNetwork(Network network);
	
	public List<Route> selectRouteList();
	public List<Route> selectRouteDetail(String router_id);
	public void insertRoute(Route route);
	public void updateRoute(Route route);
	
	public List<Instance> getInstanceList();
	public List<Instance> selectInstanceDetail(String instance_id);
	public List<Vnf> selectVnfDetail(String vnf_id);
	
	
	
	//추출용
	public List<FlavorInfo> flist();
	public List<Vnfd> vlist();
	public List<PackageInfo> plist();
	public List<ImageInfo> ilist();
	
	
	
	
	public void insertSubnet(Subnet subnet);
	public List<Subnet> selectSubnetList(String subnet_id);
	
	public void insertAllEthereum(Ethereum eth); /*추가 */
	public List<Ethereum> selectEthereum();
	
	public List<HostInfo> selectHostList();

	public List<Hyperledger> selectHyperledger();
	public void insertAllHyperledger(Hyperledger hlf); /*추가 */
	
}
