package com.sungeon.bos.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class HttpResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6082629076261027360L;

	private int code = 200;
	private String message = "OK";
	private String content;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		if (code == 400)
			this.message = "Bad Request";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
