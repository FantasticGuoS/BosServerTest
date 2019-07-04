package com.sungeon.bos.entity.request;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class VIPGetRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3517638736954814272L;

	private List<String> mobiles;
	private Integer page;
	private Integer pagesize;

	public List<String> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<String> mobiles) {
		this.mobiles = mobiles;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
