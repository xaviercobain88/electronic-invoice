package com.stf.security.enums;

public enum PermissionTypeEnum {

	READ_OWN("Read Own"), WRITE_OWN("Write Own"), EXECUTE_OWN("Execute Own"), READ_OTHER(
			"Read Other"), WRITE_OTHER("Write Other"), EXECUTE_OTHER(
			"Execute Other");

	private final String name;

	private PermissionTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
