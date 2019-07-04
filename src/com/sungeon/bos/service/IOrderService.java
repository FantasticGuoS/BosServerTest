package com.sungeon.bos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.request.EOrderAddRequest;
import com.sungeon.bos.entity.third.ThirdBackOrder;
import com.sungeon.bos.entity.third.ThirdOrder;

@Service
public interface IOrderService {

	public Integer addEOrder(EOrderAddRequest request);

	public List<ThirdOrder> saveOrderBatch(JSONArray jsons);

	public BosResult executePThirdOrder();

	public List<ThirdBackOrder> saveBackOrderBatch(JSONArray jsons);

	public BosResult executePThirdBackOrder();

	public JSONArray getOrderSendStatusPropel(int count);

	public Integer modifyOrderSendStatusPropelStatus(JSONArray orders);

	public JSONArray getBackOrderInStatusPropel(int count);

	public Integer modifyBackOrderInStatusPropelStatus(JSONArray orders);

}
