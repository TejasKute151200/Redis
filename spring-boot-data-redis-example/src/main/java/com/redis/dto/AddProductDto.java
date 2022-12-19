package com.redis.dto;

public class AddProductDto {
	
	private String key;

	private String value;

	private String newValue;

	public AddProductDto() {
		super();
	}

	public AddProductDto(String key, String value, Boolean isAdminOnly) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}