/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.base;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("ReturnOrder")
public class ReturnOrderEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1049252581734129822L;

	private String returnOrderid = null; // 电商单号ID
	private String returnOrdersn = null; // 电商单号（平台单号）

	public String getReturnOrderid() {
		return returnOrderid;
	}

	public void setReturnOrderid(String returnOrderid) {
		this.returnOrderid = returnOrderid;
	}

	public String getReturnOrdersn() {
		return returnOrdersn;
	}

	public void setReturnOrdersn(String returnOrdersn) {
		this.returnOrdersn = returnOrdersn;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
