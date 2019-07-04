package com.sungeon.bos.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sungeon.bos.dao.IBaseDao;
import com.sungeon.bos.dao.IProductDao;
import com.sungeon.bos.entity.BosResult;
import com.sungeon.bos.entity.Store;
import com.sungeon.bos.entity.base.ProductEntity;
import com.sungeon.bos.entity.base.SKUEntity;
import com.sungeon.bos.entity.base.StorageEntity;
import com.sungeon.bos.entity.request.ProductGetRequest;
import com.sungeon.bos.entity.request.StorageGetRequest;
import com.sungeon.bos.entity.third.ThirdProduct;
import com.sungeon.bos.exception.ParamInvalidException;
import com.sungeon.bos.exception.ParamNotMatchedException;
import com.sungeon.bos.service.IProductService;
import com.sungeon.bos.util.SystemProperties;

@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private IProductDao productDao;

	@Override
	public List<ProductEntity> getProduct(ProductGetRequest req) {
		// TODO Auto-generated method stub
		if (null == req.getPage())
			req.setPage(1);
		if (null == req.getPagesize())
			req.setPagesize(SystemProperties.ParamDataCount);
		if (req.getPage() < 1)
			throw new ParamInvalidException("page", "页码参数无效：" + req.getPage());
		if (req.getPagesize() < 1)
			throw new ParamInvalidException("pagesize", "每页数量参数无效：" + req.getPagesize());
		if (req.getPagesize() > SystemProperties.ParamDataCount)
			throw new ParamInvalidException("pagesize", "每页数量参数太大(<="
					+ SystemProperties.ParamDataCount + ")，容易导致内存溢出：" + req.getPagesize());

		int beg = (req.getPage() - 1) * req.getPagesize() + 1;
		int end = req.getPage() * req.getPagesize();
		return productDao.queryProduct(beg, end);
	}

	@Override
	public List<StorageEntity> getStorage(StorageGetRequest req) {
		// TODO Auto-generated method stub
		if (null == req.getPage())
			req.setPage(1);
		if (null == req.getPagesize())
			req.setPagesize(SystemProperties.ParamDataCount);
		if (req.getPage() < 1)
			throw new ParamInvalidException("page", "页码参数无效：" + req.getPage());
		if (req.getPagesize() < 1)
			throw new ParamInvalidException("pagesize", "每页数量参数无效：" + req.getPagesize());
		if (req.getPagesize() > SystemProperties.ParamDataCount)
			throw new ParamInvalidException("pagesize", "每页数量参数太大(<="
					+ SystemProperties.ParamDataCount + ")，容易导致内存溢出：" + req.getPagesize());

		// 判断店仓是否存在
		Store store = null;
		if (null != req.getStorecode() && !req.getStorecode().isEmpty()) {
			store = baseDao.queryStore(req.getStorecode());
			if (null == store)
				throw new ParamNotMatchedException("storecode", "店仓编号" + req.getStorecode() + "不存在");
		}
		int beg = (req.getPage() - 1) * req.getPagesize() + 1;
		int end = req.getPage() * req.getPagesize();
		return productDao.queryStorage(null == store ? 0 : store.getId(), beg, end);
	}

	@Override
	public List<ThirdProduct> saveProductBatch(JSONArray jsons) {
		// TODO Auto-generated method stub
		List<ThirdProduct> addList = JSONArray.parseArray(jsons.toString(), ThirdProduct.class);
		if (!addList.isEmpty())
			productDao.saveProductBatch(addList);
		return addList;
	}

	@Override
	public BosResult executePotoProductInfoCheck() {
		// TODO Auto-generated method stub
		return productDao.executePotoProductInfoCheck();
	}

	@Override
	public JSONArray getProductPropel(int page, int pagesize, Date now) {
		// TODO Auto-generated method stub
		Integer beg = page * pagesize + 1;
		Integer end = (page + 1) * pagesize;
		List<ProductEntity> products = productDao.queryProductPropel(beg, end, now);
		JSONObject json = null;
		JSONArray resp = new JSONArray();
		if (!products.isEmpty()) {
			resp = new JSONArray();
			JSONArray skus = null;
			JSONObject s = null;
			for (ProductEntity product : products) {
				json = new JSONObject();
				skus = new JSONArray();
				json.put("code", product.getProductCode());
				json.put("name", product.getProductName());
				json.put("brand", product.getBrandCode());
				json.put("classes", product.getClassCode());
				json.put("price", product.getPricelist());
				for (SKUEntity sku : product.getSkus()) {
					s = new JSONObject();
					s.put("sku", sku.getSku());
					s.put("colors", sku.getColorName() + ";" + sku.getColorCode());
					s.put("sizes", sku.getSizeName() + ";" + sku.getSizeCode());
					s.put("price", product.getPricelist());
					skus.add(s);
				}
				json.put("skus", skus);
				resp.add(json);
			}
		}
		return resp;
	}

	@Override
	public Integer clearStorageLogPropel() {
		// TODO Auto-generated method stub
		return productDao.clearStorageLogPropel();
	}

	@Override
	public JSONArray getStoragePropel(int page, int pagesize, Date now) {
		// TODO Auto-generated method stub
		int beg = page * pagesize + 1;
		int end = (page + 1) * pagesize;
		List<StorageEntity> storages = productDao.queryStoragePropel(beg, end, now);
		JSONArray resp = null;
		if (!storages.isEmpty()) {
			resp = new JSONArray();
			JSONObject json = null;
			for (StorageEntity storage : storages) {
				json = new JSONObject();
				json.put("sku_code", storage.getSku());
				json.put("skuid", storage.getSkuid());
				json.put("productid", storage.getProductid());
				json.put("stock_total", storage.getQty());
				json.put("stock_sale", storage.getQtycan());
				resp.add(json);
			}
		}
		return resp;
	}

	@Override
	public Integer addStoragePropelLog(JSONArray storages) {
		// TODO Auto-generated method stub
		return productDao.insertStoragePropelLog(storages);
	}

	@Override
	public JSONArray getStoreStoragePropel(int page, int pagesize, Date now) {
		// TODO Auto-generated method stub
		int beg = page * pagesize + 1;
		int end = (page + 1) * pagesize;
		List<StorageEntity> storages = productDao.queryStoreStoragePropel(beg, end, now);
		JSONArray resp = null;
		if (!storages.isEmpty()) {
			resp = new JSONArray();
			JSONObject json = null;
			for (StorageEntity storage : storages) {
				json = new JSONObject();
				json.put("store_code", storage.getStore());
				json.put("sku_code", storage.getSku());
				json.put("skuid", storage.getSkuid());
				json.put("productid", storage.getProductid());
				json.put("stock_total", storage.getQty());
				json.put("stock_sale", storage.getQtycan());
				resp.add(json);
			}
		}
		return resp;
	}

}
