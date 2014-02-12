package com.espaciolink.persistence.enums;

public enum PermissionNameEnum {

	GENERATE_PDF_INVOICE("Generar factura PDF"), POST_INVOICE(
			"Post Invoice"), GET_INVOICE("Get Invoice");

	private final String name;

	private PermissionNameEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}