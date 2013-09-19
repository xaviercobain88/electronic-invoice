package com.stf.security.enums;

public enum PermissionNameEnum {

	USER_PROFILE("User Profile"), CATALOGS("Catalogs");

	private final String name;

	private PermissionNameEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
