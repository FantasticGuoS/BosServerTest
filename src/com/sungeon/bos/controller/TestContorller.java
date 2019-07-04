package com.sungeon.bos.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.response.BaseResponse;
import com.sungeon.bos.util.FileUtil;

@Controller
public class TestContorller extends BaseContorller {

	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/testxml")
	@ResponseBody
	public BaseResponse testxml() {
		BaseResponse doc = new BaseResponse();
		doc.setCode(300);
		doc.getDetail().setDesc("test");
		doc.getDetail().setDescription("测试");
		return doc;
	}

	@RequestMapping("/testjson")
	@ResponseBody
	public JSONObject testjson() {
		BaseResponse doc = new BaseResponse();
		doc.setCode(300);
		doc.getDetail().setDesc("test");
		doc.getDetail().setDescription("测试");
		return JSONObject.parseObject(doc.toString());
	}

	@RequestMapping("/testHttpStatus")
	@ResponseBody
	public String testHttpStatus(HttpServletRequest req, HttpServletResponse resp) {
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return "测试HTTP状态码的返回值；只有状态码小于400才会有返回值?";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload() throws IOException {
		JSONObject response = new JSONObject();
		String filename = req.getParameter("fileName");
		InputStream input = req.getInputStream();
		File destFile = FileUtil.writeFileByInputStream("D:/Document/Image/" + filename, input);
		log.info("文件上传结果：" + destFile.exists());
		input.close();

		response.fluentPut("code", 1);
		response.fluentPut("message", "文件上传成功");
		log.info(response);
		sendResponse(response.toString());
		return response.toJSONString();
	}

	private void sendResponse(String responseString) throws IOException {
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = resp.getWriter();
		pw.write(responseString);
		pw.flush();
	}

}
