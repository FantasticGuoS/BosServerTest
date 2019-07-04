/**
 * Created by GuoS on 2016/12/3.
 */
package com.sungeon.bos.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.third.ThirdProduct;
import com.sungeon.bos.service.IProductService;
import com.sungeon.bos.util.SystemProperties;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class ProductTask extends BaseTask {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IProductService productService;

	/**
	 * 抓取商品
	 */
	// @Scheduled(cron = "${Param.CronExpression.Product.Get}")
	public void getProduct() {
		try {
			JSONObject req = null;
			while (true) {
				req = new JSONObject();
				req.put("page_size", SystemProperties.ParamPropelDataCount);
				log.info("抓取线上商品参数:" + req.toString());
				JSONObject resp = yunhuanHttpRequest("yunhuan.goods.list", req);
				log.info("抓取线上商品返回数据:" + resp);
				List<ThirdProduct> addList = new ArrayList<ThirdProduct>();
				if (resp.getInteger("Code") == 1) {
					addList = productService.saveProductBatch(resp
							.getJSONArray("Data"));
				} else {
					log.warn("抓取线上商品失败:" + resp.getString("Desc"));
				}

				if (!addList.isEmpty()) {
					// &goods_id[" + i + "]
					req = new JSONObject();
					JSONArray products = new JSONArray();
					for (ThirdProduct product : addList)
						products.add(product.getGoods_id());
					req.put("goods_id", products);
					log.info("回传抓取线上商品成功参数:" + req);
					yunhuanHttpRequest("yunhuan.goods.listcb", req);
					log.info("抓取线上商品至线下成功条数:" + addList.size());
				}

				if (addList.size() < 300)
					break;
			}
			// 执行存储过程
			productService.executePotoProductInfoCheck();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	/**
	 * 推送商品
	 */
	// @Scheduled(cron = "${Param.CronExpression.Product.Propel}")
	public void propelProduct() {
		try {
			int page = 0;
			Date now = new Date();
			JSONObject req = null;
			JSONObject resp = new JSONObject();
			while (true) {
				JSONArray products = productService.getProductPropel(page,
						SystemProperties.ParamPropelDataCount, now);
				if (!products.isEmpty()) {
					for (int i = 0; i < products.size(); i++) {
						req = products.getJSONObject(i);
						log.info("推送商品至线上参数：" + req);
						resp = yunhuanHttpRequest("yunhuan.goods.add", req);
						log.info("推送商品至线上返回数据：" + resp);
						if (resp.getInteger("Code") == 1)
							log.info("推送商品至线上成功");
						else
							log.info("推送商品至线上失败：" + resp.getString("Desc"));
					}
				}

				// 如果当前页的库存数据数量少于200则退出死循环，否则继续查找下一页
				if (products.size() < SystemProperties.ParamPropelDataCount) {
					// 最后一页时更新时间
					baseService.updateThirdTime("ProductPropelTime", now);
					break;
				} else
					page++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送库存
	 */
	// @Scheduled(cron = "${Param.CronExpression.Storage.Propel}")
	public void propelStorage() {
		try {
			productService.clearStorageLogPropel();
			int page = 0;
			Date now = new Date();
			JSONObject resp = new JSONObject();
			// 死循环
			while (true) {
				// 一次最多推送最多200条数据
				JSONArray storages = productService.getStoragePropel(page,
						SystemProperties.ParamPropelDataCount, now);
				if (!storages.isEmpty()) {
					log.info("推送库存至线上参数：" + storages);
					resp = yunhuanHttpRequest("yunhuan.goods.stock", storages);
					log.info("推送库存至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						productService.addStoragePropelLog(storages);
						log.info("推送库存至线上成功");
					} else {
						log.info("推送库存至线上失败：" + resp.getString("Desc"));
					}
				}

				if (storages.size() < SystemProperties.ParamPropelDataCount) {
					// 最后一页时更新时间
					baseService.updateThirdTime("StoragePropelTime", now);
					break;
				} else
					page++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 推送门店库存
	 */
	// @Scheduled(cron = "${Param.CronExpression.StoreStorage.Propel}")
	public void propelStoreStorage() {
		try {
			productService.clearStorageLogPropel();
			Date now = new Date();
			JSONObject resp = new JSONObject();
			int page = 0;
			while (true) {
				JSONArray storages = productService.getStoreStoragePropel(page,
						SystemProperties.ParamPropelDataCount, now);
				if (!storages.isEmpty()) {
					log.info("推送门店库存至线上参数：" + storages);
					resp = yunhuanHttpRequest("yunhuan.goods.warehouse.stock",
							storages);
					log.info("推送门店库存至线上返回数据：" + resp);
					if (resp.getInteger("Code") == 1) {
						productService.addStoragePropelLog(storages);
						log.info("推送门店库存至线上成功");
					} else {
						log.info("推送门店库存至线上失败：" + resp.getString("Desc"));
					}
				}

				if (storages.size() < SystemProperties.ParamPropelDataCount) {
					// 最后一页时更新时间
					baseService.updateThirdTime("StoreStoragePropelTime", now);
					break;
				} else
					page++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
	}

}
