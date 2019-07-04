package com.sungeon.bos.controller.view;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.controller.BaseContorller;
import com.sungeon.bos.util.SystemUtil;

@RequestMapping("/LogFile")
@Controller
public class LogFileView extends BaseContorller {

	@RequestMapping("/index")
	public String getLogFiles(Integer page) {
		dealRequest(page);
		// 跳转到服务器内部的一个功能处理方法
		// return new ModelAndView("forward:/job/index.jsp");
		// 重定向一个功能方法
		// return new ModelAndView("redirect:/dispather/b");
		// 跳转到服务器内部的一个页面
		return "/logfile/index";
	}

	private void dealRequest(Integer page) {
		if (null == page)
			page = 1;

		String filePath = req.getSession().getServletContext().getRealPath("/") + "WEB-INF/logs/debug";
		int pageAllCount = 30;

		File fs = new File(filePath);
		List<File> files = Arrays.asList(fs.listFiles());
		Collections.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				long diff = f1.lastModified() - f2.lastModified();
				if (diff > 0)
					return -1;
				else if (diff == 0)
					return 0;
				else
					return 1;
			}

			public boolean equals(Object obj) {
				return true;
			}
		});

		JSONArray jsons = new JSONArray();
		for (File file : files) {
			JSONObject json = new JSONObject();
			json.fluentPut("fileName", file.getName());
			json.fluentPut("fileSize", (file.length() / 1024 + 1) + " KB");
			json.fluentPut("fileTime", SystemUtil.getTime(file.lastModified()));
			jsons.add(json);
		}

		int jobSize = files.size();
		int pageSize = jobSize / pageAllCount + 1;
		String countDesc = "";
		if (jobSize <= pageAllCount) {
			countDesc = "1-" + jobSize + "  /  " + jobSize;
			req.setAttribute("files", jsons);
		} else if (page >= pageSize) {
			countDesc = ((pageSize - 1) * pageAllCount + 1) + "-" + jobSize + "  /  " + jobSize;
			List<Object> arr = jsons.subList((pageSize - 1) * pageAllCount, jobSize);
			req.setAttribute("files", JSONArray.parseArray(arr.toString()));
		} else {
			countDesc = ((page - 1) * pageAllCount + 1) + "-" + (page * pageAllCount) + "  /  " + jobSize;
			List<Object> arr = jsons.subList((page - 1) * pageAllCount, page * pageAllCount);
			req.setAttribute("files", JSONArray.parseArray(arr.toString()));
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
	}

}
