/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungeon.bos.dao.IBaseDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.Payway;
import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.entity.Store;
import com.sungeon.bos.entity.ThirdTime;
import com.sungeon.bos.entity.VIPType;
import com.sungeon.bos.service.IBaseService;
import com.sungeon.bos.util.FileUtil;

@Service("baseService")
public class BaseServiceImpl implements IBaseService {

	@Autowired
	private IBaseDao baseDao;

	@Override
	public Integer getNewID(String tablename) {
		return baseDao.getNewID(tablename);
	}

	@Override
	public String getNewDocno(String seqname) {
		return baseDao.getNewDocno(seqname);
	}

	@Override
	public String getParamValue(String name) {
		// TODO Auto-generated method stub
		return baseDao.getParamValue(name);
	}

	@Override
	public Store getStore(String store) {
		// TODO Auto-generated method stub
		return baseDao.queryStore(store);
	}

	@Override
	public Payway getPayway(String payway) {
		// TODO Auto-generated method stub
		return baseDao.queryPayway(payway);
	}

	@Override
	public VIPType getVIPType(String viptype) {
		// TODO Auto-generated method stub
		return baseDao.queryVIPType(viptype);
	}

	@Override
	public Integer initSQL(List<String> sqls) {
		// TODO Auto-generated method stub
		for (String sql : sqls)
			baseDao.initSQL(sql);
		return sqls.size();
	}

	@Override
	public Integer initProcedure(List<File> files) {
		// TODO Auto-generated method stub
		for (File file : files)
			baseDao.initProcedure(file.getName(), FileUtil.readFileByChar(file, "gbk"));
		return files.size();
	}
	
	@Override
	public Integer getThirdTimeId(String type) {
		// TODO Auto-generated method stub
		return baseDao.queryThirdTimeId(type);
	}

	@Override
	public String getThirdTime(String type) {
		// TODO Auto-generated method stub
		return baseDao.queryThirdTime(type);
	}

	@Override
	public Integer initThirdTime(String type) {
		// TODO Auto-generated method stub
		return baseDao.initThirdTime(type);
	}

	@Override
	public Integer insertThirdTime(ThirdTime thirdTime) {
		// TODO Auto-generated method stub
		return baseDao.insertThirdTime(thirdTime);
	}

	@Override
	public Integer updateThirdTime(String type, Date date) {
		// TODO Auto-generated method stub
		return baseDao.updateThirdTime(type, date);
	}

	@Override
	public List<ScheduleJob> getScheduleJobs() {
		// TODO Auto-generated method stub
		return baseDao.queryScheduleJobs();
	}

	@Override
	public ScheduleJob getScheduleJob(String jobName, String groupName) {
		// TODO Auto-generated method stub
		return baseDao.queryScheduleJob(jobName, groupName);
	}

	@Override
	public ScheduleJob getScheduleJobById(Integer jobId) {
		// TODO Auto-generated method stub
		return baseDao.queryScheduleJobById(jobId);
	}

	@Override
	public Integer updateScheduleJob(ScheduleJob scheduleJob) {
		// TODO Auto-generated method stub
		return baseDao.updateScheduleJob(scheduleJob);
	}

	@Override
	public BosResult testProcedure(int id) {
		// TODO Auto-generated method stub
		return baseDao.testProcedure(id);
	}

}
