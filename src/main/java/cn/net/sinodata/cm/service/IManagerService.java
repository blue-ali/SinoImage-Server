package cn.net.sinodata.cm.service;

import java.util.Map;

import cn.net.sinodata.cm.common.bean.BatchInfo;

public interface IManagerService {

	public void addBatch(BatchInfo batch, Map<String, byte[]> fileContents) throws Exception;
}
