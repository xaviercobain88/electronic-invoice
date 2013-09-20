package com.espaciolink.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the institution database table.
 * 
 */
@Entity
@Table(name = "institution")
public class Institution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(columnDefinition = "BIT")
	private Boolean active;

	@Column(name = "certificate_url")
	private String certificateUrl;

	private String salt;

	// bi-directional many-to-one association to User
	@OneToMany(mappedBy = "institution")
	private List<User> users;

	public Institution() {
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

	public String getCertificateUrl() {
		return this.certificateUrl;
	}

	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setInstitution(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setInstitution(null);

		return user;
	}

}