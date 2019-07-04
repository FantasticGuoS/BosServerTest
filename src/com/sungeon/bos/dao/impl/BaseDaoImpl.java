/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.dao.IBaseDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.Payway;
import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.entity.Store;
import com.sungeon.bos.entity.ThirdTime;
import com.sungeon.bos.entity.VIPType;
import com.sungeon.bos.mapper.IBaseMapper;
import com.sungeon.bos.util.SystemProperties;

@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IBaseMapper baseMapper;

	public String getOracleExceptionMessage(String message) {
		String[] msgs = message.split("\n");
		return msgs[1].substring(msgs[1].indexOf("ORA-"));
	}

	@Override
	public Integer getNewID(String tablename) {
		return baseMapper.getNewID(tablename);
	}

	@Override
	public String getNewDocno(String seqname) {
		return baseMapper.getNewDocno(seqname);
	}

	@Override
	public String getParamValue(String name) {
		// TODO Auto-generated method stub
		return baseMapper.getParamValue(name);
	}

	@Override
	public Store queryStore(String store) {
		// TODO Auto-generated method stub
		return baseMapper.queryStore(store);
	}

	@Override
	public Payway queryPayway(String payway) {
		// TODO Auto-generated method stub
		return baseMapper.queryPayway(payway);
	}

	@Override
	public VIPType queryVIPType(String viptype) {
		// TODO Auto-generated method stub
		return baseMapper.queryVIPType(viptype);
	}

	@Override
	public Integer initSQL(String sql) {
		// TODO Auto-generated method stub
		try {
			log.info("Initialize SQL:" + sql);
			baseMapper.initSQL(sql);
			log.info("Initialize SQL Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SQLException ex = (SQLException) e.getCause();
			if (ex.getErrorCode() > 0)
				log.info("Initialize SQL Fail:" + getOracleExceptionMessage(e.getMessage()));
		}
		return 1;
	}

	@Override
	public Integer initProcedure(String name, String sql) {
		// TODO Auto-generated method stub
		try {
			log.info("Initialize Procedure/Function/Trigger:" + name);
			baseMapper.initSQL(sql);
			log.info("Initialize Procedure/Function/Trigger Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SQLException ex = (SQLException) e.getCause();
			if (ex.getErrorCode() > 0)
				log.info("Initialize Procedure/Function/Trigger Fail:" + getOracleExceptionMessage(e.getMessage()));
		}
		return 1;
	}

	@Override
	public Integer queryThirdTimeId(String type) {
		// TODO Auto-generated method stub
		return baseMapper.queryThirdTimeId(type);
	}

	@Override
	public String queryThirdTime(String type) {
		// TODO Auto-generated method stub
		return baseMapper.queryThirdTime(type);
	}

	@Override
	public Integer initThirdTime(String type) {
		// TODO Auto-generated method stub
		return baseMapper.initThirdTime(type);
	}

	@Override
	public Integer insertThirdTime(ThirdTime thirdTime) {
		// TODO Auto-generated method stub
		return baseMapper.insertThirdTime(thirdTime);
	}

	@Override
	public Integer updateThirdTime(String type, Date date) {
		// TODO Auto-generated method stub
		return baseMapper.updateThirdTime(type, date);
	}

	@Override
	public List<ScheduleJob> queryScheduleJobs() {
		// TODO Auto-generated method stub
		return baseMapper.queryScheduleJobs(SystemProperties.ScheduleGroup);
	}

	@Override
	public ScheduleJob queryScheduleJob(String jobName, String groupName) {
		// TODO Auto-generated method stub
		return baseMapper.queryScheduleJob(jobName, groupName);
	}

	@Override
	public ScheduleJob queryScheduleJobById(Integer jobId) {
		// TODO Auto-generated method stub
		return baseMapper.queryScheduleJobById(jobId);
	}

	@Override
	public Integer updateScheduleJob(ScheduleJob scheduleJob) {
		// TODO Auto-generated method stub
		return baseMapper.updateScheduleJob(scheduleJob);
	}

	@Override
	public BosResult testProcedure(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		BosResult result = new BosResult();
		try {
			map.put("id", id);
			baseMapper.testProcedure(map);
			JSONObject res = (JSONObject) JSON.toJSON(map);
			result = JSONObject.parseObject(res.toString(), BosResult.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			result.setCode(-1);
			result.setMessage(getOracleExceptionMessage(e.getMessage()));
		}
		return result;
	}

}
