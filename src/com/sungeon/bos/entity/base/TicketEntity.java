/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.entity.base;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

@Alias("Ticket")
public class TicketEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1536723276618285396L;

	private Integer id;
	private String ticketno; // 券号
	private String checkno; // 密码、校验码
	private String name; // 名称
	private Double parvalue; // 面值
	private Integer datebeg; // 有效期起
	private Integer dateend; // 有效期止
	private String note; // 使用条件、使用备注
	private Double amount; // 满足金额
	private String giventime; // 发放时间
	private String isverify; // 是否核销
	private String verifytime; // 核销时间
	private String vipcardno; // 所属会员卡号
	private String vipid; // 所属会员ID

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTicketno() {
		return ticketno;
	}

	public void setTicketno(String ticketno) {
		this.ticketno = ticketno;
	}

	public String getCheckno() {
		return checkno;
	}

	public void setCheckno(String checkno) {
		this.checkno = checkno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getParvalue() {
		return parvalue;
	}

	public void setParvalue(Double parvalue) {
		this.parvalue = parvalue;
	}

	public Integer getDatebeg() {
		return datebeg;
	}

	public void setDatebeg(Integer datebeg) {
		this.datebeg = datebeg;
	}

	public Integer getDateend() {
		return dateend;
	}

	public void setDateend(Integer dateend) {
		this.dateend = dateend;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getGiventime() {
		return giventime;
	}

	public void setGiventime(String giventime) {
		this.giventime = giventime;
	}

	public String getIsverify() {
		return isverify;
	}

	public void setIsverify(String isverify) {
		this.isverify = isverify;
	}

	public String getVerifytime() {
		return verifytime;
	}

	public void setVerifytime(String verifytime) {
		this.verifytime = verifytime;
	}

	public String getVipcardno() {
		return vipcardno;
	}

	public void setVipcardno(String vipcardno) {
		this.vipcardno = vipcardno;
	}

	public String getVipid() {
		return vipid;
	}

	public void setVipid(String vipid) {
		this.vipid = vipid;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}

}
