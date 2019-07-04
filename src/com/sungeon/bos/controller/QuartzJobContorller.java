package com.sungeon.bos.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.job.QuartzJobManager;
import com.sungeon.bos.service.IBaseService;

@RequestMapping("/JobContorl")
@Controller
public class QuartzJobContorller extends BaseContorller {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private IBaseService baseService;
	@Autowired
	private QuartzJobManager quartzJobManager;

	@RequestMapping("/getJobs")
	@ResponseBody
	public List<ScheduleJob> getJobs() {
		List<ScheduleJob> jobList = quartzJobManager.getJobs();
		log.info("获取所有任务 " + jobList);
		return jobList;
	}

	private void dealRequest(Integer page, Boolean success) {
		if (null == page)
			page = 1;

		List<ScheduleJob> jobs = quartzJobManager.getJobs();
		int pageAllCount = 20;
		int jobSize = jobs.size();
		int pageSize = jobSize / pageAllCount + 1;
		String countDesc = "";
		if (jobSize <= pageAllCount) {
			countDesc = "1-" + jobSize + "  /  " + jobSize;
			req.setAttribute("jobs", jobs);
		} else if (page >= pageSize) {
			countDesc = ((pageSize - 1) * pageAllCount + 1) + "-" + jobSize + "  /  " + jobSize;
			req.setAttribute("jobs", jobs.subList((pageSize - 1) * pageAllCount, jobSize));
		} else {
			countDesc = ((page - 1) * pageAllCount + 1) + "-" + (page * pageAllCount) + "  /  " + jobSize;
			req.setAttribute("jobs", jobs.subList((page - 1) * pageAllCount, page * pageAllCount));
		}

		req.setAttribute("page", page);
		if (page == 1)
			req.setAttribute("lastPage", page);
		else
			req.setAttribute("lastPage", page - 1);
		if (page == pageSize)
			req.setAttribute("nextPage", page);
		else
			req.setAttribute("nextPage", page + 1);
		req.setAttribute("countDesc", countDesc);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("status", success);
	}

	@RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject pauseJob(Integer jobId) {
		JSONObject result = new JSONObject();
		ScheduleJob scheduleJob = baseService.getScheduleJobById(jobId);
		if (null == scheduleJob)
			return null;

		log.info("暂停任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]");
		boolean success = quartzJobManager.pauseJob(scheduleJob);
		scheduleJob = quartzJobManager.getJob(scheduleJob.getJobName(), scheduleJob.getGroupName());

		result.fluentPut("status", success);
		if (success)
			result.fluentPut("statusDesc",
					"暂停任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]成功");
		else
			result.fluentPut("statusDesc",
					"暂停任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]失败");
		result.fluentPut("loadType", "SINGLE");
		result.fluentPut("job", scheduleJob);
		return result;
	}

	@RequestMapping(value = "/resumeJob", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject resumeJob(Integer jobId) {
		JSONObject result = new JSONObject();
		ScheduleJob scheduleJob = baseService.getScheduleJobById(jobId);
		if (null == scheduleJob)
			return null;

		log.info("恢复任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]");
		boolean success = quartzJobManager.resumeJob(scheduleJob);
		scheduleJob = quartzJobManager.getJob(scheduleJob.getJobName(), scheduleJob.getGroupName());

		result.fluentPut("status", success);
		if (success)
			result.fluentPut("statusDesc",
					"恢复任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]成功");
		else
			result.fluentPut("statusDesc",
					"恢复任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]失败");
		result.fluentPut("loadType", "SINGLE");
		result.fluentPut("job", scheduleJob);
		return result;
	}

	@RequestMapping("/deleteJob")
	public String deleteJob() {
		String jobId = req.getParameter("jobId");

		ScheduleJob scheduleJob = baseService.getScheduleJobById(Integer.parseInt(jobId));
		if (null == scheduleJob)
			return "/job/index";

		log.info("删除任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]");
		scheduleJob.setStatus(2);
		boolean success = quartzJobManager.deleteJob(scheduleJob);
		if (success)
			baseService.updateScheduleJob(scheduleJob);

		dealRequest(1, success);
		if (success)
			req.setAttribute("statusDesc",
					"删除任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]成功");
		else
			req.setAttribute("statusDesc",
					"删除任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]失败");
		req.setAttribute("loadType", "ALL");
		return "/job/index";
	}

	@RequestMapping(value = "/triggerJob", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject triggerJob(Integer jobId) {
		JSONObject result = new JSONObject();
		ScheduleJob scheduleJob = baseService.getScheduleJobById(jobId);
		if (null == scheduleJob)
			return null;

		log.info("立即执行任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]");
		boolean success = quartzJobManager.triggerJob(scheduleJob);
		scheduleJob = quartzJobManager.getJob(scheduleJob.getJobName(), scheduleJob.getGroupName());

		result.fluentPut("status", success);
		if (success)
			result.fluentPut("statusDesc",
					"立即执行任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]成功");
		else
			result.fluentPut("statusDesc",
					"立即执行任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]失败");
		result.fluentPut("loadType", "SINGLE");
		result.fluentPut("job", scheduleJob);
		return result;
	}

	@RequestMapping("/updateJob")
	@ResponseBody
	public JSONObject updateJob(Integer jobId, String description, String cronExpression) {
		JSONObject result = new JSONObject();
		ScheduleJob scheduleJob = baseService.getScheduleJobById(jobId);
		if (null == scheduleJob)
			return null;

		log.info("修改任务 前[" + scheduleJob + "]");
		scheduleJob.setCronExpression(cronExpression);
		scheduleJob.setDescription(description);
		log.info("修改任务 后[" + scheduleJob + "]");
		boolean success = quartzJobManager.rescheduleJob(scheduleJob);
		scheduleJob = quartzJobManager.getJob(scheduleJob.getJobName(), scheduleJob.getGroupName());

		result.fluentPut("status", success);
		if (success) {
			baseService.updateScheduleJob(scheduleJob);
			result.fluentPut("statusDesc",
					"修改任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]成功");
		} else {
			result.fluentPut("statusDesc",
					"修改任务 [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]失败");
		}
		result.fluentPut("loadType", "SINGLE");
		result.fluentPut("job", scheduleJob);
		return result;
	}

}
