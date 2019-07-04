/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.base;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("Product")
public class ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2839210750915510994L;

	private Integer productId;
	private String productCode;
	private String productName;
	private String brandCode;
	private String classCode;
	private Double pricelist;
	private List<SKUEntity> skus;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Double getPricelist() {
		return pricelist;
	}

	public void setPricelist(Double pricelist) {
		this.pricelist = pricelist;
	}

	public List<SKUEntity> getSkus() {
		return skus;
	}

	public void setSkus(List<SKUEntity> skus) {
		this.skus = skus;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
