package com.sungeon.bos.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.base.ProductEntity;
import com.sungeon.bos.entity.base.StorageEntity;
import com.sungeon.bos.entity.request.ProductGetRequest;
import com.sungeon.bos.entity.request.StorageGetRequest;
import com.sungeon.bos.entity.third.ThirdProduct;

@Service
public interface IProductService {

	public List<ProductEntity> getProduct(ProductGetRequest req);

	public List<StorageEntity> getStorage(StorageGetRequest req);

	public List<ThirdProduct> saveProductBatch(JSONArray jsons);

	public BosResult executePotoProductInfoCheck();

	public JSONArray getProductPropel(int page, int pagesize, Date now);

	public Integer clearStorageLogPropel();

	public JSONArray getStoragePropel(int page, int pagesize, Date now);

	public Integer addStoragePropelLog(JSONArray storages);

	public JSONArray getStoreStoragePropel(int page, int pagesize, Date now);

}
