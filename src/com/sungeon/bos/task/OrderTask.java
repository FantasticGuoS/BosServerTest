package com.sungeon.bos.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.third.ThirdBackOrder;
import com.sungeon.bos.entity.third.ThirdOrder;
import com.sungeon.bos.service.IOrderService;
import com.sungeon.bos.util.SystemProperties;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class OrderTask extends BaseTask {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private IOrderService orderService;

	/**
	 * 抓取订单
	 */
	// @Scheduled(cron = "${Param.CronExpression.Order.Get}")
	public void getOrder() {
		try {
			JSONObject req = null;
			while (true) {
				req = new JSONObject();
				req.put("page_size", SystemProperties.ParamPropelDataCount);
				log.info("抓取线上订单参数：" + req);
				JSONObject resp = yunhuanHttpRequest("yunhuan.trade.list", req);
				log.info("抓取线上订单返回数据：" + resp);
				List<ThirdOrder> addList = new ArrayList<ThirdOrder>();
				if (resp.getInteger("Code") == 1)
					addList = orderService.saveOrderBatch(resp
							.getJSONArray("Data"));
				else
					log.warn("抓取线上订单失败：" + resp.getString("Desc"));

				// 回写
				if (!addList.isEmpty()) {
					// &trade_code[i]=
					req = new JSONObject();
					JSONArray orders = new JSONArray();
					for (ThirdOrder order : addList)
						orders.add(order.getTrade_code());
					req.put("trade_code", orders);
					log.info("回传抓取线上订单成功参数：" + req);
					yunhuanHttpRequest("yunhuan.trade.listcb", req);
					log.info("抓取线上订单至线下成功个数：" + addList.size());
				}

				if (addList.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
			// 执行存储过程
			orderService.executePThirdOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 抓取售后单
	 */
	// @Scheduled(cron = "${Param.CronExpression.BackOrder.Get}")
	public void getBackOrder() {
		try {
			JSONObject req = null;
			while (true) {
				req = new JSONObject();
				req.put("page_size", SystemProperties.ParamPropelDataCount);
				log.info("抓取线上售后单参数：" + req);
				JSONObject resp = yunhuanHttpRequest("yunhuan.saleAfter.list",
						req);
				log.info("抓取线上售后单返回数据：" + resp);
				List<ThirdBackOrder> addList = new ArrayList<ThirdBackOrder>();
				if (resp.getInteger("Code") == 1)
					addList = orderService.saveBackOrderBatch(resp
							.getJSONArray("Data"));
				else
					log.warn("抓取线上售后单失败：" + resp.getString("Desc"));

				// 回写
				if (!addList.isEmpty()) {
					// &after_code[i]=
					req = new JSONObject();
					JSONArray orders = new JSONArray();
					for (ThirdBackOrder order : addList)
						orders.add(order.getAfter_code());
					req.put("after_code", orders);
					log.info("回传抓取线上售后单成功参数：" + req);
					yunhuanHttpRequest("yunhuan.saleAfter.listcb", req);
					log.info("抓取线上售后单至线下成功个数：" + addList.size());
				}

				if (addList.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
			// 执行存储过程
			orderService.executePThirdBackOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送订单发货状态
	 */
	// @Scheduled(cron = "${Param.CronExpression.OrderOutStatus.Propel}")
	public void propelOrderOutStatus() {
		try {
			JSONArray orders = null;
			JSONObject resp = new JSONObject();
			while (true) {
				orders = orderService
						.getOrderSendStatusPropel(SystemProperties.ParamPropelDataCount);
				if (!orders.isEmpty()) {
					log.info("推送订单发货状态至线上参数：" + orders);
					resp = yunhuanHttpRequest("yunhuan.trade.shipping", orders);
					log.info("推送订单发货状态至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						// 修改推送状态
						Integer count = orderService
								.modifyOrderSendStatusPropelStatus(orders);
						log.info("推送订单发货状态至线上成功条数：" + count);
					} else {
						log.info("推送订单发货状态至线上失败：" + resp.getString("Desc"));
					}
				}

				if (orders.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送售后单收货状态
	 */
	// @Scheduled(cron = "${Param.CronExpression.BackOrderInStatus.Propel}")
	public void propelBackOrderInStatus() {
		try {
			JSONArray orders = null;
			JSONObject resp = new JSONObject();
			while (true) {
				orders = orderService
						.getBackOrderInStatusPropel(SystemProperties.ParamPropelDataCount);
				if (!orders.isEmpty()) {
					log.info("推送退货订单编号至线上参数:" + orders);
					resp = yunhuanHttpRequest("yunhuan.saleAfter.receive",
							orders);
					log.info("推送退货订单单据编号至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						orderService
								.modifyBackOrderInStatusPropelStatus(orders);
						log.info("推送退货订单单据编号至线上成功");
					} else {
						log.info("推送退货订单单据编号至线上失败");
					}
				}

				if (orders.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

}
