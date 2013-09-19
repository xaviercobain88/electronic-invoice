package com.stf.presentation.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.stf.business.facade.UserFacade;
import com.stf.business.facade.OrderFacade;
import com.stf.business.facade.ShippingAddressFacade;
import com.stf.exception.NotFoundException;
import com.stf.exception.NotPermissionException;
import com.stf.persistence.entity.User;
import com.stf.persistence.entity.ShippingAddress;
import com.stf.presentation.datamanager.ClientOrderDashboardDM;

/**
 * @author xavier
 * 
 */
@Named
@RequestScoped
@URLMapping(id = "orderDashboard", pattern = "/client/#{clientOrderDashboardAction.clientOrderDashboardDM.username}/order", viewId = "/pages/client-order-dashboard.jsf")
public class ClientOrderDashboardAction extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	OrderFacade orderFacade;
	@EJB
	UserFacade clientFacade;

	@Inject
	ClientOrderDashboardDM clientOrderDashboardDM;

	/**
	 * 
	 */
	@URLAction(onPostback = false)
	public void init() {
		// if (getUsername() == null
		// || getUsername().equals(clientOrderDashboardDM.getUsername())) {
		// TODO Throw Sign in page
		// throw new NotPermissionException();
		// }

		try {
			clientOrderDashboardDM.setClientId(clientFacade
					.findIdByUsername(clientOrderDashboardDM.getUsername()));
			//TODO Client id hard coded
			clientOrderDashboardDM.setClientId(1);
			clientOrderDashboardDM.setClientOrders(orderFacade
					.findByClientId(clientOrderDashboardDM.getClientId()));
		} catch (NotFoundException e) {
			e.printStackTrace();
			addInfoMessage(e.getMessage());
		}

	}

	public ClientOrderDashboardDM getClientOrderDashboardDM() {
		return clientOrderDashboardDM;
	}

	public void setClientOrderDashboardDM(
			ClientOrderDashboardDM clientOrderDashboardDM) {
		this.clientOrderDashboardDM = clientOrderDashboardDM;
	}

}
