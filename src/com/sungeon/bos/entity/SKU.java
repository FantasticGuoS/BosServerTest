package com.sungeon.bos.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("SKUID")
public class SKU implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5490695588266745427L;

	private Integer productid;
	private Integer skuid;
	private Integer asiid;
	private Double pricelist;

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public Integer getAsiid() {
		return asiid;
	}

	public void setAsiid(Integer asiid) {
		this.asiid = asiid;
	}

	public Double getPricelist() {
		return pricelist;
	}

	public void setPricelist(Double pricelist) {
		this.pricelist = pricelist;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
