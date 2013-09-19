/*
 */
package com.stf.business.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.dao.UserDao;
import com.stf.persistence.dao.OrderDao;
import com.stf.persistence.entity.User;
import com.stf.persistence.entity.Order;
import com.stf.persistence.enums.OrderStatusOrderEnum;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class OrderFacade {

	@EJB
	OrderDao orderDao;
	@EJB
	UserDao clientDao;

	/**
	 * @param clientId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Order> findByClientId(Integer clientId)
			throws NotFoundException {
		return orderDao.findByClientId(clientId);

	}

	/**
	 * @param order
	 * @return
	 * @throws NotSaveException
	 */
	public Order createClientOrder(Order order, Integer clientId)
			throws NotSaveException {
		order.setClientId(clientId);
		order.setActive(true);
		order.setCreateDate(new Date());
		order.setHiddenByClient(false);
		order.setOrderStatusOrderEnum(OrderStatusOrderEnum.OPEN);
		order = orderDao.create(order);
		order.setCode("OR-" + order.getId());
		return orderDao.update(order);
	}
}
