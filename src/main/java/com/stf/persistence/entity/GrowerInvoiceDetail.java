package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the grower_invoice_detail database table.
 * 
 */
@Entity
@Table(name="grower_invoice_detail")
public class GrowerInvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Order
    @ManyToOne
	private Order order;

	//bi-directional many-to-one association to Grower
    @ManyToOne
	private Grower grower;

    public GrowerInvoiceDetail() {
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
	
	public Grower getGrower() {
		return this.grower;
	}

	public void setGrower(Grower grower) {
		this.grower = grower;
	}
	
}