/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.entity.GrowerInvoiceDetail;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GrowerInvoiceDetailDao extends
		GenericDao<GrowerInvoiceDetail, Integer> {

	public GrowerInvoiceDetailDao() {
		super(GrowerInvoiceDetail.class);

	}

}
