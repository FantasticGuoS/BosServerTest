package com.sungeon.bos.entity.response;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.base.TicketEntity;

public class TicketGetResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3812751887039015006L;

	private List<TicketEntity> result;

	public List<TicketEntity> getResult() {
		return result;
	}

	public void setResult(List<TicketEntity> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
