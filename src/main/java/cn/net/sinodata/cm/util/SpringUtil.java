package cn.net.sinodata.cm.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringUtil implements ApplicationContextAware{

	private static ApplicationContext appCtx;
	
	public void setApplicationContext(ApplicationContext appCtx)
			throws BeansException {
		SpringUtil.appCtx = appCtx; 
	}
	
	public static Object getBean(String name){
		return appCtx.getBean(name);
	}
	
}
