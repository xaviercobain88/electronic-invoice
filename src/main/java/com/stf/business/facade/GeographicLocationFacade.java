/*
 */
package com.stf.business.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.persistence.dao.GeographicLocationDao;
import com.stf.persistence.entity.Catalog;
import com.stf.persistence.entity.GeographicLocation;
import com.stf.persistence.enums.GeographicLocationLevelEnum;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GeographicLocationFacade {

	@EJB
	GeographicLocationDao geographicLocationDao;

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<GeographicLocation> findStates() throws NotFoundException {
		return geographicLocationDao.findByLevel(
				GeographicLocationLevelEnum.LEVEL_1, true);
	}

	/**
	 * @param parentId
	 * @return
	 * @throws NotFoundException
	 */
	public List<GeographicLocation> findByParentId(Integer parentId)
			throws NotFoundException {

		return geographicLocationDao.findByParentId(parentId, true);
	}

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<GeographicLocation> findAllParents() throws NotFoundException {
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		list.add(true);
		list.add(false);
		return geographicLocationDao.findByParentIdNull(null, list);
	}
}
