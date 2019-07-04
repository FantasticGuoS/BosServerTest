package com.sungeon.bos.port.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;

import com.sungeon.bos.entity.base.RetailEntity;
import com.sungeon.bos.entity.request.RetailGetRequest;
import com.sungeon.bos.entity.response.RetailGetResponse;
import com.sungeon.bos.exception.ParamException;
import com.sungeon.bos.exception.SungeonException;
import com.sungeon.bos.port.Retail;
import com.sungeon.bos.service.IRetailService;
import com.sungeon.bos.util.SystemProperties;

@Controller("retail")
public class RetailImpl extends Base implements Retail {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IRetailService retailService;

	@Override
	public RetailGetResponse getRetail(RetailGetRequest request) {
		// TODO Auto-generated method stub
		RetailGetResponse response = new RetailGetResponse();
		try {
			int code = verify(request, response, SystemProperties.MethodRetailGet);
			if (code != 100)
				return response;
			List<RetailEntity> result = retailService.getRetail(request);
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
