package com.redis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.dto.AddSettingDto;
import com.redis.entities.AppSettingsEntity;
import com.redis.repo.AppSettingsRepository;
import com.redis.service.AppSettingServiceInterface;

 

@Service("appSettingsServiceImpl")
public class AppSettingsServiceImpl implements AppSettingServiceInterface {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppSettingsServiceImpl.class);

	@Autowired
	private AppSettingsRepository appSettingsRepository;

	@Override
	public List<AppSettingsEntity> getAllSetting() {
		LOG.info(" >> getAllSetting() >> Fetching >> Start");
		List<AppSettingsEntity> appSettings = appSettingsRepository.findAll();
		LOG.info(" >> getAllSetting() >> Fetching >> Done");
		return appSettings;
	}

	@Override
	public AppSettingsEntity addSetting(AddSettingDto settingDetail) {
		LOG.info(" >> addSetting() >> Create >> Start");
		AppSettingsEntity newSetting = new AppSettingsEntity();
		newSetting.setIsAdminOnly(settingDetail.getIsAdminOnly());
		newSetting.setKey(settingDetail.getKey());
		newSetting.setValue(settingDetail.getValue());
		AppSettingsEntity appSettingsEntity = appSettingsRepository.save(newSetting);
		LOG.info(" >> addSetting() >> Create >> Done");
		return appSettingsEntity;
	}

	@Override
	public AppSettingsEntity updateSettingById(Long id, AddSettingDto settingDetail)  {
		LOG.info(" >> updateSettingById() >> Update >> Start");
		AppSettingsEntity appSetting = appSettingsRepository.findById(id).orElseThrow();
		appSetting.setIsAdminOnly(settingDetail.getIsAdminOnly());
		appSetting.setKey(settingDetail.getKey());
		appSetting.setValue(settingDetail.getValue());
		AppSettingsEntity appSettingsEntity = appSettingsRepository.save(appSetting);
		LOG.info(" >> updateSettingById() >> Update >> Done");
		return appSettingsEntity;
	}
}