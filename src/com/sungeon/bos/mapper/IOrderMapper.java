package com.sungeon.bos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.base.OrderEntity;
import com.sungeon.bos.entity.base.ReturnOrderEntity;
import com.sungeon.bos.entity.third.ThirdOrder;
import com.sungeon.bos.entity.third.ThirdOrderitem;
import com.sungeon.bos.entity.third.ThirdBackOrder;
import com.sungeon.bos.entity.third.ThirdBackOrderitem;

@Repository
public interface IOrderMapper {

	public Integer insertOrderBatch(@Param("list") List<ThirdOrder> addList, @Param("brand") String brand);

	public Integer insertOrderitemBatch(@Param("tradecode") String tradecode,
			@Param("items") List<ThirdOrderitem> items, @Param("brand") String brand);

	public Map<String, Object> executePThirdOrder(@Param("brand") String brand);

	public Integer insertBackOrderBatch(@Param("list") List<ThirdBackOrder> addList, @Param("brand") String brand);

	public Integer insertBackOrderitemBatch(@Param("aftercode") String aftercode,
			@Param("items") List<ThirdBackOrderitem> items, @Param("brand") String brand);

	public Map<String, Object> executePThirdBackOrder(@Param("brand") String brand);

	public List<OrderEntity> queryOrderSendStatusPropel(@Param("count") int count, @Param("brand") String brand);

	public Integer updateOrderSendStatusPropelStatus(@Param("orders") JSONArray orders);

	public List<ReturnOrderEntity> queryBackOrderInStatusPropel(@Param("count") int count,
			@Param("brand") String brand);

	public Integer updateBackOrderInStatusPropelStatus(@Param("orders") JSONArray orders);

}
