package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the institution database table.
 * 
 */
@Entity
@Table(name="institution")
public class Institution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private byte active;

	@Column(name="certificate_url")
	private String certificateUrl;

	private String salt;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="institution")
	private List<User> users;

	public Institution() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
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