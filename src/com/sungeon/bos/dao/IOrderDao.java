package com.sungeon.bos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.OrderEntity;
import com.sungeon.bos.entity.base.ReturnOrderEntity;
import com.sungeon.bos.entity.third.ThirdBackOrder;
import com.sungeon.bos.entity.third.ThirdBackOrderitem;
import com.sungeon.bos.entity.third.ThirdOrder;
import com.sungeon.bos.entity.third.ThirdOrderitem;

@Repository
public interface IOrderDao {

	public Integer insertOrderBatch(List<ThirdOrder> addList);

	public Integer insertOrderitemBatch(String tradecode, List<ThirdOrderitem> items);

	public BosResult executePThirdOrder();

	public Integer insertBackOrderBatch(List<ThirdBackOrder> addList);

	public Integer insertBackOrderitemBatch(String aftercode, List<ThirdBackOrderitem> items);

	public BosResult executePThirdBackOrder();

	public List<OrderEntity> queryOrderSendStatusPropel(int count);

	public Integer updateOrderSendStatusPropelStatus(JSONArray orders);

	public List<ReturnOrderEntity> queryBackOrderInStatusPropel(int count);

	public Integer updateBackOrderInStatusPropelStatus(JSONArray orders);

}
