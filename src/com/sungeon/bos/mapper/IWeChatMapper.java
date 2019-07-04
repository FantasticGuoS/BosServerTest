package com.sungeon.bos.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sungeon.bos.entity.base.WeChatMessageEntity;

@Repository
public interface IWeChatMapper {

	public List<WeChatMessageEntity> queryWeChatMessagePropel(@Param("brand") String brand,
			@Param("count") int count);

	public Integer updateWeChatMessagePropelStatus(
			@Param("lists") List<WeChatMessageEntity> messages);

}
