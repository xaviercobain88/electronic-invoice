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
import com.stf.exception.NotFoundException;
import com.stf.exception.NotSaveException;
import com.stf.persistence.entity.Catalog;
import com.stf.persistence.enums.PermissionNameEnum;
import com.stf.presentation.datamanager.CatalogsDM;
import com.stf.presentation.datamanager.RequestParametersDM;
import com.stf.security.AllowedPermission;
import com.stf.security.enums.PageInformationEnum;
import com.stf.util.MessagesConstants;

@Named
@RequestScoped
@URLMappings(mappings = {
		@URLMapping(id = "catalogs1", pattern = "/catalogs/", viewId = "/pages/catalogs.jsf"),
		@URLMapping(id = "catalogs2", pattern = "/catalogs", viewId = "/pages/catalogs.jsf") })
public class CatalogsAction extends BaseController {
	@EJB
	CatalogFacade catalogFacade;
	@Inject
	CatalogsDM catalogsDM;
	@Inject
	RequestParametersDM requestParametersDM;

	/**
	 * Init Method
	 */
	@URLAction
	// @Interceptors(Guard.class)
	@AllowedPermission(permissionNameEnum = PermissionNameEnum.CATALOGS, pageInformationEnum = PageInformationEnum.SYSTEM)
	public String init() {

		if (catalogsDM.getCatalogSelectItems() == null
				|| catalogsDM.getCatalogSelectItems().isEmpty()) {
			try {
				catalogsDM.setCatalogSelectItems(getSelectItems(
						catalogFacade.findAllParents(), true));
			} catch (NotFoundException e) {
				e.printStackTrace();
				addErrorMessageBundle(MessagesConstants.RESULT_EMPTY);
			}
		}
		if (catalogsDM.getParentCatalogId() == null) {
			//
		}

		return null;
	}

	/**
	 * Listener for selecting parent catalog (ajax request)
	 */
	public void selectParentListener() {
		if (catalogsDM.getParentCatalogId() != null) {
			try {
				catalogsDM.setCatalogs(catalogFacade
						.findAllByParentId(catalogsDM.getParentCatalogId()));
			} catch (NotFoundException e) {
				e.printStackTrace();
				addInfoMessageBundle(MessagesConstants.RESULT_EMPTY);
			}
		} else {
			addWarnMessageBundle(MessagesConstants.RESULT_SELECT_VALID);
		}
	}

	/**
	 * Listener for adding new child catalog (ajax request)
	 */
	public void newChildCatalog() {
		catalogsDM.setCatalog(new Catalog());
		catalogsDM.getCatalog().setParentCatalogId(
				catalogsDM.getParentCatalogId());
		if (catalogsDM.getCatalogs() == null) {
			catalogsDM.setCatalogs(new ArrayList<Catalog>());
		}
		catalogsDM.getCatalogs().add(0, catalogsDM.getCatalog());

	}

	/**
	 * Listener for editing child catalog (ajax request)
	 */
	public void onEdit(RowEditEvent event) {
		catalogsDM.setCatalog(((Catalog) event.getObject()));
		saveCatalog();
	}

	public void onCancel(RowEditEvent event) {
		catalogsDM.setCatalogs(new ArrayList<Catalog>());
		selectParentListener();
	}

	/**
	 * Listener for saving child catalog (ajax request)
	 */
	public void saveCatalog() {
		try {
			if (catalogsDM.getCatalog().getParentCatalogId() == null) {
				addWarnMessageBundle(MessagesConstants.RESULT_SELECT_VALID);
				return;
			}
			catalogFacade.saveCatalog(catalogsDM.getCatalog(), catalogsDM
					.getCatalog().getParentCatalogId());
			selectParentListener();
			addInfoMessageBundle(MessagesConstants.RESULT_SAVED);
		} catch (NotSaveException e) {
			e.printStackTrace();
			onCancel(null);
			addErrorMessageBundle(MessagesConstants.RESULT_NOT_SAVED);
		}
	}
}
