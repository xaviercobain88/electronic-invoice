package com.stf.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the freight_forwarder database table.
 * 
 */
@Entity
@Table(name = "freight_forwarder")
public class ClientFreightForwarder extends ComboBoxModel implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@ManyToOne
	private User client;

	private String name;

	private String accountNumber;

	private String contactPerson;

	private String fax;

	private String eMail;

	private String streetAddress;

	private String country;

	private String state;

	private String city;

	private String zipCode;

	public ClientFreightForwarder() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public Object getComboBoxValue() {
		return id;
	}

	@Override
	public String getComboBoxLabel() {
		return name;
	}

}