package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the credit_transaction database table.
 * 
 */
@Entity
@Table(name="credit_transaction")
public class CreditTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Credit
    @ManyToOne
	private Credit credit;

    public CreditTransaction() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Credit getCredit() {
		return this.credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	
}