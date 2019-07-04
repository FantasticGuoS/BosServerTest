/**
 * Created by GuoS on 2017/7/28.
 */
package com.sungeon.bos.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.base.WeChatMessageEntity;
import com.sungeon.bos.service.IWeChatService;
import com.sungeon.bos.util.SystemProperties;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class WeChatTask extends BaseTask {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IWeChatService weChatService;

	/**
	 * 推送微信模版消息
	 */
	// @Scheduled(cron = "${Param.CronExpression.WechatMessage.Propel}")
	public void propelWeChatMessage() {
		try {
			List<WeChatMessageEntity> messages = null;
			JSONArray req = null;
			JSONObject resp = new JSONObject();
			while (true) {
				messages = weChatService.getWeChatMessagePropel();
				req = new JSONArray();
				if (!messages.isEmpty()) {
					req = JSONArray.parseArray(messages.toString());
					log.info("推送微信模版消息至线上参数：" + req);
					resp = yunhuanHttpRequest("yunhuan.tplmsg.send", req);
					log.info("推送微信模版消息至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						Integer count = weChatService
								.modifyWeChatMessagePropelStatus(messages);
						log.info("推送微信模版消息至线上成功条数：" + count);
					} else {
						log.warn("推送微信模版消息至线上失败：" + resp.getString("Desc"));
					}
				}
				if (messages.size() < SystemProperties.ParamPropelDataCount)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

}
