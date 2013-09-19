package com.stf.security;

import java.security.Principal;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.stf.business.facade.UserFacade;
import com.stf.exception.NotFoundException;
import com.stf.persistence.entity.User;

/**
 * 
 * @author adam-bien.com
 */
public class UserProvider {

	@Inject
	Principal principal;
	@EJB
	UserFacade userFacade;

	/**
	 * @return
	 */
	@Produces
	@LoggedUser
	public User fetch() {
		User user;
		System.out.println("usuario anonimo:" + principal.getName());
		try {
			user = userFacade
					.findByUsernameWithPermissions(principal.getName());
			return user;
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
