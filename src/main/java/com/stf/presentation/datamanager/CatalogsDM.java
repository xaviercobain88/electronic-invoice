package com.stf.presentation.datamanager;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.stf.persistence.entity.Catalog;

/**
 * @author xavier
 * 
 */
@Named
@SessionScoped
public class CatalogsDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectItem> catalogSelectItems;
	private List<Catalog> catalogs;
	private Integer parentCatalogId;
	private Catalog catalog;

	public List<SelectItem> getCatalogSelectItems() {
		return catalogSelectItems;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public void setCatalogSelectItems(List<SelectItem> catalogSelectItems) {
		this.catalogSelectItems = catalogSelectItems;
	}

	public List<Catalog> getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(List<Catalog> catalogs) {
		this.catalogs = catalogs;
	}

	public Integer getParentCatalogId() {
		return parentCatalogId;
	}

	public void setParentCatalogId(Integer parentCatalogId) {
		this.parentCatalogId = parentCatalogId;
	}

}
