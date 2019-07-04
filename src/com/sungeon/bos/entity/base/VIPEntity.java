package com.sungeon.bos.entity.base;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("VIP")
public class VIPEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4028555804786083002L;

	private Integer id;
	private Integer evipid;
	private String cardno;
	private String vipname;
	private String password;
	private String mobile;
	private String store;
	private String customer;
	private String gender;
	private Integer birthday;
	private String email;
	private String viptype;
	private Integer opendate;
	private Integer integral;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getVipname() {
		return vipname;
	}

	public void setVipname(String vipname) {
		this.vipname = vipname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getBirthday() {
		return birthday;
	}

	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getViptype() {
		return viptype;
	}

	public void setViptype(String viptype) {
		this.viptype = viptype;
	}

	public Integer getOpendate() {
		return opendate;
	}

	public void setOpendate(Integer opendate) {
		this.opendate = opendate;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
