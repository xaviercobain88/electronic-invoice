package com.stf.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.stf.business.facade.CatalogFacade;
import com.stf.business.facade.UserFacade;
import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.entity.BankReference;
import com.stf.persistence.entity.ContactInformation;
import com.stf.persistence.entity.ShippingAddress;
import com.stf.persistence.entity.TradeReference;
import com.stf.persistence.entity.User;
import com.stf.persistence.enums.AddressTypeEnum;
import com.stf.persistence.enums.ContactInformationTypeEnum;
import com.stf.persistence.enums.PermissionNameEnum;
import com.stf.persistence.util.ComboBoxModel;
import com.stf.presentation.datamanager.ErrorPageDM;
import com.stf.presentation.datamanager.RequestParametersDM;
import com.stf.presentation.datamanager.UserProfileDM;
import com.stf.security.AllowedPermission;
import com.stf.security.Guard;

@Named
@RequestScoped
@URLMappings(mappings = {
		@URLMapping(id = "editUserProfile1", pattern = "/user/#{requestParametersDM.pageInformationOwner}", viewId = "/pages/user-profile.jsf"),
		@URLMapping(id = "editUserProfile2", pattern = "/user/#{requestParametersDM.pageInformationOwner}/", viewId = "/pages/user-profile.jsf") })
public class UserProfileWithAuthorizationAction extends BaseController {
	@EJB
	CatalogFacade catalogFacade;
	@EJB
	UserFacade userFacade;

	@Inject
	UserProfileDM userProfileDM;
	@Inject
	ErrorPageDM errorPageDM;
	@Inject
	RequestParametersDM requestParametersDM;

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
	@Interceptors(Guard.class)
	@AllowedPermission(permissionNameEnum = PermissionNameEnum.USER_PROFILE)
	public String init() {

		try {
			if (requestParametersDM.getPageInformationOwner() != null
					&& !requestParametersDM.getPageInformationOwner().equals(
							"new")) {

				userProfileDM.setUser(userFacade
						.findUserByUsername(requestParametersDM
								.getPageInformationOwner()));
			} else {
				User user = new User();
				userProfileDM.setUser(user);
				addContactInformationReferences();
				userProfileDM.setBillToAddress(new ShippingAddress());
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
	public String saveUser() {
		try {
			userFacade.saveUser(userProfileDM.getUser());
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

		if (userProfileDM.getUser().getShippingAddresses() == null) {
			userProfileDM.getUser().setShippingAddresses(
					new ArrayList<ShippingAddress>());
		}
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setAddressTypeEnum(AddressTypeEnum.BILL);
		userProfileDM.getUser().getShippingAddresses().add(shippingAddress);
		userProfileDM.setBillToAddress(shippingAddress);
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
		userProfileDM.setPrincipalContactInformation(principal);
		userProfileDM.setProcurementContactInformation(procurement);
		userProfileDM.setAccountPayableContactInformation(payable);
		userProfileDM.getUser().setContactInformationList(
				contactInformationList);

	}

	/**
	 * @return
	 */
	public List<SelectItem> getStatesFromBillAddress() {
		if (userProfileDM.getBillToAddress() != null) {
			return getStates(userProfileDM.getBillToAddress().getCountryId());
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
		if (userProfileDM.getUser().getShippingAddresses() == null) {
			userProfileDM.getUser().setShippingAddresses(
					new ArrayList<ShippingAddress>());
		}

		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setAddressTypeEnum(AddressTypeEnum.SHIP);
		userProfileDM.getUser().getShippingAddresses().add(shippingAddress);
	}

	/**
	 * 
	 */
	public void deleteShipAddress() {
		if (userProfileDM.getUser().getShippingAddresses() != null
				&& countActiveRecords(userProfileDM.getUser()
						.getShippingAddresses()) > 1) {
			ShippingAddress shippingAddress = (ShippingAddress) getFacesContext()
					.getExternalContext().getRequestMap().get("address");
			if (shippingAddress != null && shippingAddress.getId() != null) {
				shippingAddress.setActive(false);
			} else {
				userProfileDM.getUser().getShippingAddresses()
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
		if (userProfileDM.getUser().getBankReferences() == null) {
			userProfileDM.getUser().setBankReferences(
					new ArrayList<BankReference>());
		}
		BankReference bankReference = new BankReference();
		ShippingAddress shippingAddress = new ShippingAddress();
		bankReference.setShippingAddress(shippingAddress);
		userProfileDM.getUser().getBankReferences().add(bankReference);
	}

	/**
	 * 
	 */
	public void deleteBankReference() {
		if (userProfileDM.getUser().getBankReferences() != null
				&& ComboBoxModel.countActiveRecords(userProfileDM.getUser()
						.getBankReferences()) > 1) {
			BankReference bankReference = (BankReference) getFacesContext()
					.getExternalContext().getRequestMap().get("bankReference");
			if (bankReference != null && bankReference.getId() != null) {
				bankReference.setActive(false);
			} else {
				userProfileDM.getUser().getBankReferences()
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
		if (userProfileDM.getUser().getTradeReferences() == null) {
			userProfileDM.getUser().setTradeReferences(
					new ArrayList<TradeReference>());
		}
		TradeReference tradeReference = new TradeReference();
		ShippingAddress shippingAddress = new ShippingAddress();
		tradeReference.setShippingAddress(shippingAddress);
		userProfileDM.getUser().getTradeReferences().add(tradeReference);
	}

	/**
	 * 
	 */
	public void deleteTradeReference() {
		if (userProfileDM.getUser().getTradeReferences() != null
				&& ComboBoxModel.countActiveRecords(userProfileDM.getUser()
						.getTradeReferences()) > 1) {
			TradeReference tradeReference = (TradeReference) getFacesContext()
					.getExternalContext().getRequestMap().get("tradeReference");
			if (tradeReference != null && tradeReference.getId() != null) {
				tradeReference.setActive(false);
			} else {
				userProfileDM.getUser().getTradeReferences()
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
			if (!userFacade.isValidUsername(userProfileDM.getUser())) {
				userProfileDM.setUsernameUnique(false);
				return;
			}
			throw new NotFoundException();
		} catch (NotFoundException e) {
			userProfileDM.setUsernameUnique(true);
		}
	}

	/**
	 * 
	 */
	public void validateEmail() {
		try {
			if (!userFacade.isValidEmail(userProfileDM.getUser())) {
				userProfileDM.setEmailUnique(false);
				return;
			}
			throw new NotFoundException();
		} catch (NotFoundException e) {
			userProfileDM.setEmailUnique(true);
		}
	}

	/**
	 * 
	 */
	public void validateReTypePassword() {
		if (!userFacade.isValidPassword(userProfileDM.getUser())) {
			userProfileDM.setReTypePasswordUnique(false);
		} else {
			userProfileDM.setReTypePasswordUnique(true);
		}
	}

}
