package com.stf.persistence.enums;

public enum PermissionNameEnum {

	USER_PROFILE("User Profile"), CATALOGS("Catalogs"), GEOGRAPHIC_LOCATIONS(
			"Geographic Locations"), ROLE_PERMISSIONS("Role Permissions");

	private final String name;

	private PermissionNameEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
