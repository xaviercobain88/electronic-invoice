/*
 */
package com.stf.business.facade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.business.util.PosWrapper;
import com.stf.exception.NotFoundException;
import com.stf.persistence.dao.PointDao;
import com.stf.persistence.entity.Point;
import com.stf.persistence.enums.PointTypeEnum;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PointFacade {

	@EJB
	PointDao pointDao;

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<Point> findPointOfSaleCalculated() throws NotFoundException {

		List<Point> posList = pointDao.findByPointType(Arrays
				.asList(PointTypeEnum.values()));
		setArrivalToPos(posList);
		setLeadTimeDescription(posList);
		return posList;
	}

	/**
	 * @param posList
	 * @return
	 */
	private List<Point> setArrivalToPos(List<Point> posList) {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d");

		for (Point point : posList) {
			if (point.getLeadTimeDays() == null
					|| point.getLeadTimeDays().equals(0)) {
				point.setArrivalToPOS(dateFormat.format(date));
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, point.getLeadTimeDays());
				point.setArrivalToPOS(dateFormat.format(calendar.getTime()));
			}
		}
		return posList;
	}

	/**
	 * @param posList
	 * @return
	 */
	private List<Point> setLeadTimeDescription(List<Point> posList) {
		for (Point point : posList) {
			if (point.getLeadTimeDays() == null
					|| point.getLeadTimeDays().equals(0)) {
				point.setLeadTimeDescription("Same Day");
			} else {
				point.setLeadTimeDescription(point.getLeadTimeDays() + " Days");
			}
		}
		return posList;
	}

}
