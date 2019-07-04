package com.sungeon.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.dao.ITicketDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.TicketEntity;
import com.sungeon.bos.entity.request.TicketGetRequest;
import com.sungeon.bos.entity.third.ThirdTicket;
import com.sungeon.bos.service.ITicketService;
import com.sungeon.bos.util.SystemUtil;

@Service("ticketService")
@Transactional
public class TicketServiceImpl implements ITicketService {

	@Autowired
	private ITicketDao ticketDao;

	@Override
	public List<TicketEntity> getTicket(TicketGetRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ThirdTicket> saveTicketBatch(JSONArray jsons) {
		// TODO Auto-generated method stub
		List<ThirdTicket> addList = JSONArray.parseArray(jsons.toString(),
				ThirdTicket.class);
		if (!addList.isEmpty())
			ticketDao.insertTicketBatch(addList);
		return addList;
	}

	@Override
	public BosResult executePThirdTicket() {
		// TODO Auto-generated method stub
		return ticketDao.executePThirdTicket();
	}

	@Override
	public JSONArray getTicketPropel(int count) {
		// TODO Auto-generated method stub
		JSONArray req = new JSONArray();
		List<TicketEntity> tickets = ticketDao.queryTicketPropel(count);
		for (TicketEntity ticket : tickets) {
			JSONObject json = new JSONObject();
			json.put("ticket_id", ticket.getId());
			json.put("ticket_no", ticket.getTicketno());
			json.put("ticket_name", ticket.getName());
			json.put("ticket_amount", ticket.getParvalue());
			json.put("ticket_time_effect", ticket.getDatebeg());
			json.put("ticket_time_invalid", ticket.getDateend());
			json.put("ticket_note", ticket.getNote());
			json.put("ticket_meet_amount", ticket.getAmount());
			json.put("ticket_time_given", ticket.getGiventime());
			json.put("ticket_status", ticket.getIsverify());
			json.put("ticket_time_verified", ticket.getVerifytime());
			json.put("ticket_user_id", ticket.getVipid());
			json.put("ticket_password", ticket.getCheckno());
			req.add(json);
		}
		return req;
	}

	@Override
	public Integer modifyTicketPropelStatus(JSONArray tickets) {
		// TODO Auto-generated method stub
		return ticketDao.updateTicketPropelStatus(tickets);
	}

	@Override
	public JSONArray getVerifiedTicketPropel(int count) {
		// TODO Auto-generated method stub
		JSONArray req = new JSONArray();
		List<TicketEntity> tickets = ticketDao.queryVerifiedTicketPropel(count);
		for (TicketEntity ticket : tickets) {
			JSONObject json = new JSONObject();
			json.put("ticket_id", ticket.getId());
			json.put("ticket_no", ticket.getTicketno());
			json.put("ticket_status", 1);
			json.put("ticket_sync_datetime_cav", SystemUtil.getTime());
			json.put("ticket_sync_status_cav", 1);
			json.put("ticket_time_verified", SystemUtil.getTime());
			req.add(json);
		}
		return req;
	}

	@Override
	public Integer modifyVerifiedTicketPropelStatus(JSONArray tickets) {
		// TODO Auto-generated method stub
		return ticketDao.updateVerifiedTicketPropelStatus(tickets);
	}

}
