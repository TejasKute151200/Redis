package com.redis.config.utils;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.dto.AppSettingDto;
import com.redis.entities.AppSettingsEntity;
import com.redis.service.AppSettingServiceInterface;

 

@Service
public class AppSetting {

	@Autowired
	private CacheOperation cache;

	@Autowired
	private AppSettingServiceInterface appSettingServiceInterface;

	public AppSetting() {

		super();

		// TODO Auto-generated constructor stub
	}

	public AppSettingDto getAllAppSetting() {

		AppSettingDto appSettings = new AppSettingDto();

		if (!cache.isKeyExist("appSetting", "appSetting")) {

			List<AppSettingsEntity> allSetting = appSettingServiceInterface.getAllSetting();

			for (Iterator<?> iterator = allSetting.iterator(); iterator.hasNext();) {

				AppSettingsEntity appSettingsEntity = (AppSettingsEntity) iterator.next();
				appSettings.setSettings(appSettingsEntity.getKey(), appSettingsEntity.getValue());

			}

			cache.addInCache("appSetting", "appSetting", appSettings);

		} else {

			appSettings = (AppSettingDto) cache.getFromCache("appSetting", "appSetting");

		}

		return appSettings;

	}

}
