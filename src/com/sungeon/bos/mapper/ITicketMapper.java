package com.sungeon.bos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.base.TicketEntity;
import com.sungeon.bos.entity.third.ThirdTicket;

@Repository
public interface ITicketMapper {

	public Integer insertTicketBatch(@Param("list") List<ThirdTicket> addList, @Param("brand") String brand);

	public Map<String, Object> executePThirdTicket(@Param("brand") String brand);

	public List<TicketEntity> queryTicketPropel(@Param("count") int count, @Param("brand") String brand);

	public Integer updateTicketPropelStatus(@Param("tickets") JSONArray tickets);

	public List<TicketEntity> queryVerifiedTicketPropel(@Param("count") int count, @Param("brand") String brand);

	public Integer updateVerifiedTicketPropelStatus(@Param("tickets") JSONArray tickets);

}
