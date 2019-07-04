/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.request;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4634728769230109719L;

	/**
	 * appid
	 */
	private String appid = null;
	/**
	 * 版本号
	 */
	private String version = null;
	/**
	 * 方法名
	 */
	private String method = null;
	/**
	 * 时间戳
	 */
	private String timestamp = null;
	/**
	 * 签名
	 */
	private String sign = null;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public boolean isEmpty() {
		if (null == getAppid())
			return true;
		if (null == getMethod())
			return true;
		if (null == getVersion())
			return true;
		if (null == getTimestamp())
			return true;
		if (null == getSign())
			return true;
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
