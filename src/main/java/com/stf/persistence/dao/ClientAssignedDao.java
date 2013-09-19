/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.entity.ClientAsigned;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ClientAssignedDao extends GenericDao<ClientAsigned, Integer> {

	public ClientAssignedDao() {
		super(ClientAsigned.class);

	}

}
