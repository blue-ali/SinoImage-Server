package cn.net.sinodata.cm.util.db;

public class DbConfigBean {
	private String type = ""; // 数据库类型

	private String name = ""; // 连接池名字

	private String driver = ""; // 数据库驱动名称
	
	private String url = ""; //数据库jdbc连接url

	private String username = ""; // 用户名

	private String password = ""; // 密码
	
	private int initialPoolSize = 0; //连接池初始连接数

	private int maxPoolSize = 0; //最大连接数
	
	private int minPoolSize = 0; //最小连接数
	
	private int idleTestPeriod = 0; //测试连接间隔时间
	
	/**
	 * 
	 */
	public DbConfigBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getIdleTestPeriod() {
		return idleTestPeriod;
	}

	public void setIdleTestPeriod(int idleTestPeriod) {
		this.idleTestPeriod = idleTestPeriod;
	}

	
	
}
