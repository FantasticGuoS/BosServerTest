package com.sungeon.bos.entity.response;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.base.VIPEntity;

public class VIPGetResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1407829504793967889L;

	private List<VIPEntity> result;

	public List<VIPEntity> getResult() {
		return result;
	}

	public void setResult(List<VIPEntity> result) {
		this.result = result;
		setCount(result.size());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
