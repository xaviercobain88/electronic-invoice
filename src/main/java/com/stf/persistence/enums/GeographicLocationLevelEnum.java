package com.stf.persistence.enums;

public enum GeographicLocationLevelEnum {

	LEVEL_1("Country"), LEVEL_2("State/Province");

	private final String name;

	private GeographicLocationLevelEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
