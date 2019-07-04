package com.sungeon.bos.entity.third;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdVIPBalanceFTP")
public class ThirdVIPBalanceFTP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2217242203264205063L;

	private Integer balance_id = null;
	private Integer user_id = null;
	private String code = null;
	private Double amount = null;
	private String source = null;
	private String source_code = null;
	private String status = null;
	private String note = null;
	private String gateway_code = null;
	private String auditor_cs = null;
	private String auditor_fnc = null;
	private String canceler = null;
	private String creator = null;
	private String modifier = null;
	private String time_created = null;
	private String time_modified = null;
	private String time_acs = null;
	private String time_afnc = null;
	private String time_canceled = null;

	public Integer getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Integer balance_id) {
		this.balance_id = balance_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource_code() {
		return source_code;
	}

	public void setSource_code(String source_code) {
		this.source_code = source_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGateway_code() {
		return gateway_code;
	}

	public void setGateway_code(String gateway_code) {
		this.gateway_code = gateway_code;
	}

	public String getAuditor_cs() {
		return auditor_cs;
	}

	public void setAuditor_cs(String auditor_cs) {
		this.auditor_cs = auditor_cs;
	}

	public String getAuditor_fnc() {
		return auditor_fnc;
	}

	public void setAuditor_fnc(String auditor_fnc) {
		this.auditor_fnc = auditor_fnc;
	}

	public String getCanceler() {
		return canceler;
	}

	public void setCanceler(String canceler) {
		this.canceler = canceler;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getTime_created() {
		return time_created;
	}

	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}

	public String getTime_modified() {
		return time_modified;
	}

	public void setTime_modified(String time_modified) {
		this.time_modified = time_modified;
	}

	public String getTime_acs() {
		return time_acs;
	}

	public void setTime_acs(String time_acs) {
		this.time_acs = time_acs;
	}

	public String getTime_afnc() {
		return time_afnc;
	}

	public void setTime_afnc(String time_afnc) {
		this.time_afnc = time_afnc;
	}

	public String getTime_canceled() {
		return time_canceled;
	}

	public void setTime_canceled(String time_canceled) {
		this.time_canceled = time_canceled;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
