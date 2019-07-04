package com.sungeon.bos.entity.base;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("VIPExpenses")
public class VIPExpensesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3978859991684485512L;

	private Integer id = null;
	private String evipid = null;
	private String docno = null;
	private String refno = null;
	private Integer billdate = null;
	private String billType = null;
	private String storeCode = null;
	private String storeName = null;
	private String storeAddress = null;
	private String storeMobile = null;
	private String storePhone = null;
	private String unicodeid;
	private String brand;
	private String ticket;
	private String salerCode;
	private String salerName;
	private Double totamtList = null;
	private Double totamtActual = null;
	private String modifieddate = null;
	private String statustime = null;
	private String description = null;
	private List<ItemEntity> items = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvipid() {
		return evipid;
	}

	public void setEvipid(String evipid) {
		this.evipid = evipid;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public Integer getBilldate() {
		return billdate;
	}

	public void setBilldate(Integer billdate) {
		this.billdate = billdate;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
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

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreMobile() {
		return storeMobile;
	}

	public void setStoreMobile(String storeMobile) {
		this.storeMobile = storeMobile;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getUnicodeid() {
		return unicodeid;
	}

	public void setUnicodeid(String unicodeid) {
		this.unicodeid = unicodeid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getSalerCode() {
		return salerCode;
	}

	public void setSalerCode(String salerCode) {
		this.salerCode = salerCode;
	}

	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public Double getTotamtList() {
		return totamtList;
	}

	public void setTotamtList(Double totamtList) {
		this.totamtList = totamtList;
	}

	public Double getTotamtActual() {
		return totamtActual;
	}

	public void setTotamtActual(Double totamtActual) {
		this.totamtActual = totamtActual;
	}

	public String getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getStatustime() {
		return statustime;
	}

	public void setStatustime(String statustime) {
		this.statustime = statustime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
