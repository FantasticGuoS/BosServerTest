/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.base;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("Storage")
public class StorageEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3629855165649193957L;

	private Integer skuid;
	private String sku;
	private Integer productid;
	private String product;
	private Integer storeid;
	private String store;
	private Integer qty;
	private Integer qtycan;

	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQtycan() {
		return qtycan;
	}

	public void setQtycan(Integer qtycan) {
		this.qtycan = qtycan;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
