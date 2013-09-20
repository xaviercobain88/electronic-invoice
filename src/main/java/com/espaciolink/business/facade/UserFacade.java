/*
 */
package com.espaciolink.business.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.espaciolink.persistence.dao.PermissionDao;
import com.espaciolink.persistence.dao.UserDao;
import com.espaciolink.persistence.entity.Permission;
import com.espaciolink.persistence.entity.User;
import com.espaciolink.persistence.exception.NotFoundException;
import com.espaciolink.persistence.exception.NotSaveException;
import com.espaciolink.util.ParametersConstants;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserFacade {

	@EJB
	UserDao userDao;
	@EJB
	PermissionDao permissionDao;

	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public Integer findIdByUsername(String username) throws NotFoundException {
		List<User> foundClients = userDao.findByUsername(username);
		if (foundClients != null && !foundClients.isEmpty()) {
			return foundClients.get(0).getId();
		}
		return null;
	}

	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public User findUserByUsername(String username) throws NotFoundException {
		List<User> foundClients = userDao.findByUsername(username);
		return foundClients.get(0);
	}

	/**
	 * @param user
	 * @return
	 * @throws NotSaveException
	 */
	public User saveUser(User user) throws NotSaveException {

		return null;
	}

	/**
	 * @param user
	 * @return
	 * @throws NotFoundException
	 */
	public Boolean isValidUsername(User user) throws NotFoundException {
		if (user != null && user.getUsername() != null
				&& !user.getUsername().isEmpty()) {
			List<User> resultList = userDao.findByUsername(user.getUsername());
			if (user.getId() != null) {
				User userFinded = userDao.findById(user.getId());
				if (userFinded.getUsername().equals(user.getUsername())) {
					return true;
				}
			}
			return resultList == null || resultList.isEmpty();
		}
		return false;

	}

	/**
	 * @param user
	 * @return
	 * @throws NotFoundException
	 */
	public Boolean isValidEmail(User user) throws NotFoundException {
		if (user != null && user.geteMail() != null
				&& !user.geteMail().isEmpty()) {
			List<User> resultList = userDao.findByEmail(user.geteMail());
			if (user.getId() != null) {
				User userFinded = userDao.findById(user.getId());
				if (userFinded.geteMail().equals(user.geteMail())) {
					return true;
				}
			}
			return resultList == null || resultList.isEmpty();
		}
		return false;
	}

	/**
	 * @param password
	 * @param reTypedPassword
	 * @return
	 */
	public Boolean isValidPassword(User user) {
		if (user != null && user.getPassword() != null
				&& !user.getPassword().isEmpty()
				&& user.getPassword().equals(user.getReTypePasssword())) {
			return true;
		}
		return false;

	}

	
	
	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public User findByUsernameWithPermissions(String username)
			throws NotFoundException {

		if (username != null && username.equals(ParametersConstants.ANONYMOUS)) {
			User user = new User();
			user.setUsername(username);
			ArrayList<Boolean> activeList = new ArrayList<Boolean>();
			activeList.add(true);
			user.setPermissions(permissionDao.findByRole(username, activeList));
			return user;
		}

		List<User> users = userDao.findByUsername(username);
		User user = null;
		List<Permission> permissions = null;
		if (users != null && !users.isEmpty()) {
			ArrayList<Boolean> activeList = new ArrayList<Boolean>();
			activeList.add(true);
			permissions = permissionDao.findByUsername(username, activeList);
			if (permissions != null && !permissions.isEmpty()) {
				user = users.get(0);
				user.setPermissions(permissions);
			}
		}

		return user;

	}

}
