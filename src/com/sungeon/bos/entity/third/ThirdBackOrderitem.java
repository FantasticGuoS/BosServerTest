package com.sungeon.bos.entity.third;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdBackOrderitem")
public class ThirdBackOrderitem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1439568020017043677L;

	private String sku_code = null;
	private Integer item_return_num = null;
	private Double item_trade_price = null;
	private Double item_amount_subtotal = null;
	private String after_code = null;
	private Integer trade_item_id = null;

	public Integer getTrade_item_id() {
		return trade_item_id;
	}

	public void setTrade_item_id(Integer trade_item_id) {
		this.trade_item_id = trade_item_id;
	}

	public String getAfter_code() {
		return after_code;
	}

	public void setAfter_code(String after_code) {
		this.after_code = after_code;
	}

	public String getSku_code() {
		return sku_code;
	}

	public void setSku_code(String sku_code) {
		this.sku_code = sku_code;
	}

	public Integer getItem_return_num() {
		return item_return_num;
	}

	public void setItem_return_num(Integer item_return_num) {
		this.item_return_num = item_return_num;
	}

	public Double getItem_trade_price() {
		return item_trade_price;
	}

	public void setItem_trade_price(Double item_trade_price) {
		this.item_trade_price = item_trade_price;
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
