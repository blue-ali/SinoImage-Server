package cn.net.sinodata.cm.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.net.sinodata.cm.mybatis.model.MBatch;
import cn.net.sinodata.cm.mybatis.model.MFile;

public interface BatchMapper {
	
	@Select("select * from batch")
	public List<MFile> selectAll();
	
	@Insert("insert into batch (batchid, sysid, orgid, createtime, lastmodified, files) values (#{batchId}, #{sysId}, #{orgId}, #{createTime}, #{lastModified}, #{files})")
	public void addBatch(MBatch batch);
}
