package com.stf.presentation.action;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import com.stf.business.facade.OrderFacade;
import com.stf.business.facade.PointFacade;
import com.stf.business.facade.PosTableFacade;
import com.stf.business.facade.ShippingAddressFacade;
import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.entity.Order;
import com.stf.persistence.enums.OrderTypeEnum;
import com.stf.presentation.datamanager.ClientOrderDashboardDM;
import com.stf.presentation.datamanager.OrderWizardDM;

/**
 * @author xavier
 * 
 */
@Named
@RequestScoped
public class OrderWizardAction extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	PosTableFacade posTableFacade;
	@EJB
	OrderFacade orderFacade;
	@EJB
	ShippingAddressFacade shippingAddressFacade;
	@EJB
	PointFacade pointFacade;
	@Inject
	OrderWizardDM orderWizardDM;
	@Inject
	ClientOrderDashboardDM clientOrderDashboardDM;

	@PostConstruct
	public void init() {
		if (orderWizardDM.getWizardOrder() == null) {
			orderWizardDM.setWizardOrder(new Order());
		}

		generatePos();
		generateShippingAddress();
	}

	/**
	 * @return
	 */
	public Boolean generatePos() {
		try {
			orderWizardDM.setPosList(pointFacade.findPointOfSaleCalculated());
			orderWizardDM.setPosSelectItems(getSelectItems(
					orderWizardDM.getPosList(), false));
			return true;

		} catch (NotFoundException e) {
			e.printStackTrace();
			addErrorMessage("No 'Points of Sales' found");
			return false;
		}
	}

	/**
	 * @return
	 */
	public Boolean generateShippingAddress() {
		try {
			orderWizardDM.setShippingAddressSelectItems(getSelectItems(
					shippingAddressFacade.findByClientId(clientOrderDashboardDM
							.getClientId()), false));
			return true;

		} catch (NotFoundException e) {
			e.printStackTrace();
			addErrorMessage("No 'Shipping Address' found");
			return false;
		}
	}

	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public List<SelectItem> getShippingAdressSelectItems() {
		try {
			return getSelectItems(shippingAddressFacade.findByClientId(1),
					false);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addErrorMessage(e.getMessage());
			return new ArrayList<SelectItem>();

		}
	}

	public List<SelectItem> getOrderTypeSelectItems() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		for (OrderTypeEnum orderTypeEnum : OrderTypeEnum.values()) {
			SelectItem item = new SelectItem(orderTypeEnum,
					orderTypeEnum.getName() + ": "
							+ orderTypeEnum.getDescription());
			selectItems.add(item);
		}
		return selectItems;
	}

	/**
	 * @param event
	 * @return
	 */
	public String onFlowProcess(FlowEvent event) {
		if (event.getNewStep().equals("successId")) {
			try {
				orderFacade.createClientOrder(orderWizardDM.getWizardOrder(),
						clientOrderDashboardDM.getClientId());
				orderWizardDM.setWizardOrder(null);
			} catch (NotSaveException e) {
				e.printStackTrace();
				addErrorMessage(e.getMessage());
				return event.getOldStep();
			}
		}
		return event.getNewStep();
	}

	/**
	 * 
	 */
	public void updateOrderList() {
		try {
			clientOrderDashboardDM.setClientOrders(orderFacade
					.findByClientId(clientOrderDashboardDM.getClientId()));
		} catch (NotFoundException e) {
			e.printStackTrace();
			addInfoMessage(e.getMessage());
		}
	}

}
