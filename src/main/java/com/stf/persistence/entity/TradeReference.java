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
@Table(name = "trade_reference")
public class TradeReference extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "company_name", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String companyName;

	@Column(name = "checking_account_number")
	private String checkingAccountNumber;

	@Column(name = "contact_name", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String contactName;

	@Column(name = "open_since")
	private String openSince;

	@Column(name = "credit_terms")
	private String creditTerms;

	@Column(name = "credit_limit")
	private String creditLimit;

	@Column(name = "current_balance")
	private String current_balance;

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

	public TradeReference() {
		this.active = true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCheckingAccountNumber() {
		return checkingAccountNumber;
	}

	public void setCheckingAccountNumber(String checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getOpenSince() {
		return openSince;
	}

	public void setOpenSince(String openSince) {
		this.openSince = openSince;
	}

	public String getCreditTerms() {
		return creditTerms;
	}

	public void setCreditTerms(String creditTerms) {
		this.creditTerms = creditTerms;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(String current_balance) {
		this.current_balance = current_balance;
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