/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ClientInvoiceDetailDao extends
		GenericDao<ClientInvoiceDetailDao, Integer> {

	public ClientInvoiceDetailDao() {
		super(ClientInvoiceDetailDao.class);

	}

}
