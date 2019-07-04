package com.sungeon.bos.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class BosResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8303046036121482996L;

	private int code;
	private String message = "OK";

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
