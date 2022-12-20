package com.redis.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.dto.AddSettingDto;
import com.redis.entities.AppSettingsEntity;
import com.redis.service.AppSettingServiceInterface;

@RestController
@RequestMapping("/app-setting")
public class AppSettingsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppSettingsController.class);

	@Autowired
	private AppSettingServiceInterface appSettingServiceInterface;

	@GetMapping("/")
	public ResponseEntity<?> getAllSettings() {
		LOG.info("CONTROLLER >> getAllSettings() >> fetching >> Start");
		List<AppSettingsEntity> settings = appSettingServiceInterface.getAllSetting();
		LOG.info("CONTROLLER >> getAllSettings() >> fetching >> Done");
		return new ResponseEntity<>( settings, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> addSetting( @RequestBody AddSettingDto settingBody) {
		LOG.info("CONTROLLER >> addSetting() >> fetching >> Start");
		AppSettingsEntity appSettingsEntity = appSettingServiceInterface.addSetting(settingBody);
		LOG.info("CONTROLLER >> addSetting() >> fetching >> Done");
		return new ResponseEntity<>( appSettingsEntity, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editSetting(Long id,  @RequestBody AddSettingDto settingDto) {
		try {
			LOG.info("CONTROLLER >> editSetting() >> update >> Start");
			AppSettingsEntity appSettingsEntity = appSettingServiceInterface.updateSettingById(id, settingDto);
			LOG.info("CONTROLLER >> editSetting() >> update >> Done");
			return new ResponseEntity<>(appSettingsEntity, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("CONTROLLER >> editSetting() >> update >> Exception >> Setting Not Found");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}