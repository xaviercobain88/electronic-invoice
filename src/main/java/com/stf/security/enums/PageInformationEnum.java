package com.stf.security.enums;

public enum PageInformationEnum {

	NEW("New"), SYSTEM("System"), OWNER("Owner");

	private final String name;

	private PageInformationEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
