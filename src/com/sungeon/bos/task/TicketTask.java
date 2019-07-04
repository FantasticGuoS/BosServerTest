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
import com.sungeon.bos.entity.third.ThirdTicket;
import com.sungeon.bos.service.ITicketService;
import com.sungeon.bos.util.SystemProperties;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class TicketTask extends BaseTask {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ITicketService ticketService;

	/**
	 * 抓取优惠券
	 */
	// @Scheduled(cron = "${Param.CronExpression.Ticket.Get}")
	public void getTicket() {
		try {
			JSONObject req = null;
			while (true) {
				req = new JSONObject();
				req.put("page_size", SystemProperties.ParamPropelDataCount);
				log.info("抓取线上优惠券参数：" + req);
				JSONObject resp = yunhuanHttpRequest("yunhuan.coupon.list", req);
				log.info("抓取线上优惠券返回数据：" + resp);
				List<ThirdTicket> addList = new ArrayList<ThirdTicket>();
				if (resp.getInteger("Code") == 1)
					addList = ticketService.saveTicketBatch(resp
							.getJSONArray("Data"));
				else
					log.warn("抓取线上优惠券失败：" + resp.getString("Desc"));

				// 回写
				if (!addList.isEmpty()) {
					// &ticket_no[i]=
					req = new JSONObject();
					JSONArray tickets = new JSONArray();
					for (ThirdTicket ticket : addList)
						tickets.add(ticket.getTicket_no());
					req.put("ticket_no", tickets);
					log.info("回传抓取线上优惠券成功参数：" + req);
					yunhuanHttpRequest("yunhuan.coupon.listcb", req);
					log.info("抓取线上优惠券成功个数：" + addList.size());
				}

				if (addList.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
			// 执行存储过程
			ticketService.executePThirdTicket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送优惠券
	 */
	// @Scheduled(cron = "${Param.CronExpression.Ticket.Propel}")
	public void propelTicket() {
		try {
			JSONArray tickets = null;
			JSONObject resp = new JSONObject();
			while (true) {
				tickets = ticketService
						.getTicketPropel(SystemProperties.ParamPropelDataCount);
				if (!tickets.isEmpty()) {
					log.info("推送优惠券至线上参数：" + tickets);
					resp = yunhuanHttpRequest("yunhuan.coupon.add", tickets);
					log.info("推送优惠券至线上返回值：" + resp);
					if (resp.getInteger("Code") == 1) {
						Integer count = ticketService
								.modifyTicketPropelStatus(tickets);
						log.info("推送优惠券至线上成功条数：" + count);
					} else {
						log.warn("推送优惠券至线上失败：" + resp.getString("Desc"));
					}
				}

				if (tickets.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送已核销优惠券
	 */
	// @Scheduled(cron = "${Param.CronExpression.TicketVerify.Propel}")
	public void propelVerifiedTicket() {
		try {
			JSONArray tickets = null;
			JSONObject resp = new JSONObject();
			while (true) {
				tickets = ticketService
						.getVerifiedTicketPropel(SystemProperties.ParamPropelDataCount);
				if (!tickets.isEmpty()) {
					log.info("推送已核销优惠券至线上参数：" + tickets);
					resp = yunhuanHttpRequest("yunhuan.coupon.writeoff",
							tickets);
					log.info("推送已核销优惠券至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						Integer count = ticketService
								.modifyVerifiedTicketPropelStatus(tickets);
						log.info("推送已核销优惠券至线上成功条数：" + count);
					} else {
						log.warn("推送已核销优惠券至线上失败：" + resp.getString("Desc"));
					}
				}

				if (tickets.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

}
