package com.stf.persistence.enums;

public enum ContactInformationTypeEnum {

	PRINCIPAL("Principal Responsible for Business Transactions"), PROCUREMENT(
			"Person in Charge of Procurement"), ACCOUNT_PAYABLE(
			"Person in Charge of Accounts Payable");

	private final String name;

	private ContactInformationTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
