package cn.net.sinodata.cm.mybatis.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.net.sinodata.cm.mybatis.dao.BatchDao;
import cn.net.sinodata.cm.mybatis.mapper.BatchMapper;
import cn.net.sinodata.cm.mybatis.model.MBatch;

@Repository
public class BatchDaoImpl implements BatchDao{

	@Resource
	private BatchMapper batchMapper;

	public void addBatch(MBatch batch) {
		batchMapper.addBatch(batch);
	}

	
}
