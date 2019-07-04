/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.Payway;
import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.entity.Store;
import com.sungeon.bos.entity.ThirdTime;
import com.sungeon.bos.entity.VIPType;

@Repository
public interface IBaseDao {

	public Integer getNewID(String tablename);

	public String getNewDocno(String seqname);

	public String getParamValue(String name);

	public Store queryStore(String store);

	public Payway queryPayway(String payway);

	public VIPType queryVIPType(String viptype);

	public Integer initSQL(String sql);

	public Integer initProcedure(String name, String sql);

	public Integer queryThirdTimeId(String type);

	public String queryThirdTime(String type);

	public Integer initThirdTime(String type);

	public Integer insertThirdTime(ThirdTime thirdTime);

	public Integer updateThirdTime(String type, Date date);

	public List<ScheduleJob> queryScheduleJobs();

	public ScheduleJob queryScheduleJob(String jobName, String groupName);

	public ScheduleJob queryScheduleJobById(Integer jobId);

	public Integer updateScheduleJob(ScheduleJob scheduleJob);

	public BosResult testProcedure(int id);

}
