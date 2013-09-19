package com.stf.presentation.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.stf.business.facade.CatalogFacade;
import com.stf.business.facade.ClientFacade;
import com.stf.business.facade.UserFacade;
import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.entity.BankReference;
import com.stf.persistence.entity.Client;
import com.stf.persistence.entity.ContactInformation;
import com.stf.persistence.entity.ShippingAddress;
import com.stf.persistence.entity.TradeReference;
import com.stf.persistence.entity.User;
import com.stf.persistence.enums.AddressTypeEnum;
import com.stf.persistence.enums.ContactInformationTypeEnum;
import com.stf.persistence.util.ComboBoxModel;
import com.stf.presentation.datamanager.ClientProfileDM;
import com.stf.presentation.datamanager.ErrorPageDM;

@Named
@RequestScoped
@URLMappings(mappings = {
		@URLMapping(id = "editClientProfile1", pattern = "/client/#{userProfileDM.username}", viewId = "/pages/client-profile.jsf"),
		@URLMapping(id = "editClientProfile2", pattern = "/client/#{userProfileDM.username}/", viewId = "/pages/client-profile.jsf"),
		@URLMapping(id = "newClientProfile1", pattern = "/client/", viewId = "/pages/client-profile.jsf"),
		@URLMapping(id = "newClientProfile2", pattern = "/client", viewId = "/pages/client-profile.jsf") })
public class ClientProfileAction extends BaseController {

	@EJB
	ClientFacade clientFacade;
	@EJB
	CatalogFacade catalogFacade;
	@EJB
	UserFacade userFacade;

	@Inject
	ClientProfileDM clientProfileDM;
	@Inject
	ErrorPageDM errorPageDM;

	/**
	 * Dumb method for triggering {@link PostConstruct} on page
	 * 
	 * @return
	 */
	public String getInit() {
		return "";
	}

	/**
	 * Init Method
	 */
	@URLAction(onPostback = false)
	public String init() {

		try {
			if (clientProfileDM.getUsername() != null) {

				clientProfileDM.setClient(clientFacade
						.findClientByUsername(clientProfileDM.getUsername()));
			} else {
				User user = new User();
				Client client = new Client();
				client.setUser(user);
				clientProfileDM.setClient(client);
				addContactInformationReferences();
				clientProfileDM.setBillToAddress(new ShippingAddress());
				addShipAddress();
				addBillShippingAddress();
				addTradeReference();
				addBankReference();

			}
			return null;
		} catch (NotFoundException e) {
			errorPageDM.setMessage("User not found");
			return "error-page";
		}

	}

	/**
	 * 
	 */
	public String saveClient() {
		try {
			clientFacade.saveClient(clientProfileDM.getClient());
			addInfoMessage("Save successfull");
		} catch (NotSaveException e) {
			e.printStackTrace();
			addErrorMessage("Unable to save Clien");
		}
		return null;
	}

	/**
	 * 
	 */
	public void addBillShippingAddress() {

		if (clientProfileDM.getClient().getShippingAddresses() == null) {
			clientProfileDM.getClient().setShippingAddresses(
					new ArrayList<ShippingAddress>());
		}
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setAddressTypeEnum(AddressTypeEnum.BILL);
		clientProfileDM.getClient().getShippingAddresses().add(shippingAddress);
		clientProfileDM.setBillToAddress(shippingAddress);
	}

	public void addContactInformationReferences() {
		List<ContactInformation> contactInformationList = new ArrayList<ContactInformation>();
		ContactInformation principal = new ContactInformation();
		principal
				.setContactInformationTypeEnum(ContactInformationTypeEnum.PRINCIPAL);
		ContactInformation procurement = new ContactInformation();
		procurement
				.setContactInformationTypeEnum(ContactInformationTypeEnum.PROCUREMENT);
		ContactInformation payable = new ContactInformation();
		payable.setContactInformationTypeEnum(ContactInformationTypeEnum.ACCOUNT_PAYABLE);
		contactInformationList.add(principal);
		contactInformationList.add(procurement);
		contactInformationList.add(payable);
		clientProfileDM.setPrincipalContactInformation(principal);
		clientProfileDM.setProcurementContactInformation(procurement);
		clientProfileDM.setAccountPayableContactInformation(payable);
		clientProfileDM.getClient().setContactInformationList(
				contactInformationList);

	}

	/**
	 * @return
	 */
	public List<SelectItem> getStatesFromBillAddress() {
		if (clientProfileDM.getBillToAddress() != null) {
			return getStates(clientProfileDM.getBillToAddress().getCountryId());
		}
		return getStates(null);

	}

	/**
	 * @return
	 */
	public List<SelectItem> getStatesFromShippingAddress() {
		ShippingAddress shippingAddress = (ShippingAddress) getFacesContext()
				.getExternalContext().getRequestMap().get("address");
		if (shippingAddress != null) {
			return getStates(shippingAddress.getCountryId());
		}
		return getStates(null);

	}

	/**
	 * @return
	 */
	public List<SelectItem> getStatesFromBankReference() {
		BankReference bankReference = (BankReference) getFacesContext()
				.getExternalContext().getRequestMap().get("bankReference");
		if (bankReference != null && bankReference.getShippingAddress() != null) {
			return getStates(bankReference.getShippingAddress().getCountryId());
		}
		return getStates(null);

	}

