package com.stf.presentation.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.stf.business.facade.CatalogFacade;
import com.stf.business.facade.ItemFacade;
import com.stf.exception.NotFoundException;
import com.stf.persistence.entity.Catalog;
import com.stf.persistence.entity.Item;
import com.stf.presentation.datamanager.FlowerFinderDM;

@Named
@RequestScoped
@URLMappings(mappings = {
		@URLMapping(id = "newBoxConfiguration1", pattern = "/order/#{flowerFinderAction.flowerFinderDM.orderCode}/box-configuration/#{flowerFinderAction.flowerFinderDM.boxConfigurationCode}", viewId = "/pages/box-configuration.jsf"),
		@URLMapping(id = "newBoxConfiguration2", pattern = "/order/#{flowerFinderAction.flowerFinderDM.orderCode}/box-configuration/#{flowerFinderAction.flowerFinderDM.boxConfigurationCode}/", viewId = "/pages/box-configuration.jsf"),
		@URLMapping(id = "editBoxConfiguration1", pattern = "/order/#{flowerFinderAction.flowerFinderDM.orderCodeNewBox}/box-configuration", viewId = "/pages/box-configuration.jsf"),
		@URLMapping(id = "editBoxConfiguration2", pattern = "/order/#{flowerFinderAction.flowerFinderDM.orderCodeNewBox}/box-configuration/", viewId = "/pages/box-configuration.jsf") })
public class FlowerFinderAction extends BaseController {

	@EJB
	CatalogFacade catalogFacade;
	@EJB
	ItemFacade itemFacade;
	@Inject
	FlowerFinderDM flowerFinderDM;

	/**
	 * Dumb method for trigger {@link PostConstruct} on page
	 * box-configuration.xhtml
	 * 
	 * @return
	 */
	public String getInit() {
		return "";
	}

	/**
	 * Init Method for getting Category Catalog
	 */
	@URLAction(onPostback = false)
	public void init() {
		flowerFinderDM.setFindedItems(new ArrayList<Item>());
		flowerFinderDM.setFindedItemsIndex(0);
		flowerFinderDM.setSelectedItem(new Item());
		if (!listaLLena(flowerFinderDM.getFlowerCategorySelectItems())) {
			resetFinder();
		}
	}

	public void resetFinder() {
		try {

			List<Catalog> lista = catalogFacade.findCategoryFromItem();
			if (listaLLena(lista)) {
				if (lista.size() > 1) {
					flowerFinderDM.setFlowerCategorySelectItems(getSelectItems(
							lista, true));
				} else {
					flowerFinderDM.setFlowerCategorySelectItems(getSelectItems(
							lista, false));
					flowerFinderDM.getSelectedItem().setCategoryCatalogId(
							(Integer) flowerFinderDM
									.getFlowerCategorySelectItems().get(0)
									.getValue());
					flowerFinderDM.getSelectedItem().setCategoryName(
							flowerFinderDM.getFlowerCategorySelectItems()
									.get(0).getLabel());
				}
				changeCategoryListener();

			}
		} catch (NotFoundException e) {
			e.printStackTrace();
			addInfoMessage("No results found");
			flowerFinderDM
					.setFlowerCategorySelectItems(new ArrayList<SelectItem>());
		}
	}

	/**
	 * Method for listening Category and getting Type Catalog
	 */
	public void changeCategoryListener() {

		flowerFinderDM.getSelectedItem().setTypeCatalogId(null);
		flowerFinderDM.getSelectedItem().setColorCatalogId(null);
		flowerFinderDM.getSelectedItem().setVarietyCatalogId(null);
		flowerFinderDM.getSelectedItem().setGradeCatalogId(null);
		flowerFinderDM.setFlowerTypeSelectItems(null);
		flowerFinderDM.setFlowerColorSelectItems(null);
		flowerFinderDM.setFlowerVarietySelectItems(null);
		flowerFinderDM.setFlowerGradeSelectItems(null);

		if (flowerFinderDM.getSelectedItem().getCategoryCatalogId() == null) {
			flowerFinderDM
					.setFlowerTypeSelectItems(new ArrayList<SelectItem>());

		} else {
			try {
				for (SelectItem item : flowerFinderDM
						.getFlowerCategorySelectItems()) {
					if (flowerFinderDM.getSelectedItem().getCategoryCatalogId()
							.equals(item.getValue())) {
						flowerFinderDM.getSelectedItem().setCategoryName(
								item.getLabel());
						break;
					}
				}
				List<Catalog> lista = catalogFacade
						.findTypeFromItemByCategory(flowerFinderDM
								.getSelectedItem().getCategoryCatalogId());

				if (listaLLena(lista)) {
					if (lista.size() > 1) {
						flowerFinderDM.setFlowerTypeSelectItems(getSelectItems(
								lista, true));
					} else {
						flowerFinderDM.setFlowerTypeSelectItems(getSelectItems(
								lista, false));

						flowerFinderDM.getSelectedItem().setTypeCatalogId(
								(Integer) flowerFinderDM
										.getFlowerTypeSelectItems().get(0)
										.getValue());

						flowerFinderDM.getSelectedItem().setTypeName(
								(String) flowerFinderDM
										.getFlowerTypeSelectItems().get(0)
										.getLabel());
					}

				}
				changeTypeListener();

			} catch (NotFoundException e) {
				e.printStackTrace();
				addInfoMessage("No results found");
				flowerFinderDM
						.setFlowerTypeSelectItems(new ArrayList<SelectItem>());
			}
		}

	}

