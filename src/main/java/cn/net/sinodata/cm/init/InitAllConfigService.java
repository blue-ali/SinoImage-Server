package cn.net.sinodata.cm.init;

import java.io.File;

import cn.net.sinodata.framework.log.SinoLogger;

/**
 * 
 * 初始化配置文件服务，在应用程序启动时加载
 * 
 * @author manan
 * 
 */
public class InitAllConfigService implements BpcInitService {

	private static final SinoLogger logger = SinoLogger.getLogger(InitAllConfigService.class);
	private static final String SEPERATOR = File.separator; 
	private static final String BASE_CONFIG_PATH = InitAllConfigService.class
			.getClassLoader().getResource("resources" + SEPERATOR + "config" + SEPERATOR).getPath();
	private static final int THREAD_SIZE = 200; // 线程池大小
	
	public boolean execute() {
		return true;
	}

}
