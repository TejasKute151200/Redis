//package com.redis.service;
//
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.redis.dto.AddProductDto;
//import com.redis.entity.Product;
//import com.redis.repo.ProductRepo;
//
//
//@Service
//public class ProductServiceImpl implements ProductService{
//	
//	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
//
//	private ProductRepo productRepo;
//
//	@Override
//	public List<Product> getAllSetting() {
//		return productRepo.findAll();
//	}
//
//	@Override
//	public Product addSetting(AddProductDto settingDetail) {
//		Product newSetting = new Product();
//		newSetting.setKey(settingDetail.getKey());
//		newSetting.setValue(settingDetail.getValue());
//		Product newProduct = productRepo.save(newSetting);
//		return newProduct;
//	}
//
//	@Override
//	public Product updateSettingById(Long id, AddProductDto settingDetail) throws Exception {
//		LOG.info(" >> updateSettingById() >> Update >> Start");
//		Product appSetting = productRepo.findProductById(id);
//		appSetting.setKey(settingDetail.getKey());
//		appSetting.setValue(settingDetail.getValue());
//		Product appSettingsEntity = productRepo.save(appSetting);
//		LOG.info(" >> updateSettingById() >> Update >> Done");
//		return appSettingsEntity;
//	}
//}