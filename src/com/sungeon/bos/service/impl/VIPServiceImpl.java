package com.sungeon.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.dao.IBaseDao;
import com.sungeon.bos.dao.IVIPDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.Store;
import com.sungeon.bos.entity.VIPType;
import com.sungeon.bos.entity.base.ItemEntity;
import com.sungeon.bos.entity.base.VIPAccountEntity;
import com.sungeon.bos.entity.base.VIPEntity;
import com.sungeon.bos.entity.base.VIPExpensesEntity;
import com.sungeon.bos.entity.request.VIPAddRequest;
import com.sungeon.bos.entity.request.VIPGetRequest;
import com.sungeon.bos.entity.third.ThirdVIP;
import com.sungeon.bos.entity.third.ThirdVIPBalanceFTP;
import com.sungeon.bos.entity.third.ThirdVIPIntegralFTP;
import com.sungeon.bos.exception.ParamInvalidException;
import com.sungeon.bos.exception.ParamNotMatchedException;
import com.sungeon.bos.service.IVIPService;
import com.sungeon.bos.util.SystemProperties;

@Service("vipService")
@Transactional
public class VIPServiceImpl implements IVIPService {

	@Autowired
	private IVIPDao vipDao;
	@Autowired
	private IBaseDao baseDao;

	@Override
	public List<VIPEntity> getVIP(VIPGetRequest req) {
		// TODO Auto-generated method stub
		if (null != req.getMobiles() && req.getMobiles().size() > SystemProperties.ParamDataCount)
			throw new ParamInvalidException("mobiles",
					"手机号码参数数量太大(<=" + SystemProperties.ParamDataCount + ")，容易导致内存溢出");
		int beg = 0;
		int end = 0;
		if (null == req.getMobiles() || req.getMobiles().size() == 0) {
			if (null == req.getPage())
				req.setPage(1);
			if (null == req.getPagesize())
				req.setPagesize(SystemProperties.ParamDataCount);
			if (req.getPage() < 1)
				throw new ParamInvalidException("page", "页码参数无效：" + req.getPage());
			if (req.getPagesize() < 1)
				throw new ParamInvalidException("pagesize", "每页数量参数无效：" + req.getPagesize());
			if (req.getPagesize() > SystemProperties.ParamDataCount)
				throw new ParamInvalidException("pagesize",
						"每页数量参数太大(<=" + SystemProperties.ParamDataCount + ")，容易导致内存溢出：" + req.getPagesize());
			beg = (req.getPage() - 1) * req.getPagesize() + 1;
			end = req.getPage() * req.getPagesize();
		}
		return vipDao.queryVIP(req.getMobiles(), beg, end);
	}

	@Override
	public Integer addVIP(VIPAddRequest req) {
		if (req.getVips().size() > SystemProperties.ParamDataCount)
			throw new ParamInvalidException("vips", "会员参数数量太大(<=" + SystemProperties.ParamDataCount + ")，容易导致内存溢出");
		int count = 0;
		List<VIPEntity> addList = new ArrayList<VIPEntity>();
		List<VIPEntity> updateList = new ArrayList<VIPEntity>();
		for (VIPEntity vip : req.getVips()) {
			if (null == vip.getCardno() || vip.getCardno() == "")
				throw new ParamInvalidException("cardno", "卡号参数无效：" + vip.getCardno());
			if (null == vip.getMobile() || vip.getMobile() == "")
				throw new ParamInvalidException("mobile", "手机号码参数无效：" + vip.getMobile());
			if (null == vip.getViptype() || vip.getViptype() == "")
				throw new ParamInvalidException("viptype", "VIP卡类型参数无效：" + vip.getViptype());
			if (null == vip.getStore() || vip.getStore() == "")
				throw new ParamInvalidException("storecode", "开卡店仓参数无效：" + vip.getStore());

			VIPType viptype = baseDao.queryVIPType(vip.getViptype());
			if (null == viptype)
				throw new ParamNotMatchedException("viptype", "VIP卡类型参数不存在：" + vip.getViptype());
			Store store = baseDao.queryStore(vip.getStore());
			if (null == store)
				throw new ParamNotMatchedException("storecode", "开卡店仓参数不存在：" + vip.getStore());

			Integer vipid = vipDao.queryVIPIdByCardno(vip.getCardno());
			vip.setViptype(viptype.getId() + "");
			vip.setStore(store.getId() + "");
			vip.setCustomer(store.getCustomerid() + "");
			if (null == vipid) {
				addList.add(vip);
			} else {
				vip.setId(vipid);
				updateList.add(vip);
			}
			count++;
		}
		if (!addList.isEmpty())
			vipDao.addVIPBatch(addList);
		if (!updateList.isEmpty())
			vipDao.updateVIPBatch(updateList);
		return count;
	}