	/**
	 * @return
	 */
	public List<SelectItem> getStatesFromTradeReference() {
		TradeReference tradeReference = (TradeReference) getFacesContext()
				.getExternalContext().getRequestMap().get("tradeReference");
		if (tradeReference != null
				&& tradeReference.getShippingAddress() != null) {
			return getStates(tradeReference.getShippingAddress().getCountryId());
		}
		return getStates(null);

	}

	/**
	 * 
	 */
	public void addShipAddress() {
		if (clientProfileDM.getClient().getShippingAddresses() == null) {
			clientProfileDM.getClient().setShippingAddresses(
					new ArrayList<ShippingAddress>());
		}

		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setAddressTypeEnum(AddressTypeEnum.SHIP);
		clientProfileDM.getClient().getShippingAddresses().add(shippingAddress);
	}

	/**
	 * 
	 */
	public void deleteShipAddress() {
		if (clientProfileDM.getClient().getShippingAddresses() != null
				&& countActiveRecords(clientProfileDM.getClient()
						.getShippingAddresses()) > 1) {
			ShippingAddress shippingAddress = (ShippingAddress) getFacesContext()
					.getExternalContext().getRequestMap().get("address");
			if (shippingAddress != null && shippingAddress.getId() != null) {
				shippingAddress.setActive(false);
			} else {
				clientProfileDM.getClient().getShippingAddresses()
						.remove(shippingAddress);
			}
		} else {
			addInfoMessage("You need at least one Shipping Address");
		}
	}

	/**
	 * @param list
	 * @return
	 */
	public Integer countActiveRecords(List<ShippingAddress> list) {
		Integer countResult = 0;
		if (listaLLena(list)) {
			for (ShippingAddress shippingAddress : list) {
				if (shippingAddress.getActive()
						&& shippingAddress.getAddressTypeEnum().equals(
								AddressTypeEnum.SHIP)) {
					countResult++;
				}
			}
		}
		return countResult;
	}

	/**
	 * 
	 */
	public void addBankReference() {
		if (clientProfileDM.getClient().getBankReferences() == null) {
			clientProfileDM.getClient().setBankReferences(
					new ArrayList<BankReference>());
		}
		BankReference bankReference = new BankReference();
		ShippingAddress shippingAddress = new ShippingAddress();
		bankReference.setShippingAddress(shippingAddress);
		clientProfileDM.getClient().getBankReferences().add(bankReference);
	}

	/**
	 * 
	 */
	public void deleteBankReference() {
		if (clientProfileDM.getClient().getBankReferences() != null
				&& ComboBoxModel.countActiveRecords(clientProfileDM.getClient()
						.getBankReferences()) > 1) {
			BankReference bankReference = (BankReference) getFacesContext()
					.getExternalContext().getRequestMap().get("bankReference");
			if (bankReference != null && bankReference.getId() != null) {
				bankReference.setActive(false);
			} else {
				clientProfileDM.getClient().getBankReferences()
						.remove(bankReference);
			}
		} else {
			addInfoMessage("You need at least one Bank Reference");
		}
	}

	/**
	 * 
	 */
	public void addTradeReference() {
		if (clientProfileDM.getClient().getTradeReferences() == null) {
			clientProfileDM.getClient().setTradeReferences(
					new ArrayList<TradeReference>());
		}
		TradeReference tradeReference = new TradeReference();
		ShippingAddress shippingAddress = new ShippingAddress();
		tradeReference.setShippingAddress(shippingAddress);
		clientProfileDM.getClient().getTradeReferences().add(tradeReference);
	}

	/**
	 * 
	 */
	public void deleteTradeReference() {
		if (clientProfileDM.getClient().getTradeReferences() != null
				&& ComboBoxModel.countActiveRecords(clientProfileDM.getClient()
						.getTradeReferences()) > 1) {
			TradeReference tradeReference = (TradeReference) getFacesContext()
					.getExternalContext().getRequestMap().get("tradeReference");
			if (tradeReference != null && tradeReference.getId() != null) {
				tradeReference.setActive(false);
			} else {
				clientProfileDM.getClient().getTradeReferences()
						.remove(tradeReference);
			}
		} else {
			addInfoMessage("You need at least one Trade Reference");
		}
	}

	/**
	 * 
	 */
	public void validateUsername() {
		try {
			if (!userFacade.isValidUsername(clientProfileDM.getClient()
					.getUser())) {
				clientProfileDM.setUsernameUnique(false);
				return;
			}
			throw new NotFoundException();
		} catch (NotFoundException e) {
			clientProfileDM.setUsernameUnique(true);
		}
	}

	/**
	 * 
	 */
	public void validateEmail() {
		try {
			if (!userFacade.isValidEmail(clientProfileDM.getClient().getUser())) {
				clientProfileDM.setEmailUnique(false);
				return;
			}
			throw new NotFoundException();
		} catch (NotFoundException e) {
			clientProfileDM.setEmailUnique(true);
		}
	}

	/**
	 * 
	 */
	public void validateReTypePassword() {
		if (!userFacade.isValidPassword(clientProfileDM.getClient().getUser())) {
			clientProfileDM.setReTypePasswordUnique(false);
		} else {
			clientProfileDM.setReTypePasswordUnique(true);
		}
	}

}
