package com.sungeon.bos.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sungeon.bos.entity.base.RetailEntity;
import com.sungeon.bos.entity.request.RetailGetRequest;
import com.sungeon.bos.service.IRetailService;

@Service("retailService")
@Transactional
public class RetailServiceImpl implements IRetailService {

	@Override
	public List<RetailEntity> getRetail(RetailGetRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
