package com.sungeon.bos.entity.request;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.base.VIPEntity;

public class VIPAddRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2085229879935866540L;

	private List<VIPEntity> vips;

	public List<VIPEntity> getVips() {
		return vips;
	}

	public void setVips(List<VIPEntity> vips) {
		this.vips = vips;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
