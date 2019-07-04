package com.sungeon.bos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sungeon.bos.entity.base.RetailEntity;
import com.sungeon.bos.entity.request.RetailGetRequest;

@Service
public interface IRetailService {

	public List<RetailEntity> getRetail(RetailGetRequest request);

}
