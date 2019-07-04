package com.sungeon.bos.entity.third;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdBackOrder")
public class ThirdBackOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9052870178829132639L;

	private String after_code = null;// 售后单号
	private String after_type = null;// 售后类型
	private String trade_time_created = null;// 下单时间
	private Integer fk_user_id = null;// 线上会员id
	private String trade_code = null;// 原订单编号
	private String after_logistic_code = null;// 快递单号
	private Double after_amount_pay = null;// 退款总金额
	private Double after_amount_balance = null;// 结余款退款金额
	private String trade_payment_code = null;// 退款支付方式
	private Double after_amount_online = null;// 退款金额
	private String after_time_audit = null;// 售后审核时间
	private String after_remark = null;// 退货原因
	private List<ThirdBackOrderitem> items = null;

	public String getAfter_code() {
		return after_code;
	}

	public void setAfter_code(String after_code) {
		this.after_code = after_code;
	}

	public String getAfter_type() {
		return after_type;
	}

	public void setAfter_type(String after_type) {
		this.after_type = after_type;
	}

	public String getTrade_time_created() {
		return trade_time_created;
	}

	public void setTrade_time_created(String trade_time_created) {
		this.trade_time_created = trade_time_created;
	}

	public Integer getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_user_id(Integer fk_user_id) {
		this.fk_user_id = fk_user_id;
	}

	public String getTrade_code() {
		return trade_code;
	}

	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}

	public String getAfter_logistic_code() {
		return after_logistic_code;
	}

	public void setAfter_logistic_code(String after_logistic_code) {
		this.after_logistic_code = after_logistic_code;
	}

	public Double getAfter_amount_pay() {
		return after_amount_pay;
	}

	public void setAfter_amount_pay(Double after_amount_pay) {
		this.after_amount_pay = after_amount_pay;
	}

	public Double getAfter_amount_balance() {
		return after_amount_balance;
	}

	public void setAfter_amount_balance(Double after_amount_balance) {
		this.after_amount_balance = after_amount_balance;
	}

	public String getTrade_payment_code() {
		return trade_payment_code;
	}

	public void setTrade_payment_code(String trade_payment_code) {
		this.trade_payment_code = trade_payment_code;
	}

	public Double getAfter_amount_online() {
		return after_amount_online;
	}

	public void setAfter_amount_online(Double after_amount_online) {
		this.after_amount_online = after_amount_online;
	}

	public List<ThirdBackOrderitem> getItems() {
		return items;
	}

	public void setItems(List<ThirdBackOrderitem> items) {
		this.items = items;
	}

	public String getAfter_time_audit() {
		return after_time_audit;
	}

	public void setAfter_time_audit(String after_time_audit) {
		this.after_time_audit = after_time_audit;
	}

	public String getAfter_remark() {
		return after_remark;
	}

	public void setAfter_remark(String after_remark) {
		this.after_remark = after_remark;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
