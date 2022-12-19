package com.redis.util;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.dto.ProductDto;
import com.redis.entity.Product;
import com.redis.service.ProductService;

@Service
public class ProductUtil  {
	
	@Autowired
	private CacheOperation cache;

	@Autowired
	private ProductService serviceInterface;

	public ProductUtil() {

		super();

	}

	public ProductDto getAllAppSetting() {

		ProductDto productDto = new ProductDto();

		if (!cache.isKeyExist("productUtil", "productUtil")) {

			List<Product> allProduct = serviceInterface.getAllSetting();

			for (Iterator<?> iterator = allProduct.iterator(); iterator.hasNext();) {

				Product product = (Product) iterator.next();
				productDto.setSettings(product.getKey(), product.getValue());

			}

			cache.addInCache("productUtil", "productUtil", productDto);

		} else {

			productDto = (ProductDto) cache.getFromCache("productUtil", "productUtil");

		}

		return productDto;

	}

}
