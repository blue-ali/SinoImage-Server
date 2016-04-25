/**
 * 
 */
package cn.net.sinodata.cm.init;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.context.ApplicationContext;

import cn.net.sinodata.framework.log.SinoLogger;
import cn.net.sinodata.framework.servlet.InitService;

/**
 * 执行所有需要初始化的服务
 * @author manan
 *
 */
public class InitializeService implements InitService{

	private final static SinoLogger logger = SinoLogger.getLogger(InitializeService.class);
	private final static String CONFIG_PATH = InitializeService.class
			.getClassLoader().getResource("resources" + File.separator + "config" + File.separator + "InitServiceConfig.xml").getPath();

	public boolean execute(ApplicationContext appctx) {
		System.out.println(CONFIG_PATH);
		String outputFmt = "初始化服务执行%s";
		try {
			// 读取配置文件中所有的功能服务配置
			Document doc = new SAXBuilder().build(CONFIG_PATH);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> syncServices = root.getChild("synchronous").getChildren("initservice");
			for (Element element : syncServices) {
				String className = element.getChildText("classname");
				String threadNum = element.getChildText("thread-num");
				startSyncService(className);
			}
			
			@SuppressWarnings("unchecked")
			List<Element> asyncServices = root.getChild("asynchronous").getChildren("initservice");
			for (Element element : asyncServices) {
				String className = element.getChildText("classname");
				String threadNum = element.getChildText("thread-num");
				startAsyncService(className, threadNum);
			}
//			List<Element> services = root.getChildren("initservice");
			
		} catch (Exception ex) {
			logger.error(String.format(outputFmt, "异常"), ex);
			return false;
		}
		logger.info(String.format(outputFmt, "成功"));
		return true;
	
	}

	/**
	 * @param className
	 * @param threadNum
	 * manan
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void startSyncService(String className) throws Exception {
		((BpcInitService)Class.forName(className).newInstance()).execute();
	}

	private void startAsyncService(final String className, String threadNum){
		int num = Integer.parseInt(threadNum);
		for (int i = 0; i < num; i++) {
			Thread thread = new Thread(new Runnable() {
				
				public void run() {
					try {
						((BpcInitService)Class.forName(className).newInstance()).execute();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		InitializeService init = new InitializeService();
		init.execute(null);
	}
}
