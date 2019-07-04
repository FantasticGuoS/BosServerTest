package com.sungeon.bos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.TicketEntity;
import com.sungeon.bos.entity.third.ThirdTicket;

@Repository
public interface ITicketDao {

	public Integer insertTicketBatch(List<ThirdTicket> addList);

	public BosResult executePThirdTicket();

	public List<TicketEntity> queryTicketPropel(int count);

	public Integer updateTicketPropelStatus(JSONArray tickets);

	public List<TicketEntity> queryVerifiedTicketPropel(int count);

	public Integer updateVerifiedTicketPropelStatus(JSONArray tickets);

}
