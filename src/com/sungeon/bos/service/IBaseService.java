/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.Payway;
import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.entity.Store;
import com.sungeon.bos.entity.ThirdTime;
import com.sungeon.bos.entity.VIPType;

@Service
public interface IBaseService {

	public Integer getNewID(String tablename);

	public String getNewDocno(String seqname);

	public String getParamValue(String name);

	public Store getStore(String store);

	public Payway getPayway(String payway);

	public VIPType getVIPType(String viptype);

	public Integer initSQL(List<String> sqls);

	public Integer initProcedure(List<File> files);

	public Integer getThirdTimeId(String type);

	public String getThirdTime(String type);

	public Integer initThirdTime(String type);

	public Integer insertThirdTime(ThirdTime thirdTime);

	public Integer updateThirdTime(String type, Date date);

	public List<ScheduleJob> getScheduleJobs();

	public ScheduleJob getScheduleJob(String jobName, String groupName);

	public ScheduleJob getScheduleJobById(Integer jobId);

	public Integer updateScheduleJob(ScheduleJob scheduleJob);

	public BosResult testProcedure(int id);

}
