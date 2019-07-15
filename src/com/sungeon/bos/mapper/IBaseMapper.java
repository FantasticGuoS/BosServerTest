/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sungeon.bos.entity.Payway;
import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.entity.Store;
import com.sungeon.bos.entity.ThirdTime;
import com.sungeon.bos.entity.VIPType;

@Mapper
public interface IBaseMapper {

	public Integer getNewID(String tablename);

	public String getNewDocno(String seqname);

	public String getParamValue(String name);

	public Store queryStore(String store);

	public Payway queryPayway(String payway);

	public VIPType queryVIPType(String viptype);

	public Integer initSQL(@Param("sql") String sql);

	public Integer queryThirdTimeId(@Param("type") String type);

	public String queryThirdTime(@Param("type") String type);

	public Integer initThirdTime(@Param("type") String type);

	public Integer insertThirdTime(ThirdTime thirdTime);

	public Integer updateThirdTime(@Param("type") String type, @Param("date") Date date);

	public List<ScheduleJob> queryScheduleJobs(@Param("groupName") String groupName);

	public ScheduleJob queryScheduleJob(@Param("jobName") String jobName, @Param("groupName") String groupName);

	public ScheduleJob queryScheduleJobById(@Param("jobId") Integer jobId);

	public Integer updateScheduleJob(@Param("job") ScheduleJob scheduleJob);

	public Map<String, Object> testProcedure(Map<String, Object> map);

}
