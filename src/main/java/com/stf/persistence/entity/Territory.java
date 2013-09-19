package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the geographic_location database table.
 * 
 */
@Entity
@Table(name = "territory")
public class Territory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte active;

	private String level;

	// bi-directional many-to-one association to Client
	@OneToMany(mappedBy = "territory")
	private List<User> clients;

	// bi-directional many-to-one association to GeographicLocation
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Territory territory;

	// bi-directional many-to-one association to GeographicLocation
	@OneToMany(mappedBy = "territory")
	private List<Territory> territories;

	// bi-directional many-to-one association to TerritoryAsigned
	@OneToMany(mappedBy = "territory")
	private List<TerritoryAsigned> territoryAsigneds;

	public Territory() {
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

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<User> getClients() {
		return this.clients;
	}

	public void setClients(List<User> clients) {
		this.clients = clients;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	public List<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}

	public List<TerritoryAsigned> getTerritoryAsigneds() {
		return this.territoryAsigneds;
	}

	public void setTerritoryAsigneds(List<TerritoryAsigned> territoryAsigneds) {
		this.territoryAsigneds = territoryAsigneds;
	}

}