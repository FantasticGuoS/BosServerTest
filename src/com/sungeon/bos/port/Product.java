package com.sungeon.bos.port;

import javax.jws.WebService;

import com.sungeon.bos.entity.request.ProductGetRequest;
import com.sungeon.bos.entity.request.StorageGetRequest;
import com.sungeon.bos.entity.response.ProductGetResponse;
import com.sungeon.bos.entity.response.StorageGetResponse;

@WebService
public interface Product {

	public ProductGetResponse getPorduct(ProductGetRequest request);

	public StorageGetResponse getStorage(StorageGetRequest request);

}
