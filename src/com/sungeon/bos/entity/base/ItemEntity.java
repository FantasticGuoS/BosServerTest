/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.base;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("Item")
public class ItemEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3971322790306966791L;

	private Integer id;
	private Integer productId;
	private String product;
	private String productName;
	private Integer skuId;
	private String sku;
	private String colorCode;
	private String colorName;
	private String sizeCode;
	private String sizeName;
	private String brandCode;
	private String brandName;
	private String classCode;
	private String className;
	private String yearCode;
	private String yearName;
	private String seasonCode;
	private String seasonName;
	private String genderCode;
	private String genderName;
	private Integer qty;
	private Integer qtyout;
	private Integer qtyin;
	private Double pricelist;
	private Double priceactual;
	private Double discount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getYearCode() {
		return yearCode;
	}

	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQtyout() {
		return qtyout;
	}

	public void setQtyout(Integer qtyout) {
		this.qtyout = qtyout;
	}

	public Integer getQtyin() {
		return qtyin;
	}

	public void setQtyin(Integer qtyin) {
		this.qtyin = qtyin;
	}

	public Double getPricelist() {
		return pricelist;
	}

	public void setPricelist(Double pricelist) {
		this.pricelist = pricelist;
	}

	public Double getPriceactual() {
		return priceactual;
	}

	public void setPriceactual(Double priceactual) {
		this.priceactual = priceactual;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
