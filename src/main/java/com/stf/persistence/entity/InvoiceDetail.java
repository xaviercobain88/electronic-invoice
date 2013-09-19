package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the invoice_detail database table.
 * 
 */
@Entity
@Table(name="invoice_detail")
public class InvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idinvoice_detail")
	private int idinvoiceDetail;

	//bi-directional many-to-one association to InvoiceHeader
	@ManyToOne
	@JoinColumn(name="invoice_header_id")
	private InvoiceHeader invoiceHeader;

	public InvoiceDetail() {
	}

	public int getIdinvoiceDetail() {
		return this.idinvoiceDetail;
	}

	public void setIdinvoiceDetail(int idinvoiceDetail) {
		this.idinvoiceDetail = idinvoiceDetail;
	}

	public InvoiceHeader getInvoiceHeader() {
		return this.invoiceHeader;
	}

	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

}