package com.sungeon.bos.service.sd.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungeon.bos.dao.IBaseDao;
import com.sungeon.bos.service.sd.ISDBaseService;

@Service("sdBaseService")
public class SDBaseServiceImpl implements ISDBaseService {
	
	@Autowired
	private IBaseDao baseDao;

	@Override
	public String getThirdTime(String type) {
		// TODO Auto-generated method stub
		return baseDao.queryThirdTime(type);
	}
	
}
