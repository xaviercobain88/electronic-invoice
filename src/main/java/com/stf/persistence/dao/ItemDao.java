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
import com.stf.persistence.entity.Item;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ItemDao extends GenericDao<Item, Integer> {

	public ItemDao() {
		super(Item.class);

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
	public List<Object> countDistinctGrower(Integer categoryId, Integer typeId,
			Integer colorId, Integer varietyId, Integer gradeId)
			throws NotFoundException {
		return findByNamedQueryObject(
				"Item.countDistinctGrower",
				with("activeItem", true).and("approvedItem", true)
						.and("activeBoxType", true)
						.and("categoryId", categoryId).and("typeId", typeId)
						.and("colorId", colorId).and("varietyId", varietyId)
						.and("gradeId", gradeId).parameters());
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
	public List<Object[]> maxPrice(Integer categoryId, Integer typeId,
			Integer colorId, Integer varietyId, Integer gradeId)
			throws NotFoundException {
		return findByNamedQueryObjectArray(
				"Item.maxPrice",
				with("activeItem", true).and("approvedItem", true)
						.and("categoryId", categoryId).and("typeId", typeId)
						.and("colorId", colorId).and("varietyId", varietyId)
						.and("gradeId", gradeId).parameters());
	}
}
