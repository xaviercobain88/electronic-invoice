package com.stf.persistence.enums;

public enum PointTypeEnum {

	POO("Point of Origin"), POE("Point of Entry"), POS("Point of Sale");

	private final String name;

	private PointTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
