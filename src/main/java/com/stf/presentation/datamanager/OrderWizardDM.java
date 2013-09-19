package com.stf.presentation.datamanager;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.stf.persistence.entity.ClientFreightForwarder;
import com.stf.persistence.entity.Order;
import com.stf.persistence.entity.Point;

@Named
@SessionScoped
public class OrderWizardDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order wizardOrder;
	private List<Point> posList;
	private List<SelectItem> posSelectItems;
	private List<SelectItem> shippingAddressSelectItems;
	private List<SelectItem> ClientFreightForwarderSelectItems;
	private ClientFreightForwarder clientFreightForwarder;

	public Order getWizardOrder() {
		return wizardOrder;
	}

	public List<SelectItem> getShippingAddressSelectItems() {
		return shippingAddressSelectItems;
	}

	public void setShippingAddressSelectItems(
			List<SelectItem> shippingAddressSelectItems) {
		this.shippingAddressSelectItems = shippingAddressSelectItems;
	}

	public void setWizardOrder(Order wizardOrder) {
		this.wizardOrder = wizardOrder;
	}

	public List<Point> getPosList() {
		return posList;
	}

	public void setPosList(List<Point> posList) {
		this.posList = posList;
	}

	public List<SelectItem> getPosSelectItems() {
		return posSelectItems;
	}

	public void setPosSelectItems(List<SelectItem> posSelectItems) {
		this.posSelectItems = posSelectItems;
	}

	public List<SelectItem> getClientFreightForwarderSelectItems() {
		return ClientFreightForwarderSelectItems;
	}

	public void setClientFreightForwarderSelectItems(
			List<SelectItem> clientFreightForwarderSelectItems) {
		ClientFreightForwarderSelectItems = clientFreightForwarderSelectItems;
	}

	public ClientFreightForwarder getClientFreightForwarder() {
		return clientFreightForwarder;
	}

	public void setClientFreightForwarder(
			ClientFreightForwarder clientFreightForwarder) {
		this.clientFreightForwarder = clientFreightForwarder;
	}

}
