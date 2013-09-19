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
import com.stf.exception.NotSaveException;
import com.stf.persistence.dao.CatalogDao;
import com.stf.persistence.entity.Catalog;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CatalogFacade {

	@EJB
	CatalogDao catalogDao;

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findCategoryFromItem() throws NotFoundException {
		return catalogDao.findCategoryFromItem();
	}

	/**
	 * @param categoryId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findTypeFromItemByCategory(Integer categoryId)
			throws NotFoundException {
		return catalogDao.findTypeFromItemByCategory(categoryId);
	}

	/**
	 * @param typeId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findColorFromItemByCategoryAndType(Integer categoryId,
			Integer typeId) throws NotFoundException {
		return catalogDao
				.findColorFromItemByCategoryAndType(categoryId, typeId);
	}

	/**
	 * @param colorId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findVarietyFromItemByCategoryAndTypeAndColor(
			Integer categoryId, Integer typeId, Integer colorId)
			throws NotFoundException {
		return catalogDao.findVarietyFromItemByCategoryAndTypeAndColor(
				categoryId, typeId, colorId);
	}

	/**
	 * @param varietyId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findGradeFromItemByCategoryAndTypeAndColorAndVariety(
			Integer categoryId, Integer typeId, Integer colorId,
			Integer varietyId) throws NotFoundException {
		return catalogDao.findGradeFromItemByCategoryAndTypeAndColorAndVariety(
				categoryId, typeId, colorId, varietyId);
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
		return catalogDao.findBoxTypeWithCountGrower(categoryId, typeId,
				colorId, varietyId, gradeId);
	}

	/**
	 * @param parentCatalogId
	 * @param isRecursive
	 * @param active
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findActiveByParentId(Integer parentCatalogId)
			throws NotFoundException {
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		list.add(true);
		return catalogDao.findByParentId(parentCatalogId, false, list);
	}

	/**
	 * @param parentCatalogId
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findAllByParentId(Integer parentCatalogId)
			throws NotFoundException {
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		list.add(true);
		list.add(false);
		return catalogDao.findByParentId(parentCatalogId, false, list);
	}

	/**
	 * @return
	 * @throws NotFoundException
	 */
	public List<Catalog> findAllParents() throws NotFoundException {
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		list.add(true);
//		list.add(false);
		return catalogDao.findByParentIdNull(null, list);
	}

	/**
	 * Service for saving a catalog
	 * 
	 * @param catalog
	 * @param parentCatalogId
	 * @return
	 * @throws NotSaveException
	 */
	public Catalog saveCatalog(Catalog catalog, Integer parentCatalogId)
			throws NotSaveException {
		if (catalog != null) {
			catalog.setParentCatalogId(parentCatalogId);
			if (catalog.getId() == null) {
				return catalogDao.create(catalog);
			} else {
				return catalogDao.update(catalog);
			}
		} else {
			throw new NotSaveException();
		}
	}
}
