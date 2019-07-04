package com.sungeon.bos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.TicketEntity;
import com.sungeon.bos.entity.request.TicketGetRequest;
import com.sungeon.bos.entity.third.ThirdTicket;

@Service
public interface ITicketService {

	public List<TicketEntity> getTicket(TicketGetRequest request);

	public List<ThirdTicket> saveTicketBatch(JSONArray jsons);

	public BosResult executePThirdTicket();

	public JSONArray getTicketPropel(int count);

	public Integer modifyTicketPropelStatus(JSONArray tickets);

	public JSONArray getVerifiedTicketPropel(int count);

	public Integer modifyVerifiedTicketPropelStatus(JSONArray tickets);

}
