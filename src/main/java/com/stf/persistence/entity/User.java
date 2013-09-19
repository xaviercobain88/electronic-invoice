package com.stf.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

/**
 * The persistent class for the user database table.
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(columnDefinition = "BIT")
	private Boolean active;

	private String password;

	private String username;

	private String eMail;

	@OneToMany(mappedBy = "user")
	private List<ElectronicInvoice> electronicInvoices;

	@ManyToOne
	private Institution institution;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> roles;

	@Transient
	private List<Permission> permissions;
	
	@Transient
    private String reTypePasssword;

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ElectronicInvoice> getElectronicInvoices() {
		return this.electronicInvoices;
	}

	public void setElectronicInvoices(List<ElectronicInvoice> electronicInvoices) {
		this.electronicInvoices = electronicInvoices;
	}

	public ElectronicInvoice addElectronicInvoice(
			ElectronicInvoice electronicInvoice) {
		getElectronicInvoices().add(electronicInvoice);
		electronicInvoice.setUser(this);

		return electronicInvoice;
	}

	public ElectronicInvoice removeElectronicInvoice(
			ElectronicInvoice electronicInvoice) {
		getElectronicInvoices().remove(electronicInvoice);
		electronicInvoice.setUser(null);

		return electronicInvoice;
	}

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getReTypePasssword() {
		return reTypePasssword;
	}

	public void setReTypePasssword(String reTypePasssword) {
		this.reTypePasssword = reTypePasssword;
	}
	

}