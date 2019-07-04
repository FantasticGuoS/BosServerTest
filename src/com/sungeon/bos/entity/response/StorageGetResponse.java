/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.response;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.base.StorageEntity;

public class StorageGetResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -226043520097575496L;

	private List<StorageEntity> result;

	public List<StorageEntity> getResult() {
		return result;
	}

	public void setResult(List<StorageEntity> result) {
		this.result = result;
		setCount(result.size());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
