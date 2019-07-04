package com.sungeon.bos.entity.third;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdVIPIntegralFTP")
public class ThirdVIPIntegralFTP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3007374561657169381L;

	private Integer integral_id = null;
	private Integer user_id = null;
	private String type = null;
	private Double amount = null;
	private String related_code = null;
	private String is_offline = null;
	private Integer thd_id = null;
	private String status = null;
	private String note = null;
	private String creator = null;
	private String modifier = null;
	private String time_created = null;
	private String time_modified = null;

	public Integer getIntegral_id() {
		return integral_id;
	}

	public void setIntegral_id(Integer integral_id) {
		this.integral_id = integral_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRelated_code() {
		return related_code;
	}

	public void setRelated_code(String related_code) {
		this.related_code = related_code;
	}

	public String getIs_offline() {
		return is_offline;
	}

	public void setIs_offline(String is_offline) {
		this.is_offline = is_offline;
	}

	public Integer getThd_id() {
		return thd_id;
	}

	public void setThd_id(Integer thd_id) {
		this.thd_id = thd_id;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
