package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the territory_asigned database table.
 * 
 */
@Entity
@Table(name = "territory_asigned")
public class TerritoryAsigned implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String level;

	// bi-directional many-to-one association to Employee
	@ManyToOne
	private Employee employee;

	// bi-directional many-to-one association to GeographicLocation
	@ManyToOne
	@JoinColumn(name = "territory_id")
	private Territory territory;

	public TerritoryAsigned() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

}