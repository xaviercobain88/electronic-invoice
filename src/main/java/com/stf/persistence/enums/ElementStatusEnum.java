package com.stf.persistence.enums;

public enum ElementStatusEnum {

	PENDING("One-time Prebook Order"), ACTIVE("Active"), BLOCKED("Blocked");

	private final String name;

	private ElementStatusEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
