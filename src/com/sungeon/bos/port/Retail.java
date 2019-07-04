package com.sungeon.bos.port;

import javax.jws.WebService;

import com.sungeon.bos.entity.request.RetailGetRequest;
import com.sungeon.bos.entity.response.RetailGetResponse;

@WebService
public interface Retail {

	public RetailGetResponse getRetail(RetailGetRequest request);

}
