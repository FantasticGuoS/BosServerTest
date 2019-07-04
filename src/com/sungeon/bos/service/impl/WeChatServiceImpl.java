package com.sungeon.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sungeon.bos.dao.IWeChatDao;
import com.sungeon.bos.entity.base.WeChatMessageEntity;
import com.sungeon.bos.service.IWeChatService;

@Service("weChatService")
@Transactional
public class WeChatServiceImpl implements IWeChatService {

	@Autowired
	private IWeChatDao weChatDao;

	@Override
	public List<WeChatMessageEntity> getWeChatMessagePropel() {
		// TODO Auto-generated method stub
		return weChatDao.queryWeChatMessagePropel();
	}

	@Override
	public Integer modifyWeChatMessagePropelStatus(List<WeChatMessageEntity> messages) {
		// TODO Auto-generated method stub
		if (messages.isEmpty())
			return 0;
		return weChatDao.updateWeChatMessagePropelStatus(messages);
	}

}
