package com.sungeon.bos.port.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;

import com.sungeon.bos.entity.request.EOrderAddRequest;
import com.sungeon.bos.entity.response.EOrderAddResponse;
import com.sungeon.bos.exception.ParamException;
import com.sungeon.bos.exception.SungeonException;
import com.sungeon.bos.port.Order;
import com.sungeon.bos.service.IOrderService;
import com.sungeon.bos.util.SystemProperties;

@Controller("order")
public class OrderImpl extends Base implements Order {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IOrderService orderService;

	@Override
	public EOrderAddResponse addEOrder(EOrderAddRequest request) {
		// TODO Auto-generated method stub
		EOrderAddResponse response = new EOrderAddResponse();
		try {
			int code = verify(request, response, SystemProperties.MethodEOrderAdd);
			if (code != 100)
				return response;
			orderService.addEOrder(request);
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
