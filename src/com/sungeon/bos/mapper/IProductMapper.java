package com.sungeon.bos.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.entity.SKU;
import com.sungeon.bos.entity.base.ProductEntity;
import com.sungeon.bos.entity.base.StorageEntity;
import com.sungeon.bos.entity.third.ThirdProduct;

@Repository
public interface IProductMapper {

	/**
	 * 条码表 m_product_alias 款号 m_product 根据条码编号查询条码表相关数据
	 * 
	 * @param sku
	 * @return
	 * 
	 */
	public SKU queryProductBySku(String sku);

	public List<ProductEntity> queryProduct(@Param("beg") int beg,
			@Param("end") int end);

	/**
	 * 商品库存
	 * 
	 * @param storeid
	 * @param beg
	 * @param end
	 * @return
	 * 
	 */
	public List<StorageEntity> queryStorage(@Param("storeid") int storeid,
			@Param("beg") int beg, @Param("end") int end);

	public Integer saveProductBatch(@Param("lists") List<ThirdProduct> addList,
			@Param("brand") String brand);

	public Map<String, Object> executePotoProductInfoCheck(
			@Param("brand") String brand);

	public List<ProductEntity> queryProductPropel(@Param("beg") int beg,
			@Param("end") int end, @Param("now") String now,
			@Param("dimbrand") String dimbrand,
			@Param("dimclass") String dimclass, @Param("brand") String brand);

	/**
	 * 清空表e_third_upload_storage_log
	 * 
	 * @return
	 * 
	 */
	public Integer clearStorageLogPropel(@Param("brand") String brand);

	/**
	 * 
	 * @param beg
	 * @param end
	 * @return
	 * 
	 */
	public List<StorageEntity> queryStoragePropel(@Param("beg") int beg,
			@Param("end") int end, @Param("now") Date now,
			@Param("brand") String brand);

	/**
	 * 批量插入数据
	 * 
	 * @param storages
	 * @return
	 * 
	 */
	public Integer insertStoragePropelLog(
			@Param("storages") JSONArray storages, @Param("brand") String brand);

	public List<StorageEntity> queryStoreStoragePropel(@Param("beg") int beg,
			@Param("end") int end, @Param("now") Date now,
			@Param("brand") String brand);

}
