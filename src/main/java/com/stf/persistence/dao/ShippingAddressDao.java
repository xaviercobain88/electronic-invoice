/*
 */
package com.stf.persistence.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.persistence.entity.ShippingAddress;
import static com.stf.persistence.util.QueryParameter.with;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ShippingAddressDao extends GenericDao<ShippingAddress, Integer> {

	public ShippingAddressDao() {
		super(ShippingAddress.class);

	}

	/**
	 * @param clientId
	 * @return
	 * @throws NotFoundException
	 */
	public List<ShippingAddress> findByClientId(Integer clientId)
			throws NotFoundException {

		return findByNamedQuery("ShippingAddress.findByClientId",
				with("active", true).and("clientId", clientId).parameters());

	}
}
