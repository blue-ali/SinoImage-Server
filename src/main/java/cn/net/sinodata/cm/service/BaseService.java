package cn.net.sinodata.cm.service;

import javax.annotation.Resource;

import cn.net.sinodata.cm.content.IContentService;
import cn.net.sinodata.cm.mybatis.dao.BatchDao;
import cn.net.sinodata.cm.mybatis.dao.FileDao;

public abstract class BaseService {
	
	@Resource
	protected FileDao fileDao;
	@Resource
	protected BatchDao batchDao;
	@Resource
	protected IContentService jcrService;

	protected String separator = "/";
	
	protected String buildPath(String... str){
		StringBuilder sb = new StringBuilder(separator);
		for (int i = 0; i < str.length; i++) {
			sb.append(str[i]);
			if(i+1<str.length){
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	
	
}
