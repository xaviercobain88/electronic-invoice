package com.espaciolink.persistence.enums;

public enum PermissionNameEnum {

	GENERATE_PDF_INVOICE("Generar factura PDF");
	

	private final String name;

	private PermissionNameEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
