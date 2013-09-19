package com.stf.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@ManyToOne
	private User user;

	public Client() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}