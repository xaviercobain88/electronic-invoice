package com.stf.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.stf.persistence.enums.GeographicLocationLevelEnum;
import com.stf.persistence.util.ComboBoxModel;

/**
 * The persistent class for the geographic_location database table.
 * 
 */
@Entity
@Table(name = "geographic_location")
@NamedQueries({
		@NamedQuery(name = "GeographicLocation.findByLevel", query = "SELECT gl FROM GeographicLocation gl WHERE gl.active = :active AND gl.level = :level"),
		@NamedQuery(name = "GeographicLocation.findByParentId", query = "SELECT gl FROM GeographicLocation gl WHERE gl.active = :active AND gl.parentLocation.id = :parentLocationId"),
		@NamedQuery(name = "GeographicLocation.findByParentIdNull", query = "SELECT  gl FROM GeographicLocation gl WHERE gl.parentLocationId is null and gl.active IN ( :active ) ORDER BY LOWER(gl.name)") })
public class GeographicLocation extends ComboBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@NotEmpty(message = "Field can't be empty")
	@Column(nullable = false)
	private String name;

	@Column(name = "level")
	@Enumerated(EnumType.STRING)
	private GeographicLocationLevelEnum level;

	// bi-directional many-to-one association to Client
	@OneToMany(mappedBy = "country")
	private List<ShippingAddress> shippingAddressesByCountry;

	@OneToMany(mappedBy = "state")
	private List<ShippingAddress> shippingAddressesByStae;

	// bi-directional many-to-one association to GeographicLocation
	@ManyToOne
	@JoinColumn(name = "parent_id", insertable = false, updatable = false)
	private GeographicLocation parentLocation;

	@Column(name = "parent_id")
	@NotNull(message = "Field can't be empty")
	private Integer parentLocationId;

	// bi-directional many-to-one association to GeographicLocation
	@OneToMany(mappedBy = "parentLocation")
	private List<GeographicLocation> childLocations;

	public GeographicLocation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GeographicLocationLevelEnum getLevel() {
		return level;
	}

	public void setLevel(GeographicLocationLevelEnum level) {
		this.level = level;
	}

	public List<ShippingAddress> getShippingAddressesByCountry() {
		return shippingAddressesByCountry;
	}

	public void setShippingAddressesByCountry(
			List<ShippingAddress> shippingAddressesByCountry) {
		this.shippingAddressesByCountry = shippingAddressesByCountry;
	}

	public List<ShippingAddress> getShippingAddressesByStae() {
		return shippingAddressesByStae;
	}

	public void setShippingAddressesByStae(
			List<ShippingAddress> shippingAddressesByStae) {
		this.shippingAddressesByStae = shippingAddressesByStae;
	}

	public GeographicLocation getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(GeographicLocation parentLocation) {
		this.parentLocation = parentLocation;
	}

	public List<GeographicLocation> getChildLocations() {
		return childLocations;
	}

	public void setChildLocations(List<GeographicLocation> childLocations) {
		this.childLocations = childLocations;
	}

	public String getName() {
		return name;
	}

	public String getNameLowerCase() {
		if (name != null) {
			return this.name.toLowerCase();
		}
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer getComboBoxValue() {
		return id;
	}

	@Override
	public String getComboBoxLabel() {
		return name;
	}

	public Integer getParentLocationId() {
		return parentLocationId;
	}

	public void setParentLocationId(Integer parentLocationId) {
		this.parentLocationId = parentLocationId;
	}

}