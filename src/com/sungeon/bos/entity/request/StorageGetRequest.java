/**
 * Created by GuoS on 2016/12/5.
 */
package com.sungeon.bos.entity.request;

import com.alibaba.fastjson.JSONObject;

public class StorageGetRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4969859505204945378L;

	private Integer page;
	private Integer pagesize;
	private String storecode;
	private String timebeg;
	private String timeend;

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

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getTimebeg() {
		return timebeg;
	}

	public void setTimebeg(String timebeg) {
		this.timebeg = timebeg;
	}

	public String getTimeend() {
		return timeend;
	}

	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
