/*
 */
package com.stf.persistence.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.persistence.entity.Client;
import static com.stf.persistence.util.QueryParameter.with;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ClientDao extends GenericDao<Client, Integer> {

	public ClientDao() {
		super(Client.class);

	}

	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public List<Client> findByUsername(String username)
			throws NotFoundException {
		return findByNamedQuery("Client.findByUsername",
				with("username", username).parameters());
	}
}
