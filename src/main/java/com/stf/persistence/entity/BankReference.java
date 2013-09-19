package com.stf.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the comission database table.
 * 
 */
@Entity
@Table(name = "bank_reference")
public class BankReference extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "institution_name", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String institutionName;

	@Column(name = "checking_account_number", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String checkingAccountNumber;

	@Column(name = "open_since", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String openSince;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id")
	@Valid
	private ShippingAddress shippingAddress;

	@Column(name = "phone_number", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String phoneNumber;

	@Column(name = "fax_number")
	private String faxNumber;

	@Column(name = "e_mail")
	private String eMail;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;

	public BankReference() {
		this.active = true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getCheckingAccountNumber() {
		return checkingAccountNumber;
	}

	public void setCheckingAccountNumber(String checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
	}

	public String getOpenSince() {
		return openSince;
	}

	public void setOpenSince(String openSince) {
		this.openSince = openSince;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public Object getComboBoxValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getComboBoxLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}