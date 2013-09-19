package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the freight_forwarder database table.
 * 
 */
@Entity
@Table(name="freight_forwarder")
public class FreightForwarder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to User
    @ManyToOne
	private User user;

	//bi-directional many-to-one association to FreightForwarderInvoiceDetail
	@OneToMany(mappedBy="freightForwarder")
	private List<FreightForwarderInvoiceDetail> freightForwarderInvoiceDetails;

    public FreightForwarder() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<FreightForwarderInvoiceDetail> getFreightForwarderInvoiceDetails() {
		return this.freightForwarderInvoiceDetails;
	}

	public void setFreightForwarderInvoiceDetails(List<FreightForwarderInvoiceDetail> freightForwarderInvoiceDetails) {
		this.freightForwarderInvoiceDetails = freightForwarderInvoiceDetails;
	}
	
}