package com.sungeon.bos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sungeon.bos.entity.base.WeChatMessageEntity;

@Repository
public interface IWeChatDao {

	public List<WeChatMessageEntity> queryWeChatMessagePropel();

	public Integer updateWeChatMessagePropelStatus(List<WeChatMessageEntity> messages);

}
