/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.response;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.base.ProductEntity;

public class ProductGetResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7826458298226163851L;

	private List<ProductEntity> result;

	public List<ProductEntity> getResult() {
		return result;
	}

	public void setResult(List<ProductEntity> result) {
		this.result = result;
		setCount(result.size());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
