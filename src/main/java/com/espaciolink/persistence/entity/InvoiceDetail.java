package com.espaciolink.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the invoice_detail database table.
 * 
 */
@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// bi-directional many-to-one association to InvoiceHeader
	@ManyToOne
	@JoinColumn(name = "invoice_header_id")
	private InvoiceHeader invoiceHeader;

	public InvoiceDetail() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InvoiceHeader getInvoiceHeader() {
		return this.invoiceHeader;
	}

	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

}