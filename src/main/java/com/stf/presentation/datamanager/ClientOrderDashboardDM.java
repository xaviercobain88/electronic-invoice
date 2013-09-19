package com.stf.presentation.datamanager;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.stf.persistence.entity.Order;

@Named
@SessionScoped
public class ClientOrderDashboardDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private List<Order> clientOrders;
	private Integer clientId;
	


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Order> getClientOrders() {
		return clientOrders;
	}

	public void setClientOrders(List<Order> clientOrders) {
		this.clientOrders = clientOrders;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

}
