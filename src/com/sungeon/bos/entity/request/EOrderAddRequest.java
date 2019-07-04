package com.sungeon.bos.entity.request;

import com.alibaba.fastjson.JSONObject;

public class EOrderAddRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2158272102745427704L;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
