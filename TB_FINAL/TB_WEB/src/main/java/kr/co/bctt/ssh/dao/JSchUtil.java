package kr.co.bctt.ssh.dao;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.KeyPair;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

/**
 * @FileName : JSchUtil.java
 * @Project : BCTT
 * @Date : 2016. 2. 12.
 * @작성자 : Park
 * @변경이력 :
 * @프로그램설명 : 서버쪽으로 command를 실행할때 사용
 */
@Repository
public class JSchUtil {
	private String hostname;
	private String username;
	private String identity=null;
	private String password=null;
	private boolean isDebugMode=true;

	public void enableDebug(){
		isDebugMode=true;
	}

	public void disableDebug(){
		isDebugMode=false;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
		this.password =null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.identity=null;
	}

	public JSchUtil(){ }

	public JSchUtil(String username, String hostname){
		this.username = username;
		this.hostname = hostname;
	}

	public void setPortForwardingL(int port,String host,int hostport){

	}

	private Session getSession() throws JSchException{
		JSch jsch=new JSch();
		if (identity!=null) {
			jsch.addIdentity(identity);
			//jsch.setKnownHosts(new ByteArrayInputStream(hostname.getBytes()));
		}

		Session session=jsch.getSession(username, hostname, 22);
		session.setConfig("StrictHostKeyChecking", "no");
		if (password!=null)	session.setPassword(password);
		return session;
	}

	public String exec(String command){
		return exec(new String[] {command}).get(0);
	}
	public List<String> exec(List<String> commands){
		return exec(commands.toArray(new String[]{}));
	}


