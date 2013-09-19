package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the markup database table.
 * 
 */
@Entity
@Table(name="markup")
public class Markup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="markup")
	private List<User> clients;

    public Markup() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getClients() {
		return this.clients;
	}

	public void setClients(List<User> clients) {
		this.clients = clients;
	}
	
}