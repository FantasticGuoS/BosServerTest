package com.sungeon.bos.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sungeon.bos.controller.BaseContorller;
import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.job.QuartzJobManager;

@RequestMapping("/Job")
@Controller
public class QuartzJobView extends BaseContorller {

	@Autowired
	private QuartzJobManager quartzJobManager;

	@RequestMapping("/index")
	public String getJobs(Integer page) {
		dealRequest(page);
		// 跳转到服务器内部的一个功能处理方法
		// return new ModelAndView("forward:/job/index.jsp");
		// 重定向一个功能方法
		// return new ModelAndView("redirect:/dispather/b");
		// 跳转到服务器内部的一个页面
		return "/job/index";
	}

	private void dealRequest(Integer page) {
		if (null == page)
			page = 1;

		int pageAllCount = 20;

		List<ScheduleJob> jobList = quartzJobManager.getJobs();

		int jobSize = jobList.size();
		int pageSize = jobSize / pageAllCount + 1;
		String countDesc = "";
		if (jobSize <= pageAllCount) {
			countDesc = "1-" + jobSize + "  /  " + jobSize;
			req.setAttribute("jobs", jobList);
		} else if (page >= pageSize) {
			countDesc = ((pageSize - 1) * pageAllCount + 1) + "-" + jobSize + "  /  " + jobSize;
			req.setAttribute("jobs", jobList.subList((pageSize - 1) * pageAllCount, jobSize));
		} else {
			countDesc = ((page - 1) * pageAllCount + 1) + "-" + (page * pageAllCount) + "  /  " + jobSize;
			req.setAttribute("jobs", jobList.subList((page - 1) * pageAllCount, page * pageAllCount));
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
		req.setAttribute("status", true);
	}

}
