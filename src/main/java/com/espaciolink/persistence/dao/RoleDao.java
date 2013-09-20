/*
 */
package com.espaciolink.persistence.dao;

import java.util.ArrayList;

import static com.espaciolink.persistence.util.QueryParameter.with;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.espaciolink.persistence.entity.Role;
import com.espaciolink.persistence.exception.NotFoundException;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class RoleDao extends GenericDao<Role, Integer> {

	public RoleDao() {
		super(Role.class);

	}

	/**
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Role> findAll(ArrayList<Boolean> active)
			throws NotFoundException {
		return findByNamedQuery("Role.findAll", with("active", active)
				.parameters());
	}
}
