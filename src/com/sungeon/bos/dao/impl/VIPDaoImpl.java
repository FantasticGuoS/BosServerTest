package com.sungeon.bos.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.dao.IVIPDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.VIPAccountEntity;
import com.sungeon.bos.entity.base.VIPEntity;
import com.sungeon.bos.entity.base.VIPExpensesEntity;
import com.sungeon.bos.entity.third.ThirdVIP;
import com.sungeon.bos.entity.third.ThirdVIPBalanceFTP;
import com.sungeon.bos.entity.third.ThirdVIPIntegralFTP;
import com.sungeon.bos.mapper.IVIPMapper;
import com.sungeon.bos.util.SystemProperties;

@Repository("vipDao")
public class VIPDaoImpl extends BaseDaoImpl implements IVIPDao {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IVIPMapper vipMapper;

	@Override
	public Integer queryVIPIdByCardno(String cardno) {
		// TODO Auto-generated method stub
		return vipMapper.queryVIPIdByCardno(cardno);
	}

	@Override
	public List<VIPEntity> queryVIP(List<String> mobiles, int beg, int end) {
		// TODO Auto-generated method stub
		return vipMapper.queryVIP(mobiles, beg, end);
	}

	@Override
	public Integer addVIPBatch(List<VIPEntity> addList) {
		// TODO Auto-generated method stub
		return vipMapper.addVIPBatch(addList);
	}

	@Override
	public Integer updateVIPBatch(List<VIPEntity> updateList) {
		// TODO Auto-generated method stub
		return vipMapper.updateVIPBatch(updateList);
	}

	@Override
	public Integer insertVIPBatch(List<ThirdVIP> addList) {
		// TODO Auto-generated method stub
		return vipMapper.insertVIPBatch(addList, SystemProperties.Brand);
	}

	@Override
	public BosResult executePThirdVIP() {
		// TODO Auto-generated method stub
		BosResult result = new BosResult();
		try {
			vipMapper.executePThirdVIP(SystemProperties.Brand);
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
	public Integer insertVIPIntegralFTPBatch(List<ThirdVIPIntegralFTP> addList) {
		// TODO Auto-generated method stub
		return vipMapper.insertVIPIntegralFTPBatch(addList, SystemProperties.Brand);
	}

	@Override
	public BosResult executePThirdVIPIntegralFTP() {
		// TODO Auto-generated method stub
		BosResult result = new BosResult();
		try {
			vipMapper.executePThirdVIPIntegralFTP(SystemProperties.Brand);
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
	public Integer insertVIPBalanceFTPBatch(List<ThirdVIPBalanceFTP> addList) {
		// TODO Auto-generated method stub
		return vipMapper.insertVIPBalanceFTPBatch(addList, SystemProperties.Brand);
	}

	@Override
	public BosResult executePThirdVIPBalanceFTP() {
		// TODO Auto-generated method stub
		BosResult result = new BosResult();
		try {
			vipMapper.executePThirdVIPBalanceFTP(SystemProperties.Brand);
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
	public List<VIPEntity> queryVIPPropel(int count) {
		// TODO Auto-generated method stub
		return vipMapper.queryVIPPropel(count, SystemProperties.Brand);
	}

	@Override
	public Integer updateVIPPropelStatus(JSONArray vips) {
		// TODO Auto-generated method stub
		return vipMapper.updateVIPPropelStatus(vips);
	}

	@Override
	public List<VIPAccountEntity> queryVIPAccountPropel(int count, String type) {
		// TODO Auto-generated method stub
		return vipMapper.queryVIPAccountPropel(count, type, SystemProperties.Brand);
	}

	@Override
	public Integer updateVIPAccountPropelStatus(JSONArray accounts, String type) {
		// TODO Auto-generated method stub
		return vipMapper.updateVIPAccountPropelStatus(accounts, type);
	}

	@Override
	public List<VIPExpensesEntity> queryVIPExpensesPropel(int count) {
		// TODO Auto-generated method stub
		return vipMapper.queryVIPExpensesPropel(count, SystemProperties.Brand);
	}

	@Override
	public Integer updateVIPExpensesPropelStatus(JSONArray exps) {
		// TODO Auto-generated method stub
		return vipMapper.updateVIPExpensesPropelStatus(exps);
	}

	@Override
	public List<VIPAccountEntity> queryVIPAccountFTPPropel(int count, String type) {
		// TODO Auto-generated method stub
		return vipMapper.queryVIPAccountFTPPropel(count, type, SystemProperties.Brand);
	}

	@Override
	public Integer updateVIPAccountFTPPropelStatus(JSONArray accounts, String type) {
		// TODO Auto-generated method stub
		return vipMapper.updateVIPAccountFTPPropelStatus(accounts, type);
	}

}
