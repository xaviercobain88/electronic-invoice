/*
 */
package com.stf.business.facade;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.dao.TerritoryAssignedDao;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TerritoryAssignedFacade {

	@EJB
	TerritoryAssignedDao territoryAssignedDao;

}
