package com.sungeon.bos.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("VIPType")
public class VIPType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4329627433583783901L;

	// @Excel(name = "id")
	private Integer id;
	// @Excel(name = "code")
	private String code;
	// @Excel(name = "name")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
