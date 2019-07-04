package com.sungeon.bos.entity.request;

import com.alibaba.fastjson.JSONObject;

public class TicketGetRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1790148140701300529L;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
