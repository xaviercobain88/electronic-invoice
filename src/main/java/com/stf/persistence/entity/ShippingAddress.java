package com.stf.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.stf.persistence.enums.AddressTypeEnum;
import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the shipping_address database table.
 * 
 */
@Entity
@Table(name = "shipping_address")
@NamedQueries({ @NamedQuery(name = "ShippingAddress.findByClientId", query = "SELECT sa FROM ShippingAddress sa JOIN sa.clients c WHERE sa.active = :active AND c.id = :clientId") })
public class ShippingAddress extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "zip_code", nullable=false)
	@NotEmpty(message = "Field can't be empty")
	private String zipCode;

	@Column(name = "number_street", nullable=false)
	@NotEmpty(message = "Field can't be empty")
	private String numberString;

	@Column(name = "suite_unit")
	private String suiteUnit;

	@Column(name = "address_type")
	@Enumerated(EnumType.STRING)
	private AddressTypeEnum addressTypeEnum;

	// bi-directional many-to-one association to Client
	@ManyToMany
	@JoinTable(name = "client_shipping_address", joinColumns = { @JoinColumn(name = "shipping_address_id") }, inverseJoinColumns = { @JoinColumn(name = "client_id") })
	private List<User> clients;

	// bi-directional many-to-one association to GeographicLocation
	@ManyToOne
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private GeographicLocation country;

	@ManyToOne
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	private GeographicLocation state;

	@Column(name = "country_id", nullable=false)
	@NotNull(message = "Field can't be empty")
	private Integer countryId;

	@Column(name = "state_id", nullable=false)
	@NotNull(message = "Field can't be empty")
	private Integer stateId;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "shippingAddress")
	private List<Order> orders;

	@OneToMany(mappedBy = "shippingAddress")
	private List<BankReference> bank_References;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShippingAddress() {
		this.active = true;
	}

	public List<User> getClients() {
		return clients;
	}

	public void setClients(List<User> clients) {
		this.clients = clients;
	}

	@Override
	public Object getComboBoxValue() {
		return id;
	}

	@Override
	public String getComboBoxLabel() {
		if (state != null) {
			return state.getName() + ", zipcode: " + zipCode;
		} else {
			return "zipcode: " + zipCode;
		}
	}

	public GeographicLocation getCountry() {
		return country;
	}

	public void setCountry(GeographicLocation country) {
		this.country = country;
	}

	public GeographicLocation getState() {
		return state;
	}

	public void setState(GeographicLocation state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getNumberString() {
		return numberString;
	}

	public void setNumberString(String numberString) {
		this.numberString = numberString;
	}

	public String getSuiteUnit() {
		return suiteUnit;
	}

	public void setSuiteUnit(String suiteUnit) {
		this.suiteUnit = suiteUnit;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public AddressTypeEnum getAddressTypeEnum() {
		return addressTypeEnum;
	}

	public void setAddressTypeEnum(AddressTypeEnum addressTypeEnum) {
		this.addressTypeEnum = addressTypeEnum;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public List<BankReference> getBank_References() {
		return bank_References;
	}

	public void setBank_References(List<BankReference> bank_References) {
		this.bank_References = bank_References;
	}

}