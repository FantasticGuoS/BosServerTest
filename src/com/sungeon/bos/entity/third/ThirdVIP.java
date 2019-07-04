package com.sungeon.bos.entity.third;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdVIP")
public class ThirdVIP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6616072299604120307L;

	private Integer user_id = null; // 线上会员ID
	private String user_vip_card = null; // 卡号
	private String user_name = null; // 用户名
	private String user_real_name = null; // 真实姓名
	private String user_nickname = null; // 昵称
	private String user_avatar = null; // 会员头像
	private String user_gender = null; // 性别
	private String user_birthday = null; // 生日
	private String user_mobile_phone = null; // 手机号
	private String user_rank_code = null; // 会员类型编号
	private String user_rank_name = null;
	private String user_store_id = null;
	private String user_store_code = null; // 开卡门店编号
	private String user_password = null;
	private String user_reg_time = null;
	private String user_time_created = null; // 注册时间
	private String user_time_modified = null; //
	private String wechat_openid = null; // 微信openid
	private String user_cus_field = null;
	private String user_pay_password = null;//会员支付密码

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_vip_card() {
		return user_vip_card;
	}

	public void setUser_vip_card(String user_vip_card) {
		this.user_vip_card = user_vip_card;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_real_name() {
		return user_real_name;
	}

	public void setUser_real_name(String user_real_name) {
		this.user_real_name = user_real_name;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}

	public String getUser_mobile_phone() {
		return user_mobile_phone;
	}

	public void setUser_mobile_phone(String user_mobile_phone) {
		this.user_mobile_phone = user_mobile_phone;
	}

	public String getUser_rank_code() {
		return user_rank_code;
	}

	public void setUser_rank_code(String user_rank_code) {
		this.user_rank_code = user_rank_code;
	}

	public String getUser_rank_name() {
		return user_rank_name;
	}

	public void setUser_rank_name(String user_rank_name) {
		this.user_rank_name = user_rank_name;
	}

	public String getUser_store_id() {
		return user_store_id;
	}

	public void setUser_store_id(String user_store_id) {
		this.user_store_id = user_store_id;
	}

	public String getUser_store_code() {
		return user_store_code;
	}

	public void setUser_store_code(String user_store_code) {
		this.user_store_code = user_store_code;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_reg_time() {
		return user_reg_time;
	}

	public void setUser_reg_time(String user_reg_time) {
		this.user_reg_time = user_reg_time;
	}

	public String getUser_time_created() {
		return user_time_created;
	}

	public void setUser_time_created(String user_time_created) {
		this.user_time_created = user_time_created;
	}

	public String getUser_time_modified() {
		return user_time_modified;
	}

	public void setUser_time_modified(String user_time_modified) {
		this.user_time_modified = user_time_modified;
	}

	public String getWechat_openid() {
		return wechat_openid;
	}

	public void setWechat_openid(String wechat_openid) {
		this.wechat_openid = wechat_openid;
	}

	public String getUser_cus_field() {
		return user_cus_field;
	}

	public void setUser_cus_field(String user_cus_field) {
		this.user_cus_field = user_cus_field;
	}

	public String getUser_pay_password() {
		return user_pay_password;
	}

	public void setUser_pay_password(String user_pay_password) {
		this.user_pay_password = user_pay_password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
