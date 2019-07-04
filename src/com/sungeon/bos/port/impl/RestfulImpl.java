package com.sungeon.bos.port.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Component;

import com.sungeon.bos.entity.response.BaseResponse;
import com.sungeon.bos.port.Restful;

@Component("/restful")
public class RestfulImpl implements Restful {

	@Override
	@GET
	@Path("/resp/{code}")
	public BaseResponse testrestful(@PathParam("code") Integer code) {
		// TODO Auto-generated method stub
		BaseResponse resp = new BaseResponse();
		resp.setCode(code);
		resp.getDetail().setDesc("HAHA");
		resp.getDetail().setDescription("好笑嘛");
		return resp;
	}

}
