package com.sungeon.bos.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sungeon.bos.dao.IWeChatDao;
import com.sungeon.bos.entity.base.WeChatMessageEntity;
import com.sungeon.bos.mapper.IWeChatMapper;
import com.sungeon.bos.util.SystemProperties;

@Repository("weChatDao")
public class WeChatDaoImpl extends BaseDaoImpl implements IWeChatDao {

	@Resource
	private IWeChatMapper wechatMapper;

	@Override
	public List<WeChatMessageEntity> queryWeChatMessagePropel() {
		// TODO Auto-generated method stub
		return wechatMapper.queryWeChatMessagePropel(SystemProperties.Brand,
				SystemProperties.ParamPropelDataCount);
	}

	@Override
	public Integer updateWeChatMessagePropelStatus(List<WeChatMessageEntity> messages) {
		// TODO Auto-generated method stub
		return wechatMapper.updateWeChatMessagePropelStatus(messages);
	}

}
