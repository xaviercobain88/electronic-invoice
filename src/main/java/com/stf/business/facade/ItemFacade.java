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
import com.stf.exception.ProccessException;
import com.stf.persistence.dao.ItemDao;
import com.stf.persistence.entity.Item;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ItemFacade {

	@EJB
	ItemDao itemDao;

	public List<Object> countDistinctGrower(Integer categoryId, Integer typeId,
			Integer colorId, Integer varietyId, Integer gradeId)
			throws NotFoundException {
		return itemDao.countDistinctGrower(categoryId, typeId, colorId,
				varietyId, gradeId);
	}

	/**
	 * @param categoryId
	 * @param typeId
	 * @param colorId
	 * @param varietyId
	 * @param gradeId
	 * @return
	 * @throws NotFoundException
	 */
	public String maxPrice(Integer categoryId, Integer typeId, Integer colorId,
			Integer varietyId, Integer gradeId) throws NotFoundException {
		List<Object[]> result = itemDao.maxPrice(categoryId, typeId, colorId,
				varietyId, gradeId);
		String maxPriceResult = "";
		if (result != null && !result.isEmpty()) {
			maxPriceResult = result.get(0)[1] + " / " + result.get(0)[0];
		} else {
			throw new NotFoundException("An error has ocurred");
		}

		return maxPriceResult;
	}
	
	public String uomPerBox(Integer categoryId, Integer typeId, Integer colorId,
			Integer varietyId, Integer gradeId) throws NotFoundException {
		List<Object[]> result = itemDao.maxPrice(categoryId, typeId, colorId,
				varietyId, gradeId);
		String maxPriceResult = "";
		if (result != null && !result.isEmpty()) {
			maxPriceResult = result.get(0)[1] + " / " + result.get(0)[0];
		} else {
			throw new NotFoundException("An error has ocurred");
		}

		return maxPriceResult;
	}

	public List<Item> generateComboBoxItemList(Item newItem, List<Item> itemList)
			throws ProccessException, NotFoundException {

		if (newItem != null && newItem.getCategoryCatalogId() != null
				&& newItem.getTypeCatalogId() != null
				&& newItem.getColorCatalogId() != null
				&& newItem.getVarietyCatalogId() != null
				&& newItem.getGradeCatalogId() != null) {

			if (itemList != null && !itemList.isEmpty()) {
				itemList = new ArrayList<Item>();
			} else {
				for (Item item : itemList) {
					if (newItem.getCategoryCatalogId().equals(
							item.getCategoryCatalogId())
							&& newItem.getTypeCatalogId().equals(
									item.getTypeCatalogId())
							&& newItem.getColorCatalogId().equals(
									item.getColorCatalogId())
							&& newItem.getVarietyCatalogId().equals(
									item.getVarietyCatalogId())
							&& newItem.getGradeCatalogId().equals(
									item.getGradeCatalogId())) {
						throw new ProccessException(
								"Item specification already set");
					} else {
						newItem.setMaximunPricePerUom(maxPrice(
								newItem.getCategoryCatalogId(),
								newItem.getTypeCatalogId(),
								newItem.getColorCatalogId(),
								newItem.getVarietyCatalogId(),
								newItem.getGradeCatalogId()));
					}
				}
			}
		}
		throw new ProccessException("Item specification is not enought");

	}

}
