package com.sungeon.bos.entity.response;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.base.RetailEntity;

public class RetailGetResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6411052391387498334L;

	private List<RetailEntity> result;

	public List<RetailEntity> getResult() {
		return result;
	}

	public void setResult(List<RetailEntity> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
