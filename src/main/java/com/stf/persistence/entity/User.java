package com.stf.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.stf.persistence.enums.ElementStatusEnum;

/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@Table(name = "user")
@NamedQueries({
		@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.eMail = :email") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String username;

	@Column(nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String password;

	@Column(nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String eMail;

	@Column(name = "element_status")
	@Enumerated(EnumType.STRING)
	private ElementStatusEnum elementStatusEnum;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> roles;

	@Transient
	private String reTypePasssword;

	@Column(name = "company_name", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String companyName;

	@Column(name = "tax_id_number", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String taxIdNumber;

	@Column(name = "asigned_status")
	private String asignedStatus;

	@Column(name = "company_type_catalog", nullable = false)
	@NotNull(message = "Field number can't be empty")
	private Integer companyTypeId;

	@Column(name = "legal_formation_catalog", nullable = false)
	@NotNull(message = "Field number can't be empty")
	private Integer legalFormationId;

	@Column(name = "company_phone_number", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String companyPhoneNumber;

	@Column(name = "company_fax_number")
	private String companyFaxNumber;

	@Column(name = "company_email")
	private String companyEMail;

	@Column(name = "company_website")
	private String companyWebsite;

	@Column(name = "in_business_since", nullable = false)
	@NotEmpty(message = "Field can't be empty")
	private String inBusinessSince;

	@Column(name = "employees_number", nullable = false)
	@NotNull(message = "Field can't be empty")
	private Integer employeesNumber;

	@Column(name = "yearly_sales_volume", nullable = false)
	@NotNull(message = "Field can't be empty")
	private BigDecimal yearlySalesVolume;

	@Column(name = "requested_credit_limit", nullable = false)
	@NotNull(message = "Field can't be empty")
	private BigDecimal requestedCreditLimit;

	@ManyToOne
	@JoinColumn(name = "company_type_catalog", insertable = false, updatable = false)
	private Catalog companyType;

	@ManyToOne
	@JoinColumn(name = "legal_formation_catalog", insertable = false, updatable = false)
	private Catalog legalFormation;

	@Column(columnDefinition = "BIT")
	private Boolean active;

	// bi-directional many-to-one association to GeographicLocation
	@ManyToOne
	@JoinColumn(name = "territory_id")
	private Territory territory;

	// bi-directional many-to-one association to Markup
	@ManyToOne
	private Markup markup;

	// bi-directional many-to-one association to ClientAsigned
	@OneToMany(mappedBy = "client")
	private List<ClientAsigned> clientAsigneds;

	// bi-directional many-to-one association to ClientInvoiceDetail
	@OneToMany(mappedBy = "client")
	private List<ClientInvoiceDetail> clientInvoiceDetails;

	// bi-directional many-to-one association to Credit
	@OneToMany(mappedBy = "client")
	private List<Credit> credits;

	@OneToMany(mappedBy = "client")
	private List<ClientFreightForwarder> clientFreightForwarders;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	@Valid
	private List<BankReference> bankReferences;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	@Valid
	private List<TradeReference> tradeReferences;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "client")
	private List<Order> orders;

	// bi-directional many-to-one association to ShippingAddress
	@ManyToMany(mappedBy = "clients", cascade = CascadeType.ALL)
	@Valid
	private List<ShippingAddress> shippingAddresses;

	@ManyToMany(mappedBy = "clients")
	@Valid
	private List<ContactInformation> contactInformationList;

	// bi-directional many-to-one association to Client
	@OneToMany(mappedBy = "user")
	private List<Client> clients;

	// bi-directional many-to-one association to Employee
	@OneToMany(mappedBy = "user")
	private List<Employee> employees;

	// bi-directional many-to-one association to FreightForwarder
	@OneToMany(mappedBy = "user")
	private List<FreightForwarder> freightForwarders;

	// bi-directional many-to-one association to Grower
	@OneToMany(mappedBy = "user")
	private List<Grower> growers;

	@Transient
	private List<Permission> permissions;

	public User() {
		elementStatusEnum = ElementStatusEnum.PENDING;
		active = true;
	}

	public String getAsignedStatus() {
		return this.asignedStatus;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setAsignedStatus(String asignedStatus) {
		this.asignedStatus = asignedStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public ElementStatusEnum getElementStatusEnum() {
		return elementStatusEnum;
	}

	public void setElementStatusEnum(ElementStatusEnum elementStatusEnum) {
		this.elementStatusEnum = elementStatusEnum;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getReTypePasssword() {
		return reTypePasssword;
	}

	public void setReTypePasssword(String reTypePasssword) {
		this.reTypePasssword = reTypePasssword;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<FreightForwarder> getFreightForwarders() {
		return freightForwarders;
	}

	public void setFreightForwarders(List<FreightForwarder> freightForwarders) {
		this.freightForwarders = freightForwarders;
	}

	public List<Grower> getGrowers() {
		return growers;
	}

	public void setGrowers(List<Grower> growers) {
		this.growers = growers;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Markup getMarkup() {
		return this.markup;
	}

	public void setMarkup(Markup markup) {
		this.markup = markup;
	}

	public List<ClientAsigned> getClientAsigneds() {
		return this.clientAsigneds;
	}

	public void setClientAsigneds(List<ClientAsigned> clientAsigneds) {
		this.clientAsigneds = clientAsigneds;
	}

	public List<BankReference> getBankReferences() {
		return bankReferences;
	}

	public void setBankReferences(List<BankReference> bankReferences) {
		this.bankReferences = bankReferences;
	}

	public List<TradeReference> getTradeReferences() {
		return tradeReferences;
	}

	public void setTradeReferences(List<TradeReference> tradeReferences) {
		this.tradeReferences = tradeReferences;
	}

	public List<ClientInvoiceDetail> getClientInvoiceDetails() {
		return this.clientInvoiceDetails;
	}

	public void setClientInvoiceDetails(
			List<ClientInvoiceDetail> clientInvoiceDetails) {
		this.clientInvoiceDetails = clientInvoiceDetails;
	}

	public List<Credit> getCredits() {
		return this.credits;
	}

	public void setCredits(List<Credit> credits) {
		this.credits = credits;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<ShippingAddress> getShippingAddresses() {
		return this.shippingAddresses;
	}

	public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<ClientFreightForwarder> getClientFreightForwarders() {
		return clientFreightForwarders;
	}

	public void setClientFreightForwarders(
			List<ClientFreightForwarder> clientFreightForwarders) {
		this.clientFreightForwarders = clientFreightForwarders;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaxIdNumber() {
		return taxIdNumber;
	}

	public void setTaxIdNumber(String taxIdNumber) {
		this.taxIdNumber = taxIdNumber;
	}

	public Integer getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(Integer companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public Integer getLegalFormationId() {
		return legalFormationId;
	}

	public void setLegalFormationId(Integer legalFormationId) {
		this.legalFormationId = legalFormationId;
	}

	public Catalog getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Catalog companyType) {
		this.companyType = companyType;
	}

	public Catalog getLegalFormation() {
		return legalFormation;
	}

	public void setLegalFormation(Catalog legalFormation) {
		this.legalFormation = legalFormation;
	}

	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}

	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}

	public String getCompanyFaxNumber() {
		return companyFaxNumber;
	}

	public void setCompanyFaxNumber(String companyFaxNumber) {
		this.companyFaxNumber = companyFaxNumber;
	}

	public String getCompanyEMail() {
		return companyEMail;
	}

	public void setCompanyEMail(String companyEMail) {
		this.companyEMail = companyEMail;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public List<ContactInformation> getContactInformationList() {
		return contactInformationList;
	}

	public void setContactInformationList(
			List<ContactInformation> contactInformationList) {
		this.contactInformationList = contactInformationList;
	}

	public String getInBusinessSince() {
		return inBusinessSince;
	}

	public void setInBusinessSince(String inBusinessSince) {
		this.inBusinessSince = inBusinessSince;
	}

	public Integer getEmployeesNumber() {
		return employeesNumber;
	}

	public void setEmployeesNumber(Integer employeesNumber) {
		this.employeesNumber = employeesNumber;
	}

	public BigDecimal getYearlySalesVolume() {
		return yearlySalesVolume;
	}

	public void setYearlySalesVolume(BigDecimal yearlySalesVolume) {
		this.yearlySalesVolume = yearlySalesVolume;
	}

	public BigDecimal getRequestedCreditLimit() {
		return requestedCreditLimit;
	}

	public void setRequestedCreditLimit(BigDecimal requestedCreditLimit) {
		this.requestedCreditLimit = requestedCreditLimit;
	}

}