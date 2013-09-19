/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.entity.BoxConfigurationItem;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class BoxConfigurationItemDao extends
		GenericDao<BoxConfigurationItem, Integer> {

	public BoxConfigurationItemDao() {
		super(BoxConfigurationItem.class);

	}

}
