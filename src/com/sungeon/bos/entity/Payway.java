package com.sungeon.bos.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("Payway")
public class Payway implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8827627423246674731L;

	// @Excel(name = "id")
	private Integer id;
	// @Excel(name = "付款方式")
	private String code;
	// @Excel(name = "付款方式名称")
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
