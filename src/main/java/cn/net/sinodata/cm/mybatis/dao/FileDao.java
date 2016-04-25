package cn.net.sinodata.cm.mybatis.dao;

import java.util.List;

import cn.net.sinodata.cm.mybatis.model.MFile;

public interface FileDao {
	
	public List<MFile> getAllFiles();

	public void addFiles(List<MFile> files);
}
