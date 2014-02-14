package com.espaciolink.persistence.enums;

public enum SRIStatusTypeEnum {

	RECIBIDA("RECIBIDA"),NO_RECIBIDA("NO RECIBIDA"), NO_AUTORIZADO("NO AUTORIZADO"), AUTORIZADO("AUTORIZADO");

	private final String name;

	private SRIStatusTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
