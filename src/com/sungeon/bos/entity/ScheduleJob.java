package com.sungeon.bos.entity;

import org.apache.ibatis.type.Alias;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;

import com.alibaba.fastjson.JSONObject;

@Alias("ScheduleJob")
public class ScheduleJob {

	/**
	 * 任务id
	 */
	private String id;
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 任务分组
	 */
	private String groupName;
	/**
	 * 任务状态
	 * <li>0：禁用</li>
	 * <li>1：启用</li>
	 * <li>2：删除</li>
	 */
	private Integer status;
	/**
	 * 任务运行状态
	 */
	private TriggerState runStatus;
	/**
	 * 任务运行时间表达式
	 */
	private String cronExpression;
	/**
	 * 任务描述
	 */
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Trigger.TriggerState getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(Trigger.TriggerState runStatus) {
		this.runStatus = runStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
