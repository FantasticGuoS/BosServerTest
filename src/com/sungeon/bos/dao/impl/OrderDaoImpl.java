package com.sungeon.bos.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.dao.IOrderDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.OrderEntity;
import com.sungeon.bos.entity.base.ReturnOrderEntity;
import com.sungeon.bos.entity.third.ThirdBackOrder;
import com.sungeon.bos.entity.third.ThirdBackOrderitem;
import com.sungeon.bos.entity.third.ThirdOrder;
import com.sungeon.bos.entity.third.ThirdOrderitem;
import com.sungeon.bos.mapper.IOrderMapper;
import com.sungeon.bos.util.SystemProperties;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl implements IOrderDao {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private IOrderMapper orderMapper;

	@Override
	public Integer insertOrderBatch(List<ThirdOrder> addList) {
		// TODO Auto-generated method stub
		return orderMapper.insertOrderBatch(addList, SystemProperties.Brand);
	}

	@Override
	public Integer insertOrderitemBatch(String tradecode, List<ThirdOrderitem> items) {
		// TODO Auto-generated method stub
		return orderMapper.insertOrderitemBatch(tradecode, items, SystemProperties.Brand);
	}

	@Override
	public BosResult executePThirdOrder() {
		// TODO Auto-generated method stub
		BosResult result = new BosResult();
		try {
			orderMapper.executePThirdOrder(SystemProperties.Brand);
			result.setCode(1);
			result.setMessage("SUCCESS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			result.setCode(-1);
			result.setMessage(getOracleExceptionMessage(e.getMessage()));
		}
		return result;
	}

	@Override
	public Integer insertBackOrderBatch(List<ThirdBackOrder> addList) {
		// TODO Auto-generated method stub
		return orderMapper.insertBackOrderBatch(addList, SystemProperties.Brand);
	}

	@Override
	public Integer insertBackOrderitemBatch(String aftercode, List<ThirdBackOrderitem> items) {
		// TODO Auto-generated method stub
		return orderMapper.insertBackOrderitemBatch(aftercode, items, SystemProperties.Brand);
	}

	@Override
	public BosResult executePThirdBackOrder() {
		// TODO Auto-generated method stub
		BosResult result = new BosResult();
		try {
			orderMapper.executePThirdBackOrder(SystemProperties.Brand);
			result.setCode(1);
			result.setMessage("SUCCESS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			result.setCode(-1);
			result.setMessage(getOracleExceptionMessage(e.getMessage()));
		}
		return result;
	}

	@Override
	public List<OrderEntity> queryOrderSendStatusPropel(int count) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderSendStatusPropel(count, SystemProperties.Brand);
	}

	@Override
	public Integer updateOrderSendStatusPropelStatus(JSONArray orders) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderSendStatusPropelStatus(orders);
	}

	@Override
	public List<ReturnOrderEntity> queryBackOrderInStatusPropel(int count) {
		// TODO Auto-generated method stub
		return orderMapper.queryBackOrderInStatusPropel(count, SystemProperties.Brand);
	}

	@Override
	public Integer updateBackOrderInStatusPropelStatus(JSONArray orders) {
		// TODO Auto-generated method stub
		return orderMapper.updateBackOrderInStatusPropelStatus(orders);
	}

}
