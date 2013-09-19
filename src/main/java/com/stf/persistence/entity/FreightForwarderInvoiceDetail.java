package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the freight_forwarder_invoice_detail database table.
 * 
 */
@Entity
@Table(name="freight_forwarder_invoice_detail")
public class FreightForwarderInvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Order
    @ManyToOne
	private Order order;

	//bi-directional many-to-one association to FreightForwarder
    @ManyToOne
	@JoinColumn(name="freight_forwarder_id")
	private FreightForwarder freightForwarder;

    public FreightForwarderInvoiceDetail() {
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
	
	public FreightForwarder getFreightForwarder() {
		return this.freightForwarder;
	}

	public void setFreightForwarder(FreightForwarder freightForwarder) {
		this.freightForwarder = freightForwarder;
	}
	
}