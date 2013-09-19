package com.stf.persistence.enums;

public enum OrderStatusForOrderEnum {

	OPEN("Edit"), CLOSED("Closed");

	private final String name;

	private OrderStatusForOrderEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
