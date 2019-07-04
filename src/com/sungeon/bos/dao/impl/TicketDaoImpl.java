package com.sungeon.bos.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.dao.ITicketDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.TicketEntity;
import com.sungeon.bos.entity.third.ThirdTicket;
import com.sungeon.bos.mapper.ITicketMapper;
import com.sungeon.bos.util.SystemProperties;

@Repository("ticketDao")
public class TicketDaoImpl extends BaseDaoImpl implements ITicketDao {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ITicketMapper ticketMapper;

	@Override
	public Integer insertTicketBatch(List<ThirdTicket> addList) {
		// TODO Auto-generated method stub
		return ticketMapper.insertTicketBatch(addList, SystemProperties.Brand);
	}

	@Override
	public BosResult executePThirdTicket() {
		// TODO Auto-generated method stub
		BosResult result = new BosResult();
		try {
			ticketMapper.executePThirdTicket(SystemProperties.Brand);
			result.setCode(1);
			result.setMessage("SUCCESS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			result.setCode(-1);
			result.setMessage(getOracleExceptionMessage(e.getMessage()));
		}
		return result;
	}

	@Override
	public List<TicketEntity> queryTicketPropel(int count) {
		// TODO Auto-generated method stub
		return ticketMapper.queryTicketPropel(count, SystemProperties.Brand);
	}

	@Override
	public Integer updateTicketPropelStatus(JSONArray tickets) {
		// TODO Auto-generated method stub
		return ticketMapper.updateTicketPropelStatus(tickets);
	}

	@Override
	public List<TicketEntity> queryVerifiedTicketPropel(int count) {
		// TODO Auto-generated method stub
		return ticketMapper.queryVerifiedTicketPropel(count, SystemProperties.Brand);
	}

	@Override
	public Integer updateVerifiedTicketPropelStatus(JSONArray tickets) {
		// TODO Auto-generated method stub
		return ticketMapper.updateVerifiedTicketPropelStatus(tickets);
	}

}
