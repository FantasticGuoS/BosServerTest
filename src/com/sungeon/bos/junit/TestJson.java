package com.sungeon.bos.junit;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.entity.base.StorageEntity;
import com.sungeon.bos.entity.request.BaseRequest;
import com.sungeon.bos.entity.response.StorageGetResponse;
import com.sungeon.bos.util.FileUtil;

public class TestJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StorageEntity sto = new StorageEntity();
		sto.setProductid(1);
		sto.setSkuid(1);
		sto.setSku("TEST");
		sto.setQtycan(10);
		sto.setQty(12);
		// JAVA对象转JSONObject
		JSONObject json = JSONObject.parseObject(sto.toString());
		System.out.println(json.toString());

		// JSONObjectString转JAVA对象
		StorageEntity sto2 = JSONObject.parseObject(json.toJSONString(), StorageEntity.class);
		System.out.println(sto2.toString());
		// JSONObject转JAVA对象
		sto2 = JSONObject.toJavaObject(json, StorageEntity.class);
		System.out.println(sto2.toString());
		System.out.println(JSON.parseObject(json.toString(), TreeMap.class));

		// JSONArrayString转List
		List<StorageEntity> stos = new ArrayList<StorageEntity>();
		stos.add(sto);
		sto = new StorageEntity();
		sto.setProductid(2);
		sto.setSkuid(2);
		sto.setSku("TEST2");
		sto.setQtycan(20);
		sto.setQty(22);
		stos.add(sto);
		List<StorageEntity> stos2 = JSONArray.parseArray(stos.toString(), StorageEntity.class);
		System.out.println(stos2);
		JSONArray j = JSONArray.parseArray(stos2.toString());
		System.out.println(j);

		// json转带有Bean和List参数的java对象
		StorageGetResponse storages = new StorageGetResponse();
		storages.setResult(stos);
		storages.setCode(100);
		storages.getDetail().setDesc("test");
		storages.getDetail().setDescription("测试");
		StorageGetResponse storage = JSONObject.parseObject(storages.toString(), StorageGetResponse.class);
		System.out.println(storage);

		// 将JSONArray文件转换为List
		System.out.println(FileUtil.filetoListFromJSONArray("D:/Document/test.json", BaseRequest.class));
	}

}
