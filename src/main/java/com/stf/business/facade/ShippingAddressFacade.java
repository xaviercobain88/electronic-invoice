/*
 */
package com.stf.business.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.persistence.dao.ShippingAddressDao;
import com.stf.persistence.entity.ShippingAddress;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ShippingAddressFacade {

	@EJB
	ShippingAddressDao shippingAddressDao;

	/**
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public List<ShippingAddress> findByClientId(Integer id)
			throws NotFoundException {
		return shippingAddressDao.findByClientId(id);

	}

}
