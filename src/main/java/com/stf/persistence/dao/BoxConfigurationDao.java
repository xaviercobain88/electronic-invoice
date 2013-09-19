/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.entity.BoxConfiguration;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class BoxConfigurationDao extends GenericDao<BoxConfiguration, Integer> {

	public BoxConfigurationDao() {
		super(BoxConfiguration.class);

	}

}
