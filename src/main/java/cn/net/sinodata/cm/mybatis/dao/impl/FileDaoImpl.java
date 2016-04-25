package cn.net.sinodata.cm.mybatis.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.net.sinodata.cm.mybatis.dao.FileDao;
import cn.net.sinodata.cm.mybatis.mapper.FileMapper;
import cn.net.sinodata.cm.mybatis.model.MFile;

@Repository
public class FileDaoImpl implements FileDao{

	@Resource
	private FileMapper fileMapper;

	public List<MFile> getAllFiles() {
		return fileMapper.selectAll();
	}

	public void addFiles(List<MFile> files) {
		fileMapper.addFiles(files);
	}
	
	
}