	/**
	 * Method for listening Type and getting Color Catalog
	 */
	public void changeTypeListener() {
		flowerFinderDM.getSelectedItem().setColorCatalogId(null);
		flowerFinderDM.getSelectedItem().setVarietyCatalogId(null);
		flowerFinderDM.getSelectedItem().setGradeCatalogId(null);
		flowerFinderDM.setFlowerColorSelectItems(null);
		flowerFinderDM.setFlowerVarietySelectItems(null);
		flowerFinderDM.setFlowerGradeSelectItems(null);
		if (flowerFinderDM.getSelectedItem().getTypeCatalogId() == null) {
			flowerFinderDM
					.setFlowerColorSelectItems(new ArrayList<SelectItem>());
		} else {
			try {
				for (SelectItem item : flowerFinderDM
						.getFlowerTypeSelectItems()) {
					if (flowerFinderDM.getSelectedItem().getTypeCatalogId()
							.equals(item.getValue())) {
						flowerFinderDM.getSelectedItem().setTypeName(
								item.getLabel());
						break;
					}
				}
				List<Catalog> lista = catalogFacade
						.findColorFromItemByCategoryAndType(flowerFinderDM
								.getSelectedItem().getCategoryCatalogId(),
								flowerFinderDM.getSelectedItem()
										.getTypeCatalogId());
				if (listaLLena(lista)) {
					if (lista.size() > 1) {
						flowerFinderDM
								.setFlowerColorSelectItems(getSelectItems(
										lista, true));
					} else {
						flowerFinderDM
								.setFlowerColorSelectItems(getSelectItems(
										lista, false));

						flowerFinderDM.getSelectedItem().setColorCatalogId(
								(Integer) flowerFinderDM
										.getFlowerColorSelectItems().get(0)
										.getValue());
						flowerFinderDM.getSelectedItem().setColorName(
								(String) flowerFinderDM
										.getFlowerColorSelectItems().get(0)
										.getLabel());
					}

				}
				changeColorListener();
			} catch (NotFoundException e) {
				e.printStackTrace();
				addInfoMessage("No results found");
				flowerFinderDM
						.setFlowerColorSelectItems(new ArrayList<SelectItem>());
			}
		}
	}

	/**
	 * Method for listening Color and getting Variety Catalog
	 */
	public void changeColorListener() {
		flowerFinderDM.getSelectedItem().setVarietyCatalogId(null);
		flowerFinderDM.getSelectedItem().setGradeCatalogId(null);
		flowerFinderDM.setFlowerVarietySelectItems(null);
		flowerFinderDM.setFlowerGradeSelectItems(null);
		if (flowerFinderDM.getSelectedItem().getColorCatalogId() == null) {
			flowerFinderDM
					.setFlowerVarietySelectItems(new ArrayList<SelectItem>());
		} else {
			try {
				for (SelectItem item : flowerFinderDM
						.getFlowerColorSelectItems()) {
					if (flowerFinderDM.getSelectedItem().getColorCatalogId()
							.equals(item.getValue())) {
						flowerFinderDM.getSelectedItem().setColorName(
								item.getLabel());
						break;
					}
				}
				List<Catalog> lista = catalogFacade
						.findVarietyFromItemByCategoryAndTypeAndColor(
								flowerFinderDM.getSelectedItem()
										.getCategoryCatalogId(), flowerFinderDM
										.getSelectedItem().getTypeCatalogId(),
								flowerFinderDM.getSelectedItem()
										.getColorCatalogId());
				if (listaLLena(lista)) {
					if (lista.size() > 1) {
						flowerFinderDM
								.setFlowerVarietySelectItems(getSelectItems(
										lista, true));
					} else {
						flowerFinderDM
								.setFlowerVarietySelectItems(getSelectItems(
										lista, false));

						flowerFinderDM.getSelectedItem().setVarietyCatalogId(
								(Integer) flowerFinderDM
										.getFlowerVarietySelectItems().get(0)
										.getValue());
						flowerFinderDM.getSelectedItem().setVarietyName(
								(String) flowerFinderDM
										.getFlowerVarietySelectItems().get(0)
										.getLabel());
					}

				}
				changeVarietyListener();
			} catch (NotFoundException e) {
				e.printStackTrace();
				addInfoMessage("No results found");
				flowerFinderDM
						.setFlowerVarietySelectItems(new ArrayList<SelectItem>());
			}
		}
	}

