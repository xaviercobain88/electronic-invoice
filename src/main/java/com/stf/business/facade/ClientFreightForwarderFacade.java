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
import com.stf.exception.NotSaveException;
import com.stf.persistence.dao.ClientFreightForwarderDao;
import com.stf.persistence.entity.ClientFreightForwarder;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ClientFreightForwarderFacade {

	@EJB
	ClientFreightForwarderDao clientFreightForwarderDao;

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<ClientFreightForwarder> findByClientId(Integer clientId)
			throws NotFoundException {

		return clientFreightForwarderDao.findByClientId(clientId, true);
	}

	/**
	 * @param clientFreightForwarder
	 * @return
	 * @throws NotSaveException
	 */
	public ClientFreightForwarder save(
			ClientFreightForwarder clientFreightForwarder)
			throws NotSaveException {
		if (clientFreightForwarder != null) {
			if (clientFreightForwarder.getId() != null) {
				clientFreightForwarderDao.update(clientFreightForwarder);
			} else {
				clientFreightForwarderDao.create(clientFreightForwarder);
			}
		}
		throw new NotSaveException("Freight Forwarder is empty");
	}

}
