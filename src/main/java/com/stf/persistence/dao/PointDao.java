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
import com.stf.persistence.entity.Point;
import com.stf.persistence.enums.PointTypeEnum;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class PointDao extends GenericDao<Point, Integer> {

	public PointDao() {
		super(Point.class);

	}

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<Point> findByPointType(List<PointTypeEnum> pointTypeList)
			throws NotFoundException {

		return findByNamedQuery("Point.findByPointType",
				with("pointTypeList", pointTypeList).and("active", true)
						.parameters());
	}

}