	/**
	 * Method for listening Variety and getting Grade Catalog
	 */
	public void changeVarietyListener() {
		flowerFinderDM.getSelectedItem().setGradeCatalogId(null);
		flowerFinderDM.setFlowerGradeSelectItems(null);
		if (flowerFinderDM.getSelectedItem().getVarietyCatalogId() == null) {
			flowerFinderDM
					.setFlowerGradeSelectItems(new ArrayList<SelectItem>());
		} else {
			try {
				for (SelectItem item : flowerFinderDM
						.getFlowerVarietySelectItems()) {
					if (flowerFinderDM.getSelectedItem().getVarietyCatalogId()
							.equals(item.getValue())) {
						flowerFinderDM.getSelectedItem().setVarietyName(
								item.getLabel());
						break;
					}
				}
				List<Catalog> lista = catalogFacade
						.findGradeFromItemByCategoryAndTypeAndColorAndVariety(
								flowerFinderDM.getSelectedItem()
										.getCategoryCatalogId(), flowerFinderDM
										.getSelectedItem().getTypeCatalogId(),
								flowerFinderDM.getSelectedItem()
										.getColorCatalogId(), flowerFinderDM
										.getSelectedItem()
										.getVarietyCatalogId());
				if (listaLLena(lista)) {
					if (lista.size() > 1) {
						flowerFinderDM
								.setFlowerGradeSelectItems(getSelectItems(
										lista, true));
					} else {
						flowerFinderDM
								.setFlowerGradeSelectItems(getSelectItems(
										lista, false));

						flowerFinderDM.getSelectedItem().setGradeCatalogId(
								(Integer) flowerFinderDM
										.getFlowerGradeSelectItems().get(0)
										.getValue());

						flowerFinderDM.getSelectedItem().setGradeName(
								(String) flowerFinderDM
										.getFlowerGradeSelectItems().get(0)
										.getLabel());
					}

				}
				changeGradeListener();
			} catch (NotFoundException e) {
				e.printStackTrace();
				addInfoMessage("No results found");
				flowerFinderDM
						.setFlowerGradeSelectItems(new ArrayList<SelectItem>());
			}
		}
	}
	

	public void changeGradeListener() {
		// flowerFinderDM.setBoxTypeId(null);
		// flowerFinderDM.setGrowersPerBoxTypeSelectItems(null);
		// if (flowerFinderDM.getGradeId() == null) {
		// flowerFinderDM
		// .setGrowersPerBoxTypeSelectItems(new ArrayList<SelectItem>());
		// } else {
		//
		// try {
		// List<Object[]> lista = catalogFacade
		// .findBoxTypeWithCountGrower(
		// flowerFinderDM.getCategoryId(),
		// flowerFinderDM.getTypeId(),
		// flowerFinderDM.getColorId(),
		// flowerFinderDM.getVarietyId(),
		// flowerFinderDM.getGradeId());
		// List<Object> lista2 = catalogFacade.countDistinctGrower(
		// flowerFinderDM.getCategoryId(),
		// flowerFinderDM.getTypeId(),
		// flowerFinderDM.getColorId(),
		// flowerFinderDM.getVarietyId(),
		// flowerFinderDM.getGradeId());
		// System.out.println(lista2.get(0));
		// for (Object[] object : lista) {
		// System.out.println(object[0]);
		// System.out.println(object[1]);
		// System.out.println(object[2]);
		// }
		// } catch (NotFoundException e) {
		// e.printStackTrace();
		// addInfoMessage("No results found");
		// flowerFinderDM.setGrowersPerBoxTypeSelectItems(null);
		// }
		// }
	}

	public void addItem() {
		if (flowerFinderDM.getSelectedItem() != null
				&& flowerFinderDM.getFindedItems() != null) {
			try {
				String maxResult = itemFacade.maxPrice(flowerFinderDM
						.getSelectedItem().getCategoryCatalogId(),
						flowerFinderDM.getSelectedItem().getTypeCatalogId(),
						flowerFinderDM.getSelectedItem().getColorCatalogId(),
						flowerFinderDM.getSelectedItem().getVarietyCatalogId(),
						flowerFinderDM.getSelectedItem().getGradeCatalogId());
				flowerFinderDM.getSelectedItem().setMaximunPricePerUom(
						maxResult);
				flowerFinderDM.getFindedItems().add(
						flowerFinderDM.getSelectedItem());
				flowerFinderDM.setFindedItemsIndex(flowerFinderDM
						.getFindedItemsIndex() + 1);
				flowerFinderDM.setSelectedItem(new Item());
				resetFinder();
			} catch (NotFoundException e) {
				e.printStackTrace();
				addInfoMessage("An error has occurred");
			}
		} else {
			addInfoMessage("The request is not complete");
		}
	}

	public FlowerFinderDM getFlowerFinderDM() {
		return flowerFinderDM;
	}

	public void setFlowerFinderDM(FlowerFinderDM flowerFinderDM) {
		this.flowerFinderDM = flowerFinderDM;
	}

}
