package com.stf.presentation.datamanager;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.stf.persistence.entity.Catalog;
import com.stf.persistence.entity.GeographicLocation;

/**
 * @author xavier
 * 
 */
@Named
@SessionScoped
public class GeographicLocationsDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectItem> geographicLocationSelectItems;
	private List<GeographicLocation> geographicLocations;
	private Integer parentGeographicLocationId;
	private GeographicLocation geographicLocation;

	public List<SelectItem> getGeographicLocationSelectItems() {
		return geographicLocationSelectItems;
	}

	public void setGeographicLocationSelectItems(
			List<SelectItem> geographicLocationSelectItems) {
		this.geographicLocationSelectItems = geographicLocationSelectItems;
	}

	public List<GeographicLocation> getGeographicLocations() {
		return geographicLocations;
	}

	public void setGeographicLocations(
			List<GeographicLocation> geographicLocations) {
		this.geographicLocations = geographicLocations;
	}

	public Integer getParentGeographicLocationId() {
		return parentGeographicLocationId;
	}

	public void setParentGeographicLocationId(Integer parentGeographicLocationId) {
		this.parentGeographicLocationId = parentGeographicLocationId;
	}

	public GeographicLocation getGeographicLocation() {
		return geographicLocation;
	}

	public void setGeographicLocation(GeographicLocation geographicLocation) {
		this.geographicLocation = geographicLocation;
	}

}
