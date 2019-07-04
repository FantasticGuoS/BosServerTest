package com.sungeon.bos.port.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;

import com.sungeon.bos.entity.base.TicketEntity;
import com.sungeon.bos.entity.request.TicketGetRequest;
import com.sungeon.bos.entity.response.TicketGetResponse;
import com.sungeon.bos.exception.ParamException;
import com.sungeon.bos.exception.SungeonException;
import com.sungeon.bos.port.Ticket;
import com.sungeon.bos.service.ITicketService;
import com.sungeon.bos.util.SystemProperties;

@Controller("ticket")
public class TicketImpl extends Base implements Ticket {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ITicketService ticketService;

	@Override
	public TicketGetResponse getTicket(TicketGetRequest request) {
		// TODO Auto-generated method stub
		TicketGetResponse response = new TicketGetResponse();
		try {
			int code = verify(request, response, SystemProperties.MethodTicketGet);
			if (code != 100)
				return response;
			List<TicketEntity> result = ticketService.getTicket(request);
			response.setResult(result);
		} catch (ParamException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			exception(response, 220, e.getError(), e.getMessage());
		} catch (SungeonException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			exception(response, 300, "", e.getMessage());
		} catch (CannotCreateTransactionException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			exception(response, 400, "Could not open JDBC Connection", "数据库链接失败");
		} catch (BadSqlGrammarException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			exception(response, 400, "SQL Syntax Error", "SQL语句异常");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			exception(response, 400, "", e.getMessage());
		}
		return response;
	}

}