	public List<String> exec(String[] commands) {
		List<String> ret = new ArrayList<String>();
		try{
			Session session = getSession();
			session.connect();
			for (String command:commands){
				ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
				channelExec.setPty(true);
				if (isDebugMode) System.out.println("command : "+command);
				channelExec.setCommand(command);
				InputStream inputStream = channelExec.getInputStream();
				//InputStream ext = channelExec.getExtInputStream();
				InputStream err = channelExec.getErrStream();
				channelExec.connect(3000);

				if (isDebugMode) System.out.print("stdout : ");
				String output="";
				byte[] buf = new byte[1024];
				int length;
				while ((length=inputStream.read(buf))!=-1){
					output+=new String(buf,0,length);
					if (isDebugMode) System.out.print(new String(buf,0,length));
				}
				if (isDebugMode) System.out.println("\nerr : "+IOUtils.toString(err));
				ret.add(StringUtils.chop(output));
				channelExec.disconnect();
			}
			session.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}


	/* ---- generate ssh keypair  --------- */

	public void keyGen(String algorithm, String passphrase){

		int type=KeyPair.RSA;

		String filename="/root/test/test1";
		String comment="1234";

		JSch jsch = new JSch();

//		JTextField passphraseField=(JTextField)new JPasswordField(20);
//		Object[] ob={passphraseField};
//		int result=
//				JOptionPane.showConfirmDialog(null, ob, "Enter passphrase (empty for no passphrase)",
//						JOptionPane.OK_CANCEL_OPTION);
//		if(result==JOptionPane.OK_OPTION){
//			passphrase=passphraseField.getText();
//		}

		try{
			KeyPair kpair=KeyPair.genKeyPair(jsch, type, 1024);
			kpair.setPassphrase(passphrase);
			kpair.writePrivateKey(filename);
			kpair.writePublicKey(filename+".pub", comment);
			System.out.println("Finger print: "+kpair.getFingerPrint());
			kpair.dispose();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	/* Scp ----------------------- */

	public String scpFrom(String rfile){
		String str ="";
		try{
			File lfile = File.createTempFile("temp", ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(lfile));
			scpFrom(rfile,lfile);
			String line;
			while((line=br.readLine())!=null) str+=line+"\n";
			br.close();
			lfile.delete();
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		return str;
	}
	public void scpFrom(String rfile,File lfile){
		//usage: java ScpFrom user@remotehost:file1 file2
		FileOutputStream fos=null;
		try{

			Session session = getSession();
			// username and password will be given via UserInfo interface.
			session.connect();

			// exec 'scp -f rfile' remotely
			String command="scp -f "+rfile;
			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream out=channel.getOutputStream();
			InputStream in=channel.getInputStream();

			channel.connect();

			byte[] buf=new byte[1024];

			// invoke '\0'
			buf[0]=0; out.write(buf, 0, 1); out.flush();

			while(true){
				int c=checkAck(in);
				if(c!='C'){
					break;
				}

				// read '0644 '
				in.read(buf, 0, 5);

				long filesize=0L;
				while(true){
					if(in.read(buf, 0, 1)<0){
						// error
						break;
					}
					if(buf[0]==' ')break;
					filesize=filesize*10L+(long)(buf[0]-'0');
				}

				String file=null;
				for(int i=0;;i++){
					in.read(buf, i, 1);
					if(buf[i]==(byte)0x0a){
						file=new String(buf, 0, i);
						break;
					}
				}

				//System.out.println("filesize="+filesize+", file="+file);

				// invoke '\0'
				buf[0]=0; out.write(buf, 0, 1); out.flush();

				// read a content of lfile

				fos=new FileOutputStream(lfile);
				int foo;
				while(true){
					if(buf.length<filesize) foo=buf.length;
					else foo=(int)filesize;
					foo=in.read(buf, 0, foo);
					if(foo<0){
						// error
						break;
					}
					fos.write(buf, 0, foo);
					filesize-=foo;
					if(filesize==0L) break;
				}
				fos.close();
				fos=null;

				if(checkAck(in)!=0){
					return;
				}

				// invoke '\0'
				buf[0]=0; out.write(buf, 0, 1); out.flush();
			}

			session.disconnect();
		}
		catch(Exception e){
			System.out.println(e);
			try{if(fos!=null)fos.close();}catch(Exception ee){}
		}
	}

	public void scpTo(String content, String rfile){
		try{
			File tfile = File.createTempFile("prefix", ".tmp");
			FileWriter fw = new FileWriter(tfile);
			fw.write(content);
			fw.close();
			scpTo(tfile, rfile);
			tfile.delete();
		}catch(IOException e){
			e.printStackTrace();
			return;
		}
	}

	public void scpTo(File lfile, String rfile){
		// ScpTo file1 user@remotehost:file2

		FileInputStream fis=null;
		try{
			Session session = getSession();
			session.connect();

			boolean ptimestamp = true;

			// exec 'scp -t rfile' remotely
			String command="scp " + (ptimestamp ? "-p" :"") +" -t "+rfile;
			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream out=channel.getOutputStream();
			InputStream in=channel.getInputStream();

			channel.connect();

			if(checkAck(in)!=0){
				//System.exit(0);
				return;
			}

			String filename = lfile.getName();
			if(ptimestamp){
				command="T "+(lfile.lastModified()/1000)+" 0";
				// The access time should be sent here,
				// but it is not accessible with JavaAPI ;-<
				command+=(" "+(lfile.lastModified()/1000)+" 0\n");
				out.write(command.getBytes()); out.flush();
				if(checkAck(in)!=0){
					//System.exit(0);
					return;
				}
			}

			// invoke "C0644 filesize filename", where filename should not include '/'
			long filesize=lfile.length();
			command="C0644 "+filesize+" ";
			if(filename.lastIndexOf('/')>0){
				command+=filename.substring(filename.lastIndexOf('/')+1);
			}
			else{
				command+=filename;
			}
			command+="\n";
			out.write(command.getBytes()); out.flush();
			if(checkAck(in)!=0){
				//System.exit(0);
				return;
			}

			// invoke a content of lfile
			fis=new FileInputStream(lfile);
			byte[] buf=new byte[1024];
			while(true){
				int len=fis.read(buf, 0, buf.length);
				if(len<=0) break;
				out.write(buf, 0, len); //out.flush();
			}
			fis.close();
			fis=null;
			// invoke '\0'
			buf[0]=0; out.write(buf, 0, 1); out.flush();
			if(checkAck(in)!=0){
				return;
			}
			out.close();

			channel.disconnect();
			session.disconnect();
		}
		catch(Exception e){
			System.out.println(e);
			try{if(fis!=null)fis.close();}catch(Exception ee){}
		}
	}

//	private int checkAck(InputStream in) throws IOException{
//		int b=in.read();
//		// b may be 0 for success,
//		//          1 for error,
//		//          2 for fatal error,
//		//          -1
//		if(b==0) return b;
//		if(b==-1) return b;
//
//		if(b==1 || b==2){
//			StringBuffer sb=new StringBuffer();
//			int c;
//			do {
//				c=in.read();
//				sb.append((char)c);
//			}
//			while(c!='\n');
//			if(b==1){ // error
//				System.out.print(sb.toString());
//			}
//			if(b==2){ // fatal error
//				System.out.print(sb.toString());
//			}
//		}
//		return b;
//	}
	
	static int checkAck(InputStream in) throws IOException{
		int b=in.read();
		// b may be 0 for success,
		//          1 for error,
		//          2 for fatal error,
		//          -1
		if(b==0) return b;
		if(b==-1) return b;

		if(b==1 || b==2){
			StringBuffer sb=new StringBuffer();
			int c;
			do {
				c=in.read();
				sb.append((char)c);
			}
			while(c!='\n');
			if(b==1){ // error
				System.out.print(sb.toString());
			}
			if(b==2){ // fatal error
				System.out.print(sb.toString());
			}
		}
		return b;
	}
	
	public static String start(String cmd){
		
		JSchUtil util = new JSchUtil("root", "192.168.0.11");
		util.keyGen("rsa", "");
		//내피씨를 서버에 등록을 먼시키고 , 키교환을 함/ 키젠: 피씨정보를가지고 퍼블릭 키를 만듬.
		//scp : 만든 키정보를 242번서버에 보내주고 인증을 받음. 
		//성공하면, 별도
		FileInputStream fis=null;
		StringBuilder sb = new StringBuilder();

		try{
			String lfile="/root/test/test1.pub";
			String user="root";
			String host="192.168.0.11";
			String rfile=".ssh/authorized_keys";
			JSch jsch=new JSch();
			
			Session session=jsch.getSession(user, host, 122);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword("safe!1234"); //접속시 필요한 서버의 패스워드 저장
			session.connect();

			boolean ptimestamp = true;

			// exec 'scp -t rfile' remotely
			String command="scp " + (ptimestamp ? "-p" :"") +" -t "+rfile;
			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream out=channel.getOutputStream();
			InputStream in=channel.getInputStream();

			channel.connect();

			if(checkAck(in)!=0) {
				System.exit(0);
			}

			File _lfile = new File(lfile);

			if(ptimestamp){
				command="T"+(_lfile.lastModified()/1000)+" 0";
				// The access time should be sent here,
				// but it is not accessible with JavaAPI ;-<
				command+=(" "+(_lfile.lastModified()/1000)+" 0\n"); 
				out.write(command.getBytes()); out.flush();
				if(checkAck(in)!=0){
					System.exit(0);
				}
			}

			// send "C0644 filesize filename", where filename should not include '/'
			long filesize=_lfile.length();
			command="C0644 "+filesize+" ";
			if(lfile.lastIndexOf('/')>0){
				command+=lfile.substring(lfile.lastIndexOf('/')+1);
			}
			else{
				command+=lfile;
			}
			command+="\n";
			out.write(command.getBytes()); out.flush();
			if(checkAck(in)!=0){
				System.exit(0);
			}

			// send a content of lfile
			fis=new FileInputStream(lfile);
			byte[] buf=new byte[1024];
			while(true){
				int len=fis.read(buf, 0, buf.length);
				if(len<=0) break;
				out.write(buf, 0, len); //out.flush();
			}
			fis.close();
			fis=null;
			// send '\0'
			buf[0]=0; out.write(buf, 0, 1); out.flush();
			if(checkAck(in)!=0){
				System.exit(0);
			}
			out.close();
				
			String command2= cmd; //요청 명령
			Channel channel2=session.openChannel("exec");
			((ChannelExec)channel2).setCommand(command2);
			
			channel.setInputStream(null);

			((ChannelExec)channel2).setErrStream(System.err);

			InputStream ins=channel2.getInputStream();

			channel2.connect();
			
			byte[] tmp=new byte[1024];
			while(true){
				while(ins.available()>0){
					int i=ins.read(tmp, 0, 1024);
					if(i<0)break;
					System.out.print(new String(tmp, 0, i)); //파싱할 정보
					sb.append(new String(tmp, 0, i));
				}
				if(channel2.isClosed()){
					if(ins.available()>0) continue; 
					System.out.println("exit-status: "+channel2.getExitStatus());
					break;
				}
			}

			channel2.disconnect();
			session.disconnect();
			
		}catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String sendCommand(String cmd){
		
		StringBuilder sb = new StringBuilder();

		try{
				
			String command2= cmd; //요청 명령
			Channel channel2=session.openChannel("exec");
			((ChannelExec)channel2).setCommand(command2);
			
			channel2.setInputStream(null);

			((ChannelExec)channel2).setErrStream(System.err);

			InputStream ins=channel2.getInputStream();

			channel2.connect();
			
			byte[] tmp=new byte[1024];
			while(true){
				
				while(ins.available()>0){
					int i=ins.read(tmp, 0, 1024);
					if(i<0)break;
//					System.out.print(new String(tmp, 0, i)); //파싱할 정보
					sb.append(new String(tmp, 0, i));
				}
				if(channel2.isClosed()){
					if(ins.available()>0) continue; 
					break;
				}
			}

			channel2.disconnect();
//			session.disconnect();
			
		}catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static Session session = null;

	public static void connect(){
		
		System.out.println("JSch Connect!!");
		
		JSchUtil util = new JSchUtil("root", "192.168.0.11");
		util.keyGen("rsa", "");
		//내피씨를 서버에 등록을 먼시키고 , 키교환을 함/ 키젠: 피씨정보를가지고 퍼블릭 키를 만듬.
		//scp : 만든 키정보를 242번서버에 보내주고 인증을 받음. 
		//성공하면, 별도
		FileInputStream fis=null;
		
		System.out.println("#################### CONNECT ############################");

		try{
			String lfile="/root/test/test1.pub";
			String user="root";
			String host="192.168.0.11";
			String rfile=".ssh/authorized_keys";
			JSch jsch=new JSch();
			
			session=jsch.getSession(user, host, 122);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword("safe!1234"); //접속시 필요한 서버의 패스워드 저장
			session.connect();
			
		}catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void disconnect(){
		
		session.disconnect();
	}
	
	//이미지 업로드를 위한 ..
	//****************************************
	public static String imageUpload(String path, String name, String format){
		

		 	JSchUtil util = new JSchUtil("kbell", "192.168.0.11");
	        util.keyGen("rsa", "");
	 
	        FileInputStream fis=null;
	        StringBuilder sb = new StringBuilder();
	        try{
//	            String lfile="C:/test1.pub";
	            String lfile=path; //****************
	            String user="kbell";
	            String host="192.168.0.11";
//	            String rfile=".ssh/authorized_keys";
	            String rfile="/home/kbell/";
	 
	            
	            JSch jsch=new JSch();
	            
	            Session session=jsch.getSession(user, host, 22);
	            session.setConfig("StrictHostKeyChecking", "no");
	            session.setPassword("*2kkbell");
	            session.connect();
	 
	            boolean ptimestamp = true;
	 
	            // exec 'scp -t rfile' remotely
	            String command="scp " + (ptimestamp ? "-p" :"") +" -t "+rfile;
//	            String command="scp " + (ptimestamp ? "-P" :"") + rfile;
	            
	            System.out.println("command :" + command);
	            Channel channel=session.openChannel("exec");
	            ((ChannelExec)channel).setCommand(command);
	 

	            OutputStream out=channel.getOutputStream();
	            InputStream in=channel.getInputStream();
	 
	            channel.connect();
	 
	            if(checkAck(in)!=0) {
	                System.exit(0);
	            }
	 
	            File _lfile = new File(lfile);
	            if(ptimestamp){
	                command="T"+(_lfile.lastModified()/1000)+" 0";
	                // The access time should be sent here,
	                // but it is not accessible with JavaAPI ;-<
	                command+=(" "+(_lfile.lastModified()/1000)+" 0\n"); 
	                out.write(command.getBytes()); out.flush();
	                if(checkAck(in)!=0){
	                    System.exit(0);
	                }
	            }
	            // send "C0644 filesize filename", where filename should not include '/'
	            long filesize=_lfile.length();
	            command="C0644 "+filesize+" ";
	            if(lfile.lastIndexOf('/')>0){
	                command+=lfile.substring(lfile.lastIndexOf('/')+1);
	            }
	            else{
	                command+=lfile;
	            }
	            command+="\n";
	     
	            
	       
	            out.write(command.getBytes()); out.flush();
	            if(checkAck(in)!=0){
	                System.exit(0);
	            }
	  
	            // send a content of lfile
	            fis=new FileInputStream(lfile);
	            byte[] buf=new byte[1024];
	            while(true){
	                int len=fis.read(buf, 0, buf.length);
	                if(len<=0) break;
	                out.write(buf, 0, len); //out.flush();
	            }
	            fis.close();
	            fis=null;
	            // send '\0'
	            buf[0]=0; out.write(buf, 0, 1); out.flush();
	            if(checkAck(in)!=0){
	                System.exit(0);
	            }
	  
	            out.close();
	      
//	            String command2="source admin-openrc.sh; neutron port-list";
//	            String command2="pwd";
//	            String command2="source admin-openrc.sh; tacker vnf-list";
//	            String command2="source admin-openrc.sh; heat stack-list";
//	            String command2="source admin-openrc.sh; neutron port-list | grep \"\"15.0.0.11\"\"";
//	            String command2="source admin-openrc.sh; openstack host show vcscf";
//	            String command2="source admin-openrc.sh; neutron net-list";
//	            String command2="source admin-openrc.sh; heat resource-list 5ca0759c-e25d-4e2b-bf97-1c043c933e3c";
//	            String command2="source admin-openrc.sh; neutron net-list";
//	            String command2="source admin-openrc.sh; neutron router-list";
//	            String command2="source admin-openrc.sh; openstack server show 96764089-64e6-4ecf-81a8-317a0f1eaf42";
//	            String command2="source admin-openrc.sh; openstack image list";
//	            String command2="source admin-openrc.sh; openstack image show ef4af84b-7dbb-49ea-bef9-1b7e85388a2b";
//	            String command2="scp -P 22 D:/Release.zip kbell@192.168.1.246:/home/kbell/";
	            int idx =lfile.lastIndexOf("/");
	            
	            System.out.println(lfile.substring(idx+1));
	            String command2="source admin-openrc.sh; glance image-create --name "+name+" --file /home/kbell/"+lfile.substring(idx+1)+" --disk-format "+format+" --container-format bare --visibility public --progress";
	            
	            Channel channel2=session.openChannel("exec");
	            ((ChannelExec)channel2).setCommand(command2);
	 
	            // X Forwarding
	            // channel.setXForwarding(true);physical_resource_id
	 
	            //channel.setInputStream(System.in);
	            channel.setInputStream(null);
	 
	            //channel.setOutputStream(System.out);
	 
	            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
	            //((ChannelExec)channel).setErrStream(fos);
	            ((ChannelExec)channel2).setErrStream(System.err);
	 
	            InputStream ins=channel2.getInputStream();
	 
	            channel2.connect();
	 
	            byte[] tmp=new byte[1024];
	            while(true){
	                while(ins.available()>0){
	                    int i=ins.read(tmp, 0, 1024);
	                    if(i<0)break;
	                    System.out.print(new String(tmp, 0, i));
	                    sb.append(new String(tmp, 0, i));
	                }
	                if(channel2.isClosed()){
	                    if(ins.available()>0) continue; 
	                    System.out.println("exit-status: "+channel2.getExitStatus());
	                    break;
	                }
	            }
	            channel2.disconnect();
	            
	            System.out.println("아아앙 경로아야양0"+path);
	            
	            
	            String command3="source admin-openrc.sh; rm -rf /home/kbell/"+path;
	            
	            Channel channel3=session.openChannel("exec");
	            ((ChannelExec)channel3).setCommand(command3);
	 
	            // X Forwarding
	            // channel.setXForwarding(true);physical_resource_id
	 
	            //channel.setInputStream(System.in);
	            channel3.setInputStream(null);
	 
	            //channel.setOutputStream(System.out);
	 
	            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
	            //((ChannelExec)channel).setErrStream(fos);
	            ((ChannelExec)channel3).setErrStream(System.err);
	 
	            ins=channel3.getInputStream();
	 
	            channel3.connect();
	 
	            tmp=new byte[1024];
	            while(true){
	                while(ins.available()>0){
	                    int i=ins.read(tmp, 0, 1024);
	                    if(i<0)break;
	                    System.out.print(new String(tmp, 0, i));
	                }
	                if(channel3.isClosed()){
	                    if(ins.available()>0) continue; 
	                    System.out.println("exit-status: "+channel3.getExitStatus());
	                    break;
	                }
	            }
	            channel3.disconnect();
	            session.disconnect();
	        }catch (JSchException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
		
		return sb.toString();

	}
	//*********************************

	public static class MyUserInfo implements UserInfo, UIKeyboardInteractive{
		public String getPassword(){ 
			return passwd; 
		}
		
		public boolean promptYesNo(String str){
			Object[] options={ "yes", "no" };
			int foo=JOptionPane.showOptionDialog(null, 
					str,
					"Warning", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			return foo==0;
		}

		String passwd;
		JTextField passwordField=(JTextField)new JPasswordField(20);

		public String getPassphrase(){ return null; }
		public boolean promptPassphrase(String message){ return true; }
		public boolean promptPassword(String message){
			Object[] ob={passwordField}; 
			int result=
					JOptionPane.showConfirmDialog(null, ob, message,
							JOptionPane.OK_CANCEL_OPTION);
			if(result==JOptionPane.OK_OPTION){
				passwd=passwordField.getText();
				return true;
			}
			else{ return false; }
		}
		public void showMessage(String message){
			JOptionPane.showMessageDialog(null, message);
		}
		final GridBagConstraints gbc = 
				new GridBagConstraints(0,0,1,1,1,1,
						GridBagConstraints.NORTHWEST,
						GridBagConstraints.NONE,
						new Insets(0,0,0,0),0,0);
		private Container panel;
		public String[] promptKeyboardInteractive(String destination,
				String name,
				String instruction,
				String[] prompt,
				boolean[] echo){
			panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			gbc.weightx = 1.0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridx = 0;
			panel.add(new JLabel(instruction), gbc);
			gbc.gridy++;

			gbc.gridwidth = GridBagConstraints.RELATIVE;

			JTextField[] texts=new JTextField[prompt.length];
			for(int i=0; i<prompt.length; i++){
				gbc.fill = GridBagConstraints.NONE;
				gbc.gridx = 0;
				gbc.weightx = 1;
				panel.add(new JLabel(prompt[i]),gbc);

				gbc.gridx = 1;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weighty = 1;
				if(echo[i]){
					texts[i]=new JTextField(20);
				}
				else{
					texts[i]=new JPasswordField(20);
				}
				panel.add(texts[i], gbc);
				gbc.gridy++;
			}

			if(JOptionPane.showConfirmDialog(null, panel, 
					destination+": "+name,
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE)
					==JOptionPane.OK_OPTION){
				String[] response=new String[prompt.length];
				for(int i=0; i<prompt.length; i++){
					response[i]=texts[i].getText();
				}
				return response;
			}
			else{
				return null;  // cancel
			}
		}
	}
	
}