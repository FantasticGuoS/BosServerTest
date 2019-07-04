package com.sungeon.bos.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.sungeon.bos.dao.IProductDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.SKU;
import com.sungeon.bos.entity.base.ProductEntity;
import com.sungeon.bos.entity.base.StorageEntity;
import com.sungeon.bos.entity.third.ThirdProduct;
import com.sungeon.bos.exception.SungeonException;
import com.sungeon.bos.mapper.IProductMapper;
import com.sungeon.bos.util.SystemProperties;
import com.sungeon.bos.util.SystemUtil;

@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl implements IProductDao {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private IProductMapper productMapper;

	@Override
	public SKU queryProductBySku(String sku) {
		// TODO Auto-generated method stub
		try {
			return productMapper.queryProductBySku(sku);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			SQLException ex = (SQLException) e.getCause();
			if (ex.getErrorCode() > 0)
				throw new SungeonException(getOracleExceptionMessage(ex.getMessage()));
			return null;
		}
	}

	@Override
	public List<ProductEntity> queryProduct(int beg, int end) {
		// TODO Auto-generated method stub
		return productMapper.queryProduct(beg, end);
	}

	@Override
	public List<StorageEntity> queryStorage(int storeid, int beg, int end) {
		// TODO Auto-generated method stub
		return productMapper.queryStorage(storeid, beg, end);
	}

	@Override
	public Integer saveProductBatch(List<ThirdProduct> addList) {
		// TODO Auto-generated method stub
		return productMapper.saveProductBatch(addList, SystemProperties.Brand);
	}

	@Override
	public BosResult executePotoProductInfoCheck() {
		// TODO Auto-generated method stub
		BosResult result = new BosResult();
		try {
			productMapper.executePotoProductInfoCheck(SystemProperties.Brand);
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
	public List<ProductEntity> queryProductPropel(int beg, int end, Date now) {
		// TODO Auto-generated method stub
		// statementType：STATEMENT（非预编译），PREPARED（预编译）或CALLABLE中的任意一个，这就告诉
		// MyBatis分别使用Statement，PreparedStatement或者CallableStatement。默认：PREPARED。这里显然不能使用预编译，要改成非预编译
		String date = "to_date('" + SystemUtil.getTime(now.getTime()) + "', 'yyyy-mm-dd hh24:mi:ss')";
		return productMapper.queryProductPropel(beg, end, date, SystemProperties.ParamProductDIMBrand,
				SystemProperties.ParamProductDIMClass, SystemProperties.Brand);
	}

	@Override
	public Integer clearStorageLogPropel() {
		// TODO Auto-generated method stub
		return productMapper.clearStorageLogPropel(SystemProperties.Brand);
	}

	@Override
	public List<StorageEntity> queryStoragePropel(int beg, int end, Date now) {
		// TODO Auto-generated method stub
		return productMapper.queryStoragePropel(beg, end, now, SystemProperties.Brand);
	}

	@Override
	public Integer insertStoragePropelLog(JSONArray storages) {
		// TODO Auto-generated method stub
		return productMapper.insertStoragePropelLog(storages, SystemProperties.Brand);
	}

	@Override
	public List<StorageEntity> queryStoreStoragePropel(int beg, int end, Date now) {
		// TODO Auto-generated method stub
		return productMapper.queryStoreStoragePropel(beg, end, now, SystemProperties.Brand);
	}

}
