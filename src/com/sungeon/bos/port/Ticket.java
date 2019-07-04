package com.sungeon.bos.port;

import javax.jws.WebService;

import com.sungeon.bos.entity.request.TicketGetRequest;
import com.sungeon.bos.entity.response.TicketGetResponse;

@WebService
public interface Ticket {

	public TicketGetResponse getTicket(TicketGetRequest request);

}
