package com.sungeon.bos.entity.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.alibaba.fastjson.JSONObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Detail")
@XmlType
public class Detail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9188911949796606618L;

	@XmlElement
	private String desc;
	@XmlElement
	private String description;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
