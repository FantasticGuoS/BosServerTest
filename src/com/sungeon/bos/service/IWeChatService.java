package com.sungeon.bos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sungeon.bos.entity.base.WeChatMessageEntity;

@Service
public interface IWeChatService {

	public List<WeChatMessageEntity> getWeChatMessagePropel();

	public Integer modifyWeChatMessagePropelStatus(List<WeChatMessageEntity> messages);

}
