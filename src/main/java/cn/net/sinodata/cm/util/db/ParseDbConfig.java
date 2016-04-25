package cn.net.sinodata.cm.util.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ParseDbConfig {
//	public static String workPath = System.getProperty("user.dir");// 工作路径
	private static String cfgPath = ParseDbConfig.class.getClassLoader().getResource("resources/config/dbconfig.xml").getPath();
	private static final String DB_TYPE_ORACLE = "oracle";
	private static final String DB_TYPE_MYSQL = "mysql";
	private static final String DRIVER_CLASS_ORACLE = "oracle.jdbc.driver.OracleDriver";
	private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
	/**
	 * 读取xml配置文件
	 * 
	 * @param path
	 * @return
	 */
	public static DbConfigBean readConfigInfo() {

//		List<DbConfigBean> dsConfig = null;
		DbConfigBean dbCfgBean = null;
		FileInputStream fi = null;
		try {
			System.out.println(cfgPath);
			fi = new FileInputStream(cfgPath);// 读取路径文件
//			dsConfig = new ArrayList<DbConfigBean>();
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(fi);
			Element root = doc.getRootElement();
			dbCfgBean = new DbConfigBean();
			String dbType = root.getChild("type").getText();
			if(dbType.equals(DB_TYPE_ORACLE)){
				dbCfgBean.setDriver(DRIVER_CLASS_ORACLE);
				StringBuilder url = new StringBuilder("jdbc:oracle:thin:@");
				url.append(root.getChild("ip").getText() + ":");
				url.append(root.getChild("port").getText() + ":");
				url.append(root.getChild("service").getText());
				dbCfgBean.setUrl(url.toString());
			}
			if(dbType.equals(DB_TYPE_MYSQL)){
				dbCfgBean.setDriver(DRIVER_CLASS_MYSQL);
				StringBuilder url = new StringBuilder("jdbc:mysql://");
				url.append(root.getChild("ip").getText() + "/");
//				url.append(root.getChild("port").getText() + ":");
				url.append(root.getChild("service").getText());
				dbCfgBean.setUrl(url.toString());
			}
			dbCfgBean.setUsername(root.getChild("username").getText());
			dbCfgBean.setPassword(root.getChild("password").getText());
			dbCfgBean.setInitialPoolSize(Integer.parseInt(root.getChild("initialPoolSize").getText()));
			dbCfgBean.setMinPoolSize(Integer.parseInt(root.getChild("minPoolSize").getText()));
			dbCfgBean.setMaxPoolSize(Integer.parseInt(root.getChild("maxPoolSize").getText()));
			dbCfgBean.setIdleTestPeriod(Integer.parseInt(root.getChild("idleTestPeriod").getText()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				if(fi != null){
					fi.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dbCfgBean;
	}

	public static void main(String[] args) {
//		ParseDbConfig pdc = new ParseDbConfig();
		ParseDbConfig.readConfigInfo();

	}
}
