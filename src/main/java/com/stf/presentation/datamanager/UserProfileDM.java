package com.stf.presentation.datamanager;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.stf.persistence.entity.User;

@Named
@SessionScoped
public class UserProfileDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private ContactInformation principalContactInformation;
	private ContactInformation procurementContactInformation;
	private ContactInformation accountPayableContactInformation;
	private ShippingAddress billToAddress;
	private Integer countryId;
	private Boolean usernameUnique, emailUnique, reTypePasswordUnique;
	private String usernameMessage, emailMessage, reTypePasswordMessage;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ShippingAddress getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(ShippingAddress billToAddress) {
		this.billToAddress = billToAddress;
	}

	public ContactInformation getPrincipalContactInformation() {
		return principalContactInformation;
	}

	public void setPrincipalContactInformation(
			ContactInformation principalContactInformation) {
		this.principalContactInformation = principalContactInformation;
	}

	public ContactInformation getProcurementContactInformation() {
		return procurementContactInformation;
	}

	public void setProcurementContactInformation(
			ContactInformation procurementContactInformation) {
		this.procurementContactInformation = procurementContactInformation;
	}

	public ContactInformation getAccountPayableContactInformation() {
		return accountPayableContactInformation;
	}

	public void setAccountPayableContactInformation(
			ContactInformation accountPayableContactInformation) {
		this.accountPayableContactInformation = accountPayableContactInformation;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Boolean getUsernameUnique() {
		return usernameUnique;
	}

	public void setUsernameUnique(Boolean usernameUnique) {
		this.usernameUnique = usernameUnique;
	}

	public Boolean getEmailUnique() {
		return emailUnique;
	}

	public void setEmailUnique(Boolean emailUnique) {
		this.emailUnique = emailUnique;
	}

	public Boolean getReTypePasswordUnique() {
		return reTypePasswordUnique;
	}

	public void setReTypePasswordUnique(Boolean reTypePasswordUnique) {
		this.reTypePasswordUnique = reTypePasswordUnique;
	}

	public String getUsernameMessage() {
		return usernameMessage;
	}

	public void setUsernameMessage(String usernameMessage) {
		this.usernameMessage = usernameMessage;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	public String getReTypePasswordMessage() {
		return reTypePasswordMessage;
	}

	public void setReTypePasswordMessage(String reTypePasswordMessage) {
		this.reTypePasswordMessage = reTypePasswordMessage;
	}

}