	@Override
	public List<ThirdVIP> saveVIPBatch(JSONArray jsons) {
		// TODO Auto-generated method stub
		List<ThirdVIP> addList = JSONArray.parseArray(jsons.toString(), ThirdVIP.class);
		if (!addList.isEmpty())
			vipDao.insertVIPBatch(addList);
		return addList;
	}

	@Override
	public BosResult executePThirdVIP() {
		// TODO Auto-generated method stub
		return vipDao.executePThirdVIP();
	}

	@Override
	public List<ThirdVIPIntegralFTP> saveVIPIntegralFTPBatch(JSONArray jsons) {
		// TODO Auto-generated method stub
		List<ThirdVIPIntegralFTP> addList = JSONArray.parseArray(jsons.toString(), ThirdVIPIntegralFTP.class);
		if (!addList.isEmpty())
			vipDao.insertVIPIntegralFTPBatch(addList);
		return addList;
	}

	@Override
	public BosResult executePThirdVIPIntegralFTP() {
		// TODO Auto-generated method stub
		return vipDao.executePThirdVIPIntegralFTP();
	}

	@Override
	public List<ThirdVIPBalanceFTP> saveVIPBalanceFTPBatch(JSONArray jsons) {
		// TODO Auto-generated method stub
		List<ThirdVIPBalanceFTP> addList = JSONArray.parseArray(jsons.toString(), ThirdVIPBalanceFTP.class);
		if (!addList.isEmpty())
			vipDao.insertVIPBalanceFTPBatch(addList);
		return addList;
	}

	@Override
	public BosResult executePThirdVIPBalanceFTP() {
		// TODO Auto-generated method stub
		return vipDao.executePThirdVIPBalanceFTP();
	}

	@Override
	public JSONArray getVIPPropel(int count) {
		// TODO Auto-generated method stub
		List<VIPEntity> vips = vipDao.queryVIPPropel(count);
		JSONArray req = new JSONArray();
		for (VIPEntity vip : vips) {
			JSONObject json = new JSONObject();
			json.put("user_id", vip.getId());
			json.put("user_rank_code", vip.getViptype());
			json.put("user_vip_card", vip.getCardno());
			json.put("user_real_name", vip.getVipname());
			json.put("user_pay_password", vip.getPassword());
			json.put("user_birthday", vip.getBirthday());
			req.add(json);
		}
		return req;
	}

	@Override
	public Integer modifyVIPPropelStatus(JSONArray vips) {
		// TODO Auto-generated method stub
		return vipDao.updateVIPPropelStatus(vips);
	}

	@Override
	public JSONArray getVIPIntegralPropel(int count) {
		// TODO Auto-generated method stub
		List<VIPAccountEntity> accounts = vipDao.queryVIPAccountPropel(count, "I");
		JSONArray req = new JSONArray();
		for (VIPAccountEntity acc : accounts) {
			JSONObject json = new JSONObject();
			json.put("id", acc.getId());
			json.put("user_id", acc.getEvipid());
			json.put("user_pay_points", acc.getIntegral());
			req.add(json);
		}
		return req;
	}

	@Override
	public Integer modifyVIPIntegralPropelStatus(JSONArray accounts) {
		// TODO Auto-generated method stub
		return vipDao.updateVIPAccountPropelStatus(accounts, "I");
	}

	@Override
	public JSONArray getVIPBalancePropel(int count) {
		// TODO Auto-generated method stub
		List<VIPAccountEntity> accounts = vipDao.queryVIPAccountPropel(count, "B");
		JSONArray req = new JSONArray();
		for (VIPAccountEntity acc : accounts) {
			JSONObject json = new JSONObject();
			json.put("id", acc.getId());
			json.put("user_id", acc.getEvipid());
			json.put("user_money", acc.getIntegral());
			req.add(json);
		}
		return req;
	}

	@Override
	public Integer modifyVIPBalancePropelStatus(JSONArray accounts) {
		// TODO Auto-generated method stub
		return vipDao.updateVIPAccountPropelStatus(accounts, "B");
	}

