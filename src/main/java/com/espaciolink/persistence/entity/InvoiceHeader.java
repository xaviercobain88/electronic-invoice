package com.espaciolink.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.espaciolink.persistence.enums.SRIStatusTypeEnum;

/**
 * The persistent class for the invoice_header database table.
 * 
 */
@Entity
@Table(name = "invoice_header")
//@XmlRootElement
@NamedQueries({ @NamedQuery(name = "InvoiceHeader.findByClaveAccesoComprobante", query = "SELECT ih FROM  InvoiceHeader ih  WHERE ih.claveAccesoComprobante = :claveAccesoComprobante") })
public class InvoiceHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String claveAccesoComprobante;
	
	
	@Column(name = "numero_autorizacion")
	private String numeroAutorizacion;

	@Column(name = "estado_sri")
	@Enumerated(EnumType.STRING)
	private SRIStatusTypeEnum permissionNameEnum;

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

	public String getClaveAccesoComprobante() {
		return claveAccesoComprobante;
	}

	public void setClaveAccesoComprobante(String claveAccesoComprobante) {
		this.claveAccesoComprobante = claveAccesoComprobante;
	}

	public SRIStatusTypeEnum getPermissionNameEnum() {
		return permissionNameEnum;
	}

	public void setPermissionNameEnum(SRIStatusTypeEnum permissionNameEnum) {
		this.permissionNameEnum = permissionNameEnum;
	}

	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}

	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}
	

}