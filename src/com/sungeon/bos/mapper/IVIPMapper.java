package com.sungeon.bos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.base.VIPAccountEntity;
import com.sungeon.bos.entity.base.VIPEntity;
import com.sungeon.bos.entity.base.VIPExpensesEntity;
import com.sungeon.bos.entity.third.ThirdVIP;
import com.sungeon.bos.entity.third.ThirdVIPBalanceFTP;
import com.sungeon.bos.entity.third.ThirdVIPIntegralFTP;

@Repository
public interface IVIPMapper {

	public Integer queryVIPIdByCardno(String cardno);

	public List<VIPEntity> queryVIP(@Param("mobiles") List<String> mobiles, @Param("beg") int beg,
			@Param("end") int end);

	public Integer addVIPBatch(@Param("list") List<VIPEntity> addList);

	public Integer updateVIPBatch(@Param("list") List<VIPEntity> updateList);

	public Integer insertVIPBatch(@Param("list") List<ThirdVIP> addList,
			@Param("brand") String brand);

	public Map<String, Object> executePThirdVIP(@Param("brand") String brand);

	public Integer insertVIPIntegralFTPBatch(@Param("list") List<ThirdVIPIntegralFTP> addList,
			@Param("brand") String brand);

	public Map<String, Object> executePThirdVIPIntegralFTP(@Param("brand") String brand);

	public Integer insertVIPBalanceFTPBatch(@Param("list") List<ThirdVIPBalanceFTP> addList,
			@Param("brand") String brand);

	public Map<String, Object> executePThirdVIPBalanceFTP(@Param("brand") String brand);

	public List<VIPEntity> queryVIPPropel(@Param("count") int count, @Param("brand") String brand);

	public Integer updateVIPPropelStatus(@Param("vips") JSONArray vips);

	public List<VIPAccountEntity> queryVIPAccountPropel(@Param("count") int count,
			@Param("type") String type, @Param("brand") String brand);

	public Integer updateVIPAccountPropelStatus(@Param("accounts") JSONArray accounts,
			@Param("type") String type);

	public List<VIPExpensesEntity> queryVIPExpensesPropel(@Param("count") int count,
			@Param("brand") String brand);

	public Integer updateVIPExpensesPropelStatus(@Param("exps") JSONArray exps);

	public List<VIPAccountEntity> queryVIPAccountFTPPropel(@Param("count") int count,
			@Param("type") String type, @Param("brand") String brand);

	public Integer updateVIPAccountFTPPropelStatus(@Param("accounts") JSONArray accounts,
			@Param("type") String type);

}
