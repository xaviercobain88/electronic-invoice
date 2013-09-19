package com.stf.persistence.enums;

public enum AddressTypeEnum {

	BILL("Bill to Address"), SHIP("Ship to Address");

	private final String name;

	private AddressTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
