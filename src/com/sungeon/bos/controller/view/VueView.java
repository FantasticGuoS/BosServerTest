package com.sungeon.bos.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sungeon.bos.controller.BaseContorller;

@RequestMapping("/Vue")
@Controller
public class VueView extends BaseContorller {

	@RequestMapping("/index")
	public String getJobs(Integer page) {
		// 跳转到服务器内部的一个功能处理方法
		// return new ModelAndView("forward:/job/index.jsp");
		// 重定向一个功能方法
		// return new ModelAndView("redirect:/dispather/b");
		// 跳转到服务器内部的一个页面
		return "/study/vueStudy";
	}

}
