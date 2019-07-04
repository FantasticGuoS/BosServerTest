package com.sungeon.bos.job;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.stereotype.Component;

import com.sungeon.bos.entity.ScheduleJob;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class QuartzJob implements Job {

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		log.debug("调度任务 - [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "]");

		if (scheduleJob.getStatus() == 1) {
			if (scheduleJob.getGroupName().equals("会员组") && scheduleJob.getJobName().equals("PropelVIPInfo"))
				doTask1();
			if (scheduleJob.getGroupName().equals("会员组") && scheduleJob.getJobName().equals("PropelVIPRetail"))
				doTask2();
			if (scheduleJob.getGroupName().equals("会员组") && scheduleJob.getJobName().equals("PropelTicketVerfied"))
				doTask3();
		}
	}

	public void doTask1() {
		log.info("正在运行程序会员组.PropelVIPInfo...");
	}

	public void doTask2() {
		log.info("正在运行程序会员组.PropelVIPRetail...");
	}

	public void doTask3() {
		log.info("正在运行程序会员组.PropelTicketVerfied...");
	}

}
