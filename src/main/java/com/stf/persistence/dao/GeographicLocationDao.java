/*
 */
package com.stf.persistence.dao;

import static com.stf.persistence.util.QueryParameter.with;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.exception.NotFoundException;
import com.stf.persistence.entity.Catalog;
import com.stf.persistence.entity.GeographicLocation;
import com.stf.persistence.enums.GeographicLocationLevelEnum;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GeographicLocationDao extends
		GenericDao<GeographicLocation, Integer> {

	public GeographicLocationDao() {
		super(GeographicLocation.class);

	}

	/**
	 * @param level
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<GeographicLocation> findByLevel(
			GeographicLocationLevelEnum level, Boolean active)
			throws NotFoundException {
		return findByNamedQuery("GeographicLocation.findByLevel",
				with("active", active).and("level", level).parameters());
	}

	/**
	 * @param parentLocationId
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<GeographicLocation> findByParentId(Integer parentLocationId,
			Boolean active) throws NotFoundException {
		return findByNamedQuery("GeographicLocation.findByParentId",
				with("active", active)
						.and("parentLocationId", parentLocationId).parameters());
	}

	/**
	 * @param isRecursive
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<GeographicLocation> findByParentIdNull(Boolean isRecursive,
			ArrayList<Boolean> active) throws NotFoundException {

		return findByNamedQuery("GeographicLocation.findByParentIdNull",
				with("active", active).parameters());
	}
}
