/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.entity.TerritoryAsigned;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class TerritoryAssignedDao extends GenericDao<TerritoryAsigned, Integer> {

	public TerritoryAssignedDao() {
		super(TerritoryAsigned.class);

	}

}
