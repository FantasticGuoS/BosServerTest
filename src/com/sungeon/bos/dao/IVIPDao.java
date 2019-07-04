package com.sungeon.bos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.VIPAccountEntity;
import com.sungeon.bos.entity.base.VIPEntity;
import com.sungeon.bos.entity.base.VIPExpensesEntity;
import com.sungeon.bos.entity.third.ThirdVIP;
import com.sungeon.bos.entity.third.ThirdVIPBalanceFTP;
import com.sungeon.bos.entity.third.ThirdVIPIntegralFTP;

@Repository
public interface IVIPDao {

	public Integer queryVIPIdByCardno(String cardno);

	public List<VIPEntity> queryVIP(List<String> mobiles, int beg, int end);

	public Integer addVIPBatch(List<VIPEntity> addList);

	public Integer updateVIPBatch(List<VIPEntity> updateList);

	public Integer insertVIPBatch(List<ThirdVIP> addList);

	public BosResult executePThirdVIP();

	public Integer insertVIPIntegralFTPBatch(List<ThirdVIPIntegralFTP> addList);

	public BosResult executePThirdVIPIntegralFTP();

	public Integer insertVIPBalanceFTPBatch(List<ThirdVIPBalanceFTP> addList);

	public BosResult executePThirdVIPBalanceFTP();

	public List<VIPEntity> queryVIPPropel(int count);

	public Integer updateVIPPropelStatus(JSONArray vips);

	public List<VIPAccountEntity> queryVIPAccountPropel(int count, String type);

	public Integer updateVIPAccountPropelStatus(JSONArray accounts, String type);

	public List<VIPExpensesEntity> queryVIPExpensesPropel(int count);

	public Integer updateVIPExpensesPropelStatus(JSONArray exps);

	public List<VIPAccountEntity> queryVIPAccountFTPPropel(int count, String type);

	public Integer updateVIPAccountFTPPropelStatus(JSONArray accounts, String type);

}
