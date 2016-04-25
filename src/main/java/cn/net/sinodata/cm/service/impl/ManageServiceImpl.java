/**
 * 
 */
package cn.net.sinodata.cm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.net.sinodata.cm.common.bean.BatchInfo;
import cn.net.sinodata.cm.common.bean.FileInfo;
import cn.net.sinodata.cm.content.jcr.model.JcrContent;
import cn.net.sinodata.cm.mybatis.model.MBatch;
import cn.net.sinodata.cm.mybatis.model.MFile;
import cn.net.sinodata.cm.service.BaseService;
import cn.net.sinodata.cm.service.IManagerService;
import cn.net.sinodata.cm.util.BeanUtil;

/**
 * @author manan
 *
 */
@Service("manageService")
@Transactional(rollbackFor=Exception.class)
public class ManageServiceImpl extends BaseService implements IManagerService{

	public void addBatch(BatchInfo batchInfo, Map<String, byte[]> fileContents) throws Exception{
		
		String batchId = batchInfo.getBatchId();
		List<MFile> fileModels = new ArrayList<MFile>();
		List<JcrContent> contents = new ArrayList<JcrContent>();
		
		List<FileInfo> fileInfos = batchInfo.getFiles();
		for (FileInfo fileInfo : fileInfos) {
			//bean转换成数据库对象
			fileModels.add(BeanUtil.fileInfo2Model(fileInfo, batchId));
			//bean转换成jcr对象
			contents.add(BeanUtil.fileInfo2JcrContent(fileInfo, batchId, fileContents));
		}
		//bean转换成数据库对象
		MBatch batchModel = BeanUtil.batchInfo2Model(batchInfo);
		
		//数据库操作，记录batch和file
		fileDao.addFiles(fileModels);
		batchDao.addBatch(batchModel);
		String batchPath = buildPath(batchInfo.getSysId(), batchInfo.getOrgId(), batchInfo.getBatchId());
		
		//JCR操作
		for (JcrContent jcrContent : contents) {
			jcrService.addContent(batchPath, jcrContent);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new ManageServiceImpl().buildPath("aaa","bbb"));
	}
	
}
