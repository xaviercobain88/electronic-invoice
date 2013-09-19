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
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.stf.persistence.enums.ContactInformationTypeEnum;
import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@Table(name = "contact_information")
public class ContactInformation extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String title;

	@Column(nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String name;

	@Column(name = "last_name", nullable = false)
	@NotEmpty(message = "Field name can't be empty")
	private String lastName;

	@Column(name = "day_time_telephone", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String dayTimeTelephone;

	@Column(name = "mobile_number")
	private String mobileNumer;

	@Column(name = "fax_number")
	private String faxNumber;

	@Column(name = "e_mail", nullable = false)
	@NotEmpty(message = "Field name can't be empty")
	private String eMail;

	@Column(name = "contact_information_type")
	@Enumerated(EnumType.STRING)
	private ContactInformationTypeEnum contactInformationTypeEnum;

	@ManyToMany
	@JoinTable(name = "client_contact_information", joinColumns = { @JoinColumn(name = "contact_information_id") }, inverseJoinColumns = { @JoinColumn(name = "client_id") })
	private List<User> clients;

	public ContactInformation() {
		active = true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDayTimeTelephone() {
		return dayTimeTelephone;
	}

	public void setDayTimeTelephone(String dayTimeTelephone) {
		this.dayTimeTelephone = dayTimeTelephone;
	}

	public String getMobileNumer() {
		return mobileNumer;
	}

	public ContactInformationTypeEnum getContactInformationTypeEnum() {
		return contactInformationTypeEnum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setContactInformationTypeEnum(
			ContactInformationTypeEnum contactInformationTypeEnum) {
		this.contactInformationTypeEnum = contactInformationTypeEnum;
	}

	public void setMobileNumer(String mobileNumer) {
		this.mobileNumer = mobileNumer;
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

	public List<User> getClients() {
		return clients;
	}

	public void setClients(List<User> clients) {
		this.clients = clients;
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