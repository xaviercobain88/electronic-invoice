package com.espaciolink.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the invoice_header database table.
 * 
 */
@Entity
@Table(name = "invoice_header")
@XmlRootElement
public class InvoiceHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	// bi-directional many-to-one association to ElectronicInvoice
	@OneToMany(mappedBy = "invoiceHeader")
	@XmlTransient
	private List<ElectronicInvoice> electronicInvoices;

	// bi-directional many-to-one association to InvoiceDetail
	@OneToMany(mappedBy = "invoiceHeader")
	private List<InvoiceDetail> invoiceDetails;

	public InvoiceHeader() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ElectronicInvoice> getElectronicInvoices() {
		return this.electronicInvoices;
	}

	public void setElectronicInvoices(List<ElectronicInvoice> electronicInvoices) {
		this.electronicInvoices = electronicInvoices;
	}

	public ElectronicInvoice addElectronicInvoice(
			ElectronicInvoice electronicInvoice) {
		getElectronicInvoices().add(electronicInvoice);
		electronicInvoice.setInvoiceHeader(this);

		return electronicInvoice;
	}

	public ElectronicInvoice removeElectronicInvoice(
			ElectronicInvoice electronicInvoice) {
		getElectronicInvoices().remove(electronicInvoice);
		electronicInvoice.setInvoiceHeader(null);

		return electronicInvoice;
	}

	public List<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public InvoiceDetail addInvoiceDetail(InvoiceDetail invoiceDetail) {
		getInvoiceDetails().add(invoiceDetail);
		invoiceDetail.setInvoiceHeader(this);

		return invoiceDetail;
	}

	public InvoiceDetail removeInvoiceDetail(InvoiceDetail invoiceDetail) {
		getInvoiceDetails().remove(invoiceDetail);
		invoiceDetail.setInvoiceHeader(null);

		return invoiceDetail;
	}

}