package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the credit database table.
 * 
 */
@Entity
@Table(name="credit")
public class Credit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Client
    @ManyToOne
	private User client;

	//bi-directional many-to-one association to CreditTransaction
	@OneToMany(mappedBy="credit")
	private List<CreditTransaction> creditTransactions;

    public Credit() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getClient() {
		return this.client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public List<CreditTransaction> getCreditTransactions() {
		return this.creditTransactions;
	}

	public void setCreditTransactions(List<CreditTransaction> creditTransactions) {
		this.creditTransactions = creditTransactions;
	}
	
}