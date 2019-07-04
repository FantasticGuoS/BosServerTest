/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.base;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("Retail")
public class RetailEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8595268264337337850L;

	private Integer id = null;
	private String docno = null;
	private Integer billdate = null;
	private String storeCode = null;
	private String storeName = null;
	private String statusTime = null;
	private Double amtList = null;
	private Double amtActual = null;
	private List<ItemEntity> productItems;
	private List<PayItemEntity> payItems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public Integer getBilldate() {
		return billdate;
	}

	public void setBilldate(Integer billdate) {
		this.billdate = billdate;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	public Double getAmtList() {
		return amtList;
	}

	public void setAmtList(Double amtList) {
		this.amtList = amtList;
	}

	public Double getAmtActual() {
		return amtActual;
	}

	public void setAmtActual(Double amtActual) {
		this.amtActual = amtActual;
	}

	public List<ItemEntity> getProductItems() {
		return productItems;
	}

	public void setProductItems(List<ItemEntity> productItems) {
		this.productItems = productItems;
	}

	public List<PayItemEntity> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItemEntity> payItems) {
		this.payItems = payItems;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
