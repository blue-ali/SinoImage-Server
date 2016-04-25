/**
 * 
 */
package cn.net.sinodata.cm.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.net.sinodata.cm.common.bean.BatchInfo;
import cn.net.sinodata.cm.common.bean.FileInfo;
import cn.net.sinodata.cm.service.IManagerService;
import cn.net.sinodata.cm.util.ImageJsonUtil;
import cn.net.sinodata.framework.log.SinoLogger;
import net.sf.json.JSONObject;

/**
 * @author manan
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class AddBatchService extends HttpServlet{
	
	private SinoLogger logger = SinoLogger.getLogger(this.getClass());
	
	@Resource
	private IManagerService manageService;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String result = "";
		BatchInfo batch = null;
		
		Map<String, byte[]> fileContents = new HashMap<String, byte[]>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
		factory.setSizeThreshold(1024 * 1024);
		// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
		factory.setRepository(new File("D:\\temp"));
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
		upload.setFileSizeMax(50 * 1024 * 1024);
		// 指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
		upload.setSizeMax(100 * 1024 * 1024);
		upload.setHeaderEncoding("UTF-8");

		List<FileItem> items = null;
		try {
			// 解析request请求
			items = upload.parseRequest(request);
			if (items != null) {
				batch = parseItem(fileContents, items);
				
				//调用管理服务添加批次
				manageService.addBatch(batch, fileContents);
				result = "{'result': '1'}";
			}else{	//失败
				result = "{'result': '0', 'errMsg': '解析请求失败，请求中不包含任何对象'}";
			}
		} catch (Exception e) {
			result = "{'result': '0', 'errMsg': " + "'上传文件失败,ERR: " + e.getMessage() + "'}";
			logger.error(e);
		} finally {
			response.getWriter().write(result);
		}
	}
	
	private BatchInfo parseItem(final Map<String, byte[]> fileContents, final List<FileItem> items) {
		// 解析表单项目
		BatchInfo batch = null;
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) { // 如果是普通表单属性
				// 相当于input的name属性 <input type="text" name="content">
				String name = item.getFieldName();
				String value = item.getString();
				logger.debug(name + ": " + value);
				Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
				classMap.put("files", FileInfo.class);
				batch = ImageJsonUtil.jsonStr2BatchInfo(value);
			} else { // 如果是上传文件
				String fileId = item.getFieldName();
				byte[] date = item.get();
				fileContents.put(fileId, date);
			}
		}
		return batch;
	}
	
}
