package com.sungeon.bos.entity.third;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdOrder")
public class ThirdOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -373466257815291220L;

	private String trade_code = null;// 订单编号
	private String trade_source_store = null;// 下单店仓
	private String trade_delivery_store = null;// 发货店仓
	private String trade_time_created = null;// 下单时间
	private Integer fk_member_id = null;// 线上会员ID
	private String trade_consignee = null;// 收货人
	private String trade_mobile = null;// 收货人手机
	private String trade_street = null;// 街道
	private String trade_note_buyer = null;// 买家备注
	private String trade_distrtype_code = null;// 配送方式
	private String trade_distrtype_name = null;// 配送方式
	private Double trade_amount_payable = null;// 订单金额
	private Double trade_amount_offset_coupon = null;// 优惠券金额
	private String trade_coupon_sn = null;// 优惠券号
	private Double trade_amount_offset_integral = null;// 积分抵扣金额
	private String trade_payment_code = null;// 支付方式
	private Double trade_amount_online = null;// 微信支付金额
	private String trade_time_pay = null;// 支付时间
	private Double trade_amount_offset_balance = null;// 结余款支付金额
	private Double trade_integral = null;// 使用积分
	private String trade_ship_code = null;// 快递公司编号
	private String trade_ship_name = null;// 快递公司名称
	private String trade_logistic_code = null;// 物流单号
	private String trade_province = null;// 省
	private String trade_city = null;// 市
	private String trade_district = null;// 乡
	private String trade_towns = null;// 村
	private Double trade_amount_delivery = null;// 配送费用
	private List<ThirdOrderitem> items = null;// 支付明细

	public String getTrade_code() {
		return trade_code;
	}

	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}

	public String getTrade_source_store() {
		return trade_source_store;
	}

	public void setTrade_source_store(String trade_source_store) {
		this.trade_source_store = trade_source_store;
	}

	public String getTrade_delivery_store() {
		return trade_delivery_store;
	}

	public void setTrade_delivery_store(String trade_delivery_store) {
		this.trade_delivery_store = trade_delivery_store;
	}

	public String getTrade_time_created() {
		return trade_time_created;
	}

	public void setTrade_time_created(String trade_time_created) {
		this.trade_time_created = trade_time_created;
	}

	public Integer getFk_member_id() {
		return fk_member_id;
	}

	public void setFk_member_id(Integer fk_member_id) {
		this.fk_member_id = fk_member_id;
	}

	public String getTrade_consignee() {
		return trade_consignee;
	}

	public void setTrade_consignee(String trade_consignee) {
		this.trade_consignee = trade_consignee;
	}

	public String getTrade_mobile() {
		return trade_mobile;
	}

	public void setTrade_mobile(String trade_mobile) {
		this.trade_mobile = trade_mobile;
	}

	public String getTrade_street() {
		return trade_street;
	}

	public void setTrade_street(String trade_street) {
		this.trade_street = trade_street;
	}

	public String getTrade_note_buyer() {
		return trade_note_buyer;
	}

	public void setTrade_note_buyer(String trade_note_buyer) {
		this.trade_note_buyer = trade_note_buyer;
	}

	public String getTrade_distrtype_code() {
		return trade_distrtype_code;
	}

	public void setTrade_distrtype_code(String trade_distrtype_code) {
		this.trade_distrtype_code = trade_distrtype_code;
	}

	public String getTrade_distrtype_name() {
		return trade_distrtype_name;
	}

	public void setTrade_distrtype_name(String trade_distrtype_name) {
		this.trade_distrtype_name = trade_distrtype_name;
	}

	public Double getTrade_amount_payable() {
		return trade_amount_payable;
	}

	public void setTrade_amount_payable(Double trade_amount_payable) {
		this.trade_amount_payable = trade_amount_payable;
	}

	public Double getTrade_amount_offset_coupon() {
		return trade_amount_offset_coupon;
	}

	public void setTrade_amount_offset_coupon(Double trade_amount_offset_coupon) {
		this.trade_amount_offset_coupon = trade_amount_offset_coupon;
	}

	public String getTrade_coupon_sn() {
		return trade_coupon_sn;
	}

	public void setTrade_coupon_sn(String trade_coupon_sn) {
		this.trade_coupon_sn = trade_coupon_sn;
	}

	public Double getTrade_amount_offset_integral() {
		return trade_amount_offset_integral;
	}

	public void setTrade_amount_offset_integral(Double trade_amount_offset_integral) {
		this.trade_amount_offset_integral = trade_amount_offset_integral;
	}

	public String getTrade_payment_code() {
		return trade_payment_code;
	}

	public void setTrade_payment_code(String trade_payment_code) {
		this.trade_payment_code = trade_payment_code;
	}

	public Double getTrade_amount_online() {
		return trade_amount_online;
	}

	public void setTrade_amount_online(Double trade_amount_online) {
		this.trade_amount_online = trade_amount_online;
	}

	public String getTrade_time_pay() {
		return trade_time_pay;
	}

	public void setTrade_time_pay(String trade_time_pay) {
		this.trade_time_pay = trade_time_pay;
	}

	public Double getTrade_amount_offset_balance() {
		return trade_amount_offset_balance;
	}

	public void setTrade_amount_offset_balance(Double trade_amount_offset_balance) {
		this.trade_amount_offset_balance = trade_amount_offset_balance;
	}

	public Double getTrade_integral() {
		return trade_integral;
	}

	public void setTrade_integral(Double trade_integral) {
		this.trade_integral = trade_integral;
	}

	public String getTrade_ship_code() {
		return trade_ship_code;
	}

	public void setTrade_ship_code(String trade_ship_code) {
		this.trade_ship_code = trade_ship_code;
	}

	public String getTrade_ship_name() {
		return trade_ship_name;
	}

	public void setTrade_ship_name(String trade_ship_name) {
		this.trade_ship_name = trade_ship_name;
	}

	public String getTrade_logistic_code() {
		return trade_logistic_code;
	}

	public void setTrade_logistic_code(String trade_logistic_code) {
		this.trade_logistic_code = trade_logistic_code;
	}

	public String getTrade_province() {
		return trade_province;
	}

	public void setTrade_province(String trade_province) {
		this.trade_province = trade_province;
	}

	public String getTrade_city() {
		return trade_city;
	}

	public void setTrade_city(String trade_city) {
		this.trade_city = trade_city;
	}

	public String getTrade_district() {
		return trade_district;
	}

	public void setTrade_district(String trade_district) {
		this.trade_district = trade_district;
	}

	public String getTrade_towns() {
		return trade_towns;
	}

	public void setTrade_towns(String trade_towns) {
		this.trade_towns = trade_towns;
	}

	public Double getTrade_amount_delivery() {
		return trade_amount_delivery;
	}

	public void setTrade_amount_delivery(Double trade_amount_delivery) {
		this.trade_amount_delivery = trade_amount_delivery;
	}

	public List<ThirdOrderitem> getItems() {
		return items;
	}

	public void setItems(List<ThirdOrderitem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
