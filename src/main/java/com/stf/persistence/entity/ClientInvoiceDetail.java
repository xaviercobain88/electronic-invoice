package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the client_invoice_detail database table.
 * 
 */
@Entity
@Table(name="client_invoice_detail")
public class ClientInvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Order
    @ManyToOne
	private Order order;

	//bi-directional many-to-one association to Client
    @ManyToOne
	private User client;

    public ClientInvoiceDetail() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public User getClient() {
		return this.client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
}