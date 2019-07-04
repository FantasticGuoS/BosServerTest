package com.sungeon.bos.entity.base;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("WeChatMessage")
public class WeChatMessageEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3922034590142579526L;

	private Integer id = null;
	private String evipid = null;
	private String message = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvipid() {
		return evipid;
	}

	public void setEvipid(String evipid) {
		this.evipid = evipid;
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
