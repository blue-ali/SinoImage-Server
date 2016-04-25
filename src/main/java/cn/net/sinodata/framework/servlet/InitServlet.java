package cn.net.sinodata.framework.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.net.sinodata.framework.log.SinoLogger;

/** 
  * @ClassName: SinoDataCentext 
  * @Description: 需要启动时加载的业务处理类
  * @author 吴建华 
  * @date 2012-10-24 下午3:17:53 
  *  
  */
@SuppressWarnings("serial")
public class InitServlet extends HttpServlet{
	
	private final static SinoLogger logger = SinoLogger.getLogger(InitServlet.class);
	
	private ApplicationContext appctx = null;
	
	public void destroy() {
		appctx = null;
	}
	
	/**
	 * 初始化服务
	 * 说明：2014-1-3 启动失败则终止整个进程。
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			appctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
			String serversInit = config.getInitParameter("serversInit");
			String outputFmt = "执行初始化服务【%s】%s";
			if(null != serversInit && !"".equals(serversInit.trim())){
				String[] serversInits = serversInit.split("\\,");
				int servsersInitsLen = serversInits.length;
				for(int i=0; i<servsersInitsLen; i++){
					String serverx = serversInits[i].trim();
					boolean isSuccess = false;
					isSuccess = ((InitService)Class.forName(serverx).newInstance()).execute(appctx);
					if(false == isSuccess) {
						logger.error(String.format(outputFmt, serverx, "失败"));
						System.exit(-1);
					} else {
						logger.info(String.format(outputFmt, serverx, "成功"));
					}
				}
			}
		} catch(Exception ex) {
			logger.error("执行初始化服务异常，请检查web.xml及相关代码", ex);
			System.exit(-1);
		}
	}
}
