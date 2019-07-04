/**
 * Created by GuoS on 2017/8/1.
 */
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
import com.sungeon.bos.entity.third.ThirdVIP;
import com.sungeon.bos.entity.third.ThirdVIPBalanceFTP;
import com.sungeon.bos.entity.third.ThirdVIPIntegralFTP;
import com.sungeon.bos.service.IVIPService;
import com.sungeon.bos.util.SystemProperties;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class VIPTask extends BaseTask {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IVIPService vipService;

	/**
	 * 抓取线上会员
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIP.Get}")
	public void getVIP() {
		try {
			JSONObject req = null;
			while (true) {
				req = new JSONObject();
				req.put("page_size", SystemProperties.ParamPropelDataCount);
				log.info("抓取线上VIP参数：" + req);
				JSONObject resp = yunhuanHttpRequest("yunhuan.user.list", req);
				log.info("抓取线上VIP返回数据：" + resp);
				List<ThirdVIP> addList = new ArrayList<ThirdVIP>();
				if (resp.getInteger("Code") == 1)
					addList = vipService
							.saveVIPBatch(resp.getJSONArray("Data"));
				else
					log.warn("抓取线上VIP失败：" + resp.getString("Desc"));

				// 回写
				if (!addList.isEmpty()) {
					// user_id[i]=
					req = new JSONObject();
					JSONArray vips = new JSONArray();
					for (ThirdVIP vip : addList)
						vips.add(vip.getUser_id());
					req.put("user_id", vips);
					log.info("回传抓取线上VIP成功参数：" + req);
					yunhuanHttpRequest("yunhuan.user.listcb", req);
					log.info("抓取线上VIP至线下成功个数：" + addList.size());
				}

				if (addList.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
			// 执行存储过程
			vipService.executePThirdVIP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 抓取会员积分流水
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIPIntegralFTP.Get}")
	public void getVIPIntegralFTP() {
		try {
			JSONObject req = null;
			while (true) {
				req = new JSONObject();
				req.put("page_size", SystemProperties.ParamPropelDataCount);
				log.info("抓取线上VIP积分变动流水参数：" + req);
				JSONObject resp = yunhuanHttpRequest("yunhuan.integral.list",
						req);
				log.info("抓取线上VIP积分变动流水返回数据：" + resp);
				List<ThirdVIPIntegralFTP> addList = new ArrayList<ThirdVIPIntegralFTP>();
				if (resp.getInteger("Code") == 1)
					addList = vipService.saveVIPIntegralFTPBatch(resp
							.getJSONArray("Data"));
				else
					log.warn("抓取线上VIP积分变动流水失败：" + resp.getString("Desc"));

				// 回写
				if (!addList.isEmpty()) {
					// integral_id[i]=
					req = new JSONObject();
					JSONArray integrals = new JSONArray();
					for (ThirdVIPIntegralFTP integral : addList)
						integrals.add(integral.getIntegral_id());
					req.put("integral_id", integrals);
					log.info("回传抓取线上VIP积分变动流水成功参数：" + req);
					yunhuanHttpRequest("yunhuan.integral.listcb", req);
					log.info("抓取线上VIP积分变动流水成功个数：" + addList.size());
				}

				if (addList.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
			// 执行存储过程
			vipService.executePThirdVIPIntegralFTP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 抓取会员可用余额流水
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIPBalanceFTP.Get}")
	public void getVIPBalanceFTP() {
		try {
			JSONObject req = null;
			while (true) {
				req = new JSONObject();
				req.put("page_size", SystemProperties.ParamPropelDataCount);
				log.info("抓取线上VIP可用余额变动流水参数：" + req);
				JSONObject resp = yunhuanHttpRequest("yunhuan.balance.list",
						req);
				log.info("抓取线上VIP可用余额变动流水返回数据：" + resp);
				List<ThirdVIPBalanceFTP> addList = new ArrayList<ThirdVIPBalanceFTP>();
				if (resp.getInteger("Code") == 1)
					addList = vipService.saveVIPBalanceFTPBatch(resp
							.getJSONArray("Data"));
				else
					log.warn("抓取线上VIP可用余额变动流水失败：" + resp.getString("Desc"));

				// 回写
				if (!addList.isEmpty()) {
					// balance_id[i]=
					req = new JSONObject();
					JSONArray balances = new JSONArray();
					for (ThirdVIPBalanceFTP balance : addList)
						balances.add(balance.getBalance_id());
					req.put("balance_id", balances);
					log.info("回传抓取线上VIP可用余额变动流水成功参数：" + req);
					yunhuanHttpRequest("yunhuan.balance.listcb", req);
					log.info("抓取线上VIP可用余额变动流水成功个数：" + addList.size());
				}

				if (addList.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
			// 执行存储过程
			vipService.executePThirdVIPBalanceFTP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送会员
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIP.Propel}")
	public void propelVIP() {
		try {
			JSONArray vips = null;
			JSONObject resp = new JSONObject();
			while (true) {
				// 更改为根据标志位去抓取需要推送至线上的VIP，且只抓evipid不为空的
				vips = vipService
						.getVIPPropel(SystemProperties.ParamPropelDataCount);
				if (!vips.isEmpty()) {
					log.info("推送VIP至线上参数为：" + vips);
					resp = yunhuanHttpRequest("yunhuan.user.update", vips);
					log.info("推送VIP至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						// 将会员的标志位更改为已推送
						Integer count = vipService.modifyVIPPropelStatus(vips);
						log.info("推送VIP至线上成功条数：" + count);
					} else {
						log.warn("推送VIP至线上失败：" + resp.getString("Desc"));
					}
				}

				if (vips.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送会员积分
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIPIntegral.Propel}")
	public void propelVIPIntegral() {
		try {
			JSONArray accounts = null;
			JSONObject resp = new JSONObject();
			while (true) {
				// 更改为推送当前积分，抓取根据积分变动推送状态为0（只要线下积分变动后台变更为0）
				accounts = vipService
						.getVIPIntegralPropel(SystemProperties.ParamPropelDataCount);
				if (!accounts.isEmpty()) {
					log.info("推送VIP当前积分至线上参数：" + accounts);
					resp = yunhuanHttpRequest("yunhuan.user.integral", accounts);
					log.info("推送VIP当前积分至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						Integer count = vipService
								.modifyVIPIntegralPropelStatus(accounts);
						log.info("推送VIP当前积分至线上成功条数：" + count);
					} else {
						log.warn("推送VIP当前积分至线上失败：" + resp.getString("Desc"));
					}
				}

				if (accounts.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送会员可用余额
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIPBalance.Propel}")
	public void propelVIPBalance() {
		try {
			JSONArray accounts = null;
			JSONObject resp = new JSONObject();
			while (true) {
				// 更改为推送当前可用余额，抓取根据可用余额变动推送状态为0（只要线下积分变动后台变更为0）
				accounts = vipService
						.getVIPBalancePropel(SystemProperties.ParamPropelDataCount);
				if (!accounts.isEmpty()) {
					log.info("推送VIP当前可用余额至线上参数：" + accounts);
					resp = yunhuanHttpRequest("yunhuan.user.integral", accounts);
					log.info("推送VIP当前可用余额至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						Integer count = vipService
								.modifyVIPBalancePropelStatus(accounts);
						log.info("推送VIP当前可用余额至线上成功条数：" + count);
					} else {
						log.warn("推送VIP当前可用余额至线上失败：" + resp.getString("Desc"));
					}
				}

				if (accounts.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送会员消费记录
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIPExpenses.Propel}")
	public void propelVIPExpenses() {
		try {
			JSONArray exps = null;
			JSONObject resp = new JSONObject();
			while (true) {
				exps = vipService
						.getVIPExpensesPropel(SystemProperties.ParamPropelDataCount);
				if (!exps.isEmpty()) {
					log.info("推送VIP消费记录至线上参数：" + exps);
					resp = yunhuanHttpRequest("yunhuan.retail.add", exps);
					log.info("推送VIP消费记录至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						Integer count = vipService
								.modifyVIPExpensesPropelStatus(exps);
						log.info("推送VIP消费记录至线上成功条数：" + count);
					} else {
						log.warn("推送VIP消费记录至线上失败：" + resp.getString("Desc"));
					}
				}

				if (exps.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送VIP积分流水
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIPIntegralFTP.Propel}")
	public void propelVIPIntegralFTP() {
		try {
			JSONArray accounts = null;
			JSONObject resp = null;
			while (true) {
				accounts = vipService
						.getVIPIntegralFTPPropel(SystemProperties.ParamPropelDataCount);
				if (!accounts.isEmpty()) {
					log.info("推送VIP积分流水至线上参数:" + accounts);
					resp = yunhuanHttpRequest("yunhuan.integral.add", accounts);
					log.info("推送VIP积分流水至线上返回参数:" + resp);
					if (resp.getInteger("Code") == 1) {
						Integer count = vipService
								.modifyVIPIntegralFTPPropelStatus(accounts);
						log.info("推送VIP积分流水至线上成功条数:" + count);
					} else {
						log.warn("推送VIP积分流水至线上失败:" + resp.getString("Desc"));
					}
				}

				if (accounts.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送VIP资金流水
	 */
	// @Scheduled(cron = "${Param.CronExpression.VIPBalanceFTP.Propel}")
	public void propelVIPBalanceFTP() {
		JSONArray accounts = null;
		JSONObject resp = null;
		try {
			while (true) {
				accounts = vipService
						.getVIPBalanceFTPPropel(SystemProperties.ParamPropelDataCount);
				if (!accounts.isEmpty()) {
					log.info("推送VIP资金流水至线上参数:" + accounts);
					resp = yunhuanHttpRequest("yunhuan.balance.add", accounts);
					log.info("推送VIP资金流水至线上返回参数:" + resp);
					if (resp.getInteger("Code") == 1) {
						int count = vipService
								.modifyVIPBalanceFTPPropelStatus(accounts);
						log.info("推送VIP资金流水至线上成功条数:" + count);
					} else {
						log.warn("推送VIP资金流水至线上失败:" + resp.getString("Desc"));
					}
				}

				if (accounts.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

}
