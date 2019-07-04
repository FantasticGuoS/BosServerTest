package com.sungeon.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.dao.IBaseDao;
import com.sungeon.bos.dao.IOrderDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.OrderEntity;
import com.sungeon.bos.entity.base.ReturnOrderEntity;
import com.sungeon.bos.entity.request.EOrderAddRequest;
import com.sungeon.bos.entity.third.ThirdBackOrder;
import com.sungeon.bos.entity.third.ThirdOrder;
import com.sungeon.bos.service.IOrderService;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private IOrderDao orderDao;

	@Override
	public Integer addEOrder(EOrderAddRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ThirdOrder> saveOrderBatch(JSONArray jsons) {
		// TODO Auto-generated method stub
		List<ThirdOrder> addList = JSONArray.parseArray(jsons.toString(),
				ThirdOrder.class);
		if (!addList.isEmpty()) {
			for (ThirdOrder order : addList) {
				String source_store = order.getTrade_source_store();
				if (null == source_store || source_store.isEmpty()) {
					source_store = baseDao
							.getParamValue("wei.order.defaultstore");
					order.setTrade_source_store(source_store);
				}
				if (!order.getItems().isEmpty())
					orderDao.insertOrderitemBatch(order.getTrade_code(),
							order.getItems());
				else
					continue;
			}
			orderDao.insertOrderBatch(addList);
		}
		return addList;
	}

	@Override
	public BosResult executePThirdOrder() {
		// TODO Auto-generated method stub
		return orderDao.executePThirdOrder();
	}

	@Override
	public List<ThirdBackOrder> saveBackOrderBatch(JSONArray jsons) {
		// TODO Auto-generated method stub
		List<ThirdBackOrder> addList = JSONArray.parseArray(jsons.toString(),
				ThirdBackOrder.class);
		if (!addList.isEmpty()) {
			orderDao.insertBackOrderBatch(addList);
			for (ThirdBackOrder order : addList) {
				if (!order.getItems().isEmpty())
					orderDao.insertBackOrderitemBatch(order.getAfter_code(),
							order.getItems());
				else
					continue;
			}
		}
		return addList;
	}

	@Override
	public BosResult executePThirdBackOrder() {
		// TODO Auto-generated method stub
		return orderDao.executePThirdBackOrder();
	}

	@Override
	public JSONArray getOrderSendStatusPropel(int count) {
		// TODO Auto-generated method stub
		JSONArray req = new JSONArray();
		List<OrderEntity> orders = orderDao.queryOrderSendStatusPropel(count);
		if (!orders.isEmpty()) {
			for (OrderEntity order : orders) {
				JSONObject json = new JSONObject();
				json.put("trade_code", order.getOrdersn());
				json.put("trade_distrtype_code", order.getDistrtype());
				json.put("trade_ship_code", order.getShipcode());
				json.put("trade_ship_name", order.getShipname());
				json.put("trade_logistic_code", order.getLogisticcode());
				req.add(json);
			}
		}
		return req;
	}

	@Override
	public Integer modifyOrderSendStatusPropelStatus(JSONArray orders) {
		// TODO Auto-generated method stub
		if (orders.isEmpty())
			return 0;
		return orderDao.updateOrderSendStatusPropelStatus(orders);
	}

	@Override
	public JSONArray getBackOrderInStatusPropel(int count) {
		// TODO Auto-generated method stub
		JSONArray req = new JSONArray();
		List<ReturnOrderEntity> orders = orderDao
				.queryBackOrderInStatusPropel(count);
		for (ReturnOrderEntity order : orders) {
			JSONObject json = new JSONObject();
			json.put("after_code", order.getReturnOrdersn());
			req.add(json);
		}
		return req;
	}

	@Override
	public Integer modifyBackOrderInStatusPropelStatus(JSONArray orders) {
		// TODO Auto-generated method stub
		return orderDao.updateBackOrderInStatusPropelStatus(orders);
	}

}
