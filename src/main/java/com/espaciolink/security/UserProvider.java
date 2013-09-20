package com.espaciolink.security;

import java.security.Principal;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.espaciolink.business.facade.UserFacade;
import com.espaciolink.persistence.entity.User;
import com.espaciolink.persistence.exception.NotFoundException;

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
