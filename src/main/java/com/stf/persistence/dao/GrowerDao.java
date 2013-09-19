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
import com.stf.persistence.entity.User;
import com.stf.persistence.entity.Grower;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GrowerDao extends GenericDao<Grower, Integer> {

	public GrowerDao() {
		super(Grower.class);

	}

	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public List<Grower> findByUsername(String username)
			throws NotFoundException {
		return findByNamedQuery("Grower.findByUsername",
				with("username", username).parameters());
	}

}
