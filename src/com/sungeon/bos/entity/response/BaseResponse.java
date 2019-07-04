/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.util.SystemUtil;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
@XmlType
public class BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 578474743222011267L;

	/**
	 * 响应码
	 */
	// @XmlAttribute(name = "code") // 作为属性值
	@XmlElement // 作为子结点参数
	private int code;
	/**
	 * 响应信息
	 */
	@XmlElement
	private String message;
	/**
	 * 结果集个数
	 */
	@XmlElement
	private int count;
	/**
	 * 响应时间
	 */
	@XmlElement
	private String time;
	/**
	 * 详情
	 */
	@XmlElement
	private Detail detail;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		setDetail(new Detail());
		switch (code) {
		case 100:
			setMessage("操作成功");
			break;
		case 110:
			setMessage("操作成功");
			break;
		case 120:
			setMessage("操作成功");
			break;
		case 200:
			setMessage("验证失败");
			break;
		case 201:
			setMessage("密钥错误");
			break;
		case 202:
			setMessage("版本错误");
			break;
		case 203:
			setMessage("方法错误");
			break;
		case 210:
			setMessage("签名验证失败");
			break;
		case 220:
			setMessage("参数异常");
			break;
		case 300:
			setMessage("操作失败");
			break;
		case 310:
			setMessage("操作太频繁");
			break;
		case 400:
			setMessage("服务器内部错误");
			break;
		default:
			setMessage("操作异常");
			break;
		}
		setCount(0);
		setTime(SystemUtil.getTime());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
