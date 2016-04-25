package cn.net.sinodata.cm.util;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.net.sinodata.cm.common.bean.BatchInfo;
import cn.net.sinodata.cm.common.bean.FileInfo;
import cn.net.sinodata.cm.content.jcr.model.JcrContent;
import cn.net.sinodata.cm.mybatis.model.MBatch;
import cn.net.sinodata.cm.mybatis.model.MFile;

public class BeanUtil {
	
	public static MBatch batchInfo2Model(BatchInfo batchInfo){
		MBatch model = new MBatch();
		model.setBatchId(batchInfo.getBatchId());
		model.setSysId(batchInfo.getSysId());
		model.setOrgId(batchInfo.getOrgId());
		model.setCreateTime(batchInfo.getCreateTime());
		model.setLastModified(batchInfo.getLastModTime());
//		model.setFiles(files);
		List<FileInfo> fileList = batchInfo.getFiles();
		StringBuilder sb = new StringBuilder();
		Iterator<FileInfo> iterator = fileList.iterator();
		while (iterator.hasNext()) {
			FileInfo fileInfo = (FileInfo) iterator.next();
			sb.append(fileInfo.getFileId());
			if(iterator.hasNext()){
				sb.append(",");
			}
		}
		model.setFiles(sb.toString());
		return model;
	}
	
	public static MFile fileInfo2Model(FileInfo fileInfo, String batchId){
		MFile model = new MFile();
		model.setFileId(fileInfo.getFileId());
		model.setBatchId(batchId);
		model.setFileName(fileInfo.getFileName());
		return model;
	}
	
	public static JcrContent fileInfo2JcrContent(FileInfo fileInfo, String batchId, Map<String, byte[]> fileContents){
		JcrContent jcrContent = new JcrContent();
		jcrContent.setFileId(fileInfo.getFileId());
		jcrContent.setFileName(fileInfo.getFileName());
//		jcrContent.setSuffix(fileInfo.ge);
		jcrContent.setLastModified(Calendar.getInstance());
		jcrContent.setData(fileContents.get(fileInfo.getFileId()));
		jcrContent.setMimeType(fileInfo.getMimeType());
		return jcrContent;
	}

}
