package com.sungeon.bos.port.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.sungeon.bos.entity.request.BaseRequest;
import com.sungeon.bos.entity.response.BaseResponse;
import com.sungeon.bos.exception.ParamNullException;
import com.sungeon.bos.util.EncryptUtil;
import com.sungeon.bos.util.SystemProperties;
import com.sungeon.bos.util.SystemUtil;

public class Base {

	private Logger log = Logger.getLogger(this.getClass());
	private static Long lasttime = SystemProperties.origDate;
	@Value("${AppId}")
	private String AppId;
	@Value("${AppSecret}")
	private String AppSecret;
	@Value("${Version}")
	private String Version;
	@Value("${Param.Sign.Status}")
	private String signStatus;
	@Value("${Param.Sign.qhs.Status}")
	private String signQhsStatus;
	@Value("${Param.Sign.Type}")
	private String signType;
	@Value("${Param.Time.Called}")
	private String timeCalled;

	protected int verify(BaseRequest req, BaseResponse resp, String method) throws Exception {
		if (null == req || req.isEmpty())
			throw new ParamNullException("req", "参数为NULL或缺少部分参数");
		if (null != req.getAppid() && !req.getAppid().equals(AppId)) {
			log.error("appid错误--传入：" + req.getAppid() + "，服务器：" + AppId);
			exception(resp, 201, "invalid appid:" + req.getAppid(), "错误的appid密钥：" + req.getAppid());
			return 201;
		}
		if (null != req.getVersion() && !req.getVersion().equals(Version)) {
			log.error("版本错误--传入：" + req.getVersion() + "，服务器：" + Version);
			exception(resp, 202, "invalid version:" + req.getVersion(), "错误的版本：" + req.getVersion());
			return 202;
		}
		if (null != req.getMethod() && !req.getMethod().equals(method)) {
			log.error("方法错误--传入：" + req.getMethod() + "，服务器：" + method);
			exception(resp, 203, "invalid method:" + req.getMethod(), "错误的方法：" + req.getMethod());
			return 203;
		}
		if (signStatus.equals("Y")) {
			String signServer = null;
			if (signType.equals("T2"))
				signServer = EncryptUtil.signToRequest(req, AppSecret, signQhsStatus.equals("Y"));
			if (signType.equals("T3"))
				signServer = EncryptUtil.signToRequest(req, AppSecret);
			if (null != req.getSign() && !req.getSign().equals(signServer)) {
				log.error("签名错误--传入：" + req.getSign() + "，服务器：" + signServer);
				exception(resp, 210, "invalid sign", "签名错误，待验签参数：" + EncryptUtil.query);
				return 210;
			}
		}
		if (Integer.parseInt(timeCalled) != 0
				&& (System.currentTimeMillis() - lasttime) < Integer.parseInt(timeCalled) * 1000) {
			log.warn("操作太频繁--本次：" + SystemUtil.getTime(System.currentTimeMillis()) + "，上次："
					+ SystemUtil.getTime(lasttime));
			exception(resp, 310, "operation too often", "操作太频繁，请" + timeCalled + "秒后再操作");
			return 310;
		}
		lasttime = System.currentTimeMillis();
		resp.setCode(100);
		return 100;
	}

	protected void exception(BaseResponse resp, int code, String error, String message) {
		resp.setCode(code);
		resp.getDetail().setDesc(error);
		resp.getDetail().setDescription(message);
	}

}
