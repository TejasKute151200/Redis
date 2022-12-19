package com.redis.service;

import java.util.List;

import com.redis.dto.AddProductDto;
import com.redis.entity.Product;

public interface ProductService {
	public List<Product> getAllSetting();

//	public Product addSetting(Product product);

//	public Product updateSettingById(Long id, Product product) throws Exception;

//	Product addSetting(ProductDto settingDetail);

	Product addSetting(AddProductDto settingDetail);

	Product updateSettingById(Long id, AddProductDto settingDetail) throws Exception;
}
