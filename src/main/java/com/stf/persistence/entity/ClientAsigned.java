package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the client_asigned database table.
 * 
 */
@Entity
@Table(name="client_asigned")
public class ClientAsigned implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	private Employee employee;

	//bi-directional many-to-one association to Client
    @ManyToOne
	private User client;

    public ClientAsigned() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public User getClient() {
		return this.client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
}