package com.stf.persistence.enums;

public enum OrderTypeEnum {

	ONE_TIME("One-time Prebook Order",
			"This is an order description that helps user to understand this choice"), HOLIDAY(
			"Preebook for holiday",
			"This is an order description that helps user to understand this choice"), STANDING(
			"Standing Order",
			"This is an order description that helps user to understand this choice");

	private final String name, description;

	private OrderTypeEnum(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
