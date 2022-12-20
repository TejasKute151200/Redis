package com.redis.service;

import java.util.List;

import com.redis.dto.AddSettingDto;
import com.redis.entities.AppSettingsEntity;

public interface AppSettingServiceInterface {
	public List<AppSettingsEntity> getAllSetting();

	public AppSettingsEntity addSetting(AddSettingDto settingDetail);

	public AppSettingsEntity updateSettingById(Long id, AddSettingDto settingDetail);
}