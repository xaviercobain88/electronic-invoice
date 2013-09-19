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
public class CreditTransactionDao extends
		GenericDao<CreditTransactionDao, Integer> {

	public CreditTransactionDao() {
		super(CreditTransactionDao.class);

	}

}
