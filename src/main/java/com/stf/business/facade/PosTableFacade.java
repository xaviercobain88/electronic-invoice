/*
 */
package com.stf.business.facade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.business.util.PosWrapper;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PosTableFacade {

	private List<PosWrapper> posList;

	public List<PosWrapper> getGeneratedPosTable() {
		List<PosWrapper> posWrapperList = new ArrayList<PosWrapper>();
		PosWrapper quito = new PosWrapper();
		quito.setCity("Quito");
		quito.setPos("FOB Quito");
		quito.setProductList("Limited");
		quito.setArrivalDays(0);
		posWrapperList.add(quito);

		PosWrapper medellin = new PosWrapper();
		medellin.setCity("Medellin");
		medellin.setPos("FOB Medellin");
		medellin.setProductList("Limited");
		medellin.setArrivalDays(0);
		posWrapperList.add(medellin);

		PosWrapper bogota = new PosWrapper();
		bogota.setCity("Bogota");
		bogota.setPos("FOB Bogota");
		bogota.setProductList("Limited");
		bogota.setArrivalDays(0);
		posWrapperList.add(bogota);

		PosWrapper miami = new PosWrapper();
		miami.setCity("Miami");
		miami.setPos("FOB Miami");
		miami.setProductList("All");
		miami.setArrivalDays(3);
		posWrapperList.add(miami);

		PosWrapper amsterdan = new PosWrapper();
		amsterdan.setCity("Amsterdan");
		amsterdan.setPos("FOB Amsterdan");
		amsterdan.setProductList("All");
		amsterdan.setArrivalDays(3);
		posWrapperList.add(amsterdan);

		PosWrapper istambul = new PosWrapper();
		istambul.setCity("Istambul");
		istambul.setPos("FOB Istambul");
		istambul.setProductList("All");
		istambul.setArrivalDays(3);
		posWrapperList.add(istambul);

		posWrapperList = setArrivalPos(posWrapperList);
		posWrapperList = setLeadTime(posWrapperList);

		return posWrapperList;

	}

	private List<PosWrapper> setArrivalPos(List<PosWrapper> posWrapperList) {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d");

		for (PosWrapper posWrapper : posWrapperList) {
			if (posWrapper.getArrivalDays() == null
					|| posWrapper.getArrivalDays().equals(0)) {
				posWrapper.setArrivalPos(dateFormat.format(date));
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, posWrapper.getArrivalDays());
				posWrapper.setArrivalPos(dateFormat.format(calendar.getTime()));
			}
		}
		return posWrapperList;
	}

	private List<PosWrapper> setLeadTime(List<PosWrapper> posWrapperList) {
		for (PosWrapper posWrapper : posWrapperList) {
			if (posWrapper.getArrivalDays() == null
					|| posWrapper.getArrivalDays().equals(0)) {
				posWrapper.setLeadTime("Same Day");
			} else {
				posWrapper.setLeadTime(posWrapper.getArrivalDays() + " Days");
			}
		}
		return posWrapperList;
	}

	public List<PosWrapper> getPosList() {
		return posList;
	}

	public void setPosList(List<PosWrapper> posList) {
		this.posList = posList;
	}

}
