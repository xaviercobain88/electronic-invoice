/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.entity.FreightForwarder;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class FreightForwarderDao extends GenericDao<FreightForwarder, Integer> {

	public FreightForwarderDao() {
		super(FreightForwarder.class);

	}

}
