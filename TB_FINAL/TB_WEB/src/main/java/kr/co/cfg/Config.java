package kr.co.cfg;

import java.util.Properties;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Config {
	
    private static Properties s_prop = null;
    
    private static String os_type 		= "windows";		// windows/linux
    private static String program_name 	= "";
    @SuppressWarnings("unused")
	private static String user_name		= "";
    
    public Config() {
    }
    
    public static boolean initProperties() {
    	
		if (s_prop == null) {
			try {
				s_prop = new Properties();
				s_prop = PropertyUtil.getProperties("kr/cfg/program.properties");
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
    	return false;
    }
    
    public static Properties getProperties() throws Exception {
    	return Config.s_prop;
    }

	public static String getOs_type() {
		return os_type;
	}

	public static void setOs_type(String os_type) {
		Config.os_type = os_type;
	}

	public static String getProgram_name() {
		return program_name;
	}

	public static void setProgram_name(String program_name) {
		Config.program_name = program_name;
	}

	public static void setUser_name(String user_name) {
		Config.user_name = user_name;
	}
}