/*
 */
package com.stf.business.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.dao.BankReferenceDao;
import com.stf.persistence.dao.ContactInformationDao;
import com.stf.persistence.dao.PermissionDao;
import com.stf.persistence.dao.ShippingAddressDao;
import com.stf.persistence.dao.TradeReferenceDao;
import com.stf.persistence.dao.UserDao;
import com.stf.persistence.entity.BankReference;
import com.stf.persistence.entity.ContactInformation;
import com.stf.persistence.entity.Permission;
import com.stf.persistence.entity.ShippingAddress;
import com.stf.persistence.entity.TradeReference;
import com.stf.persistence.entity.User;
import com.stf.security.PermissionWrapper;
import com.stf.util.ParametersConstants;

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
	ShippingAddressDao shippingAddressDao;
	@EJB
	BankReferenceDao bankReferenceDao;
	@EJB
	TradeReferenceDao tradeReferenceDao;
	@EJB
	ContactInformationDao contactInformationDao;
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
		if (foundClients != null && !foundClients.isEmpty()) {
			if (foundClients.get(0).getShippingAddresses() != null) {
				foundClients.get(0).getShippingAddresses().size();
			}
			return foundClients.get(0);
		}
		return null;
	}

	/**
	 * @param user
	 * @return
	 * @throws NotSaveException
	 */
	public User saveUser(User user) throws NotSaveException {

		/*
		 * List Shipping Address List Contact Information List Trade Reference
		 * -> Shipping Address List Bank Reference -> Shipping Addres
		 */

		if (user.getId() == null) {
			// Save Entity User
			// remove childreferences
			List<BankReference> bankReferences = user.getBankReferences();
			List<TradeReference> tradeReferences = user.getTradeReferences();
			List<ContactInformation> contactInformations = user
					.getContactInformationList();
			List<ShippingAddress> shippingAddresses = user
					.getShippingAddresses();
			// Save clean user
			user.setBankReferences(null);
			user.setTradeReferences(null);
			user.setContactInformationList(null);
			user.setShippingAddresses(null);
			User userSaved = userDao.create(user);

			if (user == userSaved)
				System.out
						.println("Si es lo mismo y por lo tanto actualiza al objeto");
			// Save Shipping Address
			for (ShippingAddress shippingAddress : shippingAddresses) {
				List<User> users = new ArrayList<User>();
				users.add(userSaved);
				shippingAddress.setClients(users);
				shippingAddressDao.create(shippingAddress);
			}
			// Save Contact Information
			System.out.println(contactInformations.size());
			for (ContactInformation contactInformation : contactInformations) {
				List<User> users = new ArrayList<User>();
				users.add(userSaved);
				contactInformation.setClients(users);
				contactInformationDao.create(contactInformation);
			}
			// Save Bank Reference
			for (BankReference bankReference : bankReferences) {
				ShippingAddress shippingAddress = shippingAddressDao
						.create(bankReference.getShippingAddress());
				bankReference.setShippingAddress(shippingAddress);
				bankReference.setClient(userSaved);
				bankReferenceDao.create(bankReference);
			}
			// Save Trade Reference
			for (TradeReference tradeReference : tradeReferences) {
				ShippingAddress shippingAddress = shippingAddressDao
						.create(tradeReference.getShippingAddress());
				tradeReference.setShippingAddress(shippingAddress);
				tradeReference.setClient(userSaved);
				tradeReferenceDao.create(tradeReference);
			}

		}
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
		System.out.println(user.getPassword());
		System.out.println(user.getReTypePasssword());
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
