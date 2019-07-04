package com.sungeon.bos.port.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;

import com.sungeon.bos.entity.base.ProductEntity;
import com.sungeon.bos.entity.base.StorageEntity;
import com.sungeon.bos.entity.request.ProductGetRequest;
import com.sungeon.bos.entity.request.StorageGetRequest;
import com.sungeon.bos.entity.response.ProductGetResponse;
import com.sungeon.bos.entity.response.StorageGetResponse;
import com.sungeon.bos.exception.ParamException;
import com.sungeon.bos.exception.ParamInvalidException;
import com.sungeon.bos.exception.ParamNotMatchedException;
import com.sungeon.bos.exception.SungeonException;
import com.sungeon.bos.port.Product;
import com.sungeon.bos.service.IProductService;
import com.sungeon.bos.util.SystemProperties;

@Controller("product")
public class ProductImpl extends Base implements Product {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IProductService productService;

	@Override
	public ProductGetResponse getPorduct(ProductGetRequest request) {
		// TODO Auto-generated method stub
		ProductGetResponse response = new ProductGetResponse();
		try {
			int code = verify(request, response, SystemProperties.MethodProductGet);
			if (code != 100)
				return response;
			List<ProductEntity> result = productService.getProduct(request);
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
	public StorageGetResponse getStorage(StorageGetRequest request) {
		// TODO Auto-generated method stub
		StorageGetResponse response = new StorageGetResponse();
		try {
			int code = verify(request, response, SystemProperties.MethodStorageGet);
			if (code != 100)
				return response;
			List<StorageEntity> result = productService.getStorage(request);
			response.setResult(result);
		} catch (ParamInvalidException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			exception(response, 220, e.getError(), e.getMessage());
		} catch (ParamNotMatchedException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			exception(response, 300, e.getError(), e.getMessage());
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
