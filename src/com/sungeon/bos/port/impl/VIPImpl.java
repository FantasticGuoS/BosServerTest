package com.sungeon.bos.port.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;

import com.sungeon.bos.entity.base.VIPEntity;
import com.sungeon.bos.entity.request.VIPAddRequest;
import com.sungeon.bos.entity.request.VIPGetRequest;
import com.sungeon.bos.entity.response.BaseResponse;
import com.sungeon.bos.entity.response.VIPGetResponse;
import com.sungeon.bos.exception.ParamException;
import com.sungeon.bos.exception.SungeonException;
import com.sungeon.bos.port.VIP;
import com.sungeon.bos.service.IVIPService;
import com.sungeon.bos.util.SystemProperties;

@Controller("vip")
public class VIPImpl extends Base implements VIP {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IVIPService vipService;

	@Override
	public VIPGetResponse getVIP(VIPGetRequest request) {
		// TODO Auto-generated method stub
		VIPGetResponse response = new VIPGetResponse();
		try {
			int code = verify(request, response, SystemProperties.MethodVIPGet);
			if (code != 100)
				return response;
			List<VIPEntity> result = vipService.getVIP(request);
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

	@Override
	public BaseResponse addVIP(VIPAddRequest request) {
		// TODO Auto-generated method stub
		BaseResponse response = new BaseResponse();
		try {
			int code = verify(request, response, SystemProperties.MethodVIPGet);
			if (code != 100)
				return response;
			Integer count = vipService.addVIP(request);
			response.setCount(count);
			response.getDetail().setDesc("VIPs added success");
			response.getDetail().setDescription("会员新增成功");
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
