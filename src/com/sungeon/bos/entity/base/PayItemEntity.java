/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.base;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("PayItem")
public class PayItemEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4500995743602788732L;

	private Integer id;
	private String paywayCode;
	private String paywayName;
	private Double payamount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaywayCode() {
		return paywayCode;
	}

	public void setPaywayCode(String paywayCode) {
		this.paywayCode = paywayCode;
	}

	public String getPaywayName() {
		return paywayName;
	}

	public void setPaywayName(String paywayName) {
		this.paywayName = paywayName;
	}

	public Double getPayamount() {
		return payamount;
	}

	public void setPayamount(Double payamount) {
		this.payamount = payamount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
