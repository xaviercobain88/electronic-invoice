/*
 */
package com.stf.persistence.dao;

import static com.stf.persistence.util.QueryParameter.with;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.persistence.entity.Order;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class OrderDao extends GenericDao<Order, Integer> {

	public OrderDao() {
		super(Order.class);

	}

	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public List<Order> findByClientId(Integer id) throws NotFoundException {

		return findByNamedQuery("Order.findByClientId", with("active", true)
				.and("id", id).parameters());
	}

}
