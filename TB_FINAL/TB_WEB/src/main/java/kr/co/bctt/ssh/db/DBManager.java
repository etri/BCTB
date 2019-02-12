package kr.co.bctt.ssh.db;


// java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.cfg.Config;

public class DBManager {
	
	private static ConnectionPool connPool;
	
    public static void init() {
    	try {
    		connPool = ConnectionPool.getInstance(Config.getProperties());
    	} catch(Exception e) {
    	}
    }

    public static synchronized Connection getConnection() throws SQLException {
    	
		if(connPool==null) {
			init();
    	}	
		
        return connPool.getConnection();
    }
    
    public static Statement getStatement(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        
        if (stmt == null) {
            throw new SQLException("PreparedStatement is null");
        }
        
        return stmt;
    }

    public static PreparedStatement getPreparedStatement(Connection con, String query) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(query);
        
        if (pstmt == null) {
            throw new SQLException("PreparedStatement is null");
        }
        
        return pstmt;
    }
    
    public static void freeConnection(Connection con, Statement stmt, ResultSet rs) {
    	try {
    		if(rs!=null)   { rs.close(); }
    		if(stmt!=null) { stmt.close(); }
    		if(con!=null)  { connPool.releaseConnection(con); }
    	} catch (SQLException ex) {
    		ex.printStackTrace();
        } finally {
//        	logger.debug("************* numCons : " + DBManager.getNumCons() + ", used : " + DBManager.getUsedSize() + ", free : " + DBManager.getFreeSize() + " *******************");
        }
    }

    public static void freeConnection(Connection con, Statement stmt) {
    	try {
    		if (stmt != null) stmt.close();
    		if (con != null) connPool.releaseConnection(con);
    	} catch (SQLException ex) {
    		ex.printStackTrace();
    	}
    }

	public static void freeConnection(ResultSet rs) {
		try {
			Statement stmt = null;
			Connection con = null;

			if (rs   != null) {
				stmt = rs.getStatement();
				rs.close();
			}
			if (stmt != null) {
				con = stmt.getConnection();
				stmt.close();
			}
			if (con  != null) connPool.releaseConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public static void freeSingleConnection(Connection con,PreparedStatement pstmt,ResultSet rs){
    	try {
    		if (pstmt != null) pstmt.close();
    		if (con != null) con.close();
    		if (rs != null) con.close();
    	} catch (SQLException ex) {
    		ex.printStackTrace();
    	}
    }


    public static synchronized void release() {
        try {
        	connPool.closeAll();
        } catch (Exception e) {
        }
    }
    
    public static synchronized void closeAllConnection() {
    	try {
    		connPool.closeAllConnection();
    	} catch (Exception e){
    	}
    }
    
    public static int getNumCons() {
    	return connPool.getNumCons();
    }
    
    public static int getUsedSize() {
    	return connPool.getUsedSize();
    }
    
    public static int getFreeSize() {
    	return connPool.getFreeSize();
    }
    
    public static boolean getMySQLLoading() {
    	return connPool.isMySQLDriverLoading();
    }
    
    public static void setMySQLLoading(boolean status) {
    	connPool.setMySQLDriverLoading(status);
    }
}
