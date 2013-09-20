/*
 */
package com.espaciolink.persistence.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.espaciolink.persistence.entity.User;
import com.espaciolink.persistence.exception.NotFoundException;

import static com.espaciolink.persistence.util.QueryParameter.with;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserDao extends GenericDao<User, Integer> {

	public UserDao() {
		super(User.class);

	}

	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public List<User> findByUsername(String username)
			throws NotFoundException {
		return findByNamedQuery("User.findByUsername",
				with("username", username).parameters());
	}

	/**
	 * @param email
	 * @return
	 * @throws NotFoundException
	 */
	public List<User> findByEmail(String email) throws NotFoundException {
		return findByNamedQuery("User.findByEmail", with("email", email)
				.parameters());
	}
}
