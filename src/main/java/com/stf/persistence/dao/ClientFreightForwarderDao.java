/*
 */
package com.stf.persistence.dao;

import static com.stf.persistence.util.QueryParameter.with;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.entity.ClientFreightForwarder;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ClientFreightForwarderDao extends
		GenericDao<ClientFreightForwarder, Integer> {

	public ClientFreightForwarderDao() {
		super(ClientFreightForwarder.class);

	}

	/**
	 * @param clientId
	 * @return
	 * @throws NotFoundException
	 */
	public List<ClientFreightForwarder> findByClientId(Integer clientId, Boolean active)
			throws NotFoundException {

		return findByNamedQuery("ClientFreightForwarder.findByClientId",
				with("active", active).and("clientId", clientId).parameters());
	}

}
