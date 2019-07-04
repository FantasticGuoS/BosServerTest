/**
 * Created by GuoS on 2017/7/28.
 */
package com.sungeon.bos.task;

import java.net.HttpURLConnection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.HttpResult;
import com.sungeon.bos.service.IBaseService;
import com.sungeon.bos.util.EncryptUtil;
import com.sungeon.bos.util.HttpUtil;
import com.sungeon.bos.util.RandomUtil;
import com.sungeon.bos.util.SystemProperties;
import com.sungeon.bos.util.SystemUtil;

public class BaseTask {

	private static Logger log = Logger.getLogger(BaseTask.class);
	@Autowired
	protected IBaseService baseService;

	protected static JSONObject yunhuanHttpRequest(String method, JSON jsonData) throws Exception {
		String noncestr = RandomUtil.getRandomString(32);
		String timestamp = SystemUtil.getTime();
		String sign = EncryptUtil.MD5(SystemProperties.AppId + SystemProperties.AppSecret + noncestr + timestamp
				+ method + jsonData.toString(), false);
		String url = SystemProperties.ParamPropelURL + "&method=" + method + "&timestamp=" + timestamp + "&noncestr="
				+ noncestr + "&appid=" + SystemProperties.AppId + "&sign=" + sign;
		// 发送请求
		HttpResult result = HttpUtil.httpPost(url, jsonData);
		// 处理响应-转换为JSON格式
		if (result.getCode() == HttpURLConnection.HTTP_OK) {
			return JSONObject.parseObject(result.getContent().trim().replace("\n", "").replace("\r", ""));
		} else {
			JSONObject resp = new JSONObject();
			log.error("请求异常或返回信息异常：" + result.getMessage());
			resp.put("Code", 0);
			resp.put("Desc", "请求异常或返回信息异常");
			return resp;
		}
	}

	protected JSONObject httpRequest(String param) throws Exception {
		HttpResult result = HttpUtil.httpPost(SystemProperties.ParamPropelURL, param);
		if (result.getCode() == HttpURLConnection.HTTP_OK) {
			return JSONObject.parseObject(result.getContent().trim().replace("\n", "").replace("\r", ""));
		} else {
			JSONObject resp = new JSONObject();
			log.error("请求异常或返回信息异常：" + result.getMessage());
			resp.put("Code", 0);
			resp.put("Desc", "请求异常或返回信息异常");
			return resp;
		}
	}

}
