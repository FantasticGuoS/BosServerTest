package com.sungeon.bos.entity.third;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdTicket")
public class ThirdTicket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8870564694422572424L;

	private Integer ticket_id = null;
	private String ticket_source = null; // 获取场景，可选值：注册、签到、完善资料、积分兑换
	private String ticket_no = null;
	private String ticket_type = null; // 券类型，可选值：现金券、实物券
	private String ticket_category = null; // 券分类
	private String ticket_name = null; // 券名称
	private Double ticket_amount = null; // 券面值
	private String ticket_time_effect = null; // 生效时间
	private String ticket_time_invalid = null; // 过期时间
	private String ticket_note = null; // 使用说明
	private Double ticket_meet_amount = null; // 满足金额
	private String ticket_time_given = null; // 发放时间
	private String ticket_status = null; // 优惠券状态，可选值：未使用、已使用、已过期
	private String ticket_time_verified = null; // 核销时间
	private String ticket_verified_note = null; // 核销备注
	private String ticket_store_code = null; // 核销门店
	private Integer ticket_user_id = null; // 所属第三方VIP
	private String ticket_password = null; // 校验码

	public String getTicket_password() {
		return ticket_password;
	}

	public void setTicket_password(String ticket_password) {
		this.ticket_password = ticket_password;
	}

	public Integer getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTicket_source() {
		return ticket_source;
	}

	public void setTicket_source(String ticket_source) {
		this.ticket_source = ticket_source;
	}

	public String getTicket_no() {
		return ticket_no;
	}

	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}

	public String getTicket_type() {
		return ticket_type;
	}

	public void setTicket_type(String ticket_type) {
		this.ticket_type = ticket_type;
	}

	public String getTicket_category() {
		return ticket_category;
	}

	public void setTicket_category(String ticket_category) {
		this.ticket_category = ticket_category;
	}

	public String getTicket_name() {
		return ticket_name;
	}

	public void setTicket_name(String ticket_name) {
		this.ticket_name = ticket_name;
	}

	public Double getTicket_amount() {
		return ticket_amount;
	}

	public void setTicket_amount(Double ticket_amount) {
		this.ticket_amount = ticket_amount;
	}

	public String getTicket_time_effect() {
		return ticket_time_effect;
	}

	public void setTicket_time_effect(String ticket_time_effect) {
		this.ticket_time_effect = ticket_time_effect;
	}

	public String getTicket_time_invalid() {
		return ticket_time_invalid;
	}

	public void setTicket_time_invalid(String ticket_time_invalid) {
		this.ticket_time_invalid = ticket_time_invalid;
	}

	public String getTicket_note() {
		return ticket_note;
	}

	public void setTicket_note(String ticket_note) {
		this.ticket_note = ticket_note;
	}

	public Double getTicket_meet_amount() {
		return ticket_meet_amount;
	}

	public void setTicket_meet_amount(Double ticket_meet_amount) {
		this.ticket_meet_amount = ticket_meet_amount;
	}

	public String getTicket_time_given() {
		return ticket_time_given;
	}

	public void setTicket_time_given(String ticket_time_given) {
		this.ticket_time_given = ticket_time_given;
	}

	public String getTicket_status() {
		return ticket_status;
	}

	public void setTicket_status(String ticket_status) {
		this.ticket_status = ticket_status;
	}

	public String getTicket_time_verified() {
		return ticket_time_verified;
	}

	public void setTicket_time_verified(String ticket_time_verified) {
		this.ticket_time_verified = ticket_time_verified;
	}

	public String getTicket_verified_note() {
		return ticket_verified_note;
	}

	public void setTicket_verified_note(String ticket_verified_note) {
		this.ticket_verified_note = ticket_verified_note;
	}

	public String getTicket_store_code() {
		return ticket_store_code;
	}

	public void setTicket_store_code(String ticket_store_code) {
		this.ticket_store_code = ticket_store_code;
	}

	public Integer getTicket_user_id() {
		return ticket_user_id;
	}

	public void setTicket_user_id(Integer ticket_user_id) {
		this.ticket_user_id = ticket_user_id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}
}
