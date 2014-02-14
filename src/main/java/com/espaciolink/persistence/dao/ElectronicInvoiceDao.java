/*
 */
package com.espaciolink.persistence.dao;

import static com.espaciolink.persistence.util.QueryParameter.with;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.espaciolink.persistence.entity.InvoiceHeader;
import com.espaciolink.persistence.exception.NotFoundException;
import com.espaciolink.persistence.exception.NotSaveException;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ElectronicInvoiceDao extends GenericDao<InvoiceHeader, Integer> {

	public ElectronicInvoiceDao() {
		super(InvoiceHeader.class);

	}

	public InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader)
			throws NotSaveException {
		
		return create(invoiceHeader);
	}
	
	public InvoiceHeader updateInvoiceHeader(InvoiceHeader invoiceHeader)
			throws NotSaveException {
		
		return update(invoiceHeader);
	}
	
	/**
	 * @param username
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<InvoiceHeader> findByClaveAccesoComprobante(String claveAccesoComprobante) throws NotFoundException {

		return findByNamedQuery("InvoiceHeader.findByClaveAccesoComprobante",
				with("claveAccesoComprobante", claveAccesoComprobante).parameters());

	}
	
}
