package com.sungeon.bos.entity.third;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ThirdProduct")
public class ThirdProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4114849711669417013L;

	private Integer goods_id = null; // 线上商品ID
	private String goods_code = null; // 线上商品编号

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_code() {
		return goods_code;
	}

	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
