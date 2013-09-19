package com.stf.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the territory_asigned database table.
 * 
 */
@Entity
@Table(name = "box_grower_negotiation")
public class BoxGrowerNegotiation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	// bi-directional many-to-one association to Employee
	@ManyToOne
	private Grower grower;

	// bi-directional many-to-one association to GeographicLocation
	@ManyToOne
	private Box box;

	public BoxGrowerNegotiation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grower getGrower() {
		return grower;
	}

	public void setGrower(Grower grower) {
		this.grower = grower;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

}