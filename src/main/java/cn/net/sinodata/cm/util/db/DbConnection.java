package cn.net.sinodata.cm.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Administrator
 *
 */
public class DbConnection {
	
	String url = "";
	String user = "";
	String password = "";
	private static final DbConnection instance = new DbConnection();
	
	public static DbConnection getInstance(){
		return instance;
	}
	
	private DbConnection(){
		init();
	}

	private void init(){
		DbConfigBean dbcfg = ParseDbConfig.readConfigInfo();
		url = dbcfg.getUrl();
		user = dbcfg.getUsername();
		password = dbcfg.getPassword();
		try {
			Class.forName(dbcfg.getDriver());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
