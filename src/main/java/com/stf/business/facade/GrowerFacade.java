/*
 */
package com.stf.business.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.persistence.dao.GrowerDao;
import com.stf.persistence.entity.Grower;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GrowerFacade {

	@EJB
	GrowerDao growerDao;

	public Integer findIdByUsername(String username) throws NotFoundException {
		List<Grower> foundGrowers = growerDao.findByUsername(username);
		if (foundGrowers != null && !foundGrowers.isEmpty()) {
			return foundGrowers.get(0).getId();
		}
		return null;
	}

	/**
	 * @param username
	 * @return
	 * @throws NotFoundException
	 */
	public Grower findGrowerByUsername(String username)
			throws NotFoundException {
		List<Grower> foundGrowers = growerDao.findByUsername(username);
		if (foundGrowers != null && !foundGrowers.isEmpty()) {
			return foundGrowers.get(0);
		}
		return null;
	}

}
