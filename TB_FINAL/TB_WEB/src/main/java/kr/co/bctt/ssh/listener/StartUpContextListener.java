package kr.co.bctt.ssh.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kr.co.cfg.Config;
import kr.co.bctt.ssh.dao.JSchUtil;
import kr.co.bctt.ssh.db.DBManager;

public class StartUpContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		JSchUtil.disconnect();
		System.out.println("ServletContextListener destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");
		Main();
	}
	
	public void Main() {
		JSchUtil.connect();
		
		Config.initProperties();
		
		Properties prop = null;
		try {
			prop = Config.getProperties();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// DB Initialize.
		DBManager.init();
		
		// 매분 20초 마다 시작
		JobScheduler project_js = new JobScheduler(ProjectExecuter.class, "0 * * * * ?", "project");
		project_js.triggerJob();
		
		JobScheduler image_js = new JobScheduler(ImageExecuter.class, "5 * * * * ?", "image");
		image_js.triggerJob();
		
		JobScheduler flavor_js = new JobScheduler(FlavorExecuter.class, "10 * * * * ?", "flavor");
		flavor_js.triggerJob();
		
		JobScheduler instance_js = new JobScheduler(InstanceExecuter.class, "15 * * * * ?", "instance");
		instance_js.triggerJob();
		
		JobScheduler network_js = new JobScheduler(NetworkExecuter.class, "20 * * * * ?", "network");
		network_js.triggerJob();
		
		JobScheduler router_js = new JobScheduler(RouterExecuter.class, "25 * * * * ?", "router");
		router_js.triggerJob();
		
		JobScheduler subnet_js = new JobScheduler(SubnetExecuter.class, "30 * * * * ?", "subnet");
		subnet_js.triggerJob();
		
		
		// 매분 20초 마다 시작
		JobScheduler hlf_js = new JobScheduler(HyperledgerExecuter.class, "0/30 * * * * ?", "hyperledger");
		hlf_js.triggerJob();
		
		
		JobScheduler hlf2_js = new JobScheduler(HyperledgerExecuter2.class, "0/20 * * * * ?", "hyperledger2");
		hlf2_js.triggerJob();
		
		JobScheduler eth_js = new JobScheduler(EthereumExecuter.class, "0/20 * * * * ?", "ethereum");
		eth_js.triggerJob();
		
		
	}
}
