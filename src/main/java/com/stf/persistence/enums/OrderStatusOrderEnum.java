package com.stf.persistence.enums;

public enum OrderStatusOrderEnum {

	OPEN("Edit"), CLOSED("Closed");

	private final String name;

	private OrderStatusOrderEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
