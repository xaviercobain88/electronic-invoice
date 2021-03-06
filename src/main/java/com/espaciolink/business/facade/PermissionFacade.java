/*
 */
package com.espaciolink.business.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.espaciolink.persistence.dao.PermissionDao;
import com.espaciolink.persistence.entity.Permission;
import com.espaciolink.persistence.exception.NotFoundException;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PermissionFacade {

	@EJB
	PermissionDao permissionDao;

	/**
	 * @param roleId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Permission> findByRoleId(Integer roleId)
			throws NotFoundException {

		ArrayList<Boolean> activeList = new ArrayList<Boolean>();
		activeList.add(true);

		return permissionDao.findByRoleId(roleId, activeList);

	}

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<Permission> findAllActive() throws NotFoundException {

		ArrayList<Boolean> activeList = new ArrayList<Boolean>();
		activeList.add(true);

		return permissionDao.findAll(activeList);

	}
}
