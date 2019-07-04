package com.sungeon.bos.entity.request;

import com.alibaba.fastjson.JSONObject;

public class RetailGetRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3058513158491586835L;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
