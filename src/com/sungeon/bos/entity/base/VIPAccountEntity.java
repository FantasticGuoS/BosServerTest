package com.sungeon.bos.entity.base;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("VIPAccount")
public class VIPAccountEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9134586329477303225L;

	private Integer id;
	private Integer vipid;
	private Integer evipid;
	private String cardno;
	private String mobile;
	private Integer integral; // 积分
	private Double balance; // 可用资金
	private Integer changedate; // 变动日期
	private String integraltype; // 积分变动类型
	private String balancetype; // 资金变动类型
	private String docno; // 线下单据号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVipid() {
		return vipid;
	}

	public void setVipid(Integer vipid) {
		this.vipid = vipid;
	}

	public Integer getEvipid() {
		return evipid;
	}

	public void setEvipid(Integer evipid) {
		this.evipid = evipid;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getChangedate() {
		return changedate;
	}

	public void setChangedate(Integer changedate) {
		this.changedate = changedate;
	}

	public String getIntegraltype() {
		return integraltype;
	}

	public void setIntegraltype(String integraltype) {
		this.integraltype = integraltype;
	}

	public String getBalancetype() {
		return balancetype;
	}

	public void setBalancetype(String balancetype) {
		this.balancetype = balancetype;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
