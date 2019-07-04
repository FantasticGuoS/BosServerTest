package com.sungeon.bos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.VIPEntity;
import com.sungeon.bos.entity.request.VIPAddRequest;
import com.sungeon.bos.entity.request.VIPGetRequest;
import com.sungeon.bos.entity.third.ThirdVIP;
import com.sungeon.bos.entity.third.ThirdVIPBalanceFTP;
import com.sungeon.bos.entity.third.ThirdVIPIntegralFTP;

@Service
public interface IVIPService {

	public List<VIPEntity> getVIP(VIPGetRequest req);

	public Integer addVIP(VIPAddRequest request);

	public List<ThirdVIP> saveVIPBatch(JSONArray jsons);

	public BosResult executePThirdVIP();

	public List<ThirdVIPIntegralFTP> saveVIPIntegralFTPBatch(JSONArray jsons);

	public BosResult executePThirdVIPIntegralFTP();

	public List<ThirdVIPBalanceFTP> saveVIPBalanceFTPBatch(JSONArray jsons);

	public BosResult executePThirdVIPBalanceFTP();

	public JSONArray getVIPPropel(int count);

	public Integer modifyVIPPropelStatus(JSONArray vips);

	public JSONArray getVIPIntegralPropel(int count);

	public Integer modifyVIPIntegralPropelStatus(JSONArray accounts);

	public JSONArray getVIPBalancePropel(int count);

	public Integer modifyVIPBalancePropelStatus(JSONArray accounts);

	public JSONArray getVIPExpensesPropel(int count);

	public Integer modifyVIPExpensesPropelStatus(JSONArray exps);

	public JSONArray getVIPIntegralFTPPropel(int count);

	public Integer modifyVIPIntegralFTPPropelStatus(JSONArray accounts);

	public JSONArray getVIPBalanceFTPPropel(int count);

	public Integer modifyVIPBalanceFTPPropelStatus(JSONArray accounts);

}
