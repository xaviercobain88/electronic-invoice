package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the box database table.
 * 
 */
@Entity
@Table(name = "box")
public class Box implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte active;

	@Column(name = "shipment_status")
	private String shipmentStatus;

	private String status;

	@Column(name = "system_id")
	private String systemId;

	// bi-directional many-to-one association to SuborderBoxConfiguration
	@ManyToOne
	@JoinColumn(name = "box_configuration_id")
	private BoxConfiguration boxConfiguration;

	// bi-directional many-to-one association to BoxComment
	@OneToMany(mappedBy = "box")
	private List<BoxComment> boxComments;

	// bi-directional many-to-one association to BoxTracking
	@OneToMany(mappedBy = "box")
	private List<BoxTracking> boxTrackings;

	// bi-directional many-to-one association to TerritoryAsigned
	@OneToMany(mappedBy = "box")
	private List<BoxGrowerNegotiation> boxGrowerNegotiations;

	public Box() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getShipmentStatus() {
		return this.shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public BoxConfiguration getBoxConfiguration() {
		return boxConfiguration;
	}

	public void setBoxConfiguration(BoxConfiguration boxConfiguration) {
		this.boxConfiguration = boxConfiguration;
	}

	public List<BoxComment> getBoxComments() {
		return this.boxComments;
	}

	public void setBoxComments(List<BoxComment> boxComments) {
		this.boxComments = boxComments;
	}

	public List<BoxTracking> getBoxTrackings() {
		return this.boxTrackings;
	}

	public void setBoxTrackings(List<BoxTracking> boxTrackings) {
		this.boxTrackings = boxTrackings;
	}

	public List<BoxGrowerNegotiation> getBoxGrowerNegotiations() {
		return boxGrowerNegotiations;
	}

	public void setBoxGrowerNegotiations(
			List<BoxGrowerNegotiation> boxGrowerNegotiations) {
		this.boxGrowerNegotiations = boxGrowerNegotiations;
	}

}