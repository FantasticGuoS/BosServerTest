package com.sungeon.bos.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.service.IBaseService;

@Component("quartzJobManager")
public class QuartzJobManager {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private IBaseService baseService;
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * 加载任务
	 */
	public void initScheduleJobs() {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler(); // 这里获取任务信息数据
			List<ScheduleJob> jobList = baseService.getScheduleJobs();
			TriggerKey triggerKey = null;
			CronTrigger trigger = null;
			JobDetail jobDetail = null;
			CronScheduleBuilder scheduleBuilder = null;
			for (ScheduleJob scheduleJob : jobList) {
				triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getGroupName());
				// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
				trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
				// 不存在，创建一个
				if (null == trigger) {
					jobDetail = JobBuilder.newJob(QuartzJob.class)
							.withIdentity(scheduleJob.getJobName(), scheduleJob.getGroupName()).build();
					jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
					// 表达式调度构建器
					scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
					// 按新的cronExpression表达式构建一个新的trigger
					trigger = TriggerBuilder.newTrigger()
							.withIdentity(scheduleJob.getJobName(), scheduleJob.getGroupName())
							.withSchedule(scheduleBuilder).build();
					if (scheduleJob.getStatus() == 1)
						scheduler.scheduleJob(jobDetail, trigger);
				} else {
					// Trigger已存在，那么更新相应的定时设置
					// 表达式调度构建器
					scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
					// 按新的cronExpression表达式重新构建trigger
					trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder)
							.build();
					// 按新的trigger重新设置job执行
					if (scheduleJob.getStatus() == 1)
						scheduler.rescheduleJob(triggerKey, trigger);
				}
			}
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * <b>计划中的任务</b></br>
	 * 
	 * 指那些已经添加到quartz调度器的任务，因为quartz并没有直接提供这样的查询接口，所以我们需要结合JobKey和Trigger来实现
	 * 
	 * @return
	 */
	public List<ScheduleJob> getJobs() {
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			ScheduleJob scheduleJob = null;
			TriggerState triggerState = null;
			CronTrigger cronTrigger = null;
			String cronExpression = null;
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					scheduleJob = baseService.getScheduleJob(jobKey.getName(), jobKey.getGroup());
					triggerState = scheduler.getTriggerState(trigger.getKey());
					scheduleJob.setRunStatus(triggerState);
					if (trigger instanceof CronTrigger) {
						cronTrigger = (CronTrigger) trigger;
						cronExpression = cronTrigger.getCronExpression();
						scheduleJob.setCronExpression(cronExpression);
					}
					jobList.add(scheduleJob);
				}
			}
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
		}
		return jobList;
	}

	/**
	 * <b>根据任务名称、分组名称查找任务</b>
	 * 
	 * @param jobName
	 * @param groupName
	 * @return
	 */
	public ScheduleJob getJob(String jobName, String groupName) {
		ScheduleJob scheduleJob = null;
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerState triggerState = null;
			CronTrigger cronTrigger = null;
			String cronExpression = null;
			JobKey jobKey = JobKey.jobKey(jobName, groupName);
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				scheduleJob = baseService.getScheduleJob(jobKey.getName(), jobKey.getGroup());
				triggerState = scheduler.getTriggerState(trigger.getKey());
				scheduleJob.setRunStatus(triggerState);
				if (trigger instanceof CronTrigger) {
					cronTrigger = (CronTrigger) trigger;
					cronExpression = cronTrigger.getCronExpression();
					scheduleJob.setCronExpression(cronExpression);
				}
			}
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
		}
		return scheduleJob;
	}

	/**
	 * <b>获取运行中的任务</b>
	 * 
	 * @return
	 */
	public List<ScheduleJob> getCurrentlyExecutingJobs() {
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			ScheduleJob job = null;
			JobDetail jobDetail = null;
			JobKey jobKey = null;
			Trigger trigger = null;
			TriggerState triggerState = null;
			CronTrigger cronTrigger = null;
			String cronExpression = null;
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			for (JobExecutionContext executingJob : executingJobs) {
				job = baseService.getScheduleJob(jobKey.getName(), jobKey.getGroup());
				jobDetail = executingJob.getJobDetail();
				jobKey = jobDetail.getKey();
				trigger = executingJob.getTrigger();
				triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setRunStatus(triggerState);
				if (trigger instanceof CronTrigger) {
					cronTrigger = (CronTrigger) trigger;
					cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
		}
		return jobList;
	}

	/**
	 * 
	 * @param scheduleJob
	 * @param replace
	 * @return
	 */
	public boolean addJob(ScheduleJob scheduleJob, boolean replace) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
					.withIdentity(scheduleJob.getJobName(), scheduleJob.getGroupName()).build();
			jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
			// 按新的cronExpression表达式构建一个新的trigger
			CronTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(scheduleJob.getJobName(), scheduleJob.getGroupName()).withSchedule(scheduleBuilder)
					.build();

			// scheduler.addJob(jobDetail, replace);
			scheduler.scheduleJob(jobDetail, trigger);
			return true;
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * <b>暂停任务</b>
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public boolean pauseJob(ScheduleJob scheduleJob) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getGroupName());
			scheduler.pauseJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * <b>暂停所有任务</b>
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public boolean pauseAllJob() {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.pauseAll();
			return true;
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * <b>恢复任务</b></br>
	 * 
	 * 和暂停任务相对
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public boolean resumeJob(ScheduleJob scheduleJob) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getGroupName());
			scheduler.resumeJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * <b>恢复所有任务</b></br>
	 * 
	 * 和暂停任务相对
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public boolean resumeAllJob() {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.resumeAll();
			return true;
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * <b>删除任务</b></br>
	 * 
	 * 删除任务后，所对应的trigger也将被删除
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public boolean deleteJob(ScheduleJob scheduleJob) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getGroupName());
			scheduler.deleteJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * <b>立即运行任务</b></br>
	 * 
	 * 这里的立即运行，只会运行一次，方便测试时用。quartz是通过临时生成一个trigger的方式来实现的，这个trigger将在本次任务运行完成之后自动删除。trigger的key是随机生成的
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public boolean triggerJob(ScheduleJob scheduleJob) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getGroupName());
			scheduler.triggerJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * <b>更新任务的时间表达式</b></br>
	 * 
	 * 更新之后，任务将立即按新的时间表达式执行
	 * 
	 * @param scheduleJob
	 *            scheduleJob.ronExpression必须为新的表达式
	 */
	public boolean rescheduleJob(ScheduleJob scheduleJob) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getGroupName());

			// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
			return true;
		} catch (SchedulerException e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			return false;
		}
	}

}
