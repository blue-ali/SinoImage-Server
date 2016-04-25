package cn.net.sinodata.cm.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import cn.net.sinodata.cm.mybatis.mapper.provider.FileMapperProvider;
import cn.net.sinodata.cm.mybatis.model.MFile;

public interface FileMapper {
	
	@Select("select * from file")
	public List<MFile> selectAll();
	
	@InsertProvider(type=FileMapperProvider.class, method="addFiles")
	public void addFiles(List<MFile> files);
}
