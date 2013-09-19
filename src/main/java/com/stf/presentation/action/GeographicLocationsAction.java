package com.stf.presentation.action;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.stf.business.facade.CatalogFacade;
import com.stf.business.facade.GeographicLocationFacade;
import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.entity.Catalog;
import com.stf.persistence.enums.PermissionNameEnum;
import com.stf.presentation.datamanager.CatalogsDM;
import com.stf.presentation.datamanager.GeographicLocationsDM;
import com.stf.presentation.datamanager.RequestParametersDM;
import com.stf.security.AllowedPermission;
import com.stf.security.enums.PageInformationEnum;
import com.stf.util.MessagesConstants;

@Named
@RequestScoped
@URLMappings(mappings = {
		@URLMapping(id = "geographicLocations1", pattern = "/geographic-locations/", viewId = "/pages/geographic-locations.jsf"),
		@URLMapping(id = "geographicLocations2", pattern = "/geographic-locations", viewId = "/pages/geographic-locations.jsf") })
public class GeographicLocationsAction extends BaseController {
	@EJB
	GeographicLocationFacade geographicLocationFacade;
	@Inject
	GeographicLocationsDM geographicLocationsDM;
	@Inject
	RequestParametersDM requestParametersDM;

	/**
	 * Init Method
	 */
	@URLAction
	// @Interceptors(Guard.class)
	@AllowedPermission(permissionNameEnum = PermissionNameEnum.GEOGRAPHIC_LOCATIONS, pageInformationEnum = PageInformationEnum.SYSTEM)
	public String init() {

		if (geographicLocationsDM.getGeographicLocationSelectItems() == null
				|| geographicLocationsDM.getGeographicLocationSelectItems()
						.isEmpty()) {
			try {
				geographicLocationsDM
						.setGeographicLocationSelectItems(getSelectItems(
								catalogFacade.findAllParents(), true));
			} catch (NotFoundException e) {
				e.printStackTrace();
				addErrorMessageBundle(MessagesConstants.RESULT_EMPTY);
			}
		}
		if (geographicLocationsDM.getParentGeographicLocationId() == null) {
			//
		}

		return null;
	}

//	/**
//	 * Listener for selecting parent catalog (ajax request)
//	 */
//	public void selectParentListener() {
//		if (geographicLocationsDM.getParentGeographicLocationId() != null) {
//			try {
//				geographicLocationsDM.setGeographicLocations(catalogFacade
//						.findAllByParentId(catalogsDM.getParentCatalogId()));
//			} catch (NotFoundException e) {
//				e.printStackTrace();
//				addInfoMessageBundle(MessagesConstants.RESULT_EMPTY);
//			}
//		} else {
//			addWarnMessageBundle(MessagesConstants.RESULT_SELECT_VALID);
//		}
//	}

	/**
	 * Listener for adding new child catalog (ajax request)
	 */
//	public void newChildCatalog() {
//		catalogsDM.setCatalog(new Catalog());
//		catalogsDM.getCatalog().setParentCatalogId(
//				catalogsDM.getParentCatalogId());
//		if (catalogsDM.getCatalogs() == null) {
//			catalogsDM.setCatalogs(new ArrayList<Catalog>());
//		}
//		catalogsDM.getCatalogs().add(0, catalogsDM.getCatalog());
//
//	}

	/**
	 * Listener for editing child catalog (ajax request)
	 */
//	public void onEdit(RowEditEvent event) {
//		catalogsDM.setCatalog(((Catalog) event.getObject()));
//		saveCatalog();
//	}
//
//	public void onCancel(RowEditEvent event) {
//		catalogsDM.setCatalogs(new ArrayList<Catalog>());
//		System.out.println("si cancela");
//		selectParentListener();
//	}

	/**
	 * Listener for saving child catalog (ajax request)
	 */
//	public void saveCatalog() {
//		try {
//			if (catalogsDM.getCatalog().getParentCatalogId() == null) {
//				addWarnMessageBundle(MessagesConstants.RESULT_SELECT_VALID);
//				return;
//			}
//			catalogFacade.saveCatalog(catalogsDM.getCatalog(), catalogsDM
//					.getCatalog().getParentCatalogId());
//			selectParentListener();
//			addInfoMessageBundle(MessagesConstants.RESULT_SAVED);
//		} catch (NotSaveException e) {
//			e.printStackTrace();
//			onCancel(null);
//			addErrorMessageBundle(MessagesConstants.RESULT_NOT_SAVED);
//		}
//	}
}
