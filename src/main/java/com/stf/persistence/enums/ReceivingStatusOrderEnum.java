package com.stf.persistence.enums;

public enum ReceivingStatusOrderEnum {

	OPEN("Edit"), CLOSED("Closed");

	private final String name;

	private ReceivingStatusOrderEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
