package com.sungeon.bos.port;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sungeon.bos.entity.response.BaseResponse;

@Path("/restful")
@Produces(MediaType.APPLICATION_JSON)
public interface Restful {

	@GET
	@Path("/resp/{code}")
	public BaseResponse testrestful(@PathParam("code") Integer code);

}
