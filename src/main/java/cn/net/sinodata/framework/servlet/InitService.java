package cn.net.sinodata.framework.servlet;

import org.springframework.context.ApplicationContext;

/** 
  * @ClassName: SinoDataInitServer 
  * @Description: 需要初始化执行的服务
  * @author 吴建华 
  * @date 2012-10-24 下午3:26:49 
  *  
  */
public interface InitService {
	/** 
	  * @Title: execute 
	  * @Description: 执行服务
	  * @param @param appctx spring服务容器
	  * @param @return    设定文件 
	  * @return boolean    返回类型 
	  * @throws 
	  */
	public boolean execute(ApplicationContext appctx);
}
