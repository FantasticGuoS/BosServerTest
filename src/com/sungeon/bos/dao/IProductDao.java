package com.sungeon.bos.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.SKU;
import com.sungeon.bos.entity.base.ProductEntity;
import com.sungeon.bos.entity.base.StorageEntity;
import com.sungeon.bos.entity.third.ThirdProduct;

@Repository
public interface IProductDao {

	public SKU queryProductBySku(String sku);

	public List<ProductEntity> queryProduct(int beg, int end);

	public List<StorageEntity> queryStorage(int storeid, int beg, int end);

	public Integer saveProductBatch(List<ThirdProduct> addList);

	public BosResult executePotoProductInfoCheck();

	public List<ProductEntity> queryProductPropel(int beg, int end, Date now);

	public Integer clearStorageLogPropel();

	public List<StorageEntity> queryStoragePropel(int beg, int end, Date now);

	public Integer insertStoragePropelLog(JSONArray storages);

	public List<StorageEntity> queryStoreStoragePropel(int beg, int end, Date now);

}
