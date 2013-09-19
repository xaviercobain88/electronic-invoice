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

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CatalogDao extends GenericDao<Catalog, Integer> {

	public CatalogDao() {
		super(Catalog.class);

	}

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findCategoryFromItem() throws NotFoundException {

		return findByNamedQuery(
				"Catalog.findCategoryFromItem",
				with("activeItem", true).and("approvedItem", true)
						.and("activeCategory", true).parameters());
	}

	/**
	 * @param categoryId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findTypeFromItemByCategory(Integer categoryId)
			throws NotFoundException {
		return findByNamedQuery(
				"Catalog.findTypeFromItemByCategory",
				with("activeItem", true).and("approvedItem", true)
						.and("activeType", true).and("categoryId", categoryId)
						.parameters());
	}

	/**
	 * @param typeId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findColorFromItemByCategoryAndType(Integer categoryId,
			Integer typeId) throws NotFoundException {
		return findByNamedQuery(
				"Catalog.findColorFromItemByType",
				with("activeItem", true).and("approvedItem", true)
						.and("activeColor", true).and("categoryId", categoryId)
						.and("typeId", typeId).parameters());
	}

	/**
	 * @param colorId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findVarietyFromItemByCategoryAndTypeAndColor(
			Integer categoryId, Integer typeId, Integer colorId)
			throws NotFoundException {
		return findByNamedQuery(
				"Catalog.findVarietyFromItemByColor",
				with("activeItem", true).and("approvedItem", true)
						.and("activeVariety", true)
						.and("categoryId", categoryId).and("typeId", typeId)
						.and("colorId", colorId).parameters());
	}

	/**
	 * @param varietyId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findGradeFromItemByCategoryAndTypeAndColorAndVariety(
			Integer categoryId, Integer typeId, Integer colorId,
			Integer varietyId) throws NotFoundException {
		return findByNamedQuery(
				"Catalog.findGradeFromItemByVariety",
				with("activeItem", true).and("approvedItem", true)
						.and("activeGrade", true).and("categoryId", categoryId)
						.and("typeId", typeId).and("colorId", colorId)
						.and("varietyId", varietyId).parameters());
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
	public List<Object[]> findBoxTypeWithCountGrower(Integer categoryId,
			Integer typeId, Integer colorId, Integer varietyId, Integer gradeId)
			throws NotFoundException {
		return findByNamedQueryObjectArray(
				"Catalog.findBoxTypeWithCountGrower", with("activeItem", true)
						.and("approvedItem", true).and("activeBoxType", true)
						.and("categoryId", categoryId).and("typeId", typeId)
						.and("colorId", colorId).and("varietyId", varietyId)
						.and("gradeId", gradeId).parameters());
	}

	/**
	 * @param parentCatalogId
	 * @param isRecursive
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findByParentId(Integer parentCatalogId,
			Boolean isRecursive, ArrayList<Boolean> active)
			throws NotFoundException {

		return findByNamedQuery("Catalog.findByParentId",
				with("parentCatalogId", parentCatalogId).and("active", active)
						.parameters());
	}

	/**
	 * @param isRecursive
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findByParentIdNull(Boolean isRecursive,
			ArrayList<Boolean> active) throws NotFoundException {

		return findByNamedQuery("Catalog.findByParentIdNull",
				with("active", active).parameters());
	}

}