	@Override
	public JSONArray getVIPExpensesPropel(int count) {
		// TODO Auto-generated method stub
		List<VIPExpensesEntity> exps = vipDao.queryVIPExpensesPropel(count);
		JSONArray req = new JSONArray();
		for (VIPExpensesEntity exp : exps) {
			JSONObject json = new JSONObject();
			json.put("id", exp.getId());
			json.put("retail_amount_goods", exp.getTotamtList());
			json.put("retail_amount_payable", exp.getTotamtActual());
			json.put("retail_date_created", exp.getBilldate());
			json.put("retail_vip_evipid", exp.getEvipid());
			json.put("retail_order_sn", exp.getDocno());
			json.put("retail_store_name", exp.getStoreName());
			json.put("retail_store_address", exp.getStoreAddress());
			json.put("retail_store_mobile", exp.getStoreMobile());
			json.put("retail_store_tel", exp.getStorePhone());
			json.put("retail_seller_note", exp.getDescription());
			json.put("retail_time_created", exp.getModifieddate());
			json.put("retail_time_modified", exp.getModifieddate());
			json.put("items", getExpensesitems(exp.getItems()));
			req.add(json);
		}
		return req;
	}

	private JSONArray getExpensesitems(List<ItemEntity> items) {
		JSONArray jsons = new JSONArray();
		for (ItemEntity item : items) {
			JSONObject json = new JSONObject();
			json.put("item_product_sn", item.getProduct());
			json.put("item_goods_name", item.getProductName());
			json.put("item_goods_sn", item.getSku());
			json.put("item_goods_number", item.getQty());
			json.put("item_goods_price", item.getPriceactual());
			json.put("item_sale_price", item.getPricelist());
			json.put("item_discount", item.getDiscount());
			json.put("item_goods_attr", item.getColorName() + "|" + item.getSizeName());
			jsons.add(json);
		}
		return jsons;
	}

	@Override
	public Integer modifyVIPExpensesPropelStatus(JSONArray exps) {
		// TODO Auto-generated method stub
		return vipDao.updateVIPExpensesPropelStatus(exps);
	}

	@Override
	public JSONArray getVIPIntegralFTPPropel(int count) {
		// TODO Auto-generated method stub
		List<VIPAccountEntity> accounts = vipDao.queryVIPAccountFTPPropel(count, "I");
		JSONArray req = new JSONArray();
		for (VIPAccountEntity acc : accounts) {
			JSONObject json = new JSONObject();
			json.put("id", acc.getId());
			json.put("integral_id", acc.getId()); // 线下单据流水id
			json.put("fk_user_id", acc.getEvipid()); // 线上vipid
			json.put("integral_amount", acc.getIntegral()); // 变动积分
			json.put("integral_type", acc.getIntegraltype()); // 变动类型(手工调整、购物送、积分抵扣)
			json.put("integral_thd_code", acc.getDocno()); // 线下单据编号
			json.put("integral_time_created", acc.getChangedate()); // 积分变动日期
			req.add(json);
		}
		return req;
	}

	@Override
	public Integer modifyVIPIntegralFTPPropelStatus(JSONArray accounts) {
		// TODO Auto-generated method stub
		return vipDao.updateVIPAccountFTPPropelStatus(accounts, "I");
	}

	@Override
	public JSONArray getVIPBalanceFTPPropel(int count) {
		// TODO Auto-generated method stub
		List<VIPAccountEntity> accounts = vipDao.queryVIPAccountFTPPropel(count, "B");
		JSONArray req = new JSONArray();
		for (VIPAccountEntity acc : accounts) {
			JSONObject json = new JSONObject();
			json.put("id", acc.getId());
			json.put("balance_id", acc.getId());// 线下单据流水id
			json.put("fk_user_id", acc.getEvipid());// 线上vipid
			json.put("balance_amount", acc.getBalance());// 变动金额
			json.put("balance_time_created", acc.getChangedate());// 变动日期
			json.put("balance_code", acc.getBalancetype());// 变动类型（消费、退款、充值
			json.put("balance_thd_code", acc.getDocno());// 单据编号
			req.add(json);
		}
		return req;
	}

	@Override
	public Integer modifyVIPBalanceFTPPropelStatus(JSONArray accounts) {
		// TODO Auto-generated method stub
		return vipDao.updateVIPAccountFTPPropelStatus(accounts, "B");
	}

}
