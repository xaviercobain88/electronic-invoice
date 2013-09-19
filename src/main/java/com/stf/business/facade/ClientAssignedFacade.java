/*
 */
package com.stf.business.facade;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.dao.ClientAssignedDao;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ClientAssignedFacade {

	@EJB
	ClientAssignedDao clientAssignedDao;

}
