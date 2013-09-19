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
import com.stf.persistence.dao.ClientDao;
import com.stf.persistence.dao.ContactInformationDao;
import com.stf.persistence.dao.ShippingAddressDao;
import com.stf.persistence.dao.TradeReferenceDao;
import com.stf.persistence.dao.UserDao;
import com.stf.persistence.entity.BankReference;
import com.stf.persistence.entity.Client;
import com.stf.persistence.entity.ContactInformation;
import com.stf.persistence.entity.ShippingAddress;
import com.stf.persistence.entity.TradeReference;
import com.stf.persistence.entity.User;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ClientFacade {

	@EJB
	ClientDao clientDao;
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

	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public Integer findIdByUsername(String username) throws NotFoundException {
		List<Client> foundClients = clientDao.findByUsername(username);
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
	public Client findClientByUsername(String username)
			throws NotFoundException {
		List<Client> foundClients = clientDao.findByUsername(username);
		if (foundClients != null && !foundClients.isEmpty()) {
			if (foundClients.get(0).getShippingAddresses() != null) {
				foundClients.get(0).getShippingAddresses().size();
			}
			return foundClients.get(0);
		}
		return null;
	}

	/**
	 * @param client
	 * @return
	 * @throws NotSaveException
	 */
	public Client saveClient(Client client) throws NotSaveException {

		/*
		 * List Shipping Address List Contact Information List Trade Reference
		 * -> Shipping Address List Bank Reference -> Shipping Addres
		 */

		if (client.getId() == null) {
			// Save Entity User
			User user = userDao.create(client.getUser());
			// remove childreferences
			List<BankReference> bankReferences = client.getBankReferences();
			List<TradeReference> tradeReferences = client.getTradeReferences();
			List<ContactInformation> contactInformations = client
					.getContactInformationList();
			List<ShippingAddress> shippingAddresses = client
					.getShippingAddresses();
			// Save clean client
			client.setBankReferences(null);
			client.setTradeReferences(null);
			client.setContactInformationList(null);
			client.setShippingAddresses(null);
			client.setUser(user);
			Client clientSaved = clientDao.create(client);

			if (client == clientSaved)
				System.out
						.println("Si es lo mismo y por lo tanto actualiza al objeto");
			// Save Shipping Address
			for (ShippingAddress shippingAddress : shippingAddresses) {
				List<Client> clients = new ArrayList<Client>();
				clients.add(clientSaved);
				shippingAddress.setClients(clients);
				shippingAddressDao.create(shippingAddress);
			}
			// Save Contact Information
			System.out.println(contactInformations.size());
			for (ContactInformation contactInformation : contactInformations) {
				List<Client> clients = new ArrayList<Client>();
				clients.add(clientSaved);
				contactInformation.setClients(clients);
				contactInformationDao.create(contactInformation);
			}
			// Save Bank Reference
			for (BankReference bankReference : bankReferences) {
				ShippingAddress shippingAddress = shippingAddressDao
						.create(bankReference.getShippingAddress());
				bankReference.setShippingAddress(shippingAddress);
				bankReference.setClient(clientSaved);
				bankReferenceDao.create(bankReference);
			}
			// Save Trade Reference
			for (TradeReference tradeReference : tradeReferences) {
				ShippingAddress shippingAddress = shippingAddressDao
						.create(tradeReference.getShippingAddress());
				tradeReference.setShippingAddress(shippingAddress);
				tradeReference.setClient(clientSaved);
				tradeReferenceDao.create(tradeReference);
			}

		}
		return null;
		// if (client.getBankReferences() != null) {
		// System.out.println("Bank Reference: "
		// + client.getBankReferences().size());
		// for (BankReference bankReference : client.getBankReferences()) {
		// bankReference.setClient(client);
		//
		// }
		// }
		//
		// if (client.getTradeReferences() != null) {
		// System.out.println("Trade Reference: "
		// + client.getTradeReferences().size());
		// for (TradeReference tradeReference : client.getTradeReferences()) {
		// tradeReference.setClient(client);
		// }
		// }
		//
		// if (client.getShippingAddresses() != null) {
		// System.out.println("Shipping Address: "
		// + client.getShippingAddresses().size());
		// for (ShippingAddress shippingAddress : client
		// .getShippingAddresses()) {
		// }
		// }
		//
		// if (client.getId() != null) {
		// return clientDao.update(client);
		// } else {
		// return clientDao.create(client);
		// }
	}

}
