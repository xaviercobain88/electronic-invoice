/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.entity.SuborderBoxConfiguration;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class SuborderBoxConfigurationDao extends
		GenericDao<SuborderBoxConfiguration, Integer> {

	public SuborderBoxConfigurationDao() {
		super(SuborderBoxConfiguration.class);

	}

}
