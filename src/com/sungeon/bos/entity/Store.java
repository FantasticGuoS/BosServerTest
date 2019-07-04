package com.sungeon.bos.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("Store")
public class Store implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6233163403830910440L;

	private Integer id;
	private String code;
	private String name;
	private Integer customerid;
	private String customercode;
	private String customername;

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

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
