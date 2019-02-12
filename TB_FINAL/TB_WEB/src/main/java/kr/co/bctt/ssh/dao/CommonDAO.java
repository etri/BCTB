package kr.co.bctt.ssh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import kr.co.bctt.ssh.db.DBManager;
import kr.co.bctt.ssh.dto.Ethereum;
import kr.co.bctt.ssh.dto.FlavorInfo;
import kr.co.bctt.ssh.dto.HostInfo;
import kr.co.bctt.ssh.dto.Hyperledger;
import kr.co.bctt.ssh.dto.Image;
import kr.co.bctt.ssh.dto.ImageInfo;
import kr.co.bctt.ssh.dto.Instance;
import kr.co.bctt.ssh.dto.Network;
import kr.co.bctt.ssh.dto.NodeInformation;
import kr.co.bctt.ssh.dto.ProjectInfo;
import kr.co.bctt.ssh.dto.Route;
import kr.co.bctt.ssh.dto.Subnet;
import kr.co.bctt.ssh.dto.Vdu;
import kr.co.bctt.ssh.dto.Vnc;

public class CommonDAO {
	
	public static int insertProjectList(List<ProjectInfo> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE project_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				
				
				
				query = new StringBuffer();
				query.append("INSERT INTO project_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getProject_id()+"', '"+list.get(i).getProject_name()+"', '"+list.get(i).getDescription()+"', '"+list.get(i).getDomain_id()+"', '"+list.get(i).getEnabled()+"')");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertResourceList(List<NodeInformation> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE resource_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				
				query = new StringBuffer();
				query.append("INSERT INTO resource_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getHost()+"', "+list.get(i).getTotalCpu()+", "+list.get(i).getUseCpu()+", "+list.get(i).getTotalMem()+", "+list.get(i).getUseMem()+", "+list.get(i).getTotalDisk()+", "+list.get(i).getUseDisk()+")");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
//				System.out.println("query.toString() :" + query.toString());
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertHostList(List<HostInfo> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE host_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				
				query = new StringBuffer();
				query.append("INSERT INTO host_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getHostName()+"', '"+list.get(i).getService()+"', '"+list.get(i).getZone()+"')");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertImageList(List<ImageInfo> list){
		
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE image_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				
				query = new StringBuffer();
				query.append("INSERT INTO image_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getName()+"', '"+list.get(i).getId()+"', '"+list.get(i).getFormat()+"', '"+list.get(i).getOs()+"', '"+list.get(i).getSize()+"', '"+list.get(i).getDescription()+"', '"+list.get(i).getProject_id()+"', '"+list.get(i).getStatus()+"', '"+list.get(i).getVisibility()+"', '"+list.get(i).get_protected()+"')");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertFlavorList(List<FlavorInfo> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE flavor_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				
				query = new StringBuffer();
				query.append("INSERT INTO flavor_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getName()+"', '"+list.get(i).getId()+"', '"+list.get(i).getVcpus()+"', '"+list.get(i).getRam()+"', '"+list.get(i).getRoot_disk()+"', '"+list.get(i).getEphemeral_disk()+"', '"+list.get(i).getSwap_disk()+"', '"+list.get(i).getRxtx_factor()+"', '"+list.get(i).getIs_public()+"', '"+list.get(i).getDisabled()+"')");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertInstanceList(List<Instance> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE instance_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				pstmt.close();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("##### TRUNCATE INSTANCE TABLE #####");
				
				query = new StringBuffer();
				query.append("INSERT INTO instance_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getDiskConfig()+"', '"+list.get(i).getAvailability_zone()+"', '"+list.get(i).getHost()+"', ");
		            query.append("'"+list.get(i).getHypervisor_hostname()+"', '"+list.get(i).getInstance_name()+"', '"+list.get(i).getPower_state()+"', ");
		            query.append("'"+list.get(i).getTask_state()+"', '"+list.get(i).getVm_state()+"', '"+list.get(i).getLaunched_at()+"', '"+list.get(i).getTerminated_at()+"', ");
		            query.append("'"+list.get(i).getAccessIPv4()+"', '"+list.get(i).getAccessIPv6()+"', '"+list.get(i).getAddresses()+"', '"+list.get(i).getConfig_drive()+"', ");
		            query.append("'"+list.get(i).getCreated()+"', '"+list.get(i).getFlavor_name()+"', '"+list.get(i).getFlavor_id()+"', '"+list.get(i).getHostId()+"', ");
		            query.append("'"+list.get(i).getId()+"', '"+list.get(i).getImage_name()+"', '"+list.get(i).getImage_id()+"', '"+list.get(i).getKey_name()+"', ");
		            query.append("'"+list.get(i).getName()+"', '"+list.get(i).getProgress()+"', '"+list.get(i).getProject_id()+"', '"+list.get(i).getProperties()+"', ");
		            query.append("'"+list.get(i).getSecurity_groups().replace("'", "''")+"', '"+list.get(i).getStatus()+"', '"+list.get(i).getUpdated()+"', '"+list.get(i).getUser_id()+"', '"+list.get(i).getVolumes_attached()+"', '"+list.get(i).getUrl()+"')");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				System.out.println("##### INSERT INSTANCE TABLE ##### :" + query.toString());
				result = pstmt.executeUpdate();
				
				System.out.println("result :" + result);
			}
			
		} catch (SQLException e) {
			System.out.println("e :" + e.toString());
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertNetworkList(List<Network> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE network_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				
				query = new StringBuffer();
				query.append("INSERT INTO network_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getNetwork_name()+"', '"+list.get(i).getNetwork_id()+"', '"+list.get(i).getSubnet_id()+"', '"+list.get(i).getCidr()+"', ");
		            query.append("'"+list.get(i).getGateway_ip()+"', '"+list.get(i).getSubnet_name()+"', '"+list.get(i).getProject_id()+"', '"+list.get(i).getAllocation_pools()+"', ");
		            query.append("'"+list.get(i).getDns_nameservers()+"', '"+list.get(i).getEnable_dhcp()+"', '"+list.get(i).getIp_version()+"', '"+list.get(i).getExternal()+"')");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertRouterList(List<Route> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE router_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				
				query = new StringBuffer();
				query.append("INSERT INTO router_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getAdmin_state_up()+"', '"+list.get(i).getAvailability_zone_hints()+"', '"+list.get(i).getAvailability_zones()+"', '"+list.get(i).getCreated_at()+"', ");
		            query.append("'"+list.get(i).getDescription()+"', '"+list.get(i).getDistributed()+"', '"+list.get(i).getExternal_gateway_info()+"', '"+list.get(i).getFlavor_id()+"', ");
		            query.append("'"+list.get(i).getHa()+"', '"+list.get(i).getId()+"', '"+list.get(i).getInterfaces_info()+"', '"+list.get(i).getName()+"', ");
		            query.append("'"+list.get(i).getProject_id()+"', '"+list.get(i).getRevision_number()+"', '"+list.get(i).getRoutes()+"', '"+list.get(i).getStatus()+"', ");
		            query.append("'"+list.get(i).getTags()+"', '"+list.get(i).getUpdated_at()+"')");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertSubnetList(List<Subnet> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("TRUNCATE subnet_table ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();
				
				query = new StringBuffer();
				query.append("INSERT INTO subnet_table VALUES ");
				
				for(int i = 0; i<list.size(); i++){
					//비정상 로그아웃 이력 삽입.
		            query.append("('"+list.get(i).getAllocation_pools()+"', '"+list.get(i).getCidr()+"', '"+list.get(i).getCreated_at()+"', '"+list.get(i).getDescription()+"', ");
		            query.append("'"+list.get(i).getDns_nameservers()+"', '"+list.get(i).getEnable_dhcp()+"', '"+list.get(i).getGateway_ip()+"', '"+list.get(i).getHost_routes()+"', ");
		            query.append("'"+list.get(i).getId()+"', '"+list.get(i).getIp_version()+"', '"+list.get(i).getIpv6_address_mode()+"', '"+list.get(i).getIpv6_ra_mode()+"', ");
		            query.append("'"+list.get(i).getName()+"', '"+list.get(i).getNetwork_id()+"', '"+list.get(i).getProject_id()+"', '"+list.get(i).getRevision_number()+"', ");
		            query.append("'"+list.get(i).getSegment_id()+"', '"+list.get(i).getService_types()+"', '"+list.get(i).getSubnetpool_id()+"', '"+list.get(i).getTags()+"', ");
		            query.append("'"+list.get(i).getUpdated_at()+"')");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static List<Ethereum> selectEthereumInfo(String idx){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;
		
		List<Ethereum> list = new ArrayList<Ethereum>();

		try {
			con = DBManager.getConnection();
			
			query.append("SELECT * FROM ethereum_table WHERE idx = "+idx+" ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				Ethereum bean = new Ethereum();
				bean.setVnfd_id(rs.getString("vnfd_id"));
				bean.setVnf_id(rs.getString("vnf_id"));
				
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	public static List<Vnc> selectVncInfo(){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		List<Vnc> list = new ArrayList<Vnc>();

		try {
			con = DBManager.getConnection();
			
			query.append("SELECT * FROM vnc_config_table");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				Vnc bean = new Vnc();
				bean.setIp_addr(rs.getString("ip_addr"));
				bean.setPort(rs.getString("port"));
				
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	public static List<Hyperledger> selectHyperledgerInfo(String idx){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;
		
		List<Hyperledger> list = new ArrayList<Hyperledger>();

		try {
			con = DBManager.getConnection();
			
			query.append("SELECT * FROM hyperledger_table WHERE idx = "+idx+" ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				Hyperledger bean = new Hyperledger();
				bean.setVnfd_id(rs.getString("vnfd_id"));
				bean.setVnf_id(rs.getString("vnf_id"));
				
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	public static int deleteEthereum(String idx){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			query.append("DELETE FROM ethereum_table WHERE idx = "+idx+" ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int deleteHyperledger(String idx){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			query.append("DELETE FROM hyperledger_table WHERE idx = "+idx+" ");
			pstmt = con.prepareStatement(query.toString());
			result = pstmt.executeUpdate();
			
			if(result > 0){
				query = new StringBuffer();
				query.append("DELETE FROM hlf_host_info_profile WHERE profile_id = "+idx+" ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.executeUpdate();	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int insertHyperledger(Hyperledger hlf){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int profile_id = -1;

		try {
			con = DBManager.getConnection();
			
			query.append("INSERT INTO hyperledger_table VALUES ");
			
            query.append("(null, NOW(), '"+hlf.getProfile_name() + "', '"+hlf.getDescription() + "', "+hlf.getOrderer_cnt() + ", "+hlf.getPeer_org_cnt() + ", "+hlf.getOrg_peer_cnt());
            query.append(", '"+hlf.getOrderer_type() + "', "+hlf.getBatch_timeout() + ", "+hlf.getMax_message_cnt() + ", "+hlf.getAbsolute_max_bytes() + ", "+hlf.getPreferred_max_bytes());
            query.append(", '"+hlf.getNetwork_id() + "', '"+hlf.getNetwork_name() + "', '"+hlf.getFlavor_id() + "', '"+hlf.getFlavor_name() + "', '', '', 'PENDING_CREATE')");
			
			pstmt = con.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys(); //AutoIncrement 키값을 가져온다.
			while (rs.next()) {
				profile_id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return profile_id;
	}
	
	public static int insertVduList(List<Vdu> list){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			if(list.size() > 0){
				query.append("INSERT INTO hlf_host_info_profile VALUES ");
				
				for(int i = 0; i<list.size(); i++){
		            query.append("("+list.get(i).getProfile_id()+", '"+list.get(i).getVdu_name()+"', '"+list.get(i).getOrg_type()+"', '"+list.get(i).getOrg_name()+"', '"+list.get(i).getHost_type()+"', '"+list.get(i).getHost_name()+"', '', 0)");
		            
		            if(i == list.size() - 1){
		            }else{
		            	query.append(",	");
		            }
				}
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int updateHlfList(int profile_id, String vnfd_id, String vnf_id){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			query.append("UPDATE hyperledger_table SET vnfd_id = '"+vnfd_id+"', vnf_id = '"+vnf_id+"' WHERE idx = "+profile_id+" ");
			
			pstmt = con.prepareStatement(query.toString());
			result = pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int updateVncList(String ip_addr, String port){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
			
			query.append("UPDATE vnc_config_table SET ip_addr = '"+ip_addr+"', port= '"+port + "'");
			
			pstmt = con.prepareStatement(query.toString());
			result = pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static List<Hyperledger> selectHyperledgerWaitList(){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		List<Hyperledger> list = new ArrayList<Hyperledger>();

		try {
			con = DBManager.getConnection();
			
			query.append("SELECT A.profile_id, HT.vnf_id FROM ");
			query.append("(SELECT profile_id FROM hlf_host_info_profile WHERE status = 0 GROUP BY profile_id) A ");
			query.append("LEFT JOIN hyperledger_table HT ON A.profile_id = HT.idx ");
			query.append("WHERE vnf_id IS NOT NULL	");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				Hyperledger bean = new Hyperledger();
				bean.setProfile_id(rs.getInt("profile_id"));
				bean.setVnf_id(rs.getString("vnf_id"));
				
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	public static List<Hyperledger> selectHyperledgerStatusList(){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		List<Hyperledger> list = new ArrayList<Hyperledger>();

		try {
			con = DBManager.getConnection();
			
			query.append("SELECT * FROM hyperledger_table WHERE status != 'ACTIVE' && status != 'ERROR' ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				Hyperledger bean = new Hyperledger();
				bean.setVnf_id(rs.getString("vnf_id"));
				
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	public static List<Ethereum> selectEthereumStatusList(){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		List<Ethereum> list = new ArrayList<Ethereum>();

		try {
			con = DBManager.getConnection();
			
			query.append("SELECT * FROM ethereum_table WHERE status != 'ACTIVE' && status != 'ERROR' ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				Ethereum bean = new Ethereum();
				bean.setVnf_id(rs.getString("vnf_id"));
				
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	public static int updateHlfStatus(String mgmt_url, int profile_id){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		String temp = mgmt_url.replace("{", "").replace("}", "").replace("\"", ""); // {, }, " 제거
		
		String[] var = temp.split(",");
		HashMap<String, String> _map = new HashMap<>();
		
		for(int i = 0; i<var.length; i++){
			if(!var[i].equals("")){
				String[] var2 = var[i].split(":");
				
				for(int j=0; j<var2.length; j++){
					_map.put(var2[0], var2[1]);
				}	
			}
		}
		
		
		int result = -1;

		try {
			con = DBManager.getConnection();
		
			Set<Entry<String, String>> set = _map.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
				  
			while (it.hasNext())
			{
				Map.Entry<String, String> e = (Map.Entry<String, String>)it.next();
				
				query = new StringBuffer();
				query.append("UPDATE hlf_host_info_profile SET host_ip = '"+e.getValue()+"', status = 1 WHERE profile_id = "+profile_id+" AND vdu_name = '"+e.getKey()+"' ");
				
				pstmt = con.prepareStatement(query.toString());
				result = pstmt.executeUpdate();
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int updateHlfStatus2(String status, String vnf_id){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
		
			query.append("UPDATE hyperledger_table SET status = '"+status+"' WHERE vnf_id = '"+vnf_id+"'");
			
			pstmt = con.prepareStatement(query.toString());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
	
	public static int updateEthereumStatus(String status, String vnf_id){
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		int result = -1;

		try {
			con = DBManager.getConnection();
		
			query.append("UPDATE ethereum_table SET status = '"+status+"' WHERE vnf_id = '"+vnf_id+"'");
			
			pstmt = con.prepareStatement(query.toString());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.freeConnection(con, pstmt, rs);
		}
		
		return result;
	}
}
