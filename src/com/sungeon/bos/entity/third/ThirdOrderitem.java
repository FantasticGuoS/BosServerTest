package com.sungeon.bos.entity.third;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdOrderitem")
public class ThirdOrderitem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -649793813667853228L;

	private String sku_code = null;// 条码
	private Integer item_num = null;// 数量
	private Double item_price_trade = null;// 成交价
	private Double item_amount_subtotal = null;// 成交金额
	private Integer trade_item_id = null;

	public Integer getTrade_item_id() {
		return trade_item_id;
	}

	public void setTrade_item_id(Integer trade_item_id) {
		this.trade_item_id = trade_item_id;
	}

	public String getSku_code() {
		return sku_code;
	}

	public void setSku_code(String sku_code) {
		this.sku_code = sku_code;
	}

	public Integer getItem_num() {
		return item_num;
	}

	public void setItem_num(Integer item_num) {
		this.item_num = item_num;
	}

	public Double getItem_price_trade() {
		return item_price_trade;
	}

	public void setItem_price_trade(Double item_price_trade) {
		this.item_price_trade = item_price_trade;
	}

	public Double getItem_amount_subtotal() {
		return item_amount_subtotal;
	}

	public void setItem_amount_subtotal(Double item_amount_subtotal) {
		this.item_amount_subtotal = item_amount_subtotal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}
}
