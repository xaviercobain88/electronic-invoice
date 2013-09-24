package com.espaciolink.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the electronic_invoice database table.
 * 
 */
@Entity
@Table(name = "electronic_invoice")
public class ElectronicInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "pdf_url")
	private String pdfUrl;

	@Column(name = "request_date")
	private Timestamp requestDate;

	@Column(name = "response_date")
	private Timestamp responseDate;

	@Column(name = "xml_url")
	private String xmlUrl;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	// bi-directional many-to-one association to InvoiceHeader
	@ManyToOne
	@JoinColumn(name = "invoice_header_id")
	private InvoiceHeader invoiceHeader;

	public ElectronicInvoice() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPdfUrl() {
		return this.pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public Timestamp getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public Timestamp getResponseDate() {
		return this.responseDate;
	}

	public void setResponseDate(Timestamp responseDate) {
		this.responseDate = responseDate;
	}

	public String getXmlUrl() {
		return this.xmlUrl;
	}

	public void setXmlUrl(String xmlUrl) {
		this.xmlUrl = xmlUrl;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public InvoiceHeader getInvoiceHeader() {
		return this.invoiceHeader;
	}

	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

}