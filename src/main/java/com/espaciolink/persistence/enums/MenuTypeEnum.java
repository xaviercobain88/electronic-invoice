package com.espaciolink.persistence.enums;

public enum MenuTypeEnum {

	STATIC_LINK("menu"), DYNAMIC_LINK("Dynamic Link");

	private final String name;

	private MenuTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
