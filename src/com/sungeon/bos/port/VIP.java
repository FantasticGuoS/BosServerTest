package com.sungeon.bos.port;

import javax.jws.WebService;

import com.sungeon.bos.entity.request.VIPAddRequest;
import com.sungeon.bos.entity.request.VIPGetRequest;
import com.sungeon.bos.entity.response.BaseResponse;
import com.sungeon.bos.entity.response.VIPGetResponse;

@WebService
public interface VIP {

	public VIPGetResponse getVIP(VIPGetRequest request);

	public BaseResponse addVIP(VIPAddRequest request);

}
