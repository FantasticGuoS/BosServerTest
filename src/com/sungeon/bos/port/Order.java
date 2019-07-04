package com.sungeon.bos.port;

import javax.jws.WebService;

import com.sungeon.bos.entity.request.EOrderAddRequest;
import com.sungeon.bos.entity.response.EOrderAddResponse;

@WebService
public interface Order {

	public EOrderAddResponse addEOrder(EOrderAddRequest request);

}
